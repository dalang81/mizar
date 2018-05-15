package com.kosmos.cloud.framework.service.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 * @author kaka 
 *
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
@ConditionalOnProperty(prefix = "spring.redis",value = "host")
public class CacheConfig {
	private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);
	@Autowired
	RedisTemplate<Object,Object> redisTemplate;

	@Value("${cache.redis.expireTime:86400}")
	long defaultExpireTime;

	@Bean
	@ConditionalOnMissingBean(RedisCacheManager.class)
	public RedisCacheManager redisCacheManager() {

		log.debug("autoconfig redisCacheManager ...");

		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);

		// set defaultExpireTime ,if not config,use 1 day
		log.info("set cache defaultExpireTime:"+defaultExpireTime);
		redisCacheManager.setDefaultExpiration(defaultExpireTime);

		return redisCacheManager;
	}
}
