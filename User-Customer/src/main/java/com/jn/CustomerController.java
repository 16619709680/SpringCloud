package com.jn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {


    @Autowired
    CustomerApi customerApi;


    @GetMapping("/alive")
    public String alive() {
        return customerApi.alive();
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

}
