package com.st.vo;

import org.nutz.dao.entity.annotation.*;

@Table("t_company")
public class Company {
	@Id
	private int id ;
	@Name(casesensitive=false)  // 忽略大小写
	private String name ;
	@Column
	private String address;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
