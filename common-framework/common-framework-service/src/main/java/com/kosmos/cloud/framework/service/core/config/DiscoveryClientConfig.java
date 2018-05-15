package com.kosmos.cloud.framework.service.core.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableDiscoveryClient
@Profile("discoveryClient")
public class DiscoveryClientConfig {
}
