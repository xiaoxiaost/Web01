package com.st.test;

import java.io.File;

import org.nutz.filepool.FilePool;

import com.st.test.dao.util.IocCreater;

public class TestDoc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FilePool pool = IocCreater.createFilePool() ;
		File file = pool.returnFile(pool.current()-1, ".doc");
		System.out.println(file.getName());
//		File file2 = pool.createFile(".txt");
		System.out.println(pool.current());
	}

}
