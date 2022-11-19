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
public class AptData { // added 11/19
	String apartmentName; 
	int dealAmount;
	int area;
	String date;
	String dong;

}
