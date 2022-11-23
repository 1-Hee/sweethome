package com.ssafy.home.apt.dto;

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
@ApiModel(value = "LikeDto (좋아요 정보)", description = "좋아요 정보를 가진 Domain Class")
public class LikeDto {
	@ApiModelProperty(value="유저 ID")
	private String userId;
	@ApiModelProperty(value="아파트 기본키")
	private int aptNo;
}
