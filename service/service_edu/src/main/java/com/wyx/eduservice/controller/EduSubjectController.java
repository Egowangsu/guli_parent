package com.wyx.eduservice.controller;


import com.wyx.Response;
import com.wyx.eduservice.entity.subject.OneSubject;
import com.wyx.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-01
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    //通过上传的excel文件来读取内容进行课程分类的添加
    @PostMapping("addSubject")
    public Response addSubject(MultipartFile file){

        eduSubjectService.saveSubject(file,eduSubjectService);
        return Response.success();
    }

    //获取课程分类,tree格式，一级分类包含二级分类
    @GetMapping("getAllSubject")
    public Response getSubject(){

        List<OneSubject> list =  eduSubjectService.getOneTwoSubject();
        return Response.success().data("list",list);
    }
}

