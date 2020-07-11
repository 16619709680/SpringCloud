package com.jn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCustomerApplication.class, args);
    }

}
