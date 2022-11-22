package com.ssafy.home.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
	private int articleNo;
	private String userId;
	private String title;
	private String content;
	private int hit;
	private int commentNo;
	private String registDate;
}
