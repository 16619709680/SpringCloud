package com.jn;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController  implements UserApi{


    @Override
    public String alive() {
        return "alive";
    }

    @Override
    public String getById(Integer id) {
        return "你的ID是："+id;
    }

    @Override
    public Person postPerson(Person person) {
        return person;
    }
}
