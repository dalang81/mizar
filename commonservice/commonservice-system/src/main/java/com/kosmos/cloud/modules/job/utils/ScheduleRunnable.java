package com.kosmos.cloud.modules.job.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import com.kosmos.cloud.common.exception.RRException;
import com.kosmos.cloud.common.utils.SpringContextUtils;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 * 
 * @author kaka
 *  
 * @date 2016年11月30日 下午12:49:33
 */
public class ScheduleRunnable implements Runnable {
	private Object target;
	private Method method;
	private String params;
	
	public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
		this.target = SpringContextUtils.getBean(beanName);
		this.params = params;
		
		if(StringUtils.isNotBlank(params)){
			this.method = target.getClass().getDeclaredMethod(methodName, String.class);
		}else{
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}

	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if(StringUtils.isNotBlank(params)){
				method.invoke(target, params);
			}else{
				method.invoke(target);
			}
		}catch (Exception e) {
			throw new RRException("执行定时任务失败", e);
		}
	}

}
