package com.ssafy.home.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.Notice;

@Mapper
public interface NoticeMapper {
	int writeArticle(Notice notice) throws SQLException;
	List<Notice> listArticle(Map<String, Object> map) throws SQLException;
	Notice getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modifyArticle(Notice notice) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
	
}
