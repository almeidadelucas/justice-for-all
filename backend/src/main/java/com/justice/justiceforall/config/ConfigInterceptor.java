package com.justice.justiceforall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

  @Autowired
  private HandlerInterceptor authenticationInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
  registry.addInterceptor(authenticationInterceptor).excludePathPatterns("/actuator/**", "/error");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**");
  }
}
