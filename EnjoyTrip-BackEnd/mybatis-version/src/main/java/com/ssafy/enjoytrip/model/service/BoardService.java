package com.ssafy.enjoytrip.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.dao.BoardDao;
import com.ssafy.enjoytrip.model.dao.MemberDao;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private MemberDao memberDao;

	@Transactional
	public int insert(Board board) throws Exception{
		if (memberDao.selectByUserId(board.getUserId()) == null) {
			return -1; // 해당 유저 없음
		}
		return boardDao.insert(board);		
	}

	public List<Board> list() throws Exception {
		return boardDao.list();
	}

	public Board board(int articleNo) throws Exception {
		boardDao.updateHit(articleNo); // 조회수 +1
		return selectByArticleNo(articleNo);
	}

	public Board selectByArticleNo(int articleNo) throws Exception {
		return boardDao.selectByArticleNo(articleNo);
	}
	
	@Transactional
	public int update(Board board) throws Exception {
		if (selectByArticleNo(board.getArticleNo()) == null) {
			return -1; // 해당 게시글 없음
		}
		return boardDao.update(board);
	}

	@Transactional
	public int delete(int articleNo) throws Exception{
		if (selectByArticleNo(articleNo) == null) {
			return -1; // 해당 게시글 없음
		}
		return boardDao.delete(articleNo);
	}
}
