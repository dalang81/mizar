package com.kosmos.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import com.kosmos.cloud.framework.service.core.ApplicationStarter;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
	public static void main(String[] args) {
		ApplicationStarter.run(UserServiceApplication.class, args);
	}
}
