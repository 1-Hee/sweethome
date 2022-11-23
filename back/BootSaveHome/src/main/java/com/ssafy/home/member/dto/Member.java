package com.ssafy.home.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "Member (유저 정보)", description = "유저의 정보를 가진 Domain Class")
public class Member {
	
	@ApiModelProperty(value="유저 ID")
	@NotBlank
	private String id;
	@ApiModelProperty(value="비밀번호")
	@NotBlank
	private String password;
	@ApiModelProperty(value="유저 명")
	@NotBlank
	private String name;
	@ApiModelProperty(value="나이")
	@NotNull
	private int age;
	@ApiModelProperty(value="이메일 명")
	@NotBlank
	private String email;
	@ApiModelProperty(value="이메일 도메인")
	@NotBlank
	private String domain;
	@ApiModelProperty(value="등급")
	@NotNull
	private int grade;
	@ApiModelProperty(value="가입날짜")
	@NotBlank
	private String date;
	@ApiModelProperty(value="Refresh 토큰")
	@NotBlank
	private String token;
	
	public Member(String id, String password, String name, int age, String email, String domain, int grade,
			String date) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.email = email;
		this.domain = domain;
		this.grade = grade;
		this.date = date;
	}
	
	
}
