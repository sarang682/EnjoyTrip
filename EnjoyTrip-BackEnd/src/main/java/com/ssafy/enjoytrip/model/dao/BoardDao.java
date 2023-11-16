package com.ssafy.enjoytrip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.Board;

@Mapper
public interface BoardDao {
	public int insert(Board board) throws Exception;
	public List<Board> list() throws Exception;
	public boolean updateHit(int articleNo) throws Exception;
	public Board selectByArticleNo(int articleNo) throws Exception;
	public int update(Board board) throws Exception;
	public int delete(int articleNo) throws Exception;
}
