package com.st.util.database;

import java.util.Properties;

import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.st.util.database.constant.DbConstants;

/**
 * 数据源工具类。
 * 
 * @author pangwu86@gmail.com
 * 
 */
public abstract class DSUtil {

	private static Log logger = Logs.getLog(DSUtil.class);

	// 默认配置信息
	private static String defaultDBConfigFile = "dbconfig.properties";

	private static DSConfig defaultDBConfig = null;

	/**
	 * 获得默认数据源的配置信息。
	 * 
	 * @return
	 */
	public static DSConfig getDefaultDSConfig() {
		if (null == defaultDBConfig) {
			defaultDBConfig = loadDefaultDSConfig();
		}
		return defaultDBConfig;
	}

	/**
	 * 根据数据库名称关键字获得数据库驱动类名。
	 * 
	 * @param dsConfig
	 * @return
	 */
	public static String getDriverClassName(DSConfig dsConfig) {
		String dbType = dsConfig.getDbType().toLowerCase();
		if (DbConstants.ORACLE_TYPE.equals(dbType)) {
			return DbConstants.ORACLE_DRIVER;
		} else if (DbConstants.MYSQL_TYPE.equals(dbType)) {
			return DbConstants.MYSQL_DRIVER;
		} else if (DbConstants.POSTGRESQL_TYPE.equals(dbType)) {
			return DbConstants.POSTGRESQL_DRIVER;
		} else if (DbConstants.DB2_TYPE.equals(dbType)) {
			return DbConstants.DB2_DRIVER;
		} else if (DbConstants.SQLSERVER_TYPE.equals(dbType)) {
			return DbConstants.SQLSERVER_DRIVER;
		} else {
			logger.error("[" + dbType + "]未知数据库类型，无法获得数据库驱动类名。");
			throw new RuntimeException();
		}
	}

	/**
	 * 根据数据库配置获得连接字符串。
	 * 
	 * @param dsConfig
	 * @return
	 */
	public static String getUrl(DSConfig dsConfig) {
		StringBuffer url = null;
		String dbType = dsConfig.getDbType().toLowerCase();
		if (DbConstants.ORACLE_TYPE.equals(dbType)) {
			// 例如 jdbc:oracle:thin@192.168.12.11:1521:shjd
			url = new StringBuffer().append(DbConstants.ORACLE_URL)
					.append(dsConfig.getIp()).append(DSConstants.COLON)
					.append(dsConfig.getPort()).append(DSConstants.COLON)
					.append(dsConfig.getInstance());
		} else if (DbConstants.MYSQL_TYPE.equals(dbType)) {
			// 例如 jdbc:mysql://localhost:3306/examol?
			url = new StringBuffer().append(DbConstants.MYSQL_URL)
					.append(dsConfig.getIp()).append(DSConstants.COLON)
					.append(dsConfig.getPort()).append(DSConstants.SLASH)
					.append(dsConfig.getInstance());
			// 参数不为空
			if (!Strings.isBlank(dsConfig.getParams())) {
				url.append(DSConstants.QO).append(dsConfig.getParams());
			}
		} else if (DbConstants.POSTGRESQL_TYPE.equals(dbType)) {
			// TODO
			throw new RuntimeException("未实现");
		} else if (DbConstants.DB2_TYPE.equals(dbType)) {
			// TODO
			throw new RuntimeException("未实现");
		} else if (DbConstants.SQLSERVER_TYPE.equals(dbType)) {
			// TODO
			throw new RuntimeException("未实现");
		} else {
			logger.error("[" + dbType + "]未知数据库类型，无法获得连接字符串。");
			throw new RuntimeException();
		}
		return url.toString();
	}

	private static DSConfig loadDefaultDSConfig() {
		DSConfig defaultDBConfig = new DSConfig();
		Properties props = new Properties();
		try {
			props.load(DSUtil.class.getResourceAsStream(DSConstants.SLASH
					+ defaultDBConfigFile));
		} catch (Exception e) {
			logger.error(
					String.format("默认数据库配置文件[%s]加载失败！", defaultDBConfigFile), e);
			throw new RuntimeException(e);
		}
		defaultDBConfig.setIp(props.getProperty(DSConstants.IP));
		defaultDBConfig.setPort(props.getProperty(DSConstants.PORT));
		defaultDBConfig.setInstance(props.getProperty(DSConstants.INSTANCE));
		defaultDBConfig.setParams(props.getProperty(DSConstants.PARAMS));
		defaultDBConfig.setUsername(props.getProperty(DSConstants.USERNAME));
		defaultDBConfig.setPassword(props.getProperty(DSConstants.PASSWORD));
		defaultDBConfig.setDbType(props.getProperty(DSConstants.DBTYPE));
		defaultDBConfig.setDbPool(props.getProperty(DSConstants.DBPOOL));
		defaultDBConfig
				.setDataSource(props.getProperty(DSConstants.DATASOURCE));
		logger.debug("默认数据库配置信息加载成功。");
		return defaultDBConfig;
	}
}
