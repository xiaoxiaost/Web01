package com.st.test.dao.impl;

import org.nutz.dao.Dao;

import com.st.test.dao.DaoTest;
import com.st.vo.Company;
import com.st.vo.Employee;
import com.st.vo.Person;

public class DaoTestImpl implements DaoTest {

	
	@Override
	public void createTable(Dao dao) {
		dao.create(Person.class, true);   // 创建表
		dao.create(Company.class, true);
		dao.create(Employee.class, true);
		
	}

	@Override
	public void insertObject(Dao dao, Object obj) {
		dao.insert(obj);
		
	}

}
