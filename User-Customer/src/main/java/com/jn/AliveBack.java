package com.jn;

import org.springframework.stereotype.Component;

@Component
public class AliveBack implements CustomerApi {


    @Override
    public String alive() {
        return "服务降级处理。。。。。。。。。";
    }

    @Override
    public String getById(Integer id) {
        return null;
    }

    @Override
    public Person postPerson(Person person) {
        return null;
    }
}
