package com.wyx.oss.controller;

import com.wyx.Response;
import com.wyx.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/upload")
@CrossOrigin  //解决跨域问题
@Api(tags="上传文件")
public class OssController {

    @Autowired
    OssService ossService;

    //上传头像的方法
    @PostMapping
    @ApiOperation("上传文件方法")
    public Response uploadFile(@ApiParam("需要上传的文件") MultipartFile file){
        //获取上传文件MultipartFile
        //方法返回上传到oss的路径，可以用来存入数据库中
        String url = ossService.uploadFileAvatar(file);
        return Response.success().data("url",url);
    }
}
