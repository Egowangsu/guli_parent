package com.wyx.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="service-vod",fallback = VodClientImpl.class)   //调用的服务项目名,fallback启用的是熔断机制
@Component   //交给mvc容器管理
public interface VodClient {
    @PostMapping("/vodservice/checkToken2/{token}")  //全路径，若是有@Pathvarible("id ")就必须写确切的参数值
    public String checkToken2(@PathVariable("token") String token);
}
