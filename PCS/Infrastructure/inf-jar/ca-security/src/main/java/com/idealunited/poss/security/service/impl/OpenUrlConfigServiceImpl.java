package com.idealunited.poss.security.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.poss.security.model.OpenUrlConfig;
import com.idealunited.poss.security.model.OpenUrlIpGroup;
import com.idealunited.poss.security.service.IOpenUrlConfigService;

public class OpenUrlConfigServiceImpl implements IOpenUrlConfigService {

	private Log logger = LogFactory.getLog(getClass());
	private BaseDAO daoService;
	private String namespace;

	/**
	 * @param daoService
	 *            the daoService to set
	 */
	public void setDaoService(BaseDAO daoService) {
		this.daoService = daoService;
	}

	/**
	 * @param namespace
	 *            the namespace to set
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idealunited.poss.security.service.IOpenUrlConfigService#findOpenUrlConfigByUrl
	 * (java.lang.String)
	 */
	@Override
	public OpenUrlConfig findOpenUrlConfigByUrl(String url) {

		OpenUrlConfig obj = (OpenUrlConfig) daoService.findObjectByCriteria(
				namespace.concat("findByUrl"), url);
		if (obj != null) {
			List<OpenUrlIpGroup> listIpGroup = daoService.findByQuery(
					namespace.concat("findIpGroupByConfigId"),
					obj.getConfigId());
			obj.setIpGroups(listIpGroup);
		}

		return obj;
	}

	@Override
	public List<String> findAllOpenUrls() {
		return daoService
				.findByQuery(namespace.concat("findAllOpenUrls"), null);
	}

	@Override
	public OpenUrlConfig createOpenUrlConfig(OpenUrlConfig openUrlConfig) {
		List<OpenUrlIpGroup> ipGroups = openUrlConfig.getIpGroups();
		long key = (Long) daoService.create(
				namespace.concat("createOpenUrlConfig"), openUrlConfig);
		if (ipGroups != null) {
			for (OpenUrlIpGroup ipGroup : ipGroups) {
				ipGroup.setConfigId(key);
				daoService.create(namespace.concat("createIpGroup"), ipGroup);
			}
		}
		openUrlConfig.setConfigId(key);
		return openUrlConfig;
	}

	@Override
	public void updateIpGroup(OpenUrlIpGroup ipGroup) {
		daoService.update(namespace.concat("updateIpGroup"), ipGroup);
	}

	@Override
	public void updateOpenUrlConfig(OpenUrlConfig openUrlConfig) {
		daoService.update(namespace.concat("updateOpenUrlConfig"),
				openUrlConfig);
	}

	@Override
	public OpenUrlIpGroup createIpGroup(OpenUrlIpGroup ipGroup) {
		long key = (Long) daoService.create(namespace.concat("createIpGroup"),
				ipGroup);
		ipGroup.setGroupId(key);
		return ipGroup;
	}

	@Override
	public boolean removeIpGroup(Long key) {
		return daoService.delete(namespace.concat("deleteIpGroup"), key);
	}

	@Override
	public boolean removeUrlConfig(Long configId) {
		daoService.delete(namespace.concat("deleteIpGroupByConfig"), configId);
		return daoService.delete(namespace.concat("deleteUrlConfig"), configId);
	}

}
