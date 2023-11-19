package com.ssafy.enjoytrip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.Gugun;
import com.ssafy.enjoytrip.model.Sido;
import com.ssafy.enjoytrip.model.AttractionDescription;
import com.ssafy.enjoytrip.model.AttractionInfo;
import com.ssafy.enjoytrip.model.ContentType;
import com.ssafy.enjoytrip.model.service.AttractionService;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

	@Autowired
	AttractionService service;
	
	@GetMapping("/sido")
	public ResponseEntity<?> listSido() throws Exception{
		try {
			return new ResponseEntity<List<Sido>>(service.listSido(), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<?> listGugun(@RequestParam("sido-code") int sidoCode) throws Exception{
		try {
			if (service.selectBySidoCode(sidoCode) == null) {
				return notFound("시도코드");
			}
			return new ResponseEntity<List<Gugun>>(service.listGugun(sidoCode), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/content-type")
	public ResponseEntity<?> listContentType() throws Exception{
		try {
			return new ResponseEntity<List<ContentType>>(service.listContentType(), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/info")
	public ResponseEntity<?> listAttractionInfo(
			@RequestParam(value="sido-code", required=false) Integer sidoCode, 
			@RequestParam(value="gugun-code", required=false) Integer gugunCode, 
			@RequestParam(value="content-type-id", required=false) Integer contentTypeId) throws Exception{
		try {
			// 예외 처리
			if (sidoCode != null && service.selectBySidoCode(sidoCode) == null) {
				return notFound("시도코드");
			}
			
			if (gugunCode != null && (sidoCode == null || service.selectByGugunCode(sidoCode, gugunCode) == null)) {
				return notFound("구군코드");
			}
				
			if (contentTypeId != null && service.selectByContentTypeId(contentTypeId) == null) {
				return notFound("관광지 유형");
			}
			
			// 전체
			if (sidoCode == null && gugunCode == null && contentTypeId == null) {
				return new ResponseEntity<List<AttractionInfo>>(service.listAttractionInfo(), HttpStatus.OK);
			}
			
			// 시도
			if (gugunCode == null && contentTypeId == null) {
				return new ResponseEntity<List<AttractionInfo>>(service.listAttractionInfoBySido(sidoCode), HttpStatus.OK);
			}
			
			// 관광지 유형
			if (sidoCode == null && gugunCode == null) {
				return new ResponseEntity<List<AttractionInfo>>(service.listAttractionInfoByContentType(contentTypeId), HttpStatus.OK);
			}
			
			// 시도 + 구군
			if (contentTypeId == null) {
				return new ResponseEntity<List<AttractionInfo>>(service.listAttractionInfoByGugun(sidoCode, gugunCode), HttpStatus.OK);
			}
						
			// 시도 + 관광지 유형
			if (gugunCode == null) {
				return new ResponseEntity<List<AttractionInfo>>(service.listAttractionInfoBySidoAndContentType(sidoCode, contentTypeId), HttpStatus.OK);
			}
			
			// 시도 + 구군 + 관광지 유형
			return new ResponseEntity<List<AttractionInfo>>(service.listAttractionInfoByGugunAndContentType(sidoCode, gugunCode, contentTypeId), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/info/{content-id}")
	public ResponseEntity<?> attractionInfo(@PathVariable("content-id") int contentId) throws Exception{
		try {
			AttractionInfo attractionInfo = service.attractionInfo(contentId);

			if (attractionInfo == null) {
				return notFound("관광지");
			}
			
			return new ResponseEntity<AttractionInfo>(attractionInfo, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/description/{content-id}")
	public ResponseEntity<?> attractionDescription(@PathVariable("content-id") int contentId) throws Exception{
		try {
			AttractionDescription attractionDescription = service.attractionDescription(contentId);

			if (attractionDescription == null) {
				return notFound("관광지");
			}
			
			return new ResponseEntity<AttractionDescription>(attractionDescription, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	private ResponseEntity<?> notFound(String string) {
		return new ResponseEntity<String>("존재하지 않는 " + string + "입니다.", HttpStatus.BAD_REQUEST);
	}
}
