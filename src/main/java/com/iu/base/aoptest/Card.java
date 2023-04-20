package com.iu.base.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//1. log기록을 여러개 찍기 위하여 AOP 사용
//2. Transcation 적용할 때 사용 -> 한번 성공하면 다 성공하고, 한 번 실패하면 다 실패해야함
// 
@Component
@Slf4j
@Aspect
public class Card {

	//advice
	//@Before("execution(* com.iu.base.aoptest.Transport.use*())")
	//@AfterReturning("execution(* com.iu.base.aoptest.Transport.use*())")
	//@Around("execution(* com.iu.base.aoptest.Transport.use*(..))")
	//@AfterThrowing("execution(* com.iu.base.*.*Service.set*(..))")
	
	//Proceeding JoinPoint -> Around에만 있음
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		log.error("탑승입니다");
		
		Object[] objs = joinPoint.getArgs();
		
		for (Object object : objs) {
			log.warn("ARGS ====> {}", object.toString());
		}
		
		//실행하는 리턴값을 받아서 homeController에서 또 리턴을 해줘야한다
		Object obj =  joinPoint.proceed();
		log.error("하차입니다");
		log.warn("OBJECT=>{}", obj.toString());
		return obj;
	}
}
