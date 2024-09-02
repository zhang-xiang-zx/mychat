package cn.zhangxiang.mychat.config.response;

import cn.zhangxiang.mychat.config.exception.MyException;
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

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.OK)  // 你可以根据需求设置不同的Http状态码
    public Result<String> handleAccountValidationException(MyException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }
}

