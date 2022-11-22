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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.Notice;
import com.ssafy.home.board.dto.Search;
import com.ssafy.home.board.model.service.BoardService;
import com.ssafy.home.member.dto.Member;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	private static final long serialVersionUID = 1L;
	private Map<String, String> map;
	private final BoardService boardService;
	
	public BoardController(BoardService service) {
		this.boardService = service;
	}
	
	@GetMapping("/index/recommend") 
	public ResponseEntity<?> getTopFourLike() {
		try {
			List<Board> list = boardService.getTopFourLike();
			return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("list")
	private ResponseEntity<?> list(Search search) throws Exception {
		PageHelper.startPage(search);
		List<Board> list = boardService.listArticle();
		return new ResponseEntity<PageInfo>(PageInfo.of(list),HttpStatus.OK);
	}

	@PostMapping("write")
	private ResponseEntity<?> write(@RequestBody Board board) throws Exception {
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
	