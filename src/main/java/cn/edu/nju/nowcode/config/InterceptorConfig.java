package cn.edu.nju.nowcode.config;

import cn.edu.nju.nowcode.interceptor.TicketCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cong on 2018-05-21.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getTicketInteceptor()).excludePathPatterns("/login/page","/login","/register","/error",
                "/images/**","/scripts/**","/styles/**","/sensitive/**");
        super.addInterceptors(registry);
    }

    @Bean
    public TicketCheckInterceptor getTicketInteceptor(){
        return new TicketCheckInterceptor();
    }

}
