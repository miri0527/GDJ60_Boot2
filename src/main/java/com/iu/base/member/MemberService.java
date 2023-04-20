package com.iu.base.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	public int setMemberJoin(MemberVO memberVO) throws Exception {
		int result =  memberDAO.setMemberJoin(memberVO);
		result = memberDAO.setMemberRoleAdd(memberVO);
		
		return result;
	}
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception {
		return memberDAO.getMemberLogin(memberVO);
	}
}
