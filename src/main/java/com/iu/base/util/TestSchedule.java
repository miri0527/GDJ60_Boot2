package com.iu.base.util;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iu.base.board.notice.NoticeDAO;
import com.iu.base.board.notice.NoticeVO;
import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberService;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private  MailManager mailManager;

	//fixRate : 해당 메서드가 언제 동료 되는지 상관 없음 -> 스레드 개념과 비슷
	//fixDelay : 해당 메서드가 종료 후 일정 간격 실행
//	@Scheduled(fixedDelay =1000, initialDelay = 5000)
//	public void test() {
//		log.error("======반복중======");
//	}
	
	//그 시간이 됐을 때 실행시켜줌
	@Scheduled(cron="0/10 * * * * *")			
	public void test() throws Exception {
		int result = memberDAO.setEnabled();
		
		log.error("====휴먼계정=====");
	
		
	}
	
//	@Scheduled(cron="10 * * * * *")
	public void test2() throws Exception {
		List<MemberVO> ar = memberDAO.getBirth();
		
		StringBuffer sb = new StringBuffer();
		sb.append("오늘은");
		
		for(MemberVO memberVO : ar) {
			sb.append(memberVO.getName());
			sb.append(",");
		}
		
		sb.append("생일입니다!");
		
		NoticeVO noticeVO = new NoticeVO();
		
		noticeVO.setTitle("생축");
		noticeVO.setWriter("사장님");
		noticeVO.setContents(sb.toString());
		noticeDAO.setInsert(noticeVO);
		
		
	}
}
