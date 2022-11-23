package com.ssafy.home.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class MemberSignUpDTO {
	@NotBlank
	private String id;
	@NotBlank
	private String password;
	@NotBlank
	private String name;
	@NotNull
	private int age;
	@NotBlank
	private String email;
	@NotBlank
	private String domain;
}
