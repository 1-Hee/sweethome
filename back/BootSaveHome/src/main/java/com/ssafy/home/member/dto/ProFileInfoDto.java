package com.ssafy.home.member.dto;

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
@ApiModel(value = "ProFileInfoDto (ProFile경로 정보)", description = "업로드 된 프로필 경로를 가진 Domain Class")
public class ProFileInfoDto {

	@ApiModelProperty(value="멤버 기본 키")
	private String id;
	@ApiModelProperty(value="저장된 폴더")
	private String saveFolder;
	@ApiModelProperty(value="기존 파일 명")
	private String originalFile;
	@ApiModelProperty(value="저장 시 파일 명")
	private String saveFile;

}