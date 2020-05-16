package com.slawomirlasik.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// setup pointcut declarations
	@Pointcut("execution(* com.slawomirlasik.springdemo.controller.*.*(..))")
	private void forControllerPackage() {

	}

	// do the same for servixe and dao
	@Pointcut("execution(* com.slawomirlasik.springdemo.service.*.*(..))")
	private void forServicePackage() {

	}

	@Pointcut("execution(* com.slawomirlasik.springdemo.dao.*.*(..))")
	private void forDaoPackage() {

	}

	@Pointcut("forControllerPackage() || forServicePackage() ||  forDaoPackage()")
	private void forAppFlow() {

	}
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		// display method we are calling
		String theMethod = theJoinPoint.getSignature().toLongString();
		myLogger.info("======>>>> in @Before: calling method " + theMethod);
		
		// display the arguments to the method
		
		// get the arguments
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru and display args
		for(Object tempArg : args) {
			myLogger.info("=======>> argument: " + tempArg);
		}
		
	}

	// add @AfterReturining advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result"
			)
	public void afterReturning(JoinPoint joinPoint, Object result) {
		
		// display method we are calling
		String theMethod = joinPoint.getSignature().toLongString();
		myLogger.info("======>>>> in @AfterReturning: calling method " + theMethod);
		
		// display data returned
		
		myLogger.info("======>>> result: " + result);
		
	}

}









