/**
 *  File: BaseDaoImpl.java
 *  Description:
 *  Copyright Corporation. All rights reserved.
 *  Date      Author      Changes
 *  Dec 9, 2011   ch-ma     Create
 *
 */
package com.idealunited.inf.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.inf.dao.IPageable;
import com.idealunited.inf.dao.ISequenceable;
import com.idealunited.inf.dao.Page;
import com.idealunited.inf.dao.QueryResult;
import com.idealunited.util.MapUtil;
import com.idealunited.util.ReflectUtils;

/**
 * this dao for default Instance
 */
public class BaseDAOImpl<T> extends SqlMapClientDaoSupport implements BaseDAO<T> {

	protected String namespace = "";
	public static final int FIRST_PAGE_OFFSET = 0;
	public static final String COUNT_SUBFIX = "_COUNT";
	private IPageable pageGenerator;
	private ISequenceable sequenceGenerator;
	private SqlExecutor sqlExecutor;

	public void initialize() throws Exception {
		if (sqlExecutor != null) {
			SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
			ReflectUtils.setFieldValue(((SqlMapClientImpl) sqlMapClient).getDelegate(), "sqlExecutor",
					SqlExecutor.class, sqlExecutor);
		}
	}

	public void setPageGenerator(IPageable pageGenerator) {
		this.pageGenerator = pageGenerator;
	}

	public void setSqlExecutor(SqlExecutor sqlExecutor) {
		this.sqlExecutor = sqlExecutor;
	}

