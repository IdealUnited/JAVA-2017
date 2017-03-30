package com.idealunited.poss.security.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.model.AccessAuthority;
import com.idealunited.poss.security.service.IAccessAuthorityService;

public class AccessAuthrityServiceImpl implements IAccessAuthorityService {

	private Log logger = LogFactory.getLog(getClass());
	private BaseDAO daoService;
	private String namespace;

	/**
	 * @param namespace
	 *            the namespace to set
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setDaoService(BaseDAO daoService) {
		this.daoService = daoService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idealunited.poss.security.service.IAccessAuthorityService#createAccessAuthority
	 * (com.idealunited.poss.security.model.AccessAuthority)
	 */
	@Override
	public void createAccessAuthority(AccessAuthority accessAuthority) {
		daoService.create(namespace.concat("create"), accessAuthority);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idealunited.poss.security.service.IAccessAuthorityService#findPage()
	 */
	@Override
	public Page<AccessAuthority> search(Page<AccessAuthority> page,
			AccessAuthority authority) {
		return daoService.findByQuery(namespace.concat("search"), page,
				authority);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idealunited.poss.security.service.IAccessAuthorityService#updateAccessAuthority
	 * (com.idealunited.poss.security.model.AccessAuthority)
	 */
	@Override
	public boolean updateAccessAuthority(AccessAuthority accessAuthority) {
		return daoService.update(namespace.concat("update"), accessAuthority);
	}

	@Override
	public AccessAuthority getAccessAuthorityById(Long id) {
		List<AccessAuthority> list = daoService.findByQuery(
				namespace.concat("findById"), id);
		if (list.size() == 1)
			return list.get(0);
		return null;
	}

	@Override
	public boolean deleteAccessAuthority(Long id) {

		return daoService.delete(namespace.concat("delete"), id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idealunited.poss.security.service.IAccessAuthorityService#validateIp(java
	 * .lang.String)
	 */
	@Override
	public boolean validateIp(String ip) {
//		AccessAuthority authority = new AccessAuthority();
//		authority.setStatus(1);
//		authority.setAuthIp(ip);
//		int count = (Integer) daoService.findObjectByCriteria(
//				namespace.concat("existsAuthIp"), authority);
//		return count > 0;
		return true;
	}

}
