package com.wyx.vodservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vodservice")
public class TestController {

    @PostMapping("/checkToken2/{token}")
    public String checkToken2(@PathVariable("token") String token){
        if("88888888".equals(token)) {
            return "{'result':'true','mobile':'15867203266'}";
        }else {
            return "{'result':'fail','mobile':'0'}";
        }

    }
}
