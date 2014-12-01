package com.st.test;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Lang;
import org.nutz.lang.random.Random;
import org.nutz.lang.random.RecurArrayRandom;
import org.nutz.lang.random.StringGenerator;

import com.st.vo.Person;

public class TestJson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person();
		p.setName("zhangsan");
		p.setAge(24);
		String str = Json.toJson(p);
		String ss = Json.toJson(p, JsonFormat.compact());
		System.out.println(str);
		System.out.println(ss);
		
	}

}
