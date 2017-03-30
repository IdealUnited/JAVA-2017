package com.idealunited.poss.systemmanager.dao;

import java.util.List;

import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.poss.systemmanager.model.Org;

/**
 * 机构 Dao
 * 
 * @author wucan
 * @author 戴德荣
 * @descript
 * @data 2010-7-24 上午11:58:41
 */
public interface IOrgDao extends BaseDAO<Org>{
	/**
	 * 通过部门code来查找机构对象
	 * @param orgCode
	 * @return 　Org对象或是null
	 * @date 2011-1-6
	 */
	public Org findByOrgCode(String orgCode);
	
	/**
	 * 得到所有的org信息
	 * @return 
	 * @date 2011-1-6
	 */
	public List<Org> findAll();
}
