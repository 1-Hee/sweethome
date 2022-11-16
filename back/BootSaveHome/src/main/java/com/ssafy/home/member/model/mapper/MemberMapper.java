package com.ssafy.home.member.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.member.dto.Member;

@Mapper
public interface MemberMapper {
	Member select(String id) throws SQLException;

	int delete(String id) throws SQLException;

	int update(Member member) throws SQLException;

	int insert(Member member) throws SQLException;

	List<Member> selectAll() throws SQLException;
}
