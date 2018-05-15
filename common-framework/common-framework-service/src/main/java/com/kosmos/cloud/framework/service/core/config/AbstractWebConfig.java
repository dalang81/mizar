package com.kosmos.cloud.framework.service.core.config;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kosmos.cloud.framework.service.core.interceptor.LogInterceptor;

@Configuration
public abstract class AbstractWebConfig extends WebMvcConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(AbstractWebConfig.class);
	@Value("${characterEncoding:UTF-8}")
	protected String characterEncoding;

	@Autowired
	private LogInterceptor logInterceptor;
	
	@Value("${allow.cross.domain:*}")
	private String allowDomain;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api*").allowedOrigins(allowDomain);
			}
		};
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		
		registry.addInterceptor(logInterceptor);
	}
    

	@Bean
	public FilterRegistrationBean characterEncodingFilter() {

		log.debug("autoconfig characterEncodingFilter ...");
		
		FilterRegistrationBean reg = new FilterRegistrationBean();
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding(characterEncoding);
		filter.setForceEncoding(true);
		reg.setFilter(filter);
		
		reg.addUrlPatterns("/*");
		return reg;
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {

		log.debug("autoconfig stringHttpMessageConverter ...");
		
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName(characterEncoding));
		return stringHttpMessageConverter;
	}

	@Bean
	public RequestContextListener requestContextListener() {
		log.debug("autoconfig requestContextListener ...");

		RequestContextListener requestContextListener = new RequestContextListener();

		return requestContextListener;
	}

}
