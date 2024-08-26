package cn.zhangxiang.mychat.config.response;

import com.alibaba.fastjson2.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一格式返回
 *
 * @author zhangxiang
 * @date 2024-08-26 14:57
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    /**
     * 是否支持advice功能; true支持,false不支持
     *
     * @author zhangxiang
     * @date 2024/8/26 15:11
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 字符串手动转为json格式
        if(body instanceof String){
            return JSON.toJSONString(Result.success(body));
        }
        // 全局异常直接返回
        if(body instanceof Result){
            return body;
        }
        return Result.success(body);
    }
}

