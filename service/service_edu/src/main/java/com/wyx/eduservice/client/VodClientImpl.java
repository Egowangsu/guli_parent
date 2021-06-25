package com.wyx.eduservice.client;

import org.springframework.stereotype.Component;
//熔断器，实现OssClient接口，当接口调用出现异常时，就会执行实现类的方法

@Component
public class VodClientImpl implements VodClient {

    @Override
    public String checkToken2(String token) {
        System.out.println("接口访问异常，执行熔断机制，访问实现类");
        return null;
    }
}
