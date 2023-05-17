package com.iu.base.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.nimbusds.oauth2.sdk.client.ClientReadRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSocialService extends DefaultOAuth2UserService{

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	
		log.error("===={} : Social====",userRequest.getAccessToken());
		//DB에서 조회 후 회원 추가 또는 회원정보(Role) 조회
		// kakao에서 로그인 처리 후 실행
		// KaKao에서 받은 정보를 MemberVO로 변경
		
		ClientRegistration registration=userRequest.getClientRegistration();
		
		log.error("{} :::", registration.getRegistrationId());
		log.error("{} :::", registration.getScopes());
		log.error("{} :::", registration.getClientName());
		log.error("{} :::",registration.getClientId());
		
	
		OAuth2User user = super.loadUser(userRequest);
		log.error("{} :::", user.getName());
		
		
		return this.socialJoinCheck(userRequest);
	}

	//네이버를 구현했을 때 네이버 메서드를 따로 구현하기 이하여 메서드 따로 생성
	private OAuth2User socialJoinCheck(OAuth2UserRequest auth2UserRequest) {
		//DB에서 조회 후 회원 추가 또는 회원 정보 조회
		//Kakao에서 받은 정보를 MemberVO로 변경
		OAuth2User user = super.loadUser(auth2UserRequest);
		
		Map<String, Object> map = user.getAttributes();
		
		Iterator<String> it= map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			log.error("Key :: {}", key);
			log.error("Value :: {}", map.get(key));
		}
		
		HashMap<String, Object> m = (HashMap<String, Object>) map.get("properties");
		log.error("NickName {} :::", m.get("nickname"));
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setAttributes(map);
		
		memberVO.setUsername(m.get("nickname").toString());
		
		List<RoleVO> roleVOs = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		
		roleVO.setRoleName("ROLE_MEMBER");
		roleVOs.add(roleVO);
		
		memberVO.setRoleVOs(roleVOs);
		
		memberVO.setEnabled(true);
		
		return memberVO;
	}
	
}
