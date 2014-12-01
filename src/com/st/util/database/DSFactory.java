package com.st.util.database;

import java.beans.PropertyVetoException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据源工厂。
 * 
 * @author pangwu86@gmail.com
 * 
 */
class DSFactory {

	private static Log logger = Logs.getLog(DSFactory.class);

	/**
	 * 根据配置信息，返回特定的数据源。
	 * 
	 * @param dsConfig
	 * @return
	 */
	public static DataSource createDataSource(DSConfig dsConfig) {
		DataSource ds = null;
		try {
			if (!Strings.isBlank(dsConfig.getDataSource())) {
				// 采用容器当中的数据源
				logger.debug("从容器中取外部数据源");
				Context initCtx = new InitialContext();

				if ("tomcat".equalsIgnoreCase(dsConfig.getDbPool())) {
					// Tomcat 数据源
					ds = (DataSource) initCtx.lookup("java:comp/env/"
							+ dsConfig.getDataSource());
				} else if ("was".equalsIgnoreCase(dsConfig.getDbPool())) {
					// was 数据源
					ds = (DataSource) initCtx.lookup(dsConfig.getDataSource());
				} else {
					// 其他 数据源
					ds = (DataSource) initCtx.lookup(dsConfig.getDataSource());
				}
				return ds;
			} else if (!Strings.isBlank(dsConfig.getDbPool())
					&& DSConstants.POOL_DBCP.equals(dsConfig.getDbPool())) {
				// 采用DBCP
				logger.debug("取 DBCP 数据源");
				ds = createBasicDataSource(dsConfig);
			} else {
				// 采用C3P0
				logger.debug("取 C3P0 数据源");
				ds = createComboPooledDataSource(dsConfig);
			}
		} catch (Throwable e) {
			// 打印数据源详细信息
			if (logger.isErrorEnabled()) {
				logger.error("数据源信息：");
				logger.error(DSUtil.getDriverClassName(dsConfig));
				logger.error(DSUtil.getUrl(dsConfig));
				logger.error(dsConfig.getUsername());
				logger.error(dsConfig.getPassword());
				logger.error(dsConfig.getDataSource());
			}
			throw new RuntimeException("加载数据源失败。", e);
		}
		return ds;
	}

	/**
	 * 生成DBCP数据源。
	 * 
	 * @param dsConfig
	 * @return
	 */
	private static BasicDataSource createBasicDataSource(DSConfig dsConfig) {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DSUtil.getDriverClassName(dsConfig));
		ds.setUrl(DSUtil.getUrl(dsConfig));
		ds.setUsername(dsConfig.getUsername());
		ds.setPassword(dsConfig.getPassword());
		// 配置参数
		configBasicDataSource(ds);
		return ds;
	}

	/**
	 * 配置可选参数。
	 * 
	 * @param ds
	 */
	private static void configBasicDataSource(BasicDataSource ds) {
		// TODO

	}

	/**
	 * 生成C3P0数据源。
	 * 
	 * @param dsConfig
	 * @return
	 * @throws PropertyVetoException
	 */
	private static ComboPooledDataSource createComboPooledDataSource(
			DSConfig dsConfig) throws PropertyVetoException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass(DSUtil.getDriverClassName(dsConfig));
		ds.setJdbcUrl(DSUtil.getUrl(dsConfig));
		ds.setUser(dsConfig.getUsername());
		ds.setPassword(dsConfig.getPassword());
		// 配置参数
		configComboPooledDataSource(ds);
		return ds;
	}

	/**
	 * 配置可选参数。
	 * 
	 * @param ds
	 */
	private static void configComboPooledDataSource(ComboPooledDataSource ds) {
		// TODO

	}

}
