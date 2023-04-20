package com.iu.base.board.qna;

import com.iu.base.board.BoardVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaVO extends BoardVO {
	
	private Long ref;
	private Long step;
	private Long depth;
}
