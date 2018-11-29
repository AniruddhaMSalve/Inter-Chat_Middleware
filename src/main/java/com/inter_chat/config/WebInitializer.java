package com.inter_chat.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.inter_chat.Inter_Chat_Backend.config.DBConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer // WebInitializer is similar to
																							// web.xml

{
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		System.out.println("Customize Registration");
		registration.setInitParameter("dispatchOptionsRequest", "true");
		registration.setAsyncSupported(true);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("Root Configuration Classes Accessed");
		return new Class[] { WebResolver.class, DBConfig.class }; // WebResolver is similar to dispatcher-servlet.xml
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("Servlet Configuration Classes Accessed");
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("Servlet Mapping Accessed");
		return new String[] { "/" };
	}

	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding(StandardCharsets.UTF_8.name());
		return new Filter[] { encodingFilter };
	}
}