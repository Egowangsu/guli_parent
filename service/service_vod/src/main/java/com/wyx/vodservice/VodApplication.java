package com.wyx.vodservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

//改启动类不需要加载数据库，加上exclude，不然会默认找数据源，如果没写数据库信息就会报错
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient //注册到nacos，加不加无所谓，可以看作是一个标识
@ComponentScan(basePackages = {"com.wyx"})  //可以扫描到同名包下的配置类
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class, args);
    }
}
