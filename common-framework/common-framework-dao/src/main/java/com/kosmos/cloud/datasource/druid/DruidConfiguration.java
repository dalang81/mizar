package com.kosmos.cloud.datasource.druid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;

@Configuration
public class DruidConfiguration {
	
	@ConditionalOnClass(DruidDataSource.class)
	@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
	static class Druid extends DruidConfiguration {
		@Bean
		@ConfigurationProperties("spring.datasource.druid")
		public DruidDataSource dataSource(DataSourceProperties properties) {
			DruidDataSource druidDataSource = (DruidDataSource) properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
			DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
			String validationQuery = databaseDriver.getValidationQuery();
			if (validationQuery != null) {
				druidDataSource.setValidationQuery(validationQuery);
			}
			return druidDataSource;
		}
	}
	
	@Value("${druid.stat.loginUsername:admin}")
	private String loginUsername;

	@Value("${druid.stat.loginPassword:p@SSw0rd}")
	private String loginPassword;
	
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("loginUsername", loginUsername);
		reg.addInitParameter("loginPassword", loginPassword);
		reg.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return reg;
	}
}
