package com.ssafy.home.member.model.service;

import java.util.List;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.dto.MemberImageDTO;
import com.ssafy.home.member.dto.ProFileInfoDto;

public interface MemberService {
	Member selectById(String id) throws Exception;

	int deleteById(String id) throws Exception;

	int updateUser(Member member) throws Exception;

	int insertUser(Member member) throws Exception;

	List<Member> selectAll() throws Exception;

	void saveRefreshToken(String id, String refreshToken) throws Exception;

	void deleteRefreshToken(String id) throws Exception;

	String getRefreshToken(String id) throws Exception;

	int addProfileInfo(ProFileInfoDto proFileInfoDto) throws Exception;

	MemberImageDTO selectByIdAndImage(String id) throws Exception;
}
