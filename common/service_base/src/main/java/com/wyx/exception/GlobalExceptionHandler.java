package com.wyx.exception;

import com.wyx.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/*
*
* 统一异常处理类
*
* */

@ControllerAdvice  //异常处理类需要加的注解
@ResponseBody  //为了返回数据Response(json格式)
//或者上面两个注解可以直接使用一个注解@RestControllerAdvice
@Slf4j  //开启logback日志
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  //指定出现何种异常执行这个方法,这里是全局异常
    public Response error(Exception e){
        e.printStackTrace();
        return Response.error().message("使用了全局异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)  //特定异常，指定一个特定的异常类才执行
    public Response error(ArithmeticException e){
        e.printStackTrace();
        return Response.error().message("执行了ArithmeticException异常处理");
    }

    @ExceptionHandler(GuliException.class)  //自定义异常
    public Response error(GuliException e){
        log.error(e.getMessage());  //可以将错误的异常信息写入到日志文件中
        e.printStackTrace();
        return Response.error().code(e.getCode()).message(e.getMsg());
    }

}
