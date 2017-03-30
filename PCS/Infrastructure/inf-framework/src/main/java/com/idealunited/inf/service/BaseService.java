/**
 *  File: BaseService.java
 *  Description:
 *  Date      Author      Changes
 *  Apr 30, 2013   chaoyue     Create
 *
 */
package com.idealunited.inf.service;

import java.util.Collection;
import java.util.List;

import com.idealunited.inf.dao.Page;

/**
 * 
 */
public interface BaseService<T> {

	/**
	 * create record
	 * 
	 * @param pojo
	 * @return
	 */
	Object create(final T pojo);

	/**
	 * batch create records, Either all succeed or fail
	 * 
	 * @param pojoList
	 * @return
	 */
	List<Object> batchCreate(final Collection pojoList);

	/**
	 * delete record
	 * 
	 * @param param
	 * @return
	 */
	boolean delete(final Object param);

	/**
	 * batch delete records, Either all succeed or fail
	 * 
	 * @param paramList
	 * 
	 */
	void batchDelete(final List<Object> paramList);

	/**
	 * update a record
	 * 
	 * @param pojo
	 * @return
	 */
	boolean update(final T pojo);

	/**
	 * update a record
	 * 
	 * @param pojo
	 * @return
	 */
	boolean update(final String sqlId, final T pojo);

	/**
	 * 
	 * @param pojo
	 * @param criteria
	 * @return
	 */
	boolean updateByCriteria(final T pojo);

	/**
	 * find data by id
	 * 
	 * @param id
	 * @return model object
	 */
	T findById(final Object id);

	/**
	 * find data by criteria
	 * 
	 * @param criteria
	 * @return model object
	 */
	T findObjectByCriteria(final Object criteria);

	/**
	 * load all data
	 * 
	 * @return
	 */
	List<T> loadAll();

	/**
	 * find data by criteria
	 * 
	 * @param data
	 * @return
	 */
	List<T> findByCriteria(final Object criteria);

	/**
	 * find data by criteria
	 * 
	 * @param data
	 * @return
	 */
	List<T> findByCriteria(final String sqlId, Object criteria);

	/**
	 * @param criteria
	 * @param page
	 * @return
	 */
	List<T> findByCriteria(final Object criteria, Page page);

	/**
	 * 
	 * @param sqlId
	 * @param page
	 * @param criteria
	 * @return
	 */
	Page<T> findByCriteria(final String sqlId, final Object criteria,
			Page<T> page);
}
