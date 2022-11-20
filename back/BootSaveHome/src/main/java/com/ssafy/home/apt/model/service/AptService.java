package com.ssafy.home.apt.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.home.apt.dto.Apt;
import com.ssafy.home.apt.dto.AptData;
import com.ssafy.home.apt.dto.AptInfo;
import com.ssafy.home.board.dto.Search;

public interface AptService {
	List<Apt> getList(String type, String code) throws Exception;

	List<AptInfo> getAptList(String fullcode) throws Exception;
	
	List<AptInfo> getAptListByLatLng(String lat, String lng) throws Exception;
	
	List<AptInfo> getAptListByDongName(String dong) throws Exception;
	
	List<AptData> getAptListByFullCode(int pageNum, int pageSize, String fullCode) throws Exception; // added 11/19
	
	List<AptData> getAptListByDong(int pageNum, int pageSize, String dongName) throws Exception; // added 11/19
	
	List<AptData> getAptListByPos(int pageNum, int pageSize, String lat, String lng) throws Exception; // added 11/20
}
