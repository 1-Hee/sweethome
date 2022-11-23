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
@ApiModel(value = "AptInfo (아파트 상세 정보)", description = "아파트의 상세 정보를 가진 Domain Class")
public class AptInfo {
	
	@ApiModelProperty(value="아파트 기본키")
	private int aptNo;
	@ApiModelProperty(value="동 코드")
	private String dongCode;
	@ApiModelProperty(value="아파트 명")
	private String apartmentName;
	@ApiModelProperty(value="거래 가격")
	private String dealAmount;
	@ApiModelProperty(value="면적")
	private String area;
	@ApiModelProperty(value="거래 날짜")
	private String date;
	@ApiModelProperty(value="경도")
	private String lng;
	@ApiModelProperty(value="위도")
	private String lat;
	@ApiModelProperty(value="동")
	private String address;
	@ApiModelProperty(value="좋아요 수")
	private int lc;
}
