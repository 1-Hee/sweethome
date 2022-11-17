package com.ssafy.home.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.board.dto.Notice;
import com.ssafy.home.board.model.mapper.NoticeMapper;
import com.ssafy.home.util.SizeConstant;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	private NoticeMapper mapper;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public int writeArticle(Notice notice) throws Exception {
		return mapper.writeArticle(notice);
	}

//	@Override
//	public List<Board> listArticle(Map<String, String> map) throws Exception {
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int spl = SizeConstant.SIZE_PER_LIST;
//		int start = (pgno - 1) * spl;
//		map.put("start", start + "");
//		map.put("spl", spl + "");
//		return noticeDAO.listArticle(map);
//	}
	
	@Override
	public List<Notice> listArticle() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int pgno = 1;
		int spl = SizeConstant.SIZE_PER_LIST;
		int start = (pgno - 1) * spl;
		map.put("start", start);
		map.put("spl", spl);
		return mapper.listArticle(map);
	}

	@Override
	public Notice getArticle(int articleNo) throws Exception {
		return mapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		mapper.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(Notice notice) throws Exception {
		mapper.modifyArticle(notice);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		mapper.deleteArticle(articleNo);
	}

}
