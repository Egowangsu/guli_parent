package com.wyx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//统一结果返回结构
@Data
public class Response {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //构造方法私有化，使这个类不能被外界new，只能调用其静态方法，但自己能new.
    private Response(){};

    //成功时调用的静态方法
    public static Response success(){
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(ResultCode.SUCCESS);
        response.setMessage("成功");
        return response;
    }

    //失败时调用的静态方法
    public static Response error(){
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(ResultCode.ERROR);
        response.setMessage("失败");
        return response;
    }

    public Response success(Boolean success){
    this.setSuccess(success);
    return this;
    }

    public Response code(Integer code){
    this.setCode(code);
    return this;
    }

    public Response message(String message){
    this.setMessage(message);
    return this;
    }

    public Response data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

    public Response data(Map<String, Object> map){
    this.setData(map);
    return this;
    }
}
