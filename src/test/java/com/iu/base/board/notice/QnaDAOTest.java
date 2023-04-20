package com.iu.base.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.base.board.BoardVO;
import com.iu.base.board.qna.QnaDAO;

@SpringBootTest
class QnaDAOTest {

	@Autowired
	private QnaDAO qnaDAO;
	@Test
	void setInsertTest() throws Exception {
		for(int i=3; i<124; i++) {
			BoardVO boardVO = new BoardVO();
			
			boardVO.setTitle("제목" + i);
			boardVO.setWriter("작성자" + i);
			boardVO.setContents("내용" + i);
			
			
			int result = qnaDAO.setInsert(boardVO);
			
			if(i%10 == 0) {
				Thread.sleep(500);
			}
			
		}
	}
}
