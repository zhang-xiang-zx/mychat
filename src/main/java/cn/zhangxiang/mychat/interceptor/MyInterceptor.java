package cn.zhangxiang.mychat.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        log.info("preHandle：{}",request.getRequestURI());
        String token = request.getHeader("token");
        log.info("查看token：{}",token);
        if(token == null){
            log.info("没有token");
//            response.sendRedirect("/user/login"); //重定向登录页
            return false;
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

