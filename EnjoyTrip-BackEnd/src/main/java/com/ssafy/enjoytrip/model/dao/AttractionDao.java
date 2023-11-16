package com.ssafy.enjoytrip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.AttractionDescription;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.ContentType;
import com.ssafy.enjoytrip.model.Gugun;
import com.ssafy.enjoytrip.model.Sido;

@Mapper
public interface AttractionDao {

	public List<Sido> listSido() throws Exception;
	public List<Gugun> listGugun(int sidoCode) throws Exception;
	public List<ContentType> listContentType() throws Exception;
	
	public Sido selectBySidoCode(int sidoCode);
	public Gugun selectByGugunCode(int sidoCode, int gugunCode);
	public ContentType selectByContentTypeId(int contentTypeId);
	
	public List<AttractionInfo> listAttractionInfo() throws Exception;
	public List<AttractionInfo> listAttractionInfoBySido(int sidoCode) throws Exception;
	public List<AttractionInfo> listAttractionInfoByGugun(int sidoCode, int gugunCode) throws Exception;
	public List<AttractionInfo> listAttractionInfoByContentType(int contentTypeId) throws Exception;
	public List<AttractionInfo> listAttractionInfoBySidoAndContentType(int sidoCode, int contentTypeId) throws Exception;
	public List<AttractionInfo> listAttractionInfoByGugunAndContentType(int sidoCode, int gugunCode, int contentTypeId) throws Exception;
	
	public AttractionInfo attractionInfo(int contentId) throws Exception;
	public AttractionDescription attractionDescription(int contentId) throws Exception;
	
}
