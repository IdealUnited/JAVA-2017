package com.idealunited.poss.security.service;

import java.util.List;

import com.idealunited.poss.security.model.OpenUrlConfig;
import com.idealunited.poss.security.model.OpenUrlIpGroup;

public interface IOpenUrlConfigService {
	/**
	 * 通过url来得到openUrlConfig
	 * @param url
	 * @return 
	 * @date 2011-1-14
	 */
	public OpenUrlConfig findOpenUrlConfigByUrl(String url);
	
	/**
	 * 得到所有的开放的url
	 * @return List<String>
	 * @date 2011-1-17
	 */
	public List<String> findAllOpenUrls();
	
	/**
	 * 创建一个新的OpenUrlConfig
	 * @return  OpenUrlConfig
	 * @date 2011-1-18
	 */
	public OpenUrlConfig createOpenUrlConfig(OpenUrlConfig openUrlConfig);
	/**
	 * 更新OpenUrlConfig
	 * @param openUrlConfig 
	 * @date 2011-1-18
	 */
	public void  updateOpenUrlConfig(OpenUrlConfig openUrlConfig); 
	/**
	 * 更新ipGroup
	 * @param ipGroup 
	 * @date 2011-1-18
	 */
	public void updateIpGroup(OpenUrlIpGroup ipGroup);
	
	/**
	 * 创建一个ipGroup
	 * @param ipGroup
	 * @return 创建的对象
	 * @date 2011-1-18
	 */
	public OpenUrlIpGroup createIpGroup(OpenUrlIpGroup ipGroup);
	
	/**
	 * 删除IPGroup
	 * @param key 
	 * @date 2011-1-18
	 */
	public boolean removeIpGroup(Long key);
	

	/**
	 * 删除Urlconfig
	 * @param key 
	 * @date 2011-1-18
	 */
	public boolean removeUrlConfig(Long configId);
}
