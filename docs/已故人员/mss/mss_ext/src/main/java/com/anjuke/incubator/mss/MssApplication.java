package com.anjuke.incubator.mss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class MssApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MssApplication.class, args);
    }

}
