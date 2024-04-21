package com.jmv.studentManagement.model;

public class RegisterDto {
    private String name;
    private String username;
    private String password;
    private Role role;
	public RegisterDto() {
		super();
	}
	public RegisterDto(String name, String username, String password, Role role) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}