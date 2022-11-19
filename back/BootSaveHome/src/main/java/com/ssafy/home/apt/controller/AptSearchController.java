package com.ssafy.home.apt.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ssafy.home.apt.dto.Apt;
import com.ssafy.home.apt.dto.AptData;
import com.ssafy.home.apt.dto.AptInfo;
import com.ssafy.home.apt.model.service.AptService;
import com.ssafy.home.board.dto.Search;


@RestController
@RequestMapping("/apt")
public class AptSearchController{	
	
	private final AptService aptService;
	
	@Autowired
	public AptSearchController(AptService aptService) {
		this.aptService = aptService;		
	}
	
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
	@GetMapping("/search/apt-list")
	public ResponseEntity<?> getAptListByDongCode(Search search, String fullCode) {		
		try {
//			System.out.println(search.toString()+" "+fullCode);			
			List<AptData> aptDataList = aptService.getAptListByFullCode(search.getPageNo(), search.getListSize(), fullCode);
//			System.out.println(aptDataList.toString());			
			if(aptDataList != null && !aptDataList.isEmpty()) {
				return new ResponseEntity<List<AptData>>(aptDataList, HttpStatus.OK);
			}else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	// created 11/19 , 아파트 정보를 동 이름으로부터 가져오는 메서드 추가!
	@GetMapping("/search/dong")
	public ResponseEntity<?> getAptListByDong(Search search, String dongName) {		
		try {
//			System.out.println(search.toString()+" "+fullCode);		
			int pageNum = search.getPageNo(); 
			int pageSize = search.getListSize();
			List<AptData> aptDataList = aptService.getAptListByDong(pageNum, pageSize, dongName);
//			System.out.println(aptDataList.toString());			
			if(aptDataList != null && !aptDataList.isEmpty()) {
				System.out.println(aptDataList.toString());
				return new ResponseEntity<List<AptData>>(aptDataList, HttpStatus.OK);
			}else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
}
