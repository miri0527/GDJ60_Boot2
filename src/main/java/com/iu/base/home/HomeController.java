package com.iu.base.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.iu.base.aoptest.Card;
import com.iu.base.aoptest.Transport;
import com.iu.base.board.BoardFileVO;
import com.iu.base.util.Pager;

@Controller
public class HomeController {
	
	@Autowired
	private Transport transport;
	
	@Autowired
	private Card card;

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/use")
	public void use() throws Exception {
		Pager pager = new Pager();
		pager.setKind("Bus Title");
		
		//card.cardCheck();
		transport.useBus(pager);
		//card.cardCheck();
		
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setFileName("Subway File");
		
		//card.cardCheck();
		transport.useSubway(boardFileVO);
		//card.cardCheck();
		
	
		transport.takeWalk();
	
	}
}
