package com.iu.base.board.notice;

import org.apache.ibatis.annotations.Mapper;

import com.iu.base.board.BoardDAO;

//어차피 dao는 return sqlSession이기 떼문에
//굳이 class를 만들필요가 없기 때문에 interface 적용

@Mapper
public interface NoticeDAO  extends BoardDAO{

}
