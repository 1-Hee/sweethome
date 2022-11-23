package com.ssafy.home.board.dto;

import java.util.List;

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
@ApiModel(value = "Notice (공지사항 정보)", description = "공지사항의 정보를 가진 Domain Class")
public class Notice {
	@ApiModelProperty(value="공지사항 기본 키")
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
