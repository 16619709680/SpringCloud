package com.jn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ProviderController implements UserApi {

    AtomicInteger atomicInteger = new AtomicInteger();

    @Value("${server.port}")
    String port;

    @Override
    public String alive() {

        try {
            System.out.println("准备睡觉。。。。。。。。。。");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = atomicInteger.getAndIncrement();
        System.out.println("端口：" + port + "第" + i + "次调用");
        return "alive";
    }

    @Override
    public String getById(Integer id) {
        return "你的ID是：" + id;
    }

    @Override
    public Person postPerson(Person person) {
        return person;
    }

    @GetMapping("/RestTemplate")
    public String RestTemplate(){
        return "6不6";
    }
}

