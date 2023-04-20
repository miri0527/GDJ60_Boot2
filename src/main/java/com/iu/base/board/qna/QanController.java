package com.iu.base.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardVO;
import com.iu.base.util.Pager;

import lombok.Getter;

@Controller
@RequestMapping("/qna/*")
public class QanController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() throws Exception {
		return "qna";
	}

	@GetMapping("list")
	public ModelAndView getList(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar =  qnaService.getList(pager);
		
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
	public ModelAndView setInsert(BoardVO boardVO, MultipartFile[] boardFiles) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		
		int result = qnaService.setInsert(boardVO, boardFiles);
		
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	
}
