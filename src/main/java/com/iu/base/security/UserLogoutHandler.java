package com.iu.base.security;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserLogoutHandler implements LogoutHandler{
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restKey;
	
	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String redirectUri;
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		//실패
//		//1.
//		//request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//		//2.
//			 
//		MemberVO memberVO =  (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	 	Map<String,Object> atts =  memberVO.getAttributes();
//	 	
//	 	Iterator<String> keys= atts.keySet().iterator();
//	 	
//	 	log.error("RestApiKey ::{}", restKey);
//	 	log.error("RedirectUri ::{}",redirectUri);
//	 	
//	 	
//	 	while(keys.hasNext()) {
//	 		String key = keys.next();
//	 		Object value = atts.get(key);
//	 		log.error("Key ::: {} ",key);
//	 		log.error("Value ::: {} ",value);
//	 	}
//		this.logoutAll();
		this.simpleLogout();
		
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
		
		
	
//	private void logoutAll() {
//		//카카오계정과 함께 로그아웃
//		//1. 요청 준비
//		RestTemplate restTemplate = new RestTemplate();
//		
//		//2. Header
//		
//		//3. parameter
//		MultiValueMap<String, String> params =  new LinkedMultiValueMap<>();
//		params.add("client_id", restKey);
//		params.add("logout_redirect_uri", redirectUri);
//		
//		//4.
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params,null);
//			
//		//5. 오쳥 발생
////		String response =restTemplate.getForObject("https://kauth.kakao.com/oauth/logout", String.class, request);
//		ResponseEntity<String> response =restTemplate.getForEntity("https://kauth.kakao.com/oauth/logout?client_id="+restKey+"&logout_redirect_uri=http://localhost/"
//				, String.class, request);
//		String result =  response.getBody(); //String타입으로 한번 더 꺼내기
//		log.error("Logout Result ::: {}", result);
//	}
//	
	//줌에 없음 => 중간에 꺼짐 ㅠㅠㅠㅠ
	//로그아웃
	//일반 로그아웃은 웹브라우저와 공유를 하기때문에 웹브라우저에 하나라도 카카오 로그아웃 되지 않으면 다시 로그인했을 때 바로 로그인된다
	private void simpleLogout() {
		RestTemplate restTemplate = new RestTemplate();
		
		//회원번호 꺼내기
		MemberVO memberVO = (MemberVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		log.error("회원번호 : {}", memberVO.getAttributes().get("id"));
		
		//header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " +adminKey);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		//parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_id");
		//서비으세서 로그아웃시킬 사용자의 회원번호
		params.add("target_id", memberVO.getAttributes().get("id").toString());
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params,headers);
		
		String id =  restTemplate.postForObject("https://kapi.kakao.com/v1/user/logout", request, String.class);
		
		log.error("LogoutResult :: {}",id);
		
		
		
	}

	
}
