package com.iu.base.member;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("info")
	public void info(HttpSession session) {
		//암호회되지 않은 실제 비밀번호
		String pw="12345678";
		
		MemberVO memberVO =(MemberVO)memberService.loadUserByUsername("user1");
		
		//암호화 된 password와 평문을 암호화 시킨것과 다르다
		log.error("====={}====", memberVO.getPassword());
		log.error("====={}====", passwordEncoder.encode(pw));
		log.error("{} ::::", memberVO.getPassword().equals(passwordEncoder.encode(pw)));
		
		//평문으로 된 pw와 db에 저장된 암호화된 비밀번호랑 비교
		boolean check = passwordEncoder.matches(pw, memberVO.getPassword());
		
		log.error("{} :::", check);
		
		log.error("=====Login Info======");
//		Enumeration<String> names = session.getAttributeNames();
//		
//		while(names.hasMoreElements()) {
//			log.error("====={}====",names.nextElement());
//		}
		Object obj =  session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.error("===={}======",obj);
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		Authentication authentication =  contextImpl.getAuthentication();
		
		log.error("======{}=====",obj);
		log.error("======USERName : {} =====", authentication.getName());
		log.error("=====Detail : {} ======", authentication.getDetails());
		log.error("======MemberVO : {}", authentication.getPrincipal());
	}
	
	@GetMapping("memberJoin")
	public ModelAndView setMemberJoin(@ModelAttribute MemberVO memberVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/memberJoin");
		//mv.addObject(new MemberVO());
		
		return mv;
	}
	
	
	@PostMapping("memberJoin")
	public ModelAndView setMemberJoin(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//true라면 에러
		boolean check = memberService.memberCheck(memberVO, bindingResult);
		
		if(check) {
			mv.setViewName("member/memberJoin");
			return mv;
		}
		
//		if(bindingResult.hasErrors()) {
//			mv.setViewName("member/memberJoin");
//			return mv;
//		}
		
		int result = memberService.setMemberJoin(memberVO);
		
		mv.setViewName("redirect:../");
		
		return mv;
		
	}
	
	@GetMapping("memberLogin")
	public ModelAndView getMemberLogin(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		Object obj= session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		if(obj == null) {
			mv.setViewName("member/memberLogin");
		}else {
			mv.setViewName("redirect../");
		}
		
		
		
		
		return mv;
		
	}
	
	//Security로 넘어가면서 post가 없어도 login가능
	
//	@PostMapping("memberLogin")
//	public ModelAndView getMemberLogin(MemberVO memberVO, HttpSession session) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		
//		memberVO =  memberService.getMemberLogin(memberVO);
//		
//		mv.setViewName("redirect:./memberLogin");
//		
//		if(memberVO !=null) {
//			 session.setAttribute("member", memberVO);
//			 mv.setViewName("redirect:../");
//			 
//		}
//		
//		return mv;
//	}
	
	@GetMapping("memberLogout")
	public ModelAndView getMemberLogout(HttpSession session, MemberVO memberVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		memberVO =  (MemberVO) session.getAttribute("member");
		int result =  memberService.setLogoutTime(memberVO);
		
		
		session.invalidate();
		
		mv.setViewName("redirect:../");
		
		return mv;
	}
	

	@GetMapping("idDuplicateCheck") 
	@ResponseBody
	//jsp를 거칠 필요없이 바로 js로 감
	public boolean idDuplicateCheck(MemberVO memberVO) throws Exception {
		//String -> return이름이 jsp 이름 
		//void -> url주소가 jsp 이름
		log.debug("=======ID 중복 체크 =====");
		boolean check = true;
		memberVO =  memberService.idDuplicateCheck(memberVO);
		
		if(memberVO !=null) {
			check = false;
		}
		
		return check;
	}
	
	@GetMapping("myPage")
	public void getMyPage() throws Exception {
		
	}
	
	@GetMapping("admin")
	public void getAdmin() throws Exception{
		
	}
	
	

}
