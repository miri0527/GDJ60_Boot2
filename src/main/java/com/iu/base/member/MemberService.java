package com.iu.base.member;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.error("========Spring Security Login========");
		log.error("======={}===========", username);
		//MemberMapper에서 password가 꺼내져있으면  spring Security가 알아서 memberVO를 리턴할 때 패스워드를 비교해준다  
		//service에서는 password를 꺼내서 비교하는 역할이지 로그인 성공 여부를 비교하는 것은 아니다	
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO =  memberDAO.getMemberLogin(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}

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
			  bindingResult.rejectValue("username", "member.username.Duplicate");
		  }
		  
		
		 
		 return result;
	}
	
	public int setMemberJoin(MemberVO memberVO) throws Exception {
		//memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result =  memberDAO.setMemberJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
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
