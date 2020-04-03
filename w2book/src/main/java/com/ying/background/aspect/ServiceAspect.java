package com.ying.background.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceAspect.class);
	ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	ThreadLocal<JoinPoint> joinPoints = new ThreadLocal<JoinPoint>();
	@Pointcut("execution(public * com.ying.background.services.*.impl.*.*(..))")
	public void log() {
		
	}

//	@Pointcut("execution(public * com.ying.book.*.services.impl.*.*(..))")
//	public void log2() {
//
//	}
	
//	@Before("log() || log2()")
	@Before("log()")
	public void before(JoinPoint joinPoint) {
		LOG.info(">>----------------- request worker start -----------------<<");
		joinPoints.set(joinPoint);
		startTime.set(System.currentTimeMillis());
		Object[] args  = joinPoint.getArgs();
		LOG.info(joinPoint.getSignature().getName()+"() method request data is --> "+ JSON.toJSONString(args));
	}
	
//	@AfterReturning(returning = "o",pointcut = "log() || log2()")
@AfterReturning(returning = "o",pointcut = "log()")
	public void afterReturning(Object o) {
		JoinPoint joinPoint = joinPoints.get();
		long end = System.currentTimeMillis();
		LOG.info(joinPoint.getSignature().getName()+"() method response data is --> " + JSON.toJSONString(o));
		LOG.info(joinPoint.getSignature().getName() + "()--> start: " + startTime.get() + ",  end: " + end  + ", cost time:--->" +  (end - startTime.get()));
	}
	
	
//	@Around(value = "log()")
//	public void around(ProceedingJoinPoint joinPoint) {
//		LOG.info(">>----------------- request worker start -----------------<<");
//
//        Object result = new BaseResponse();
//        // check request is null
//        long start = System.currentTimeMillis();
//        Object[] args  = joinPoint.getArgs();
//        LOG.info(joinPoint.getSignature().getName()+"() method request data is --> " + JSON.toJSONString(args));
//        try {
//        	result = joinPoint.proceed();
//        } catch (Throwable e) {
//        	e.printStackTrace();
//        }
//        long end = System.currentTimeMillis();
//        Signature signature = joinPoint.getSignature();
//        LOG.info(signature.getName()+"() method response data is --> " + JSON.toJSONString(result));
//        LOG.info(signature.getName() + "()--> start: " + start + ",  end: " + end  + ", cost time:--->" +  (end - start));
//	}
	
}
