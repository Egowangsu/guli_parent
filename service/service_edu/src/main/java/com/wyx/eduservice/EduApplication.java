package com.wyx.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//如果不加该注解，项目启动时会自动在该包下（eduservice）找class，但是要使用其他包时（例如service_base下的config）
// 就必须加上该注解了
@ComponentScan(basePackages = {"com.wyx"})
//@EnableDiscoveryClient  //nacos注册  ，将该项目注册到nacos注册中心
@EnableDiscoveryClient //注册到nacos，加不加无所谓，可以看作是一个标识
@EnableFeignClients    //服务调用端添加注解
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
