package com.ssafy.home.member.dto;

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
public class MemberImageDTO {
	private String id;
	private String password;
	private String name;
	private int age;
	private String  email;
	private String domain;
	private int grade;
	private String date;
	private String token;
	private String saveFolder;
	private String originalFile;
	private String saveFile;
}
