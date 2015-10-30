package com.anjuke.incubator.mss;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ImportResource("classpath:/applicationContext.xml")
@EnableAsync
@EnableScheduling
public class MssConfig {

    /*
     * 如果需要自定义 maxThreads minThreads，则需要用 JettyEmbeddedServletContainerFactory 方法
     *
    @Bean
    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(
            @Value("${server.port:8080}") final String port,
            @Value("${jetty.threadPool.maxThreads:1}") final String maxThreads,
            @Value("${jetty.threadPool.minThreads:1}") final String minThreads,
            @Value("${jetty.threadPool.idleTimeout:60000}") final String idleTimeout) {

        final JettyEmbeddedServletContainerFactory factory =  new JettyEmbeddedServletContainerFactory(Integer.valueOf(port));
        factory.addServerCustomizers(new JettyServerCustomizer() {
            @Override
            public void customize(final Server server) {
                // Tweak the connection pool used by Jetty to handle incoming HTTP connections
                final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
                threadPool.setMaxThreads(Integer.valueOf(maxThreads));
                threadPool.setMinThreads(Integer.valueOf(minThreads));
                threadPool.setIdleTimeout(Integer.valueOf(idleTimeout));
            }
        });

        return factory;
    }
    */
}
