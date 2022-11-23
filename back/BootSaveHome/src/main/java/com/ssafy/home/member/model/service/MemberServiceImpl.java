package com.ssafy.home.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.dto.MemberImageDTO;
import com.ssafy.home.member.dto.ProFileInfoDto;
import com.ssafy.home.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper mapper) {
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


	@Override
	public void saveRefreshToken(String id, String refreshToken) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		mapper.saveRefreshToken(map);
	}


	@Override
	public void deleteRefreshToken(String id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("token", null);
		mapper.deleteRefreshToken(map);
	}


	@Override
	public String getRefreshToken(String id) throws Exception {
		return mapper.getRefreshToken(id);
	}


	@Override
	public int addProfileInfo(ProFileInfoDto proFileInfoDto) throws Exception {
		return mapper.insertProfileInfo(proFileInfoDto);
	}


	@Override
	public MemberImageDTO selectByIdAndImage(String id) throws Exception {
		return mapper.selectByIdAndImage(id);
	}

	
}
