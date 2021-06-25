package com.wyx.eduservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api")
    public class tokenController{
        @RequestMapping("/checkToken2")
        public String checkToken2(@RequestParam(value = "type",required = true) String type,
                                  @RequestParam(value = "key",required = true) String key,
                                  @RequestParam(value = "token",required = true) String token){
            if(("88888888").equals(token)&&"getPortal".equals(type)&&"Material2021Wzg".equals(key)){
                return "{'result':'true','mobile':'9999999999'}";
            }else{
                return "{'result':'false','mobile':'0'}";
            }
        }
}
