package com.wyx.eduservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.wyx.Response;
import com.wyx.eduservice.client.VodClient;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "服务调用测试用例")
@RestController
public class FeignController {

    @Autowired
    VodClient vodClient;
    @RequestMapping("/api/checkToken/{token}")
    public Response checkToken(@PathVariable String token) {
        String res = "";
        try{
            String result=vodClient.checkToken2(token);
            if(!StringUtils.isBlank(result)) {
                JSONObject paramsObj = JSONObject.parseObject(result);
                res =(String)paramsObj.get("result")+paramsObj.get("mobile");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return Response.success().data("res",res);
        }
    }
}
