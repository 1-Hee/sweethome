package com.ssafy.home.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileInfoDto {

	private int articleNo;
	private String saveFolder;
	private String originalFile;
	private String saveFile;

}
