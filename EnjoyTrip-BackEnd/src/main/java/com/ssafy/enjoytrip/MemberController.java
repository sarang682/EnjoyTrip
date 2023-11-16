package com.ssafy.enjoytrip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.model.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@PostMapping("")
	public ResponseEntity<?> insertMember(@RequestBody Member member) throws Exception{
		try {
			int result = service.insert(member);
			
			if (result > 0) {
				return new ResponseEntity<String>("회원가입 성공", HttpStatus.CREATED);
			}
			if (result < 0) {
				return new ResponseEntity<String>("아이디가 중복되었습니다.", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member) throws Exception{
		try {
			if (service.login(member)) {
				return new ResponseEntity<String>("로그인 성공", HttpStatus.OK);
			}
			
			return new ResponseEntity<String>("로그인 실패", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/id-check/{user-id}")
	public ResponseEntity<?> idCheck(@PathVariable("user-id") String userId) throws Exception{
		try {
			if (service.idCheck(userId)) {
				return new ResponseEntity<String>("중복된 아이디 입니다.", HttpStatus.OK);
			}
			
			return new ResponseEntity<String>("사용 가능한 아이디 입니다.", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/password")
	public ResponseEntity<?> updatePassword(@RequestBody Member member) throws Exception {
		try {
			int result = service.updatePassword(member);
			
			if (result > 0) {
				return new ResponseEntity<String>("비밀번호 수정 성공", HttpStatus.OK);
			}
			if (result < 0) {
				return new ResponseEntity<String>("해당 아이디가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/email")
	public ResponseEntity<?> updateEmail(@RequestBody Member member) throws Exception {
		try {
			int result = service.updateEmail(member);
			
			if (result > 0) {
				return new ResponseEntity<String>("이메일 수정 성공", HttpStatus.OK);
			}
			if (result < 0) {
				return new ResponseEntity<String>("해당 아이디가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("{user-id}")
	public ResponseEntity<?> deleteUser(@PathVariable("user-id") String userId) throws Exception {
		try {
			int result = service.delete(userId);
			
			if (result > 0) {
				return new ResponseEntity<String>("회원 탈퇴 성공", HttpStatus.OK);
			}
			if (result < 0) {
				return new ResponseEntity<String>("해당 아이디가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
