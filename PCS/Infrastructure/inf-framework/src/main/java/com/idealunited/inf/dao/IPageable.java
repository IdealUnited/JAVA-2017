package com.idealunited.inf.dao;

public interface IPageable {

	public boolean supportsLimit();

	/**
	 * 返回包含分页参数的SQL语句
	 * 
	 * @param sql
	 *            原始SQL语句
	 * @param offset
	 *            位移
	 * @param limit
	 *            条数
	 * @return 物理分页的SQL语句
	 */
	public String getLimitString(String sql, int offset, int limit);
}
