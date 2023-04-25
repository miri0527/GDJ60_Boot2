package com.iu.base.board.qna;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardService;
import com.iu.base.board.BoardVO;
import com.iu.base.util.FileManager;
import com.iu.base.util.Pager;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.qna}")
	private String path;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		
		Long totalCount = qnaDAO.getTotalCount(pager);
		
		pager.makeNum(totalCount);
		
		pager.makeStartRow();
		
		List<BoardVO> ar = qnaDAO.getList(pager);
		
		return ar;

	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] multipartFiles) throws Exception {
		int result = qnaDAO.setInsert(boardVO);
		
		for(MultipartFile  multipartFile  : multipartFiles) {
			
			if(!multipartFile.isEmpty()) {
				String fileName = fileManager.saveFile(path, multipartFile);
				BoardFileVO boardFileVO = new BoardFileVO();
				
				boardFileVO.setFileName(fileName);
				boardFileVO.setOriName(multipartFile.getOriginalFilename());
				boardFileVO.setNum(boardVO.getNum());
				
				result = qnaDAO.setFileInsert(boardFileVO);
			}
		}
		return result;
	}

	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int setDelete(BoardVO boardVO, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
