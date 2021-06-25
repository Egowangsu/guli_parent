package com.wyx.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyx.Response;
import com.wyx.eduservice.entity.EduTeacher;
import com.wyx.eduservice.entity.vo.QueryTeacher;
import com.wyx.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-19
 */
@Api(tags = "后台讲师管理")  //swagger界面提示，controller的
@RestController
//Restful风格
@RequestMapping("/eduservice/teacher")
@CrossOrigin  //解决跨域问题
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("findAll")
    @ApiOperation("查询所有讲师")  //swagger界面提示注解，方法上的
    public Response findAllTeacher(){
        //查询所有教师信息
        List<EduTeacher> list = eduTeacherService.list(null);
//        执行自定义异常
//        try{
//            Integer s = 10/0;
//        }catch(Exception e){
//            throw new GuliException(20001,"执行了自定义异常处理");
//        }

        return Response.success().data("teacherList",list);
    }

    //逻辑删除讲师
    @ApiOperation("逻辑删除讲师")  //swagger界面提示的注解
    @DeleteMapping("removeById/{id}")     //下面的注解是参数上的，swagger页面提示
    public Response removeTeacherById(@ApiParam(name = "id" ,value = "讲师id" ,required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return Response.success();
        }else{
            return Response.error();
        }
    }

    //分页查询讲师
    @ApiOperation("分页查询讲师")
    @GetMapping("/pageListTeacher/{current}/{limit}")
    public Response pageListTeacher(@PathVariable long current,@PathVariable long limit){
        Page<EduTeacher> pageList = new Page(current,limit);
        //调用方法，底层实现封装，把分页所有数据封装到pageList对象中
        eduTeacherService.page(pageList, null);
        //拿总记录数
        long total=pageList.getTotal();
        //数据list集合
        List<EduTeacher> records = pageList.getRecords();
        //最后传值可以将上面两个需要的值传入，也可以将整个pageList对象传入
        return Response.success().data("pageList",pageList);
    }

    //多条件查询分页
    @PostMapping("/queryByCondition/{current}/{limit}")
    //@RequestBody注解作用是接收请求的body部分的json数据传入对象中，必须是post请求
    //因为参数是可以为空的，所以要设置required = false
    public Response queryByCondition(@PathVariable long current,@PathVariable long limit,
                                     @RequestBody(required = false) QueryTeacher queryTeacher){
        Page<EduTeacher> pageList = new Page(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //判断条件是否为空，若不为空则进行条件拼接
        String name = queryTeacher.getName();
        Integer level = queryTeacher.getLevel();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();
        if(!StringUtils.isBlank(name)){
            //构建条件，用like实现模糊查询
            wrapper.like("name", name);   //第一个name是数据库的字段名，第二个name是前端的传值
        }
        if(!org.springframework.util.StringUtils.isEmpty(level)){
            //eq，等于的意思
            wrapper.eq("level", level);
        }
        if(!StringUtils.isBlank(begin)){
            //构建条件，用like实现模糊查询
            wrapper.ge("gtm_create", begin);
        }
        if(!StringUtils.isBlank(end)){
            //构建条件，用like实现模糊查询
            wrapper.like("gmt_create", end);
        }
        //根据创建时间降序
        wrapper.orderByDesc("gmt_create");

        //将查询结果封装到pageList对象中
        eduTeacherService.page(pageList, wrapper);
        //返回查询结果
        return Response.success().data("pageList",pageList);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("/addTeacher")
    public Response addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
        return flag ? Response.success() : Response.error();
    }

    @ApiOperation("根据讲师id查询")
    @GetMapping("/getById/{id}")
    public Response getById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return Response.success().data("teacher",teacher);

    }

    @ApiOperation("修改讲师信息")
    @PostMapping("updateTeacher")
    public Response updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        return flag ? Response.success() : Response.error();
    }

}

