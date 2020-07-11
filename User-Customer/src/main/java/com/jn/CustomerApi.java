package com.jn;


import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name="user-provider" , configuration = FeignAuthConfiguration.class ,fallbackFactory = WebError.class)
public interface CustomerApi extends UserApi{
}
