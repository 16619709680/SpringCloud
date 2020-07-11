package com.jn;


import org.springframework.web.bind.annotation.*;

@RequestMapping("/User")
public interface UserApi {

    @GetMapping("alive")
    String alive();


    @GetMapping("getById")
    String getById(@RequestParam("id") Integer id);

    @PostMapping("postPerson")
    Person postPerson(@RequestBody Person person);
}
