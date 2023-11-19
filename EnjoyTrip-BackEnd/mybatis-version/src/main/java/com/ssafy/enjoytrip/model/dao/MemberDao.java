package com.ssafy.enjoytrip.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.Member;

@Mapper
public interface MemberDao {
	
	public int insert(Member member) throws Exception;
	public Member selectByUserId(String userId) throws Exception;
	public int updatePassword(Member member) throws Exception;
	public int updateEmail(Member member) throws Exception;
	public int delete(String userId) throws Exception;
}
