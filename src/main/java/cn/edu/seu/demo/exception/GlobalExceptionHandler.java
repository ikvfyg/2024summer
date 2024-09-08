package cn.edu.seu.demo.exception;

/**
 * 全局异常处理器
 */

import cn.edu.seu.demo.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error(ex.getMessage());
    }
}
