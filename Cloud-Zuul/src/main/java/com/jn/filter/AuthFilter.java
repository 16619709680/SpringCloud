package com.jn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义鉴权过滤器
 */
@Component
public class AuthFilter extends ZuulFilter {

    //过滤器类型：pre 、routing 、post 、error
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //执行顺序，在谁前，在谁后，可以+1，-1.值越小，越在前面
    @Override
    public int filterOrder() {
        return -1;
    }

    //此过滤器是否执行，true  false，可以写过滤器是否执行的判断条件。
    @Override
    public boolean shouldFilter() {
        //获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println("请求URI:" + requestURI);
        String checkUri = "/api/jn/zuul-user-customer/token";
        if (checkUri.equals(requestURI)) {
            return false;
        } else {
            return false;
        }
    }

    //具体执行逻辑。
    @Override
    public Object run() throws ZuulException {
        System.out.println(".....AuthFilter 拦截请求");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getHeader("Authorization");
        System.out.println(".........request Token :" + token);
        String checkToken = "1234";
        if (checkToken.equals(token)) {
            System.out.println(".....AuthFilter  校验通过。。。。。");
        } else {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            currentContext.setResponseBody("验证失败哦");
        }
        currentContext.setSendZuulResponse(true);
        return null;
    }
}
