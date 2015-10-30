package com.anjuke.incubator.mss.aps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.anjuke.aps.ApsContext;
import com.anjuke.aps.ApsStatus;
import com.anjuke.aps.ExceptionHandler;
import com.anjuke.aps.ModuleVersion;
import com.anjuke.aps.Request;
import com.anjuke.aps.RequestHandler;
import com.anjuke.aps.Response;
import com.anjuke.aps.exception.ApsException;
import com.anjuke.aps.server.spring.DefaultExceptionHandler;
import com.anjuke.aps.spring.ApsMethod;
import com.anjuke.aps.spring.config.ApsServiceInstance;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * APS 的 Handler
 *
 * init 时
 * 从 Spring 的 applicationContext 里面找到所有 ApsService
 * 从这些 ApsService 里面取得所有可用的 method 和对应的 invoker
 * 并保存在 methodBeanCache 里
 * 
 * lifecycle 里
 * 请求来时，从 methodBeanCache 里面根据 method 取得 invoker
 * 把参数按照 **个数** 和 **期望的类型** 转换好，然后调用
 *
 *
 */
public class MssSpringRequestHandler implements RequestHandler {

    private static final Logger LOG = LoggerFactory
            .getLogger(MssSpringRequestHandler.class);

    @Value("${aps.sp.id}")
    String apsSpId;

    @Value("${aps.sp.version}")
    String apsSpVersion;

    @Autowired
    private ApplicationContext applicationContext;

    private ObjectMapper objectMapper;

    private Map<String, ApsMethodInvoker> methodBeanCache;

    private Set<ModuleVersion> modules = Sets.newHashSet();

    private ExceptionHandler exceptionHandler = new DefaultExceptionHandler();

    private String exceptionHandlerBeanName;


    public ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public String getExceptionHandlerBeanName() {
        return exceptionHandlerBeanName;
    }

    public void setExceptionHandlerBeanName(String exceptionHandlerBeanName) {
        this.exceptionHandlerBeanName = exceptionHandlerBeanName;
    }

    @Override
    public void init(ApsContext context) {
        objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES,
                false);

        methodBeanCache = Maps.newHashMap();
        // 从 Spring 的 applicationContext 里面获得所有 ApsService
        Map<String, ApsServiceInstance> instancesMap = applicationContext
                .getBeansOfType(ApsServiceInstance.class);

        if (exceptionHandlerBeanName != null) {
            this.exceptionHandler = applicationContext.getBean(
                    exceptionHandlerBeanName, ExceptionHandler.class);
        }

        for (ApsServiceInstance instance : instancesMap.values()) {

            Class<?> clazz = instance.getServiceClass();

            Method[] methodArray = clazz.getDeclaredMethods();
            for (Method method : methodArray) {
                ApsMethod apsMethod = method.getAnnotation(ApsMethod.class);
                if (apsMethod == null) {
                    continue;
                }
                String beanName = apsMethod.bean();
                String methodName = apsMethod.method();

                Object bean = applicationContext.getBean(beanName);
                if (bean == null) {
                    throw new NullPointerException("bean " + beanName
                            + " in context not found");
                }
                Class<?>[] parameterClasses = method.getParameterTypes();

                String targetMethodName = StringUtils.isEmpty(apsMethod
                        .targetMethodName()) ? methodName : apsMethod
                        .targetMethodName();
                try {
                    Method targetMethod = bean.getClass().getDeclaredMethod(
                            targetMethodName, parameterClasses);
                    ApsMethodInvoker apsMethodInvoker = new ApsMethodInvoker(
                            bean, targetMethod, method);

                    // method
                    putMethodBeanCache(methodName, apsMethodInvoker);
                    // bean.method
                    String urlByBean = beanName + "." + methodName;
                    putMethodBeanCache(urlByBean, apsMethodInvoker);
                    // :sp_id:bean.method
                    String urlByColon = ":" + apsSpId + ":"
                        + beanName + "." + methodName;
                    putMethodBeanCache(urlByColon, apsMethodInvoker);

                } catch (SecurityException e) {
                    LOG.error(e.getMessage(), e);
                    throw e;
                } catch (NoSuchMethodException e) {
                    String msg = "target method " + targetMethodName
                            + " in bean " + beanName
                            + " not found, " + e.getMessage();
                    throw new ApsException(msg, e);
                }
            }
        }
        modules.add(new ModuleVersion(apsSpId, apsSpVersion));
    }

    public void putMethodBeanCache(String url, ApsMethodInvoker apsMethodInvoker) {
        Object o = methodBeanCache.put(url, apsMethodInvoker);
        if (o != null) {
            throw new IllegalStateException("duplicate aps url regestered: "
                    + url);
        }
    }

    @Override
    public Set<String> getRequestMethods() {
        return methodBeanCache.keySet();
    }

    @Override
    public void destroy(ApsContext context) {
        methodBeanCache.clear();
    }

    @Override
    public Set<ModuleVersion> getModules() {
        return modules;
    }

    public void handle(Request request, Response response) {
        String method = request.getRequestMethod();
        ApsMethodInvoker invoker = methodBeanCache.get(method);
        if (invoker == null) {
            throw new IllegalStateException();
        }

        List<Object> parameters = request.getRequestParams();

        Object[] convertedParams = convertParameters(parameters,
                invoker.getGenericParameterTypes());
        try {
            Object result = invoker.invoke(convertedParams);
            response.setResult(objectMapper.convertValue(result, Object.class));
            response.setStatus(ApsStatus.SUCCESS);
        } catch (InvocationTargetException e) {
            exceptionHandler.handleException(
                    (Exception) e.getTargetException(), request, response);
        } catch (Exception e) {
            exceptionHandler.handleException(e, request, response);
        }

    };

    private Object[] convertParameters(List<Object> parameter,
            Type[] expectedTypes) {
        int size = parameter.size();
        if (size != expectedTypes.length) {
            throw new IllegalStateException("Aps request parameter length is "
                    + size + " and declaired method parameter length is "
                    + expectedTypes.length);
        }

        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            JavaType javaType = objectMapper.getTypeFactory().constructType(
                    expectedTypes[i]);
            result[i] = objectMapper.convertValue(parameter.get(i), javaType);
        }
        return result;
    }
}
