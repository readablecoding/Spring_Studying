package com.springlegacy.ex1.vo;

import java.util.Arrays;

public class TestData {
	private String str;
	private String password;
	private String[] hobby;
	
	public TestData() {
		super();
	}

	public TestData(String str, String password, String[] hobby) {
		super();
		this.str = str;
		this.password = password;
		this.hobby = hobby;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "TestData [str=" + str + ", password=" + password + ", hobby=" + Arrays.toString(hobby) + "]";
	}
	
}
