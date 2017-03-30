package com.idealunited.poss.systemmanager.service;

import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.model.AccessLog;

public interface ILogService {

	public Page<AccessLog> search(Page<AccessLog> page, AccessLog log);
}
