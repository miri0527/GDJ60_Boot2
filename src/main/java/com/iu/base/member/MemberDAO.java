package com.iu.base.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	//회원가입
	public int setMemberJoin(MemberVO memberVO) throws Exception;
	
	public int setMemberRoleAdd(Map<String, Object> map) throws Exception;
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception;
	
	
	//로그인
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception;
}
