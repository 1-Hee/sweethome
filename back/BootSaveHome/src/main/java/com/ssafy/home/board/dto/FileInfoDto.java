package com.ssafy.home.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "FileInfoDto (File경로 정보)", description = "업로드 된 파일 경로를 가진 Domain Class")
public class FileInfoDto {

	@ApiModelProperty(value="공지사항 기본 키")
	private int articleNo;
	@ApiModelProperty(value="저장된 폴더")
	private String saveFolder;
	@ApiModelProperty(value="기존 파일 명")
	private String originalFile;
	@ApiModelProperty(value="저장 시 파일 명")
	private String saveFile;

}
