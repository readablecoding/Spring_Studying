package com.springlegacy.ex2.vo;

import lombok.Data;

@Data	//lombok을 통해 생성자, getter setter, toString을 생략함
public class Member {
	private String id;
	private String password;
	private int age;
	private String name;
}
