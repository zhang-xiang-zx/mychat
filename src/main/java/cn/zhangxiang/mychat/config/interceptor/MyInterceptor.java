package cn.zhangxiang.mychat.config.interceptor;

import cn.zhangxiang.mychat.config.auth.NoLogin;
import cn.zhangxiang.mychat.config.exception.MyException;
import cn.zhangxiang.mychat.utils.Assert;
import cn.zhangxiang.mychat.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * preHandle：在请求到达处理器之前执行，true就继续操作，false则中断请求处理
 * postHandle：在处理器处理请求之后执行
 * afterCompletion：在视图渲染之后执行，可以用于资源清理等操作
 * @author zhangxiang
 * @date 2024-08-23 9:37
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 免登录注解
        Method method = ((HandlerMethod) handler).getMethod();
        if(method.isAnnotationPresent(NoLogin.class)){
            NoLogin annotation = method.getAnnotation(NoLogin.class);
            if(annotation.required()){
                return true;
            }
        }
        String token = request.getHeader("Authorization");
        if(Assert.notNull(token)){
            String userId = JwtUtils.getAudience(token);
            Boolean isOk = JwtUtils.verifyToken(token, userId);
            if(!isOk){
                throw new MyException(4003,"token过期");
            }
            request.setAttribute("userId",userId);
        }else {
            throw new MyException(4002,"无Authorization");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("postHandle：{},{}",request.getRequestURI(),response.getStatus());
        // 日志记录... 缓存处理...
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");

    }
}

