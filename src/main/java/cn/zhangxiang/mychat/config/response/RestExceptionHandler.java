package cn.zhangxiang.mychat.config.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangxiang
 * @date 2024-08-26 16:09
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 全局异常处理
     * @author zhangxiang
     * @date 2024/8/26 16:14
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(Exception e){

        return Result.fail(500,e.getMessage());
    }
}

