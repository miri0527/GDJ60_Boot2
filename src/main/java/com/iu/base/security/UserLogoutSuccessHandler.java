package com.iu.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.iu.base.member.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Autowired
	//new하고 객체를 만들었기 때문에 Autowired가 먹지 않는다
	//사용하기 위해서는 componet 객체를 annotation으로 선언해줘야한다
	private MemberDAO memberDAO;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		log.error("=======logout success handler ==========");
		log.error("======{}======",memberDAO);
		//forward
		//redirect
		response.sendRedirect("/");
	}

	
	
	
}
