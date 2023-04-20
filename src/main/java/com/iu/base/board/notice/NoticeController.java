package com.iu.base.board.notice;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardVO;
import com.iu.base.util.Pager;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	
	
	@ModelAttribute("board")
	public String getBoard() throws Exception {
		return "notice";
	}
	
	//list
	@GetMapping("list")
	//mapping이 될 때 매개변수로 받아오는 값은 자동으로 ModelAttribute가 붙어 Model에 담겨서 response 객체에 
	//ModelAttribute에 담긴 클래스명의 첫글자가 소문자로 받아짐
	public ModelAndView getList(@ModelAttribute Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		
		log.info("search : {}", pager.getSearch());
		log.info("kind  : {}", pager.getKind());
		List<BoardVO> ar =  noticeService.getList(pager);
	
		
		mv.addObject("list", ar);
		mv.setViewName("board/list");

		return mv;
	}
	
	@GetMapping("add")
	public ModelAndView setInsert() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/add");
		return mv;
	}
	
	@PostMapping("add")
	public ModelAndView setInsert(NoticeVO noticeVO, MultipartFile[] boardFiles) throws Exception {
		ModelAndView mv =  new ModelAndView();
		
		for (MultipartFile multipartFile : boardFiles) {
			log.info("OriginalName : {} Size : {} ", multipartFile.getOriginalFilename(), multipartFile.getSize());
		}
		
		int result = noticeService.setInsert(noticeVO, boardFiles);
		
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(NoticeVO noticeVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		BoardVO boardVO =  noticeService.getDetail(noticeVO);
		
		mv.addObject("dto", boardVO);
		mv.setViewName("board/detail");
		
		return mv;
	}
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFileVO boardFileVO) throws Exception {
		boardFileVO = noticeService.getFileDetail(boardFileVO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardFileVO", boardFileVO);
		//bean의 이름을 찾아서 간다
		mv.setViewName("fileManager");
		
		return mv;
	}
	

}
