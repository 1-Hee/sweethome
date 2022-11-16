package com.ssafy.home.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.model.mapper.BoardMapper;
import com.ssafy.home.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper mapper;
	
	@Autowired
	private BoardServiceImpl(BoardMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public int writeArticle(Board board) throws Exception {
		return mapper.writeArticle(board);
	}

	@Override
	public List<Board> listArticle() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int pgno = 1;
		int spl = SizeConstant.SIZE_PER_LIST;
		int start = (pgno - 1) * spl;
		map.put("start", start);
		map.put("spl", spl);
		return mapper.listArticle(map);
	}

	@Override
	public Board getArticle(int articleNo) throws Exception {
		return mapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		mapper.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(Board board) throws Exception {
		mapper.modifyArticle(board);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		mapper.deleteArticle(articleNo);
	}

}
