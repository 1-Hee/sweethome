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
@ApiModel(value = "AptData (아파트 정보)", description = "아파트의 정보를 가진 Domain Class")
public class AptData { // added 11/19
	@ApiModelProperty(value="아파트 명")
	String apartmentName; 
	@ApiModelProperty(value="거래 가격")
	String dealAmount;
	@ApiModelProperty(value="면적")
	String area;
	@ApiModelProperty(value="거래 날짜")
	String date;
	@ApiModelProperty(value="동")
	String dong;
	@ApiModelProperty(value="경도")
	String lng;
	@ApiModelProperty(value="위도")
	String lat;

}
