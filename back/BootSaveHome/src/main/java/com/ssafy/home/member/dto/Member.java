package com.ssafy.home.member.dto;

public class Member {
	
	private String id;
	private String password;
	private String name;
	private int age;
	private String email;
	private String domain;
	private int grade;
	private String date;
	
	public Member() {}

	
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", age=" + age + ", email=" + email
				+ ", domain=" + domain + ", grade=" + grade + ", date=" + date + "]";
	}

	
	
}
