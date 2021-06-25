package com.wyx.eduservice.controller;


import com.wyx.Response;
import com.wyx.eduservice.entity.vo.CourseEntityVo;
import com.wyx.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-05
 */
@RestController
@RequestMapping("/eduservice/educourse")
@CrossOrigin
public class EduCourseController {
    @Autowired
    EduCourseService eduCourseService;

    @PostMapping("addCourseInfo")
    public Response addCourseInfo(@RequestBody CourseEntityVo courseEntityVo){
        String id=eduCourseService.addCourseInfo(courseEntityVo);
        //返回新添加的课程的id值，为了后面添加大纲使用
        return Response.success().data("courseId",id);
    }

    @RequestMapping("/test")
    public String test(){
        return "你好世界！";
    }

}

