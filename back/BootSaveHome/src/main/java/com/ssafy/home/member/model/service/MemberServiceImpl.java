package com.ssafy.home.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	
	@Autowired
	private MemberServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}
	

	@Override
	public Member selectById(String id) throws Exception {
		return mapper.select(id);
	}

	@Override
	public int deleteById(String id) throws Exception {
		return mapper.delete(id);
	}

	@Override
	public int updateUser(Member member) throws Exception {
		return mapper.update(member);
	}

	@Override
	public int insertUser(Member member) throws Exception {
		return mapper.insert(member);
	}

	@Override
	public List<Member> selectAll() throws Exception {
		return mapper.selectAll();
	}


}
