package com.iu.base.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.iu.base.security.UserLoginFailHandler;
import com.iu.base.security.UserLogoutSuccessHandler;
import com.iu.base.security.UserSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		//Security에서 무시해야하는 url 패턴 등록
		
		//css, js.img 등은 security 무시(요청이 들어오는 것은 무조건 다 security 적용 됨)
		
		return web -> web
					.ignoring()
					.antMatchers("/images/**")
				.antMatchers("/js/**")
						.antMatchers("/css/**")
					.antMatchers("/assets/**")
					.antMatchers("/html/**")
					.antMatchers("/favicon/**")
					.antMatchers("/notice/list");
		
	};
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		//interceptor
		httpSecurity
				.cors() 
				.and()
				.csrf()
				.disable()
			//cors와 csfr를 활성화하지 않겠다
			.authorizeRequests() //URL과 권한 매칭
				.antMatchers("/").permitAll()
				.antMatchers("/member/memberJoin").permitAll()
				.antMatchers("/notice/add").hasRole("ADMIN") //이름은 ROLE_ADMIN이지만 hasRole이면 ROLE_ 제외
				.antMatchers("/notice/update").hasRole("ADMIN")
				.antMatchers("/notice/delete").hasRole("ADMIN")
				.antMatchers("/notice/*").permitAll()
				//hasAnyRole => 회원 한 명이 하나의 role을 가지고 있을 때 
				//.antMatchers("/qna/add").hasAnyRole("ADMIN", "MANAGER", "MEMBER")
				.anyRequest().permitAll()
				//.anyRequest().authenticated()
				.and()
			.formLogin()
				//내장된 로그인폼을 사용하지 않고 개발자가 만든 url 폼
				
				.loginPage("/member/memberLogin")
				//기본 파라미터는 username이지만 우리가 설정한 파라미터는 userName이기에 선언해둬야한다
				//비밀번호는 password -> 우리는 password로 해놔서 설정 x
//				.usernameParameter("userName")
				//인증에 성공했을 때
				//.defaultSuccessUrl("/")
				//Autowired로 객체를 집어넣어도 되고 새로운 객체를 만들어도됨
				.successHandler(new UserSuccessHandler())
				//인증에 실패했을 때
				//.failureUrl("/member/memberLogin")
				.failureHandler(new UserLoginFailHandler())
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/memberLogout")
				//.logoutSuccessUrl("/")
				.logoutSuccessHandler(new UserLogoutSuccessHandler())
				.invalidateHttpSession(true)
				.deleteCookies("JESSIONID")
				.permitAll()
				;
				
				
		return httpSecurity.build();		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
