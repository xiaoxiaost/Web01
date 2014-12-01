package com.st.exception;

import com.st.util.database.constant.DbOperationResultEnum;



/**
 * 一个包含异常信息跟数据库操作结果信息的异常类。
 * 
 * @author pangwu86@gmail.com
 * 
 */
@SuppressWarnings("serial")
public class BizException extends RuntimeException {

	private DbOperationResultEnum dbOperationResultEnum = null;

	private Throwable cause = null;

	/**
	 * 业务异常。
	 * 
	 * @param dbOperationResultEnum
	 * @param cause
	 */
	public BizException(DbOperationResultEnum dbOperationResultEnum, Throwable cause) {
		super(cause);
		this.cause = cause;
		this.dbOperationResultEnum = dbOperationResultEnum;
	}

	public Throwable getCause() {
		return this.cause;
	}

	public DbOperationResultEnum getDBResult() {
		return this.dbOperationResultEnum;
	}
}
