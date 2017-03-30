package com.idealunited.poss.systemmanager.service.impl;

import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.model.AccessLog;
import com.idealunited.poss.systemmanager.dao.ILogDao;
import com.idealunited.poss.systemmanager.service.ILogService;

public class LogServiceImpl implements ILogService {

	private ILogDao logDao;

	@Override
	public Page<AccessLog> search(Page<AccessLog> page, AccessLog log) {
		return logDao.search(page, log);
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

}
