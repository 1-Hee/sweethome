package com.ssafy.home.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.FileInfoDto;
import com.ssafy.home.board.dto.Notice;


public interface NoticeService {
	int writeArticle(Notice notice) throws Exception;
	List<Notice> listArticle() throws Exception;
	Notice getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(Notice notice) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	List<Notice> getTopFourLike() throws Exception;
	int addFileInfo(FileInfoDto fileInfoDto) throws Exception;
	List<FileInfoDto> getFiles(int no) throws Exception;
}
