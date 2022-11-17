package com.ssafy.home.apt.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.apt.dto.Apt;
import com.ssafy.home.apt.dto.AptInfo;
import com.ssafy.home.apt.model.service.AptService;


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
	
}
