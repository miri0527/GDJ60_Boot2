package com.iu.base.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO  implements UserDetails, OAuth2User{
	
	
	//enabled Setter가 필요하여 멤버변수로 선언
	private boolean enabled;
	
	
	@NotBlank
	private String username;
	
	@NotBlank
	@Size(min = 8, max = 15)
	private String password;
	
	private String passwordCheck;
	
	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	@Past
	private Date birth;
	
	private Date lastTime;

	private List<RoleVO> roleVOs;
	
	//OAuth2User, token 정보 저장
	private Map<String, Object> attributes;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//GrantedAuthority이거나 이 타입을 넣어라
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO : roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
			
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		//계정의 만료 여부
		//true : 만료 안됨
		//false : 만료됨, 로그인 안됨
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//계정 잠김 여부
		//true : 계정 잠기지 않음
		//false : 계정 잠김, 로그인 안됨
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//password 만료 여부
		//true : 만료 안됨
		//false : 만료 됨, 로그인 안됨
		return true;
	}

	//원래 Security에 있던 것
	@Override
	public boolean isEnabled() {
		//계정 사용 여부
		//true : 계정 활성화
		//false : 계정 비활성화, 로그인 안됨
		return this.enabled;
	}

	
//	@Override
//	public String getUserName() {
		//username(id) return
//		return null;
//	}
	
//	@Override
//	public String getPassword() {
		//password return
//		return null;
//	}
	
	
	
}
