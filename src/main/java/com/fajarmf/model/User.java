package com.fajarmf.model;

public class User {	
	private String password;
	private Integer usertype;
	private Integer userid;
	private static Integer userCounter = 100;
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getUserType() {
		return this.usertype;
	}
	
	public void setUserType(Integer userType) {
		this.usertype = userType;
	}
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String username;
	
	public User(Integer userid, String username, Integer usertype) {		
		
		this.userid = userid;
		this.username = username;
		this.usertype = usertype;
	}
	
	public User(){
		
	}
	
	public User(Integer userid, Integer usertype) {
		this.userid = userid;
		this.usertype = usertype;
	}
	
	public User(String username, Integer usertype) {
		userCounter++;
		
		this.userid = userCounter;
		this.username = username;
		this.usertype = usertype;
	}
	
	public User(String username, String password, Integer usertype) {
		userCounter++;
		
		this.userid = userCounter;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}
	@Override
	public String toString() {
		return "User >>> [userid=" + userid + ", username=" + username + "]";
	}
}