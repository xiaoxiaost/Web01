package com.st.service.impl;

import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.st.exception.BizException;
import com.st.service.BaseService;
import com.st.util.DaoUtil;
import com.st.util.database.DSConfig;
import com.st.util.database.constant.DbOperationResultEnum;

/**
 * 通用操作 实现。<br>
 * 
 * @author pangwu86@gmail.com
 * 
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected Log logger = Logs.getLog(getClass());

	private Mirror<T> mirror;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		// 尝试获得泛型的类型
		try {
			Class<T> entryClass = (Class<T>) Mirror.getTypeParam(getClass(), 0);
			mirror = Mirror.me(entryClass);
			if (logger.isDebugEnabled())
				logger.debugf("获得泛型的实际类型: %s", entryClass.getName());
		} catch (Throwable e) {
			if (logger.isWarnEnabled())
				logger.warn("!!!无法获得泛型类型!", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 方便的提供上层Dao。
	 * 
	 * @return
	 */
	public NutDao getDao() {
		return DaoUtil.getDao();
	}

	/**
	 * 方便的提供上层Dao。
	 * 
	 * @param dbConfig
	 * @return
	 */
	public NutDao getDao(DSConfig dbConfig) {
		return DaoUtil.getDao(dbConfig);
	}

	/**
	 * 获得当前泛型类型。
	 * 
	 * @return
	 */
	public Class<T> getEntryClz() {
		return mirror.getType();
	}

	/**
	 * 从配置SQL文件中取得SQL文。
	 * 
	 * @param key
	 * @return
	 */
	public Sql createSql(String key) {
		return DaoUtil.getSQLDao().sqls().create(key);
	}

	/**
	 * 直接执行一组SQL语句。
	 * 
	 * @param sqls
	 */
	public boolean execute(Sql... sqls) {
		try {
			DaoUtil.getDao().execute(sqls);
			return true;
		} catch (Throwable e) {
			if (logger.isErrorEnabled()) {
				logger.error("批量执行SQL语句报错。", e);
			}
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通用查询。(无分页)
	 * 
	 * @param cdn
	 * @return
	 */
	public List<T> query(Condition cdn) {
		return DaoUtil.getDao().query(getEntryClz(), cdn, null);
	}

	/**
	 * 通用查询。(带分页信息)
	 * 
	 * @param cdn
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public QueryResult query(Condition cdn, int pageNumber, int pageSize) {
		Dao dao = DaoUtil.getDao();
		Pager pager = dao.createPager(pageNumber, pageSize);
		List<T> list = dao.query(getEntryClz(), cdn, pager);
		if (null != pager) {
			pager.setRecordCount(dao.count(getEntryClz(), cdn));
		}
		return new QueryResult(list, pager);
	}

	/**
	 * 通用获取,对象中引用的对象。(根据正则匹配)
	 * 
	 * @param obj
	 * @param regex
	 * @return
	 */
	public T fetchLinks(T obj, String regex) {
		return DaoUtil.getDao().fetchLinks(obj, regex);
	}

	/**
	 * 通用获取。(根据条件)
	 * 
	 * @param cdn
	 * @return
	 */
	public T fetch(Condition cdn) {
		return DaoUtil.getDao().fetch(getEntryClz(), cdn);
	}

	/**
	 * 通用获取。(根据id)
	 * 
	 * @param id
	 * @return
	 */
	public T fetch(long id) {
		return DaoUtil.getDao().fetch(getEntryClz(), id);
	}

	/**
	 * 通用获取。(根据name)
	 * 
	 * @param name
	 * @return
	 */
	public T fetch(String name) {
		return DaoUtil.getDao().fetch(getEntryClz(), name);
	}

	/**
	 * 通用删除。(根据实体)
	 * 
	 * @param entity
	 * @return
	 */
	public DbOperationResultEnum delete(T entity) {
		try {
			return 1 == DaoUtil.getDao().delete(entity) ? DbOperationResultEnum.DELETE_SUCCESS
					: DbOperationResultEnum.DELETE_FAILURE;
		} catch (Throwable e) {
			if (logger.isErrorEnabled()) {
				logger.error("删除数据出错。", e);
			}
			throw new BizException(DbOperationResultEnum.DELETE_FAILURE, e);
		}
	}

	/**
	 * 通用插入。(根据实体)
	 * 
	 * @param entity
	 * @return
	 */
	public DbOperationResultEnum insert(T entity) {
		try {
			DaoUtil.getDao().insert(entity);
			return DbOperationResultEnum.INSERT_SUCCESS;
		} catch (Throwable e) {
			if (logger.isErrorEnabled()) {
				logger.error("插入数据出错。", e);
			}
			throw new BizException(DbOperationResultEnum.INSERT_FAILURE, e);
		}
	}

	/**
	 * 通用插入(附带相关信息一起插入)。(根据实体)
	 * 
	 * @param entity
	 * @param regex
	 * @return
	 */
	public DbOperationResultEnum insertWith(T entity, String regex) {
		try {
			DaoUtil.getDao().insertWith(entity, regex);
			return DbOperationResultEnum.INSERT_SUCCESS;
		} catch (Throwable e) {
			if (logger.isErrorEnabled()) {
				logger.error("插入数据出错。", e);
			}
			throw new BizException(DbOperationResultEnum.INSERT_FAILURE, e);
		}
	}

	/**
	 * 通用更新。(根据条件)
	 * 
	 * @param entity
	 * @return
	 */
	public DbOperationResultEnum update(Condition cnd, Chain chain) {
		try {
			return DaoUtil.getDao().update(getEntryClz(), chain, cnd) >= 0 ? DbOperationResultEnum.UPDATE_SUCCESS
					: DbOperationResultEnum.UPDATE_FAILURE;
		} catch (Throwable e) {
			if (logger.isErrorEnabled()) {
				logger.error("更新数据出错。", e);
			}
			throw new BizException(DbOperationResultEnum.UPDATE_FAILURE, e);
		}
	}

	/**
	 * 通用更新。(根据实体)
	 * 
	 * @param entity
	 * @return
	 */
	public DbOperationResultEnum update(T entity) {
		try {
			return 1 == DaoUtil.getDao().update(entity) ? DbOperationResultEnum.UPDATE_SUCCESS
					: DbOperationResultEnum.UPDATE_FAILURE;
		} catch (Throwable e) {
			if (logger.isErrorEnabled()) {
				logger.error("更新数据出错。", e);
			}
			throw new BizException(DbOperationResultEnum.UPDATE_FAILURE, e);
		}
	}

	/**
	 * 通用批量删除。(删除全部)
	 * 
	 * @return
	 */
	public DbOperationResultEnum clear() {
		return clear(null);
	}

	/**
	 * 通用批量删除。(根据条件删除)
	 * 
	 * @param cdn
	 * @return
	 */
	public DbOperationResultEnum clear(Condition cdn) {
		try {
			return DaoUtil.getDao().clear(getEntryClz(), cdn) >= 0 ? DbOperationResultEnum.CLEAR_SUCCESS
					: DbOperationResultEnum.CLEAR_FAILURE;
		} catch (Throwable e) {
			if (logger.isErrorEnabled()) {
				logger.error("批量删除数据出错。", e);
			}
			throw new BizException(DbOperationResultEnum.CLEAR_FAILURE, e);
		}
	}
}
