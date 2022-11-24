package com.ssafy.home.apt.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.home.apt.dto.Apt;
import com.ssafy.home.apt.dto.AptData;
import com.ssafy.home.apt.dto.AptInfo;
import com.ssafy.home.apt.dto.LikeDto;
import com.ssafy.home.board.dto.Search;

public interface AptService {
	List<Apt> getList(String type, String code) throws Exception;

	List<AptInfo> getAptList(String fullcode) throws Exception;
	
	List<AptInfo> getAptListByLatLng(String lat, String lng) throws Exception;
	
	List<AptInfo> getAptListByDongName(String dong) throws Exception;
	
	List<AptInfo> getAptListByFullCode(int pageNum, int pageSize, String fullCode) throws Exception; // added 11/19
	
	List<AptInfo> getAptListByDong(int pageNum, int pageSize, String dongName) throws Exception; // added 11/19
	
	List<AptInfo> getAptListByAptName(int pageNum, int pageSize, String aptName) throws Exception; // added 11/20
	
//	List<AptInfo> getAptListByLatLng(int pageNum, int pageSize, String lat, String lng ) throws Exception; // added 11/24

	int addLike(LikeDto likedto) throws Exception;

	int getLikeCount(int no) throws Exception;

	List<AptInfo> getTopFourLike() throws Exception;

	int updateLike(int no) throws Exception;

	List<AptInfo> getLikeApt(String id) throws Exception;

	List<AptInfo> getTopFourPrice() throws Exception;

}
