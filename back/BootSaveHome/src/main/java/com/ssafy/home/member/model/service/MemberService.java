package com.ssafy.home.member.model.service;

import java.util.List;

import com.ssafy.home.member.dto.Member;

public interface MemberService {
	Member selectById(String id) throws Exception;

	int deleteById(String id) throws Exception;

	int updateUser(Member member) throws Exception;

	int insertUser(Member member) throws Exception;

	List<Member> selectAll() throws Exception;
}
