package com.ssafy.enjoytrip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.AttractionDescription;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.Gugun;
import com.ssafy.enjoytrip.model.Sido;

@Mapper
public interface AttractionDao {

	public List<Sido> listSido() throws Exception;
	public List<Gugun> listGugun(int sidoCode) throws Exception;
	
	public Sido selectBySidoCode(int sidoCode);
	public Gugun selectByGugunCode(int sidoCode, int gugunCode);
	
	public List<AttractionInfo> listAttractionInfo(int sidoCode, int gugunCode) throws Exception;
	public AttractionInfo attractionInfo(int contentId) throws Exception;
	public AttractionDescription attractionDescription(int contentId) throws Exception;
	
}
