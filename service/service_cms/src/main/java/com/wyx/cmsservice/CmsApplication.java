package com.wyx.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

//改启动类不需要加载数据库，加上exclude，不然会默认找数据源，如果没写数据库信息就会报错
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@CrossOrigin
@ComponentScan(basePackages = {"com.wyx"})
@MapperScan("com.wyx.cmsservice.mapper")  //扫描xml文件，也可以写在配置类中
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
