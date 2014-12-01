package com.st.test.dao.util;

import org.nutz.dao.Dao;
import org.nutz.filepool.FilePool;
import org.nutz.filepool.NutFilePool;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

public class IocCreater {

	private static IocCreater iocCreater  = null;
	private static Ioc ioc ;
	
	private IocCreater(){
		if(ioc == null){
			ioc = new NutIoc(new JsonLoader("ioc.js"));
		}
	}
	
	private static IocCreater getIocCreater(){
		if(iocCreater == null){
			iocCreater = new IocCreater();
		}
		return iocCreater ;
	}
	
	public static Dao createDao(){
		if(iocCreater == null){
			iocCreater = IocCreater.getIocCreater();
		}
		return iocCreater.ioc.get(Dao.class);
	}
	
	public static FilePool createFilePool(){
		if(iocCreater == null){
			iocCreater = IocCreater.getIocCreater();
		}
		return  iocCreater.ioc.get(NutFilePool.class);
		
	}
}
