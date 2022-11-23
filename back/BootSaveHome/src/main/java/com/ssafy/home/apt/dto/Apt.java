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
@ApiModel(value = "Apt(아파트 축약 정보)", description = "아파트의 축약 정보를 가진 Domain Class")
public class Apt {
	@ApiModelProperty(value="아파트 코드")
	private String code;
	@ApiModelProperty(value="아파트 명")
	private String name;
	@ApiModelProperty(value="경도")
	private String lng;
	@ApiModelProperty(value="위도")
	private String lat;
	
}
