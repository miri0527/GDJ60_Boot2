package com.iu.base.config;

import java.util.Locale;

import javax.websocket.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer {
	
	//객체 생성 : annotation, xml -> <bean>
	//bean의 이름은 클래스의 앞글자가 소문자
	//new 객체 만들어서 객체를 리턴할테니 spring tool에 저장
	//우리가 만드는 메서드가 아니기 때문에 어노테이션을 bean으로 씀
	//메서드 이름명을 꼭 localeResolver()로 하기
	@Bean
	public LocaleResolver localeResolver() {
		//1. session
		//session아이디를 cookie에 담아서 옴
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.KOREAN);
		
		//2. cookie
		//CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		//cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		//cookieLocaleResolver.setCookieName("lang");
		
		return sessionLocaleResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor getLocaleChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang_opt");
		
		
		return localeChangeInterceptor;
	}
}
