package com.rocky.sudoku.model;

public class User {
	private int userId;
	private String userName;
	private String userAge;
	private String userGender;
	private String userDate;

	public User() {
	}

	public User(int userId, String userName, String userAge, String userGender, String userDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userDate = userDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserDate() {
		return userDate;
	}

	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

}
