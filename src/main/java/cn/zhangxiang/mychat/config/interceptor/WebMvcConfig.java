package cn.zhangxiang.mychat.config.interceptor;

import cn.zhangxiang.mychat.config.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器的拦截规则
 * @author zhangxiang
 * @date 2024-08-23 9:47
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置拦截路径
     * @author zhangxiang
     * @date 2024/8/23 15:46
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**"); // 指定需要拦截的请求路径
//                .excludePathPatterns("/user/register"); // 指定不需要拦截的请求路径
    }

    /**
     * 跨域
     * @author zhangxiang
     * @date 2024/8/23 15:45
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        /**
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
//                .allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
         */
    }
}

