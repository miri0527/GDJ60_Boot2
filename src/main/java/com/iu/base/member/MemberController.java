package com.iu.base.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/*")
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

}
