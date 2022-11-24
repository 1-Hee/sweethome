package com.ssafy.home.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.dto.MemberImageDTO;
import com.ssafy.home.member.dto.ProFileInfoDto;

@Mapper
public interface MemberMapper {
	Member select(String id) throws SQLException;

	int delete(String id) throws SQLException;

	int update(Member member) throws SQLException;

	int insert(Member member) throws SQLException;

	List<Member> selectAll() throws SQLException;

	void saveRefreshToken(Map<String, String> map);

	void deleteRefreshToken(Map<String, String> map);

	String getRefreshToken(String id);

	int insertProfileInfo(ProFileInfoDto proFileInfoDto) throws SQLException;

	MemberImageDTO selectByIdAndImage(String id) throws SQLException;

	Member selectByEmail(Object resu) throws SQLException;
}
