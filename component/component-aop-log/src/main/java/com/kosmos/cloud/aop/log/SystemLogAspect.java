package com.kosmos.cloud.aop.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


@Aspect
@Component
@ConditionalOnProperty(value="spring.aoplog.enable",havingValue="true",matchIfMissing=false)
public class SystemLogAspect {
	
	private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);
	
	@Pointcut("execution (* com.kosmos.cloud.controller..*.*(..))")
	public  void controllerAspect() {}
	
	@Around("controllerAspect()")
	public Object doLog(ProceedingJoinPoint pjp) {
		logger.info("request : {}",JSON.toJSONString(pjp.getArgs()));
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			logger.error(JSON.toJSONString(((MethodSignature) pjp.getSignature()).getMethod().getName()),e);
		}
		logger.info("return : {}",JSON.toJSONString(result));
		return result;
	}
	
}
