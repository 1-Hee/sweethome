package com.ssafy.home.apt.dto;

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
public class AptInfo {
	private int aptNo;
	private String dongCode;
	private String apartmentName;
	private String dealAmount;
	private String area;
	private String date;
	private String lng;
	private String lat;
	private String dong;
	private int lc;
}
