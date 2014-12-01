package com.st.util.database.constant;

/**
 * 
 * 
 * @author pangwu86@gmail.com
 * 
 */
public final class DbConstants {

	// 数据库类型
	public final static String ORACLE_TYPE = "oracle";
	public final static String MYSQL_TYPE = "mysql";
	public final static String POSTGRESQL_TYPE = "postgresql";
	public final static String DB2_TYPE = "db2";
	public final static String SQLSERVER_TYPE = "sqlserver";
	public final static String SQLITE_TYPE = "sqlite";

	// For Oracle
	public final static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public final static String ORACLE_URL = "jdbc:oracle:thin:@";

	// For MySQL
	public final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public final static String MYSQL_URL = "jdbc:mysql://";

	// For PostgreSQL
	public final static String POSTGRESQL_DRIVER = "";

	// For DB2
	public final static String DB2_DRIVER = "";

	// For SqlServer
	public final static String SQLSERVER_DRIVER = "";

	// ********************* 日期类型
	public final static String ORACLE_DATE_FORMAT = "yyyy-mm-dd";
	// public final static String ORACLE_DATE_FORMAT = "yyyy-mm-dd HH:MM:SS";

}
