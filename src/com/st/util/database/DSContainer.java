package com.st.util.database;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.nutz.lang.Strings;

/**
 * 数据源容器。
 * 
 * @author pangwu86@gmail.com
 * 
 */
public abstract class DSContainer {

	/**
	 * 保持所有数据源的集合。
	 */
	private static Map<String, DataSource> dss = new ConcurrentHashMap<String, DataSource>();

	/**
	 * 根据配置信息获得数据源。 <br>
	 * 一份配置信息只保持一个数据源。
	 * 
	 * @param dbConfig
	 * @return
	 */
	public static DataSource getDataSource(DSConfig dbConfig) {
		DataSource ds = null;
		String key = null;
		if (!Strings.isBlank(dbConfig.getDataSource())) {
			// 采用配置好的数据源
			key = dbConfig.getDataSource();
		} else {
			// 采用配置数据源
			// 所有必要配置信息组成KEY
			key = new StringBuffer().append(dbConfig.getIp()).append(dbConfig.getPort())
					.append(dbConfig.getInstance()).append(dbConfig.getUsername())
					.append(dbConfig.getPassword()).append(dbConfig.getDbType()).toString();
		}
		synchronized (dss) {
			ds = dss.get(key);
			if (null == ds) {
				ds = DSFactory.createDataSource(dbConfig);
				dss.put(key, ds);
			}
		}
		return ds;
	}
}
