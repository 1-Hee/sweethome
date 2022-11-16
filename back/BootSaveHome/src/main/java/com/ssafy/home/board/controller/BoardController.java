package com.ssafy.home.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.model.service.BoardService;
import com.ssafy.home.member.dto.Member;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	private static final long serialVersionUID = 1L;
	private Map<String, String> map;
	private final BoardService boardService;
	
	private BoardController(BoardService service) {
		this.boardService = service;
	}
	
	@GetMapping("list")
	private ResponseEntity<?> list() throws Exception {
			List<Board> list = boardService.listArticle();
			return new ResponseEntity<List<Board>>(list,HttpStatus.OK);
	}

	@PostMapping("write")
	private ResponseEntity<?> write(@RequestBody Board board, HttpSession session) throws Exception {
//		Member member = (Member) session.getAttribute("userInfo");
		Board board2 = new Board();
		board2.setUserId(board.getUserId());
		board2.setTitle(board.getTitle());
		board2.setContent(board.getContent());
		boardService.writeArticle(board2);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("view/{no}")
	private ResponseEntity<?> view(@PathVariable("no") int no) throws Exception {
		Board board = boardService.getArticle(no);
		boardService.updateHit(no);
		return new ResponseEntity<Board>(board,HttpStatus.OK);
	}

	@PutMapping("modify/{no}")
	private ResponseEntity<?> modify(@PathVariable("no") int no, @RequestBody Board board) throws Exception {
			Board board2 = boardService.getArticle(no);
			board2.setTitle(board.getTitle());
			board2.setContent(board.getContent());
			boardService.modifyArticle(board2);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{no}")
	private ResponseEntity<?> delete(@PathVariable("no") int no) throws Exception {
				boardService.deleteArticle(no);
				return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
	