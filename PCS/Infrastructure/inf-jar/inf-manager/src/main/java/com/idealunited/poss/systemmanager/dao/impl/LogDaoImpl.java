package com.idealunited.poss.systemmanager.dao.impl;

import com.idealunited.inf.dao.Page;
import com.idealunited.inf.dao.impl.BaseDAOImpl;
import com.idealunited.poss.security.model.AccessLog;
import com.idealunited.poss.systemmanager.dao.ILogDao;

public class LogDaoImpl extends BaseDAOImpl<AccessLog> implements ILogDao {

	@Override
	public Page<AccessLog> search(Page<AccessLog> page, AccessLog log) {
		return findByQuery("search", page, log);
	}
}
