package com.ssafy.home.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.Search;

@Mapper
public interface BoardMapper {
	int writeArticle(Board board) throws SQLException;
//	int adminWriteArticle(String userId) throws SQLException;
	List<Board> listArticle() throws SQLException;
	Board getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(Board board) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
	List<Board> selectTopFourLike() throws SQLException;
	
}
