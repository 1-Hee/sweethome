package com.ssafy.home.member.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.home.exception.NoArgsException;
import com.ssafy.home.exception.NoIDException;
import com.ssafy.home.exception.PasswordException;
import com.ssafy.home.jwt.model.service.JwtServiceImpl;
import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.dto.MemberImageDTO;
import com.ssafy.home.member.dto.MemberLoginDTO;
import com.ssafy.home.member.dto.MemberSignUpDTO;
import com.ssafy.home.member.dto.ProFileInfoDto;
import com.ssafy.home.member.model.service.MemberService;
import com.ssafy.home.util.VerifyEmail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/member")
@Api("멤버 컨트롤러 API V1")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private JwtServiceImpl jwtService;
	@Autowired
	private MemberService service;
	@Autowired
	private VerifyEmail verifyemail;
	
	@ApiOperation(value="로그인", notes="아이디와 패스워드를 통해 로그인합니다. 결과로 JWT토큰을 얻습니다.")
	@ApiResponses({
		@ApiResponse(code=200,message="조회 성공!"), @ApiResponse(code=404,message="페이지 없음!"), @ApiResponse(code=500,message="서버 에러!")
	})
	@PostMapping("login")
	private ResponseEntity<?> login(@RequestBody @Validated MemberLoginDTO member, BindingResult bindingResult, HttpServletRequest request) throws Exception {
		System.out.println(bindingResult);
		if(bindingResult.hasErrors()) {
			throw new NoArgsException("입력값이 올바르지 않습니다.");
		}
		System.out.println(request.getRequestURL());
		Map<String, Object> resultMap = new HashMap<>();
		String id = member.getId();
		String password = member.getPassword();
		MemberImageDTO member2 = service.selectByIdAndImage(id);
		if(member2==null) throw new NoIDException("아이디가 존재하지 않습니다.");
		else if(!member2.getPassword().equals(password)) throw new PasswordException("비밀번호가 맞지 않습니다.");
		String accessToken = jwtService.createAccessToken("userid", member2.getId());// key, data
		String refreshToken = jwtService.createRefreshToken("userid", member2.getId());// key, data
		service.saveRefreshToken(member.getId(),refreshToken);
		resultMap.put("member", member2);
		resultMap.put("access-token", accessToken);
		resultMap.put("refresh-token", refreshToken);
		resultMap.put("message", SUCCESS);
		return new ResponseEntity<Map<String, Object>>(resultMap,HttpStatus.OK);
	}
	
	// 엑세스 토큰으로 사용자 정보 재검증
	@ApiOperation(value="Access 토큰 검증", notes="Access 토큰을 통해 검증 작업을 합니다.")
	@GetMapping("/info/access/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String userId, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			Member member = service.selectById(userId);
			resultMap.put("userInfo", member);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	// 리프레시 토큰으로 사용자 정보 검증
	@ApiOperation(value="Access 토큰 초기화", notes="Access 토큰의 갱신을 위해 DB의 리프레시 토큰 정보를 활용합니다.")
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
	
	
	@ApiOperation(value="로그아웃", notes="로그아웃 합니다.")
	@GetMapping("logout/{id}")
	private ResponseEntity<?> logout(@PathVariable String id) throws Exception {
		service.deleteRefreshToken(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value="비밀번호 찾기", notes="이메일을 통해 비밀번호를 찾습니다.")
	@PostMapping("find")
	private ResponseEntity<?> find(@RequestBody String email) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		String[] str = email.split("@");
		resultMap.put("email",str[0]);
		resultMap.put("domain",str[1]);
		Member member = service.selectByEmail(resultMap);
		if(member==null) throw new NoIDException("아이디가 존재하지 않습니다.");
		verifyemail.sendEmail(member);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="내 정보 조회", notes="내 정보를 조회합니다.")
	@GetMapping("info/{id}")
	private ResponseEntity<?> info(@PathVariable String id) throws Exception {
		Member member = service.selectById(id);
		return new ResponseEntity<Member>(member,HttpStatus.OK);
	}
	
	@ApiOperation(value="비밀번호, 이메일 변경", notes="비밀번호 또는 이메일을 변경합니다.")
	@PutMapping(value = "update", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	private ResponseEntity<?> update(@RequestPart Member member, @RequestPart MultipartFile file) throws Exception {
		Member member2 = service.selectById(member.getId());
		String password = member.getPassword();
		String fullEmail = member.getEmail()+"@"+member.getDomain();
		if(password==null&&fullEmail==null) throw new NoArgsException("입력값이 올바르지 않습니다.");
		if(password!=null) {
			member2.setPassword(password);
		} 
		if(fullEmail!=null) {
			String[] str = fullEmail.split("@");
			System.out.println(str[0]);
			System.out.println(str[1]);
			member2.setEmail(str[0]);
			member2.setDomain(str[1]);
		}
		service.updateUser(member);
		if(!file.isEmpty()) {
//			String realPath = servletContext.getRealPath("/upload");
			String realPath = "C:/board/upload/imageUpload";
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = realPath + File.separator + today;
			File folder = new File(saveFolder);
			if(!folder.exists()) folder.mkdirs();
			ProFileInfoDto proFileInfoDto = new ProFileInfoDto();
			String originalFilename = file.getOriginalFilename();
			if(!originalFilename.isEmpty()) {
				String saveFileName = Long.toString(System.nanoTime())
						+ originalFilename.substring(originalFilename.lastIndexOf('.'));
				proFileInfoDto.setId(member.getId());
				proFileInfoDto.setSaveFolder(today);
				proFileInfoDto.setOriginalFile(originalFilename);
				proFileInfoDto.setSaveFile(saveFileName);
				file.transferTo(new File(folder, saveFileName));
			}
			service.addProfileInfo(proFileInfoDto);
			return new ResponseEntity<ProFileInfoDto>(proFileInfoDto, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@ApiOperation(value="계정 삭제", notes="계정을 삭제합니다.")
	@DeleteMapping("delete/{id}")
	private ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value="회원가입", notes="회원가입 합니다.")
	@PostMapping("signup")
	private ResponseEntity<?> signup(@RequestBody @Validated MemberSignUpDTO member, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			throw new NoArgsException("입력값이 올바르지 않습니다.");
		}
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
	
	@ApiOperation(value="전체 멤버 목록", notes="전체 멤버 목록을 반환합니다.")
	@GetMapping("list")
	private ResponseEntity<?> list() throws Exception{
		List<Member> list = service.selectAll();
		return new ResponseEntity<List<Member>>(list,HttpStatus.OK);
	}
	
	
}
