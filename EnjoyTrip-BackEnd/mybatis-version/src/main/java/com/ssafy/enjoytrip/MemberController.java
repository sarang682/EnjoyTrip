package com.ssafy.enjoytrip;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.Member;
import com.ssafy.enjoytrip.model.service.MemberService;
import com.ssafy.enjoytrip.util.JWTUtil;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@Autowired
	JWTUtil jwtUtil;
	
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
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody Member member) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status= HttpStatus.ACCEPTED;
		try {
			Member loginMember = service.login(member);
			if(loginMember != null) {
				String accessToken = jwtUtil.createAccessToken(loginMember.getUserId());
//				String refreshToken = jwtUtil.createRefreshToken(loginMember.getUserId());
				
//				발급받은 refresh token을 DB에 저장.
//				service.saveRefreshToken(loginMember.getUserId(), refreshToken);
//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
//				resultMap.put("refresh-token", refreshToken);
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userId") String userId, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			try {
//				로그인 사용자 정보.
				Member member = service.selectByUserId(userId);
				resultMap.put("userInfo", member);
				status = HttpStatus.OK;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/id-check/{user-id}")
	public ResponseEntity<?> idCheck(@PathVariable("user-id") String userId) throws Exception{
		try {
			if (service.idCheck(userId)) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
			
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
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
