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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/board")
@Api("자유게시판 컨트롤러 API V1")
public class BoardController {
	
	private static final long serialVersionUID = 1L;
	private Map<String, String> map;
	private final BoardService boardService;
	
	public BoardController(BoardService service) {
		this.boardService = service;
	}
	
	@ApiOperation(value="조회수 순 상위4개 게시글 조회", notes="board table의 hit 역순으로 게시글을 반환합니다.")
	@ApiResponses({
		@ApiResponse(code=200,message="조회 성공!"), @ApiResponse(code=404,message="페이지 없음!"), @ApiResponse(code=500,message="서버 에러!")
	})
	@GetMapping("/index/recommend") 
	public ResponseEntity<?> getTopFourLike() throws Exception {
		List<Board> list = boardService.getTopFourLike();
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="게시글 목록", notes="전체 게시글 목록을 반환합니다.")
	@GetMapping("list")
	private ResponseEntity<?> list(Search search) throws Exception {
		PageHelper.startPage(search);
		List<Board> list = boardService.listArticle();
		return new ResponseEntity<PageInfo>(PageInfo.of(list),HttpStatus.OK);
	}

	@ApiOperation(value="게시글 등록", notes="게시글을 등록합니다.")
	@PostMapping("write")
	private ResponseEntity<?> write(@RequestBody Board board) throws Exception {
		Board board2 = new Board();
		board2.setUserId(board.getUserId());
		board2.setTitle(board.getTitle());
		board2.setContent(board.getContent());
		boardService.writeArticle(board2);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="게시글 조회", notes="articleNo에 해당하는 게시글을 조회합니다.")
	@GetMapping("view/{no}")
	private ResponseEntity<?> view(@PathVariable("no") int no) throws Exception {
		Board board = boardService.getArticle(no);
		boardService.updateHit(no);
		return new ResponseEntity<Board>(board,HttpStatus.OK);
	}

	@ApiOperation(value="게시글 수정", notes="articleNo에 해당하는 게시글을 수정합니다.")
	@PutMapping("modify/{no}")
	private ResponseEntity<?> modify(@PathVariable("no") int no, @RequestBody Board board) throws Exception {
			Board board2 = boardService.getArticle(no);
			board2.setTitle(board.getTitle());
			board2.setContent(board.getContent());
			boardService.modifyArticle(board2);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="게시글 삭제", notes="articleNo에 해당하는 게시글을 삭제합니다.")
	@DeleteMapping("delete/{no}")
	private ResponseEntity<?> delete(@PathVariable("no") int no) throws Exception {
				boardService.deleteArticle(no);
				return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
	