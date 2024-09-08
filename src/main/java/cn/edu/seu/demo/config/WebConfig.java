package cn.edu.seu.demo.config;

import cn.edu.seu.demo.intercepter.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login/*","/admin/login/*","/admin/login","/login");
    }
    /**
     * 设置CORS跨域请求机制
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 设置允许跨域请求的域名
                .allowedHeaders("*") // 设置允许的请求头
                .allowCredentials(true) // 是否允许发送身份凭证（比如 cookies）
                .allowedMethods("*") // 允许的方法
                .maxAge(3600); // 跨域允许时间
    }
}
