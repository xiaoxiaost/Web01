package com.st.test;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.st.test.dao.DaoTest;
import com.st.test.dao.impl.DaoTestImpl;
import com.st.vo.Company;
import com.st.vo.Employee;
import com.st.vo.Person;

public class Test {

	public static void main(String[] args) {

		DaoTest dt = new DaoTestImpl() ;
		Ioc ioc = new NutIoc(new JsonLoader("dao.js"));
		DataSource ds = ioc.get(DataSource.class);
		
		Dao dao = new NutDao(ds); 

	// 	dt.createTable(dao);   //创建表
	//TODO 插入数据
//		Person p = new Person();
//		p.setName("DEF");
//		p.setAge(20);
//		dt.insertObject(dao, p);
//		
//		Company com = new Company();
//		com.setName("boanda");
//		com.setAddress("深圳市");
//		dt.insertObject(dao, com);
//		
//		Employee emp = new Employee() ;
//		emp.setName("suntao");
//		emp.setAge(24);
//		dt.insertObject(dao, emp);
		//TODO 查询数据
		// 通过主键@Id和@Name字段进行检索
		Person pp = dao.fetch(Person.class, 1);
		System.out.println(pp.getName());
		
		//TODO 更新数据
//		pp.setAge(23);
//		dao.update(pp);
		
		
		// TODO 删除数据
//		dao.delete(Person.class, 1);
		
	}

}
