package com.st.test.dao;

import org.nutz.dao.Dao;

public interface DaoTest {

	void createTable(Dao dao);
	
	void insertObject(Dao dao,Object obj);
}
