package com.ssafy.enjoytrip.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.model.dao.MemberDao;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Transactional
	public int insert(Member member) throws Exception{
		if (selectByUserId(member.getUserId()) == null) {
			return memberDao.insert(member);
		}
		return -1; // 아이디 중복
	}
	
	public Member selectByUserId(String userId) throws Exception {
		return memberDao.selectByUserId(userId);
	}
	
	public Member login(Member member) throws Exception {
		Member m = selectByUserId(member.getUserId());
		if(m==null || !m.getUserPassword().equals(member.getUserPassword()))
			return null;
		return m;
	}
	
	public boolean idCheck(String userId) throws Exception {
		if (selectByUserId(userId) == null) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public int updatePassword(Member member) throws Exception {
		if (selectByUserId(member.getUserId()) == null) {
			return -1; // 해당 아이디 없음
		}
		return memberDao.updatePassword(member);
	}
	
	@Transactional
	public int updateEmail(Member member) throws Exception {
		if (selectByUserId(member.getUserId()) == null) {
			return -1; // 해당 아이디 없음
		}
		return memberDao.updateEmail(member);
	}
	
	@Transactional
	public int delete(String userId) throws Exception{
		if (selectByUserId(userId) == null) {
			return -1; // 해당 아이디 없음
		}
		return memberDao.delete(userId);
	}

	@Transactional
	public int update(Member member) throws Exception {
		if (selectByUserId(member.getUserId()) == null) {
			return -1;
		}
		return memberDao.update(member);
	}
}
