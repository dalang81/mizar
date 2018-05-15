package com.kosmos.cloud.framework.service.core.interceptor;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.MDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Aspect
@Configuration
public class LogInterceptor extends HandlerInterceptorAdapter {

	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String REQUEST_ID = "requestId";
	
	private static final String CLIENT_IP = "clientIp";
	
	private static final String SERVER_IP = "serverIp";
	
	private static final String SERVER_PORT = "serverPort";
	
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestId = request.getParameter(REQUEST_ID);
		if (StringUtils.isEmpty(requestId)) {
			requestId = UUID.randomUUID().toString().replace("-", "");
		}

		MDC.put(REQUEST_ID, requestId);
		
		MDC.put(CLIENT_IP, request.getRemoteAddr());
		
		MDC.put(SERVER_IP, request.getLocalAddr());
		
		MDC.put(SERVER_PORT, request.getLocalPort());
		
		long beginTime = System.currentTimeMillis();// 1、开始时间
		startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）
		log.info("计时开始:    Method: {}    URI: {}", request.getMethod(),request.getRequestURI()  + getQueryString(request));
		return true;
	}

	@AfterReturning(pointcut = "execution(* com.thingok.*.*(..))", returning = "returnValue")
	public void log(JoinPoint point, Object returnValue) {
		log.debug("class:" + point.getSignature().getDeclaringTypeName());
		log.debug("method:" + point.getSignature().getName());
		log.debug("args:" + Arrays.toString(point.getArgs()));
		Object[] args = point.getArgs();
		if (null != args && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				log.debug("args " + i + ":"
						+ ToStringBuilder.reflectionToString(args[i], ToStringStyle.SHORT_PREFIX_STYLE));
			}
		}
		log.debug("return:" + returnValue);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// 保存操作日志
		// LogUtils.saveLog(request, handler, ex, null);
		
		// XSS filter
		response.addHeader("X-Content-Type-Options", "nosniff");

		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long endTime = System.currentTimeMillis(); // 2、结束时间
		log.info("计时结束:    Method: {}    URI: {}    耗时：{}毫秒", request.getMethod(),request.getRequestURI()  + getQueryString(request), (endTime - beginTime));
		MDC.clear();
	}
	
	/**
	 * 格式化参数
	 * @param req
	 * @return
	 */
	private String getQueryString(HttpServletRequest req) {
		StringBuilder buffer = new StringBuilder("?");
		Enumeration<String> emParams = req.getParameterNames();
		try {
			while (emParams.hasMoreElements()) {
				String sParam = emParams.nextElement();
				String sValues = req.getParameter(sParam);
				buffer.append(sParam).append("=").append(sValues).append("&");
			}
			return buffer.substring(0, buffer.length() - 1);
		} catch (Exception e) {
			log.error("get post arguments error", buffer.toString());
		}
		return "";
	}
}
