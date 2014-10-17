package com.st.vo;

import org.nutz.dao.entity.annotation.*;

@Table("t_person")
public class Person {
	@Id
	private int id ;
	@Name
	private String name ;
	@Column
	private int age ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}
