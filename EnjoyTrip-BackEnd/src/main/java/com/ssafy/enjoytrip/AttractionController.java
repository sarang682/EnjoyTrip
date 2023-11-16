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
				return new ResponseEntity<String>("존재하지 않는 시도코드입니다.", HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<?> listAttractionInfo(@RequestParam("sido-code") int sidoCode, @RequestParam("gugun-code") int gugunCode) throws Exception{
		try {
			if (service.selectBySidoCode(sidoCode) == null) {
				return new ResponseEntity<String>("존재하지 않는 시도코드입니다.", HttpStatus.BAD_REQUEST);
			}
			if (service.selectByGugunCode(sidoCode, gugunCode) == null) {
				return new ResponseEntity<String>("해당 시도에 존재하지 않는 구군코드입니다.", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<List<AttractionInfo>>(service.listAttractionInfo(sidoCode, gugunCode), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/info/{content-id}")
	public ResponseEntity<?> attractionInfo(@PathVariable("content-id") int contentId) throws Exception{
		try {
			AttractionInfo attractionInfo = service.attractionInfo(contentId);

			if (attractionInfo == null) {
				return new ResponseEntity<String>("존재하지 않는 관광지입니다.", HttpStatus.BAD_REQUEST);
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
				return new ResponseEntity<String>("존재하지 않는 관광지입니다.", HttpStatus.BAD_REQUEST);
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
}
