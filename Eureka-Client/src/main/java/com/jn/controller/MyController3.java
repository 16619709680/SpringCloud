package com.jn.controller;


import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
@RestController
public class MyController3 {


    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;


    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/client9")
    public Object client9() {
        String url = "http://provider/getmap";
        Map forObject = restTemplate.getForObject(url, Map.class);
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(url, Map.class);
        System.out.println("forEntity" + forEntity);
        System.out.println("forObject" + forObject);
        return forEntity;
    }


    @RequestMapping("/client10")
    public Object client10() {
        String url = "http://provider/getObj2?name={name}";
        Person person = restTemplate.getForObject(url, Person.class, "我是一个大帅逼");
        return person;
    }


    @RequestMapping("/client11")
    public Object client11(HttpServletResponse response) throws IOException {
        String url = "http://provider/postParam";
        Map<String, String> stringStringMap = Collections.singletonMap("name", "张三");
        URI uri = restTemplate.postForLocation(url, stringStringMap, Person.class);
        response.sendRedirect(uri.toURL().toString());
        return null;
    }
}
