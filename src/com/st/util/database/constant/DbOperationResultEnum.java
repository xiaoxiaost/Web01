package com.st.util.database.constant;

/**
 * 封装了各种数据库操作结果。
 * 
 * @author pangwu86@gmail.com
 * 
 */
public enum DbOperationResultEnum {

	OPERATION_SUCCESS(true, "操作成功。"),

	OPERATION_FAILURE(false, "操作失败。"),

	INSERT_SUCCESS(true, "插入成功。"),

	INSERT_FAILURE(false, "插入失败。"),

	UPDATE_SUCCESS(true, "更新成功。"),

	UPDATE_FAILURE(false, "更新失败。"),

	DELETE_SUCCESS(true, "删除成功。"),

	DELETE_FAILURE(false, "删除失败，数据可能被引用，请先删引用关系。"),

	CLEAR_SUCCESS(true, "批量删除成功。"),

	CLEAR_FAILURE(false, "批量删除失败，数据可能被引用，请先删引用关系。");

	private boolean success;

	private String msg;

	private DbOperationResultEnum(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMsg() {
		return msg;
	}
}
