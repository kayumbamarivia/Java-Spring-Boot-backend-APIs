package com.jmv.studentManagement.controller;

public class SearchRequest {
    private Long userId;
    private String searchTerm;
	public SearchRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchRequest(Long userId, String searchTerm) {
		super();
		this.userId = userId;
		this.searchTerm = searchTerm;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
    
}
