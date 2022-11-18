package com.ssafy.home.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.Notice;
import com.ssafy.home.board.dto.Search;
import com.ssafy.home.board.model.service.NoticeService;
import com.ssafy.home.member.dto.Member;

@RestController
@RequestMapping("/notice")
public class NoticeController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Map<String, String> map;
	private final NoticeService noticeService;
	
	@Autowired
	private NoticeController(NoticeService service) {
		this.noticeService = service;
	}
	
	
	@GetMapping("list")
	private ResponseEntity<?> list(Search search) throws Exception {
		PageHelper.startPage(search);
		List<Notice> list = noticeService.listArticle();
		return new ResponseEntity<PageInfo>(PageInfo.of(list),HttpStatus.OK);
	}

	@PostMapping("write")
	private ResponseEntity<?> write(@RequestBody Notice notice) throws Exception {
			Notice notice2 = new Notice();
			notice2.setUserId(notice.getUserId());
			notice2.setTitle(notice.getTitle());
			notice2.setContent(notice.getContent());
			noticeService.writeArticle(notice2);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("view/{no}")
	private ResponseEntity<?> view(@PathVariable("no") int no) throws Exception {
			Notice notice = noticeService.getArticle(no);
			noticeService.updateHit(no);
			return new ResponseEntity<Notice>(notice,HttpStatus.OK);
	}
	
	@PutMapping("modify/{no}")
	private ResponseEntity<?> mvModify(@PathVariable("no") int no, @RequestBody Notice notice) throws Exception {
		Notice notice2 = noticeService.getArticle(no);
		notice2.setTitle(notice.getTitle());
		notice2.setContent(notice.getContent());
		noticeService.modifyArticle(notice2);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("delete/{no}")
	private ResponseEntity<?> delete(@PathVariable("no") int no) throws Exception {
		noticeService.deleteArticle(no);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
