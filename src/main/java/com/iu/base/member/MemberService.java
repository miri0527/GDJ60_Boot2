package com.iu.base.member;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service

public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	
	
	//패스워드 일치하는지 검증하는 메서드
	//에러 유무를 검증하는 메서드
	public boolean memberCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean result = false;
		
		//false : error가 없음, 검증성공
		//true : error가 실패, 검증실패
		
		//1. annotation 검증 결과
		 result = bindingResult.hasErrors();
		 
		 //2.password가 일치하는지 검증
		 if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			 result = true;
			 //우리가 검증하는 것은 annotation이 없다
			 //bindingResult.rejectValue("멤버변수명(path)',properties의 key(코드)');
			 bindingResult.rejectValue("passwordCheck", "member.password.notEqual");
		 }
		 
		 //3. ID 중복 검사
		  //MemberVO checkMember =  memberDAO.idDuplicateCheck(memberVO);
		 MemberVO checkMember = memberDAO.idDuplicateCheck(memberVO);
		  
		  if(checkMember !=null) {
			  result = true;
			  bindingResult.rejectValue("userName", "member.userName.Duplicate");
		  }
		  
		
		 
		 return result;
	}
	
	public int setMemberJoin(MemberVO memberVO) throws Exception {
		memberVO.setEnabled(true);
		int result =  memberDAO.setMemberJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("userName", memberVO.getUserName());
		map.put("num", 3);
		result = memberDAO.setMemberRoleAdd(map);
		
		return result;
	}
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception {
		return memberDAO.getMemberLogin(memberVO);
	}
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception {
		return memberDAO.idDuplicateCheck(memberVO);
		
	}
	
	public int setLogoutTime(MemberVO memberVO) throws Exception {
		return memberDAO.setLogoutTime(memberVO);
	}
	

	
}
