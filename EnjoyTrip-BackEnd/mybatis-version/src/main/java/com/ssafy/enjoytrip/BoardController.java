package com.ssafy.enjoytrip;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.enjoytrip.model.Board;
import com.ssafy.enjoytrip.model.service.BoardService;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService service;
	
	//게시글 쓰기----------------------------------------------------------------------
	@PostMapping("/articles")
	public ResponseEntity<?> insertBoard (@RequestBody Board board) {
		try {
			int result = service.insert(board);
			
			if (result > 0) {
				return new ResponseEntity<String>("게시글 작성 성공", HttpStatus.CREATED);
			}
			if (result < 0) {
				return new ResponseEntity<String>("해당 유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return exceptionHandling(e);
		}	
	}
	
	// 게시글 리스트 가져오기-------------------------------------------------------------
	@GetMapping("/articles")
	public ResponseEntity<?> listBoard() throws Exception{
		try {
			return new ResponseEntity<List<Board>>(service.list(), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	// 특정 게시글 가져오기-------------------------------------------------------------
	@GetMapping("/articles/{article-no}")
	public ResponseEntity<?> getBoard(@PathVariable("article-no") int articleNo) throws Exception {
		try {
			Board board = service.board(articleNo);
			
			if (board == null) {
				return new ResponseEntity<String>("해당 게시글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Board>(board, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 특정 게시글 수정하기-------------------------------------------------------------
	@PutMapping("/articles")
	public ResponseEntity<?> updateBoard(@RequestBody Board board) throws Exception {
		try {
			int result = service.update(board);
			
			if (result > 0) {
				return new ResponseEntity<String>("게시글 수정 성공", HttpStatus.OK);
			}
			if (result < 0) {
				return new ResponseEntity<String>("해당 게시글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 특정 게시글 삭제하기-------------------------------------------------------------
	@DeleteMapping("/articles/{article-no}")
	public ResponseEntity<?> deleteBoard(@PathVariable("article-no") int articleNo) throws Exception {	
		try {
			int result = service.delete(articleNo);
			
			if (result > 0) {
				return new ResponseEntity<String>("게시글 삭제 성공", HttpStatus.OK);
			}
			if (result < 0) {
				return new ResponseEntity<String>("해당 게시글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
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
