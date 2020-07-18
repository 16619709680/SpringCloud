package com.jn;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerController {


    @Autowired
    CustomerApi customerApi;

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    @GetMapping("/alive")
    public String alive() {
        return "Customer:port " + port + "------>" + customerApi.alive();
    }

    /**
     * get
     */
    @GetMapping("/getByid")
    public String getByid(@RequestParam("id") Integer id) {
        return customerApi.getById(id);
    }

    /**
     * POST
     */
    @PostMapping("/postPerson")
    public Person postPerson(@RequestBody Person person) {
        person.setAge(person.getAge() + 10);
        return person;
    }

    /**
     * hystrix 整合 RestTemplate
     */
    @HystrixCommand(defaultFallback = "callback")
    @GetMapping("/aliveRestTemplate")
    public String aliveRestTemplate() {
        String url = "http://user-provider/RestTemplate";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    public String callback() {
        return "error....";
    }

    @GetMapping("/token")
    public String cookie(HttpServletRequest request) {
        String token = request.getHeader("token");
        System.out.println("token:    " + token);
        return "token:"+token;
    }

}
