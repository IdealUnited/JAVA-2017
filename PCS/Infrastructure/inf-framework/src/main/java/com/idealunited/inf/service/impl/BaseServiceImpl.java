/**
 *  File: BaseServiceImpl.java
 *  Description:
 *  Date      Author      Changes
 *  Apr 30, 2013   chaoyue     Create
 *
 */
package com.idealunited.inf.service.impl;

import java.util.Collection;
import java.util.List;

import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.inf.dao.Page;
import com.idealunited.inf.model.Model;
import com.idealunited.inf.service.BaseService;
import com.idealunited.util.BeanConvertUtil;

/**
 * 
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected BaseDAO mainDao;

	protected abstract Class getModelClass();

	protected abstract Class getDtoClass();

	public BaseDAO getMainDao() {
		return mainDao;
	}

	public final void setMainDao(BaseDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Override
	public Object create(T pojo) {

		return getMainDao().create(
				BeanConvertUtil.convert(getModelClass(), pojo));
	}

	@Override
	public List<Object> batchCreate(Collection pojoList) {

		return getMainDao().batchCreate(
				(List<Model>) BeanConvertUtil
						.convert(getModelClass(), pojoList));
	}

	@Override
	public boolean delete(Object param) {
		return getMainDao().delete(param);
	}

	@Override
	public void batchDelete(List<Object> paramList) {
		getMainDao().batchDelete(paramList);
	}

	@Override
	public boolean update(T pojo) {

		return getMainDao().update(
				BeanConvertUtil.convert(getModelClass(), pojo));
	}

	@Override
	public boolean update(String sqlId, T pojo) {

		return getMainDao().update(sqlId,
				BeanConvertUtil.convert(getModelClass(), pojo));
	}

	@Override
	public boolean updateByCriteria(T pojo) {

		return getMainDao().updateByCriteria(
				BeanConvertUtil.convert(getModelClass(), pojo));
	}

	@Override
	public T findById(Object id) {
		return (T) BeanConvertUtil.convert(getDtoClass(), getMainDao()
				.findById(id));
	}

	@Override
	public T findObjectByCriteria(Object criteria) {
		return (T) BeanConvertUtil.convert(getDtoClass(), getMainDao()
				.findObjectByCriteria(criteria));
	}

	@Override
	public List<T> loadAll() {

		return (List<T>) BeanConvertUtil.convert(getDtoClass(), getMainDao()
				.findAll());
	}

	@Override
	public List<T> findByCriteria(Object criteria) {

		return (List<T>) BeanConvertUtil.convert(getDtoClass(), getMainDao()
				.findByCriteria(criteria));
	}

	public List<T> findByCriteria(String sqlId, Object criteria) {
		return (List<T>) BeanConvertUtil.convert(getDtoClass(), getMainDao()
				.findByCriteria(sqlId, criteria));
	}

	@Override
	public List<T> findByCriteria(Object criteria, Page page) {
		return (List<T>) BeanConvertUtil.convert(getDtoClass(), getMainDao()
				.findByCriteria(criteria, page));
	}

	@Override
	public Page<T> findByCriteria(String sqlId, Object criteria, Page<T> page) {
		page.setResult((List<T>) BeanConvertUtil.convert(getDtoClass(),
				getMainDao().findByQuery(sqlId, page, criteria).getResult()));
		return page;
	}
}
