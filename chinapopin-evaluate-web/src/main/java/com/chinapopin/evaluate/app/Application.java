package com.chinapopin.evaluate.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.chinapopin.framework.core.spring", "com.chinapopin.*.datasource", "com.chinapopin.evaluate.*"})
@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        new Application().configure(new SpringApplicationBuilder(Application.class)).run(
                args);
    }
}
