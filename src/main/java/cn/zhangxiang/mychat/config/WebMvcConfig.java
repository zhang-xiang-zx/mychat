package cn.zhangxiang.mychat.config;

import cn.zhangxiang.mychat.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器的拦截规则
 * @author zhangxiang
 * @date 2024-08-23 9:47
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**") // 指定需要拦截的请求路径
                .excludePathPatterns("/user/login"); // 指定不需要拦截的请求路径
    }
}

