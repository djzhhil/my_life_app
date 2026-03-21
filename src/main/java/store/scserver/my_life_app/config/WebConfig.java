package store.scserver.my_life_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import store.scserver.my_life_app.interceptor.JwtInterceptor;

/**
 * Web MVC 配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/**")  // 拦截所有 /api 开头的请求
                .excludePathPatterns(
                        "/api/user/register",   // 放行注册
                        "/api/user/login"       // 放行登录
                );
    }
}
