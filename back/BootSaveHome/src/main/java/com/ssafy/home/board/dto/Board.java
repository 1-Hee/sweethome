package com.ssafy.home.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int articleNo;
	private String userId;
	private String userName;
	private String title;
	private String content;
	private int hit;
	private int commentNo;
	private String registDate;
	
}
