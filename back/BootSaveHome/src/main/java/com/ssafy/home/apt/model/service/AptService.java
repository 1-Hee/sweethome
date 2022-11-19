package com.ssafy.home.apt.model.service;

import java.util.List;

import com.ssafy.home.apt.dto.Apt;
import com.ssafy.home.apt.dto.AptData;
import com.ssafy.home.apt.dto.AptInfo;

public interface AptService {
	List<Apt> getList(String type, String code) throws Exception;

	List<AptInfo> getAptList(String fullcode) throws Exception;
	
	List<AptInfo> getAptListByLatLng(String lat, String lng) throws Exception;
	
	List<AptInfo> getAptListByDongName(String dong) throws Exception;
	
	List<AptData> getAptListByFullCode(String fullCode) throws Exception; // added 11/19
}
