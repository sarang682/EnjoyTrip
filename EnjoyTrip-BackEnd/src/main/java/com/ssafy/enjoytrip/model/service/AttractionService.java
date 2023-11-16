package com.ssafy.enjoytrip.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.AttractionDescription;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.ContentType;
import com.ssafy.enjoytrip.model.Gugun;
import com.ssafy.enjoytrip.model.Sido;
import com.ssafy.enjoytrip.model.dao.AttractionDao;

@Service
public class AttractionService {
	
	@Autowired
	private AttractionDao attractionDao;
	
	public List<Sido> listSido() throws Exception {
		return attractionDao.listSido();
	}
	
	public Sido selectBySidoCode(int sidoCode) throws Exception {
		return attractionDao.selectBySidoCode(sidoCode);
	}
		
	public List<Gugun> listGugun(int sidoCode) throws Exception {
		return attractionDao.listGugun(sidoCode);
	}
	
	public Gugun selectByGugunCode(int sidoCode, int gugunCode) throws Exception {
		return attractionDao.selectByGugunCode(sidoCode, gugunCode);
	}
	
	public List<ContentType> listContentType() throws Exception {
		return attractionDao.listContentType();
	}
	
	public ContentType selectByContentTypeId(int contentTypeId) throws Exception {
		return attractionDao.selectByContentTypeId(contentTypeId);
	}
	
	public List<AttractionInfo> listAttractionInfo(int sidoCode, int gugunCode) throws Exception {
		return attractionDao.listAttractionInfo(sidoCode, gugunCode);
	}
	
	public AttractionInfo attractionInfo(int contentId) throws Exception {
		return attractionDao.attractionInfo(contentId);
	}
	
	public AttractionDescription attractionDescription(int contentId) throws Exception {
		return attractionDao.attractionDescription(contentId);
	}
}
