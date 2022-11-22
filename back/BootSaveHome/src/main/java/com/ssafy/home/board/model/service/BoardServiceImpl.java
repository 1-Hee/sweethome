package com.ssafy.home.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.Search;
import com.ssafy.home.board.model.mapper.BoardMapper;
import com.ssafy.home.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper mapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public int writeArticle(Board board) throws Exception {
		return mapper.writeArticle(board);
	}

	@Override
	public List<Board> listArticle() throws Exception {
		return mapper.listArticle();
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

	@Override
	public List<Board> getTopFourLike() throws Exception {
		return mapper.selectTopFourLike();
	}

}
