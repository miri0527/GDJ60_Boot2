package com.iu.base.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("memberJoin")
	public ModelAndView setMemberJoin() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/memberJoin");
		
		return mv;
	}
	
	
	@PostMapping("memberJoin")
	public ModelAndView setMemberJoin(MemberVO memberVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		int result = memberService.setMemberJoin(memberVO);
		
		mv.setViewName("redirect:../");
		
		return mv;
		
	}
	
	@GetMapping("memberLogin")
	public ModelAndView getMemberLogin() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/memberLogin");
		
		return mv;
		
	}
	
	@PostMapping("memberLogin")
	public ModelAndView getMemberLogin(MemberVO memberVO, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		memberVO =  memberService.getMemberLogin(memberVO);
		
		mv.setViewName("redirect:./memberLogin");
		
		if(memberVO !=null) {
			 session.setAttribute("member", memberVO);
			 mv.setViewName("redirect:../");
			 
		}
		
		return mv;
	}
	
	@GetMapping("memberLogout")
	public ModelAndView getMemberLogout(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
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
