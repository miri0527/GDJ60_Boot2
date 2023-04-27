package com.iu.base.member;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	//회원가입
	public int setMemberJoin(MemberVO memberVO) throws Exception;
	
	public int setMemberRoleAdd(Map<String, Object> map) throws Exception;
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception;
	
	//사용자 모은 정보
	public List<MemberVO> getMemberAll() throws Exception;
	
	//로그인
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception;
	
	//로그아웃했을 떄 시간
	public int setLogoutTime(MemberVO memberVO) throws Exception;
	
	//3일 후 비교 시간
	public int setEnabled() throws Exception;
	
	public List<MemberVO> getBirth() throws Exception; 	
	

}
