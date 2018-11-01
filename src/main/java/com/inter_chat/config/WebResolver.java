package com.inter_chat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.inter_chat")
public class WebResolver {
	public InternalResourceViewResolver getViewResolver() {
		System.out.println("View Resolver Created");
		InternalResourceViewResolver iResolver = new InternalResourceViewResolver();
		iResolver.setPrefix("/WEB-INF/jsp");
		iResolver.setSuffix(".jsp");
		return iResolver;
	}
}