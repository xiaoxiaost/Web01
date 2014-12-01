package com.st.util.database;

/**
 * 数据库配置。
 * 
 * @author pangwu86@gmail.com
 * 
 */
public class DSConfig {
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 端口号
	 */
	private String port;
	/**
	 * 实例名称（或数据库名称）
	 */
	private String instance;
	/**
	 * 参数字符串（参数名1=参数值1&参数名2=参数值2）
	 */
	private String params;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 数据库类型
	 */
	private String dbType;
	/**
	 * 已经配置好的数据源（例如tomcat中的数据源）
	 */
	private String dataSource;
	/**
	 * 数据库连接池类型
	 */
	private String dbPool;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDbPool() {
		return dbPool;
	}

	public void setDbPool(String dbPool) {
		this.dbPool = dbPool;
	}
}
