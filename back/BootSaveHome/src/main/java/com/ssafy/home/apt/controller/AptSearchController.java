package com.ssafy.home.apt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.apt.dto.Apt;
import com.ssafy.home.apt.dto.AptData;
import com.ssafy.home.apt.dto.AptInfo;
import com.ssafy.home.apt.dto.LikeDto;
import com.ssafy.home.apt.model.service.AptService;
import com.ssafy.home.board.dto.Search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/apt")
@Api("아파트 컨트롤러 API V1")
public class AptSearchController{	
	
	private final AptService aptService;
	
	@Autowired
	public AptSearchController(AptService aptService) {
		this.aptService = aptService;		
	}
	
	@ApiOperation(value="좋아요 역순 상위4개 아파트 조회", notes="apt_table의 lc 역순으로 상위 4개 아파트를 반환합니다.")
	@ApiResponses({
		@ApiResponse(code=200,message="조회 성공!"), @ApiResponse(code=404,message="페이지 없음!"), @ApiResponse(code=500,message="서버 에러!")
	})
	@GetMapping("/index/recommend/like") 
	public ResponseEntity<?> getTopFourLike() {
		try {
			List<AptInfo> aptList = aptService.getTopFourLike();
			return new ResponseEntity<List<AptInfo>>(aptList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="가격 순 상위4개 아파트 조회", notes="apt_table의 dealAmount 순으로 상위 4개 아파트를 반환합니다.")
	@GetMapping("/index/recommend/price") 
	public ResponseEntity<?> getTopFourPrice() {
		try {
			List<AptInfo> aptList = aptService.getTopFourPrice();
			return new ResponseEntity<List<AptInfo>>(aptList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="type, code 별 아파트 목록", notes="type과 code를 넘겨받아 해당 아파트 목록을 반환합니다.")
	@GetMapping("/box")
	public ResponseEntity<?> box(HttpServletRequest request, String type, String code) {
		try {
			System.out.println(type+" "+code);
			List<Apt> aptList = aptService.getList(type, code);
			if(!aptList.isEmpty()) {
				return new ResponseEntity<List<Apt>>(aptList, HttpStatus.OK);
			}else return new ResponseEntity(HttpStatus.NO_CONTENT);
 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}		
	
	@ApiOperation(value="code 별 아파트 목록", notes="code를 넘겨받아 해당 아파트 목록을 반환합니다.")
	@GetMapping("/apt-list")
	public ResponseEntity<?> aptList(String code){ 
		try {
			List<AptInfo> aptInfoList = aptService.getAptList(code);
			if(aptInfoList != null && !aptInfoList.isEmpty()) {
				return new ResponseEntity<List>(aptInfoList, HttpStatus.OK);
			}else return new ResponseEntity(HttpStatus.NO_CONTENT);
			

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation(value="lat, lng 별 아파트 목록", notes="lat, lng를 넘겨받아 해당 아파트 목록을 반환합니다.")
	@GetMapping("/around")
	public ResponseEntity<?> aroundList(String lat, String lng) {
		System.out.println(lat + " " + lng);
		try {
			List<AptInfo> aptInfoList = aptService.getAptListByLatLng(lat, lng);
			if(aptInfoList != null && !aptInfoList.isEmpty()) {
				return new ResponseEntity<List>(aptInfoList, HttpStatus.OK);
			}else return new ResponseEntity(HttpStatus.NO_CONTENT);
			

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@ApiOperation(value="dong 별 아파트 목록", notes="dong을 넘겨받아 해당 아파트 목록을 반환합니다.")
	@GetMapping("/search")
	public ResponseEntity<?> searchList(String dong) {
		System.out.println(dong);
		try {
			List<AptInfo> aptInfoList = aptService.getAptListByDongName(dong);
			if(aptInfoList != null && !aptInfoList.isEmpty()) {
				return new ResponseEntity<List>(aptInfoList, HttpStatus.OK);
			}else return new ResponseEntity(HttpStatus.NO_CONTENT);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	// created 11/19 , 아파트 정보를 db로부터 가져오는 메서드 추가!. com.ssafy.home.apt.dto 패키지에 AptData도 만듦.
	@ApiOperation(value="fullCode 별 아파트 목록", notes="fullCode를 넘겨받아 해당 아파트 목록을 반환합니다.")
	@GetMapping("/search/apt-list")
	public ResponseEntity<?> getAptListByDongCode(Search search, String fullCode) {		
		try {
//			System.out.println(search.toString()+" "+fullCode);			
			List<AptInfo> aptDataList = aptService.getAptListByFullCode(search.getPageNo(), search.getListSize(), fullCode);
//			System.out.println(aptDataList.toString());			
			if(aptDataList != null && !aptDataList.isEmpty()) {
				return new ResponseEntity<List<AptInfo>>(aptDataList, HttpStatus.OK);
			}else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	// created 11/19 , 아파트 정보를 동 이름으로부터 가져오는 메서드 추가!
	@ApiOperation(value="dongName 별 아파트 목록", notes="dongName을 넘겨받아 해당 아파트 목록을 반환합니다.")
	@GetMapping("/search/dong")
	public ResponseEntity<?> getAptListByDong(Search search, String dongName) {		
		try {
//			System.out.println(search.toString()+" "+fullCode);		
			int pageNum = search.getPageNo(); 
			int pageSize = search.getListSize();
			List<AptInfo> aptDataList = aptService.getAptListByDong(pageNum, pageSize, dongName);
//			System.out.println(aptDataList.toString());			
			if(aptDataList != null && !aptDataList.isEmpty()) {
				// System.out.println(aptDataList.toString());
				return new ResponseEntity<List<AptInfo>>(aptDataList, HttpStatus.OK);
			}else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	// created 11/21 , 아파트 정보를 동 아파트 이름으로부터 가져오는 메서드 추가!
	@ApiOperation(value="aptName 별 아파트 목록", notes="aptName을 넘겨받아 해당 아파트 목록을 반환합니다.")
	@GetMapping("/search/name")
	public ResponseEntity<?> getAptListPos(Search search, String aptName) {		
		try {
			System.out.println(search.toString()+" "+aptName);		
			int pageNum = search.getPageNo(); 
			int pageSize = search.getListSize();
			List<AptInfo> aptDataList = aptService.getAptListByAptName(pageNum, pageSize, aptName);
			if(aptDataList != null && !aptDataList.isEmpty()) {
				return new ResponseEntity<List<AptInfo>>(aptDataList, HttpStatus.OK);
			}else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	// /search/name
	@ApiOperation(value="UserId 별 좋아하는 아파트 목록", notes="UserId를 넘겨받아 좋아요를 누른 아파트 목록을 반환합니다.")
	@GetMapping("/like/{id}")
	public ResponseEntity<?> getLikeApt(@PathVariable String id) {
		try {
			List<AptInfo> list = aptService.getLikeApt(id);
			return new ResponseEntity<List<AptInfo>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@ApiOperation(value="좋아요 버튼 동작", notes="UserId와 AptNo을 넘겨받아 apt_like table에 좋아요를 누른 사람과 해당 아파트를 등록합니다.")
	@PostMapping("/like")
	public ResponseEntity<?> giveLike(@RequestBody LikeDto likedto) {
		try {
			aptService.addLike(likedto);
			aptService.updateLike(likedto.getAptNo());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
//	@GetMapping("/like/{no}")
//	public ResponseEntity<?> getLikeCount(@PathVariable int no) {
//		try {
//			int cnt = aptService.getLikeCount(no);
//			return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}	
//	}
	
	
}
