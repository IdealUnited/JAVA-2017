package com.idealunited.inf.dao.impl;

import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class IbatisDAOSupport<T> extends BaseDAOImpl {

	private DataSource noProxyDataSource;
	private JdbcTemplate jdbcTemplate;

	public DataSource getNoProxyDataSource() {
		return noProxyDataSource;
	}

	public void setNoProxyDataSource(DataSource noProxyDataSource) {
		this.noProxyDataSource = noProxyDataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(getSqlMapClientTemplate()
					.getDataSource());
		}
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// 创建 更新 相关
	public final Object saveObject(T pojo) {
		Object id = getSqlMapClientTemplate().insert(
				namespace.concat("create"), pojo);
		return id;
	}

	public Integer updateObject(T pojo) {
		Integer id = getSqlMapClientTemplate().update(
				namespace.concat("update"), pojo);
		return id;
	}

	public Integer updateObjectBySqlName(String sqlName, T pojo) {
		Integer id = getSqlMapClientTemplate().update(
				namespace.concat(sqlName), pojo);
		return id;
	}

	// 查询相关

	public final T findObjectById(Object id) {
		return (T) getSqlMapClientTemplate().queryForObject(
				namespace.concat("findById"), id);
	}

	@Override
	public List<T> findBySelective(Object data) {
		return (List<T>) getSqlMapClientTemplate().queryForList(
				namespace.concat("findBySelective"), data);
	}

	@Override
	public T findObjectBySelective(Object data) {

		return (T) getSqlMapClientTemplate().queryForObject(
				namespace.concat("findBySelective"), data);
	}

	@SuppressWarnings("unchecked")
	public List findByTemplate(String sqlId, Object data) {
		return (List) getSqlMapClientTemplate().queryForList(
				namespace.concat(sqlId), data);
	}

	public Object findObjectByTemplate(String sqlId, Object data) {
		return getSqlMapClientTemplate().queryForObject(
				namespace.concat(sqlId), data);
	}

	public final List findObjectsBySql(final Class clazz, final String sql) {
		return null;
	}

	public int count(String sqlId, Object data) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				namespace.concat(sqlId), data);
	}

	public int countByExample(Object data) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				namespace.concat("countByExample"), data);
	}

	// 删除相关

	public boolean delete(long id) {
		return getSqlMapClientTemplate().delete(namespace.concat("delete"), id) == 1;
	}

	public boolean deletes(List ids) {
		return getSqlMapClientTemplate().delete(namespace.concat("deletes"),
				ids) == ids.size();
	}

	public boolean deleteObjectById(Object id) {
		return getSqlMapClientTemplate().delete(namespace.concat("delete"), id) == 1;
	}

	public final Object findObjectByProc(final String procName,
			final Vector vInPara, final Vector vOutPara) {
		return null;
	}

	public final List findObjectsByProc(final String procName,
			final Vector vInPara, final Vector vOutPara) {
		return null;
	}

	/**
	 * 调用存储过程
	 */
	public void execProc(String procName, Vector vector) {

	}

	public String getNextID(String seqName) {
		return ""
				+ getJdbcTemplate().queryForLong(
						"select SEQ_PRICE_STRATEGY_ID.nextval  from DUAL");
	}

	public boolean deleteBySelective(Object data) {
		return false;
	}

	public List findListByProc(String procName, Vector vInPara, Vector vOutPara) {
		return null;
	}

}