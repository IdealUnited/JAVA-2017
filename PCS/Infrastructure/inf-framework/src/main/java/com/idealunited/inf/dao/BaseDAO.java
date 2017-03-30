package com.idealunited.inf.dao;

import java.util.List;
import java.util.Map;

public interface BaseDAO<T> {

	String BEAN_ID = "PLATFORM.DEFAULT.DAOSERVICE";

	/**
	 * create record,sqlId is: create
	 * 
	 * @param pojo
	 * @return
	 */
	Object create(final T pojo);

	/**
	 * create record,sqlId is: create
	 * 
	 * @param pojo
	 * @return
	 */
	Object create(final String sqlId, final T pojo);

	/**
	 * batch create records,sqlId is: create ,Either all succeed or fail
	 * 
	 * @param pojoList
	 * @return
	 */
	List<Object> batchCreate(final List<T> pojoList);

	/**
	 * batch create records,Either all succeed or fail
	 * 
	 * @param sqlId
	 * @param pojoList
	 * @return
	 */
	List<Object> batchCreate(final String sqlId, final List<T> pojoList);

	/**
	 * do delete a record,sqlId is: delete
	 * 
	 * @param obj
	 * @return
	 */
	boolean delete(final Object param);

	/**
	 * do delete a record
	 * 
	 * @param sqlId
	 * @param obj
	 * @return
	 */
	boolean delete(final String sqlId, final Object param);

	/**
	 * batch delete records,sqlId is:delete ,Either all succeed or fail
	 * 
	 * @param paramList
	 * 
	 */
	void batchDelete(final List paramList);

	/**
	 * batch delete records,Either all succeed or fail
	 * 
	 * @param paramList
	 * 
	 */
	void batchDelete(final String sqlId, final List<Object> paramList);

	/**
	 * 
	 * @param stmtId
	 * @param paramList
	 * @return
	 */
	Integer deleteBatch(final String stmtId, final List<Object> paramList);

	/**
	 * update a record,sqlId is: update
	 * 
	 * @param pojo
	 * @return
	 */
	boolean update(final T pojo);

	/**
	 * 
	 * @param pojo
	 * @param criteria
	 * @return
	 */
	boolean updateByCriteria(final T pojo);

	/**
	 * update a record
	 * 
	 * @param pojo
	 * @param others
	 * @return
	 */
	boolean update(final String sqlId, final T pojo);

	/**
	 * 批量更新订单
	 * 
	 * @param paramList
	 * @return
	 */
	boolean batchUpdate(final List<T> paramList);

	/**
	 * update a record
	 * 
	 * @param pojo
	 * @param others
	 * @return
	 */
	boolean updateByMap(final String sqlId, final Map paraMap);

	/**
	 * 
	 * @param stmtId
	 * @param paramList
	 * @return
	 */
	<T> Integer updateBatch(final String stmtId, final List<T> paramList);

	/**
	 * find data by id,sqlId is: findById
	 * 
	 * @param sqlId
	 * @param id
	 * @return model object
	 */
	T findById(final Object id);

	/**
	 * find data by id,sqlId is: findById
	 * 
	 * @param sqlId
	 * @param id
	 * @return model object
	 */
	T findById(final String sqlId, final Object id);

	/**
	 * find data by criteria,sqlId is: findObjectByCriteria
	 * 
	 * @param criteria
	 * @return model object
	 */
	T findObjectByCriteria(final Object criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	T findObjectBySelective(final Object criteria);

	/**
	 * 
	 * @param sqlId
	 * @param criteria
	 * @return
	 */
	T findObjectByCriteria(final String sqlId, final Object criteria);

	/**
	 * 
	 * @param sqlId
	 * @param criteria
	 * @return
	 */
	T findObjectByTemplate(final String sqlId, final Object criteria);

	/**
	 * load all data,sqlId is: loadAll
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 
	 * @param sqlId
	 * @return
	 */
	List<T> findAll(String sqlId);

	/**
	 * find data by criteria,sqlId is: findByCriteria
	 * 
	 * @param data
	 * @return
	 */
	List<T> findByCriteria(final Object criteria);

	/**
	 * 根据指定的条件返回查询结果，全匹配
	 * 
	 * @param data
	 * @return
	 */
	List<T> findByCriteria(final String sqlId, final Object criteria);

	/**
	 * 
	 * @param sqlId
	 * @param criteria
	 * @return
	 */
	List<T> findBySelective(final String sqlId, final Object criteria);

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	List<T> findBySelective(final Object criteria);

	/**
	 * 
	 * @param sqlId
	 * @param criteria
	 * @return
	 */
	List<T> findByTemplate(final String sqlId, final Object criteria);

	/**
	 * 
	 * @param page
	 * @return
	 */
	List<T> findByCriteria(final Object criteria, Page page);

	/**
	 * 
	 * @param page
	 * @return
	 */
	List<T> findByCriteria(final String sqlId, final Object criteria, Page page);

	/**
	 * 
	 * @param sqlId
	 * @param criteria
	 * @param page_offset
	 * @param pageSize
	 * @return
	 */
	List<T> findByCriteria(final String sqlId, final Object criteria,
			int page_offset, int pageSize);

	/**
	 * 
	 * @param sqlId
	 * @param page
	 * @param criteria
	 * @return
	 */
	List<T> findByQuery(final String sqlId, final Object criteria);

	/**
	 * 
	 * @param sqlId
	 * @param page
	 * @param criteria
	 * @return
	 */
	Page<T> findByQuery(final String sqlId, Page<T> page, final Object criteria);

	/**
	 * 
	 * @param sqlId
	 * @param page
	 * @param criteria
	 * @return
	 */
	Page<T> findByQuery(final String sqlId, Page<T> page,
			final Object[] criteria);

	/**
	 * 
	 * @param data
	 * @return
	 */
	int countByCriteria(final Object criteria);

	/**
	 * 
	 * @param data
	 * @param sqlId
	 * @return
	 */
	int countByCriteria(final String sqlId, final Object criteria);
}