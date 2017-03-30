package com.idealunited.poss.systemmanager.dao;

import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.model.AccessLog;

public interface ILogDao extends BaseDAO<AccessLog> {
	
	public Page<AccessLog> search(Page<AccessLog> page,AccessLog log);
 	
}
