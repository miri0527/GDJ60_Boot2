package com.iu.base.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.iu.base.board.BoardVO;

@SpringBootTest
@Rollback(true)
//테스트하고 익셉션이 발생하면 다시 원상복구
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	//@Test
	void setInsertTest() throws Exception {
		for(int i=0; i<120; i++) {
			BoardVO boardVO = new NoticeVO();
			
			boardVO.setTitle("제목" + i);
			boardVO.setWriter("작성자" + i);
			boardVO.setContents("내용" + i);
			
			int result =  noticeDAO.setInsert(boardVO);
			
			if(i%10 == 0) {
				Thread.sleep(500);
			}
		}
		
		
		System.out.println("종료");
		
		
	}
	
	

}
