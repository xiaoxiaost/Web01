package com.st.test;

import java.util.List;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.st.test.dao.DaoTest;
import com.st.test.dao.impl.DaoTestImpl;
import com.st.test.dao.util.IocCreater;
import com.st.vo.Company;
import com.st.vo.Employee;
import com.st.vo.Person;

public class Test {

	public static void main(String[] args) {

//		DaoTest dt = new DaoTestImpl() ;
//		Ioc ioc = new NutIoc(new JsonLoader("dao.js"));
//		DataSource ds = ioc.get(DataSource.class);
		
		Dao dao = IocCreater.createDao(); 
		
		Sql sql = Sqls.create("SELECT * FROM t_person ");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(Record.class));
		dao.execute(sql) ;
		
		List<Record> list = sql.getList(Record.class) ;
		System.out.println(list.get(0).getString("name"));
	//	Dao dao = new NutDao(datasource,new FileSqlManager("demo/sqls/all.sqls"));
	//	System.out.println(dao.sqls().count());
	//	Dao dao = new NutDao(ds,new FileSqlManager("com/st/sql/new.sql"));
	//	System.out.println(dao.sqls().get("select_sql_1"));
	}

}
