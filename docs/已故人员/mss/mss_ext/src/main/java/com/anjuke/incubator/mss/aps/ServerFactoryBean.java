package com.anjuke.incubator.mss.aps;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import com.anjuke.aps.ExceptionHandler;
import com.anjuke.aps.kiteline.handler.PingRequestHandler;
import com.anjuke.aps.kiteline.handler.StatusMessageFilter;
import com.anjuke.aps.kiteline.handler.StatusRequestHandler;
import com.anjuke.aps.server.ApsServerStatusListener;
import com.anjuke.aps.server.DefaultMessageHandler;
import com.anjuke.aps.server.processor.AccessLogRequestFilter;
import com.anjuke.aps.server.processor.DefaultRequestProcessor;
import com.anjuke.aps.server.zmq.ZMQServer;

/**
 * 从 Context 里面获得需要的 filters, handlers 等
 * 组装可用的 ZMQServer 来处理 APS 请求
 *
 */
public class ServerFactoryBean implements FactoryBean<ZMQServer>,
        InitializingBean, DisposableBean {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    DefaultMessageHandler messageHandler;

    // kiteline
    // @Autowired
    // ApsServerStatusListener statusListener;

    // filters
    @Autowired
    StatusMessageFilter statusMessageFilter;
    @Autowired
    AccessLogRequestFilter accesslogRequestFilter;

    // handlers
    @Autowired
    PingRequestHandler ping;
    @Autowired
    StatusRequestHandler status;
    @Autowired
    MssSpringRequestHandler mss;

    // processor
    @Autowired
    DefaultRequestProcessor processor;

    @Value("${aps.sp.port:8964}")
    int port;

    @Value("${aps.sp.ip:null}")
    String ip;

    private ZMQServer server = new ZMQServer();

    @Override
    public ZMQServer getObject() throws Exception {
        return server;
    }

    @Override
    public Class<? extends ZMQServer> getObjectType() {
        return server.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        processor.addHandler(mss);
        processor.addHandler(ping);
        processor.addHandler(status);

        messageHandler.addFilter(statusMessageFilter);
        messageHandler.addFilter(accesslogRequestFilter);

        messageHandler.setProcessor(processor);

        if (ip != null) {
            server.setHostname(ip);
        }
        server.setPort(port);
        // server.addServerStatusListener(statusListener);
        server.setMessageHandler(messageHandler);
        server.start();
    }

    @Override
    public void destroy() throws Exception {
        server.stop();
    }
}
