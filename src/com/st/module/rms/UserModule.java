package com.st.module.rms;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

public class UserModule {

	@At("/showUser")
	@Ok("jsp:jsp.hello")
	public String showUser(){
		
		return "我是用户模块" ;
	}
}
