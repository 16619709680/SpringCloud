package com.jn;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HystrixTest extends HystrixCommand {
    protected HystrixTest(HystrixCommandGroupKey group) {
        super(group);
    }


    public static void main(String[] args) {

        /**
         * queue()：以异步非阻塞方式执行run()。以demo为例，
         * 	一调用queue()就直接返回一个Future对象，
         * 	同时hystrix创建一个新线程运行run()，
         * 	调用程序通过Future.get()拿到run()的返回结果，
         * 	而Future.get()是阻塞执行的
         */
        Future<String> ext = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext")).queue();
        String result = "";
        try {
            result = ext.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("程序执行结果：" + result);
    }

    @Override
    protected String run() throws Exception {
        System.out.println("业务逻辑执行。。。。。。");
        int i = 1 / 1;
        return String.valueOf(i);
    }


    @Override
    protected Object getFallback() {
        System.out.println("程序执行出错了！！！");
        return "我擦勒！！";
    }
}
