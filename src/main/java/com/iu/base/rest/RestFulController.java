package com.iu.base.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardVO;
import com.iu.base.board.notice.NoticeService;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;


//@Controller
@Slf4j
@RestController //Controller 내의 모든 메서드가 @ResponseBody가 필요하다면 선언
public class RestFulController {

	@Autowired
	private NoticeService noticeService;
	
	
	//@GetMapping("/rest/{num}/detail")
	@GetMapping("/rest/detail")
	//@ResponseBody
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		boardVO =  noticeService.getDetail(boardVO);
		
		return boardVO;
	}

	//parameter => path에 있는 변수의 값을 어떤 변수에 담으세요 
	//매개변수에 선언한 변수명과 {}에 있는 변수명과 일치해야함
	//또는 name을 써서 달라도 변수를 찾을 수 있게 만듦                    파라미터가 나오지 않아도 value값에 기본으로 1을 넣어준다
	@GetMapping("/rest/list/{page}/{kind}")
	//jsp로 가지 않고 json형태로 list<BoardVO> 를 받는다
	//@ResponseBody
	public List<BoardVO> getList(@PathVariable(name = "page"/*value = "1", required = false*/) Long page, String kind, Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.error("{} :::page",page );
		
		List<BoardVO> ar = noticeService.getList(pager);
		
//		mv.addObject("list", ar);
//		mv.setViewName("board/list");
		
		return ar;
		//return mv;
	}
}
