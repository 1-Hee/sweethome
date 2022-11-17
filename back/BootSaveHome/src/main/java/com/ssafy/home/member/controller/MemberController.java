package com.ssafy.home.member.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.model.service.MemberService;
import com.ssafy.home.util.VerifyEmail;

@RestController
@RequestMapping("/member")
public class MemberController {

	private final MemberService service;
	private final VerifyEmail verifyemail;
	
	@Autowired
	private MemberController(MemberService service, VerifyEmail verifyemail) {
		this.service = service;
		this.verifyemail = verifyemail;
	}
	
	@PostMapping("login")
	private ResponseEntity<?> login(@RequestBody Member member) throws Exception {
		String id = member.getId();
		System.out.println(id);
		String password = member.getPassword();
		System.out.println(password);
		Member member2 = service.selectById(id);
		if(member2!=null&&member2.getPassword().equals(password)) {
			return new ResponseEntity<Member>(member2,HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("logout")
	private ResponseEntity<?> logout() throws Exception {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("find")
	private ResponseEntity<?> find(String id) throws Exception {
		String msg = "해당 아이디가 존재하지 않습니다."; 
		Member member = service.selectById(id);
		if(member!=null) {
			verifyemail.sendEmail(member);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("info/{id}")
	private ResponseEntity<?> info(@PathVariable String id) throws Exception {
		Member member = service.selectById(id);
		return new ResponseEntity<Member>(member,HttpStatus.OK);
	}
	
	@PostMapping("update")
	private ResponseEntity<?> update(@RequestBody Member member) throws Exception {
		Member member2 = service.selectById(member.getId());
		String password = member.getPassword();
		String fullEmail = member.getEmail()+member.getDomain();
		if(password==null&&fullEmail==null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		if(password==null) {
			member2.setPassword(password);
		} else {
			String[] str = fullEmail.split("@");
			System.out.println(str[0]);
			System.out.println(str[1]);
			member2.setEmail(str[0]);
			member2.setDomain(str[1]);
		}
		service.updateUser(member);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("delete/{id}")
	private ResponseEntity<?> delete(@PathVariable String id) throws Exception{
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("signup")
	private ResponseEntity<?> signup(@RequestBody Member member) throws Exception{
		String id = member.getId();
		String name = member.getName();
		String password = member.getPassword();
		int age = member.getAge();
		String email = member.getEmail();
		String domain = member.getDomain();
		Member registMember = new Member(id,password,name,age,email,domain,0,LocalDate.now().toString());
		service.insertUser(registMember);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("list")
	private ResponseEntity<?> list() throws Exception{
		List<Member> list = service.selectAll();
		return new ResponseEntity<List<Member>>(list,HttpStatus.OK);
	}
}
