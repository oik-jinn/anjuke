package com.anjuke.incubator.mss.aps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Copy paste from JAVA-APS
 *
 */
class ApsMethodInvoker {
    private final Object targetBean;
    private final Method method;
    private final Method registerMethod;

    ApsMethodInvoker(Object targetBean, Method method,Method registerMethod) {
        super();
        this.targetBean = targetBean;
        this.method = method;
        this.registerMethod=registerMethod;
    }

    public Object invoke(Object... parames) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        return method.invoke(targetBean, parames);
    }

    public Type[] getGenericParameterTypes(){
        return registerMethod.getGenericParameterTypes();
    }

}
