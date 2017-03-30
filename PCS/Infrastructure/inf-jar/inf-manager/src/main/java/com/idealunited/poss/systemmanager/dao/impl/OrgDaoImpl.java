package com.idealunited.poss.systemmanager.dao.impl;

import java.util.List;

import com.idealunited.inf.dao.impl.BaseDAOImpl;
import com.idealunited.poss.systemmanager.dao.IOrgDao;
import com.idealunited.poss.systemmanager.model.Org;

/**
 * 部站相关数据操作与查询
 * 
 * @author 戴德荣
 * @date 2011-1-6
 *
 */
public class OrgDaoImpl extends BaseDAOImpl<Org> implements IOrgDao {

	@Override
	public Org findByOrgCode(String orgCode) {
		return (Org) findObjectByCriteria("findByOrgCode", orgCode);
	}

	@Override
	public List<Org> findAll() {
		return findByCriteria("allOrg");
	}

}
