package com.ssafy.home.apt.dto;


public class AptInfo {
	
	private String apartmentName;
	private String dealAmount;
	private String area;
	private String date;
	private String lng;
	private String lat;
	private String dong;
	
	public AptInfo() {
		super();
	}

	public AptInfo(String apartmentName, String dealAmount, String area, String date, String lng, String lat,
			String dong) {
		super();
		this.apartmentName = apartmentName;
		this.dealAmount = dealAmount;
		this.area = area;
		this.date = date;
		this.lng = lng;
		this.lat = lat;
		this.dong = dong;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}
	
	
}
