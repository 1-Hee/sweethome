package com.ssafy.home.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.Search;


public interface BoardService {
	int writeArticle(Board board) throws Exception;
	List<Board> listArticle() throws Exception;
	Board getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(Board board) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	List<Board> getTopFourLike() throws Exception;
}
