package com.wyx.eduservice.controller;

import com.wyx.Response;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin   //解决跨域问题
public class EduLoginController {

    @PostMapping("/login")
    public Response login(){

   return Response.success().data("token","12345789");

    }

    @GetMapping("/info")
    public Response getInfo(){
        Map map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name", "admin");
        map.put("avatar", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1986451467,394304688&fm=26&gp=0.jpg");
        return Response.success().data(map);
    }
}
