package com.wyx.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 自定义异常类
 */
@Data //生成set/get方法
@AllArgsConstructor //生成有参构造方法
@NoArgsConstructor //生成无参构造方法
public class GuliException extends RuntimeException {
    private String code ; //状态码
    private String msg; //异常信息
}
