package com.may.meta_study.config;

import com.may.meta_study.entity.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 处理所有未捕获的异常
     *
     * @param e 异常
     * @return 统一返回结果
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        return Result.fail("系统错误: " + e.getMessage());
    }
    
    /**
     * 处理参数校验异常
     *
     * @param e 异常
     * @return 统一返回结果
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return Result.fail(400, "参数错误: " + e.getMessage());
    }
}