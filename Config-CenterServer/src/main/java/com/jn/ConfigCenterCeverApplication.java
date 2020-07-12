package com.jn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterCeverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterCeverApplication.class, args);
    }

}
