package com.st.util;

import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.st.util.database.DSConfig;
import com.st.util.database.DSContainer;
import com.st.util.database.DSUtil;

/**
 * DAO层切换类。
 * 
 * 
 */
public abstract class DaoUtil {

	protected static Log logger = Logs.getLog(DaoUtil.class);

	private static NutDao sqlDao = new NutDao();

	private static NutDao defaultDao = new NutDao(DSContainer.getDataSource(DSUtil
			.getDefaultDSConfig()));

	/**
	 * 加载配置SQL
	 */
	static {
		sqlDao.setSqlManager(new FileSqlManager("/config/sql"));
		if (logger.isDebugEnabled()) {
			logger.debugf("加载SQL配置文件成功，共%s条", sqlDao.sqls().count());
			for (String key : sqlDao.sqls().keys()) {
				String sql = sqlDao.sqls().get(key);
				logger.debugf("Key: %s Value: %s", key, sql);
			}
		}
	}

	public static NutDao getSQLDao() {
		return sqlDao;
	}

	/**
	 * 获得当前选中的Dao。
	 * 
	 * @return
	 */
	public static NutDao getDao() {
		return defaultDao;
	}

	/**
	 * 根据数据源配置信息，获得Dao。
	 * 
	 * @param dbConfig
	 * @return
	 */
	public static NutDao getDao(DSConfig dbConfig) {
		return new NutDao(DSContainer.getDataSource(dbConfig));
	}

}
