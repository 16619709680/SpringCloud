package com.jn.filter;


import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 令牌桶进行限流
 */
@Component
public class RateFilter extends ZuulFilter {

    private static int count = 0;

    /**
     * 创建一个稳定输出令牌的RateLimter,保证平均每秒不会超过permitsPerSecond个请求
     * 当请求到来的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
     * 当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，会囤积最多permitsPerSecond个请求
     */
    private static final RateLimiter rateLimiter = RateLimiter.create(5);

    //过滤器级别
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //执行顺序  数字越小越小执行 限流要尽早
    @Override
    public int filterOrder() {
        return -10;
    }

    //拦截请求
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //请求业务处理
    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        if (!rateLimiter.tryAcquire()) {
            System.out.println("获取令牌失败..........,限流" + count++);
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
            currentContext.setSendZuulResponse(false);
        }
        return null;
    }
}
