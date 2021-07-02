package com.wyx.controller;

import com.alibaba.nacos.client.naming.utils.StringUtils;
import com.wyx.Response;
import com.wyx.service.MsmService;
import com.wyx.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{phone}")
    public Response code(@PathVariable String phone) {  //根据手机号发送验证码
        String code = redisTemplate.opsForValue().get(phone);
        //先从redis中获取，判断是否存在改值
        if(!StringUtils.isEmpty(code)) return Response.success();

        code = RandomUtil.getFourBitRandom();  //获取随机数当做验证码发送到手机端
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);     //下面的sms是阿里云短信申请端的codeKey
        boolean isSend = msmService.send(phone, "SMS_180051135", param);
        if(isSend) {
            //发送成功，存入redis，设置有效时间为5分钟
            redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
            return Response.success();
        } else {
            return Response.error().message("发送短信失败");
        }
    }
}
