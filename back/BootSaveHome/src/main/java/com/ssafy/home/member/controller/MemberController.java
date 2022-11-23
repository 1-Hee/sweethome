package com.ssafy.home.member.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.jwt.model.service.JwtServiceImpl;
import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.model.service.MemberService;
import com.ssafy.home.util.VerifyEmail;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;
	@Autowired
	private MemberService service;
	@Autowired
	private VerifyEmail verifyemail;
	
	@PostMapping("login")
	private ResponseEntity<?> login(@RequestBody Member member) throws Exception {
		logger.trace("trace Log");
		logger.debug("debug Log");
		logger.info("info Log");
		logger.warn("warn Log");
		logger.error("error Log");
		logger.error("error Log");
		logger.error("error Log");
		logger.error("error Log");
		logger.error("error Log");
		logger.error("error Log");
		logger.error("error Log");
		Map<String, Object> resultMap = new HashMap<>();
		String id = member.getId();
		String password = member.getPassword();
		Member member2 = service.selectById(id);
		if(member2!=null&&member2.getPassword().equals(password)) {
			String accessToken = jwtService.createAccessToken("userid", member2.getId());// key, data
			String refreshToken = jwtService.createRefreshToken("userid", member2.getId());// key, data
			service.saveRefreshToken(member.getId(),refreshToken);
			resultMap.put("member", member2);
			resultMap.put("access-token", accessToken);
			resultMap.put("refresh-token", refreshToken);
			resultMap.put("message", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(resultMap,HttpStatus.OK);
		} else {
			resultMap.put("message", FAIL);
			return new ResponseEntity<Map<String, Object>>(resultMap,HttpStatus.BAD_REQUEST);
		}
	}
	
	// 엑세스 토큰으로 사용자 정보 재검증
	@GetMapping("/info/access/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String userId, HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				Member member = service.selectById(userId);
				resultMap.put("userInfo", member);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	// 리프레시 토큰으로 사용자 정보 검증
	@PostMapping("/refresh")
	private ResponseEntity<?> refreshToken(@RequestBody Member member, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, member : {}", token, member);
		if(jwtService.checkToken(token)) {
			if(token.equals(service.getRefreshToken(member.getId()))) {
				String accessToken = jwtService.createAccessToken("id", member.getId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
	}
	
	
	
	@PostMapping("login/user")
	private ResponseEntity<?> tempLogin(@RequestBody Member member) throws Exception {
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
	
	@GetMapping("logout/{id}")
	private ResponseEntity<?> logout(@PathVariable String id) throws Exception {
		service.deleteRefreshToken(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
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
