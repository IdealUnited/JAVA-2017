package com.idealunited.poss.systemmanager.model;

import com.idealunited.inf.model.BaseObject;


/**
 * url资源
 * 
 * @author wucan
 * @version
 * @data 2010-7-22 上午10:43:33
 */
public class ResUrl extends BaseObject{

	private static final long serialVersionUID = 1L;
	
	private Long resKy;
	private String url;

	public Long getResKy() {
		return resKy;
	}

	public void setResKy(Long resKy) {
		this.resKy = resKy;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}