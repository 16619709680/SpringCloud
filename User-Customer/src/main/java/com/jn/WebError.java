package com.jn;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

@Component
public class WebError implements FallbackFactory<CustomerApi> {
    @Override
    public CustomerApi create(Throwable throwable) {

        return new CustomerApi() {

            @Override
            public String alive() {
                String err = "";
                System.out.println("----->" + throwable);
                if (throwable instanceof HystrixTimeoutException) {
                    err = "远程服务器调用超时";
                    return err;
                } else if (throwable instanceof FeignException.InternalServerError) {
                    err = "内部服务器信息异常" + throwable.getLocalizedMessage();
                    return err;
                }else{
                    err = "卧槽,这是什么错";
                }
                return err;
            }

            @Override
            public String getById(Integer id) {
                return null;
            }

            @Override
            public Person postPerson(Person person) {
                return null;
            }
        };
    }
}
