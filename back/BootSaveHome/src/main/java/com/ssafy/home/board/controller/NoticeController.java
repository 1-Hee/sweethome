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

import com.ssafy.home.board.dto.Notice;
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
	private ResponseEntity<?> list() throws Exception {
		List<Notice> list = noticeService.listArticle();
		return new ResponseEntity<List<Notice>>(list,HttpStatus.OK);
	}

	@PostMapping("write")
	private ResponseEntity<?> write(@RequestBody Notice notice, HttpSession session) throws Exception {
		Member member = (Member) session.getAttribute("userInfo");
			Notice notice2 = new Notice();
			notice2.setUserId(member.getId());
			notice2.setTitle(notice.getTitle());
			notice2.setContent(notice.getContent());
			noticeService.writeArticle(notice2);
			System.out.println("공지사항 작성완료");
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
