package com.ssafy.home.board.dto;

public class Search {
	private int pageNum;
	private int begin;
	private int pageSize;
	
	public Search() {
		this(1, 10);
	}
	public Search(int pageNo) { 
		this(pageNo, 10);
	}
	public Search(int pageNo, int listSize) { 
		this.pageNum = pageNo; 
		this.pageSize = listSize; 
	}

	public int getPageNo() {
		return pageNum;
	}
	public void setPageNo(int pageNo) {
		this.pageNum = pageNo;
	}
	
	public int getBegin() {
		return (pageNum - 1) * 10;
	}
	
	public int getListSize() {
		return pageSize;
	}
	public void setListSize(int listSize) {
		this.pageSize = listSize;
	}
}