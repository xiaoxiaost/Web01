package com.st.service;

import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Condition;
import org.nutz.dao.QueryResult;
import org.nutz.dao.sql.Sql;

import com.st.util.database.constant.DbOperationResultEnum;

/**
 * CRUD基本操作。<br>
 * 基于泛型类。
 * 
 * @author pangwu86@gmail.com
 * 
 * @param <T>
 *            实体类类型
 */
public interface BaseService<T> {

	/**
	 * 从配置SQL文件中取得SQL文。
	 * 
	 * @param key
	 * @return
	 */
	public Sql createSql(String key);

	/**
	 * 直接执行一组SQL语句。
	 * 
	 * @param sqls
	 * @return
	 */
	public boolean execute(Sql... sqls);

	/**
	 * 通用查询。(无分页)
	 * 
	 * @param cdn
	 * @return
	 */
	public List<T> query(Condition cdn);

	/**
	 * 通用查询。(带分页信息)
	 * 
	 * @param cdn
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public QueryResult query(Condition cdn, int pageNumber, int pageSize);

	/**
	 * 通用获取,对象中引用的对象。(根据正则匹配)
	 * 
	 * @param obj
	 * @param regex
	 * @return
	 */
	public T fetchLinks(T obj, String regex);

	/**
	 * 通用获取。(根据条件)
	 * 
	 * @param cdn
	 * @return
	 */
	public T fetch(Condition cdn);

	/**
	 * 通用获取。(根据id)
	 * 
	 * @param id
	 * @return
	 */
	public T fetch(long id);

	/**
	 * 通用获取。(根据name)
	 * 
	 * @param name
	 * @return
	 */
	public T fetch(String name);

	/**
	 * 通用删除。(根据实体)
	 * 
	 * @param entity
	 * @return
	 */
	public DbOperationResultEnum delete(T entity);

	/**
	 * 通用插入。(根据实体)
	 * 
	 * @param entity
	 * @return
	 */
	public DbOperationResultEnum insert(T entity);

	/**
	 * 通用插入(附带相关信息一起插入)。(根据实体)
	 * 
	 * @param entity
	 * @param regex
	 * @return
	 */
	public DbOperationResultEnum insertWith(T entity, String regex);

	/**
	 * 通用更新。(根据条件)
	 * 
	 * @param entity
	 * @return
	 */
	public DbOperationResultEnum update(Condition cnd, Chain chain);

	/**
	 * 通用更新。(根据实体)
	 * 
	 * @param entity
	 * @return
	 */
	public DbOperationResultEnum update(T entity);

	/**
	 * 通用批量删除。(删除全部)
	 * 
	 * @return
	 */
	public DbOperationResultEnum clear();

	/**
	 * 通用批量删除。(根据条件删除)
	 * 
	 * @param cdn
	 * @return
	 */
	public DbOperationResultEnum clear(Condition cdn);

	/**
	 * 返回该Service使用的实体类类型。
	 * 
	 * @return
	 */
	public Class<T> getEntryClz();

}
