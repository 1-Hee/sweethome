package com.ssafy.home.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Board (자유게시판 정보)", description = "자유게시판의 정보를 가진 Domain Class")
public class Board {
	@ApiModelProperty(value="자유게시판 기본 키")
	private int articleNo;
	@ApiModelProperty(value="유저 ID")
	private String userId;
	@ApiModelProperty(value="제목")
	private String title;
	@ApiModelProperty(value="내용")
	private String content;
	@ApiModelProperty(value="조회 수")
	private int hit;
	@ApiModelProperty(value="답글 수")
	private int commentNo;
	@ApiModelProperty(value="등록 날짜")
	private String registDate;
}
