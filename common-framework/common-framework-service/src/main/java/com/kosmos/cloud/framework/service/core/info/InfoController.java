package com.kosmos.cloud.framework.service.core.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/*spring admin 已实现*/
@RestController
public class InfoController {

	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * 本地服务实例的信息
	 * 
	 * @return
	 */
	@GetMapping("/info")
	public ServiceInstance showInfo() {
		ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}
}
