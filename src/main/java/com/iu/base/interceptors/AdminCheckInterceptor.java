package com.iu.base.interceptors;

import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.iu.base.member.MemberVO;
import com.iu.base.member.RoleVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		log.info(memberVO.getUserName());
		
		List<RoleVO> ar =  memberVO.getRoleVOs();
		
		
		for (RoleVO roleVO : ar) {
			if(roleVO.getRoleName().equals("ROLE_ADMIN")) {
				return true;
			}
				
		}
		
		request.setAttribute("message", "관리자 권한이 아닙니다");
		request.setAttribute("url", "../");
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
		view.forward(request, response);
	
		return false;
	}
	
}