	public void setSequenceGenerator(ISequenceable sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	public ISequenceable getSequenceGenerator() {
		return sequenceGenerator;
	}

	@Override
	public Object create(final T pojo) {
		return getSqlMapClientTemplate().insert(namespace.concat("create"), pojo);
	}

	@Override
	public Object create(final String sqlId, final T pojo) {
		return getSqlMapClientTemplate().insert(namespace.concat(sqlId), pojo);
	}

	@Override
	public List<Object> batchCreate(final List<T> pojoList) {
		return batchCreate("create", pojoList);
	}

	@Override
	public List<Object> batchCreate(final String sqlId, final List<T> pojoList) {
		List<Object> result = (List<Object>) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				List<Object> keys = new ArrayList<Object>();
				for (T pojo : pojoList) {
					Object id = executor.insert(namespace.concat(sqlId), pojo);
					if (id != null) {
						keys.add(id);
					}
				}
				executor.executeBatch();
				return keys;
			}
		});
		return result;
	}

	@Override
	public boolean delete(Object param) {
		return delete("delete", param);
	}

	@Override
	public boolean delete(String sqlId, Object param) {
		return getSqlMapClientTemplate().delete(namespace.concat(sqlId), param) == 1;
	}

	@Override
	public void batchDelete(List paramList) {
		batchDelete("delete", paramList);
	}

	@Override
	public void batchDelete(final String sqlId, final List<Object> paramList) {

		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				Integer count = 0;
				for (Object param : paramList) {
					count += executor.delete(namespace.concat(sqlId), param);
				}
				executor.executeBatch();
				return count;
			}
		});
	}

	@Override
	public Integer deleteBatch(final String sqlId, final List<Object> paramList) {
		Integer cnt = 0;
		cnt = (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				Integer count = 0;
				for (Object param : paramList) {
					count += executor.delete(namespace.concat(sqlId), param);
				}
				executor.executeBatch();
				return count;
			}
		});
		return cnt;
	}

	@Override
	public boolean update(final T pojo) {
		return update("update", pojo);
	}

	/**
	 * 
	 * @param pojo
	 * @return
	 */
	public boolean updateByCriteria(final T pojo) {

		return update("updateByCriteria", pojo);
	}

	@Override
	public boolean update(final String sqlId, final T pojo) {
		return getSqlMapClientTemplate().update(namespace.concat(sqlId), pojo) == 1;
	}

	@Override
	public boolean updateByMap(String sqlId, Map paraMap) {

		return getSqlMapClientTemplate().update(namespace.concat(sqlId), paraMap) == 1;
	}

	@Override
	public <T> Integer updateBatch(final String stmtId, final List<T> paramList) {
		try {
			return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					executor.startBatch();
					Integer count = 0;
					for (T param : paramList) {
						count = executor.update(namespace.concat(stmtId), param);
					}
					executor.executeBatch();
					return count;
				}
			});
		} catch (Exception e) {
			logger.error("批量更新错误 [语句编号=" + stmtId + "]", e);
		}
		return 0;
	}

	@Override
	public T findById(final Object id) {
		return (T) getSqlMapClientTemplate().queryForObject(namespace.concat("findById"), id);
	}

	@Override
	public T findById(final String sqlId, final Object id) {
		return (T) getSqlMapClientTemplate().queryForObject(namespace.concat(sqlId), id);
	}

	@Override
	public T findObjectByCriteria(final Object criteria) {
		return findObjectByCriteria("findByCriteria", criteria);
	}

	@Override
	public T findObjectByTemplate(String sqlId, Object criteria) {
		return findObjectByCriteria(sqlId, criteria);
	}

	@Override
	public T findObjectByCriteria(final String sqlId, final Object criteria) {
		return (T) getSqlMapClientTemplate().queryForObject(namespace.concat(sqlId), criteria);
	}

	@Override
	public List<T> findByCriteria(final Object criteria) {
		return findByCriteria("findByCriteria", criteria);
	}

	@Override
	public List<T> findByCriteria(final String sqlId, final Object criteria) {
		String sid = namespace.concat(sqlId);
		Map map = null;
		if ((criteria instanceof String) || (criteria instanceof Integer)|| (criteria instanceof Long)) {
			return (List) getSqlMapClientTemplate().queryForList(sid, criteria);
		} else if (criteria instanceof Map) {
			map = (Map) criteria;
		} else {
			map = MapUtil.bean2map(criteria);
		}
		return (List) getSqlMapClientTemplate().queryForList(sid, map);
	}

	@Override
	public List<T> findByCriteria(final Object criteria, Page page) {
		int size = page.getPageSize();
		int offset = page.currentRecordIndex();

		int total = countByCriteria(criteria);
		page.setTotalRecord(total);
		page.setTotalCount(total);

		Map map = null;
		if (!(criteria instanceof Map)) {
			map = MapUtil.bean2map(criteria);
		} else {
			map = (Map) criteria;
		}
		map.put("limitClauseStart", offset);
		map.put("limitClauseCount", size);
		return getSqlMapClientTemplate().queryForList(namespace.concat("findByCriteria"), map);
	}

	@Override
	public List<T> findByCriteria(final String sqlId, final Object criteria, Page page) {
		int size = page.getPageSize();
		int offset = page.currentRecordIndex();
		String sid = namespace.concat(sqlId);

		int total = countByCriteria(sqlId + "_COUNT", criteria);
		page.setTotalRecord(total);
		page.setTotalCount(total);
		Map map = null;
		if (!(criteria instanceof Map)) {
			map = MapUtil.bean2map(criteria);
		} else {
			map = (Map) criteria;
		}
		map.put("limitClauseStart", offset);
		map.put("limitClauseCount", size);
		return getSqlMapClientTemplate().queryForList(sid, map);
	}

	@Override
	public List<T> findByTemplate(String sqlId, Object criteria) {
		return getSqlMapClientTemplate().queryForList(namespace.concat(sqlId), criteria);
	}

	@Override
	public List<T> findBySelective(String sqlId, Object criteria) {
		return getSqlMapClientTemplate().queryForList(namespace.concat(sqlId), criteria);
	}

	@Override
	public List<T> findBySelective(Object criteria) {
		return getSqlMapClientTemplate().queryForList(namespace.concat("findBySelective"), criteria);
	}

	@Override
	public List<T> findByQuery(final String sqlId, final Object criteria) {
		String sid = namespace.concat(sqlId);
		return (List) getSqlMapClientTemplate().queryForList(sid, criteria);
	}

	@Override
	public Page<T> findByQuery(final String sqlId, Page<T> page, final Object criteria) {

		int size = page.getPageSize();
		int offset = page.currentRecordIndex();
		String sid = namespace.concat(sqlId);

		int total = countByCriteria(sqlId + "_COUNT", criteria);
		page.setTotalRecord(total);
		page.setTotalCount(total);

		Map map = null;
		if (!(criteria instanceof Map)) {
			map = MapUtil.bean2map(criteria);
		} else {
			map = (Map) criteria;
		}
		map.put("limitClauseStart", offset);
		map.put("limitClauseCount", size);
		List<T> list = getSqlMapClientTemplate().queryForList(sid, map);
		page.setResult(list);
		return page;
	}

	@Override
	public Page<T> findByQuery(String sqlId, Page<T> page, Object[] criteria) {

		Object param = (criteria != null) ? criteria[0] : null;
		List<T> list = findByCriteria(sqlId, param, page);
		page.setResult(list);

		return page;
	}

	@Override
	public List<T> findAll(String sqlId) {
		return (List) getSqlMapClientTemplate().queryForList(namespace.concat(sqlId));
	}

	@Override
	public List<T> findAll() {
		return (List) getSqlMapClientTemplate().queryForList(namespace.concat("loadAll"));
	}

	@Override
	public int countByCriteria(final Object criteria) {
		Object value = getSqlMapClientTemplate().queryForObject(namespace.concat("countByCriteria"), criteria);
		return value != null ? (Integer) value : 0;
	}

	@Override
	public int countByCriteria(final String sqlId, final Object criteria) {
		Object value = getSqlMapClientTemplate().queryForObject(namespace.concat(sqlId), criteria);
		return value != null ? (Integer) value : 0;

	}

	@Override
	public T findObjectBySelective(Object criteria) {
		return (T) getSqlMapClientTemplate().queryForObject(namespace.concat("findBySelective"), criteria);
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace.concat(".");
	}

	private <T> QueryResult<T> findByQuery(String stmtId, int offset, int size, Object... params) {
		QueryResult<T> queryResult = new QueryResult<T>();
		Object param = (params != null) ? params[0] : null;
		try {
			// 当offset值为FIRST_PAGE_OFFSET时，被默认为需要查询总数
			if (offset == FIRST_PAGE_OFFSET) {
				String countSqlId = stmtId + COUNT_SUBFIX;
				if (logger.isDebugEnabled()) {
					logger.debug("自动调用  [" + countSqlId + "]");
				}
				int totalSize = (Integer) this.getSqlMapClientTemplate().queryForObject(countSqlId, param);
				// queryResult.setTotalCount(totalSize);
			}
			Map map = MapUtil.bean2map(param);
			map.put("limitClauseStart", offset);
			map.put("limitClauseCount", size);
			queryResult.setResult(getSqlMapClientTemplate().queryForList(stmtId, map));
		} catch (Exception e) {
			logger.error("查询错误 [语句编号=" + stmtId + ",查询参数=" + param + "]", e);
		}
		return queryResult;
	}

	@Override
	public List<T> findByCriteria(String sqlId, Object criteria, int page_offset, int pageSize) {
		Map map = null;
		if (!(criteria instanceof Map)) {
			map = MapUtil.bean2map(criteria);
		} else {
			map = (Map) criteria;
		}
		map.put("limitClauseStart", pageSize * page_offset);
		map.put("limitClauseCount", pageSize);
		return getSqlMapClientTemplate().queryForList(namespace.concat(sqlId), map);
	}

	@Override
	public boolean batchUpdate(List<T> paramList) {
		int cnt = updateBatch("update", paramList);
		return true;
	}
}
