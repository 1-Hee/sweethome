package com.ssafy.home.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {
	
	private String id;
	private String password;
	private String name;
	private int age;
	private String email;
	private String domain;
	private int grade;
	private String date;
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
