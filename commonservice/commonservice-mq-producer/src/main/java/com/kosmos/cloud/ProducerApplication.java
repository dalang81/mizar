package com.kosmos.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.kosmos.cloud.producer.ProducerService;

@EnableEurekaClient
@SpringBootApplication
@EnableBinding(ProducerService.class) //可以绑定多个接口
public class ProducerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}
