package com.jn;


import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name="user-provider")
public interface CustomerApi extends UserApi{
}
