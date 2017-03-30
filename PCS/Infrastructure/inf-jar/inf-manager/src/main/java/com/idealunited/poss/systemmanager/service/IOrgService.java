package com.idealunited.poss.systemmanager.service;

import java.util.List;

import com.idealunited.poss.systemmanager.model.Org;


/**
 * 部门相关的service
 * @author 戴德荣
 * @date 2011-1-6 
 *
 */
public interface IOrgService {
	/**
	 * 通过orgCode来取得部门
	 * @param orgCode
	 * @return Org
	 * @date 2011-1-6
	 */
	public Org findByCode(String orgCode);
	
	/**
	 * 得到所有的部门信息
	 * @return List<Org>
	 * @date 2011-1-6
	 */
	public List<Org> findAllOrg();
}
