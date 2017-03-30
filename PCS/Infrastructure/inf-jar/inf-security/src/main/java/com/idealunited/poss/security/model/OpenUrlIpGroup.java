package com.idealunited.poss.security.model;

import com.idealunited.poss.security.util.IpUtils;

/**
 * ip段
 * @author 戴德荣
 * @date 2011-1-14 
 */

public class OpenUrlIpGroup {
	private Long groupId;
	private String startIp;
	private String endIp;
	private Long configId;
	private Long startIpLong ;
	private Long endIpLong ;
	private String remark;
	

	public OpenUrlIpGroup() {
	}
	public OpenUrlIpGroup(String startIp, String endIp) {
		if(startIpLong > endIpLong ){
			throw new IllegalArgumentException("开始ip不能大于结束ip设置");
		}
		setStartIp(startIp);
		setEndIp(endIp) ;
	}

	/**
	 * @return the startIp
	 */
	public String getStartIp() {
		if(! IpUtils.validateIp(startIp)){
			throw new IllegalArgumentException("起始ip设置有误");
		}
		
		return startIp;
	}

	/**
	 * @return the endIp
	 */
	public String getEndIp() {
		if(! IpUtils.validateIp(endIp)){
			throw new IllegalArgumentException("结束ip设置有误");
		}
		
		return endIp;
	}
	/**
	 * 判断ip地址是否在这个范围内
	 * @param ip
	 * @return 
	 * @date 2011-1-14
	 */
	public boolean containIp(String ip){
		long ipLongValue =  IpUtils.ipLongValue(ip);
		return ipLongValue <= endIpLong && ipLongValue >= startIpLong;
	}

	/**
	 * @return the startIpLong
	 */
	public Long getStartIpLong() {
		return startIpLong;
	}

	/**
	 * @param startIpLong the startIpLong to set
	 */
	public void setStartIpLong(Long startIpLong) {
		this.startIpLong = startIpLong;
	}

	/**
	 * @return the endIpLong
	 */
	public Long getEndIpLong() {
		return endIpLong;
	}

	/**
	 * @param endIpLong the endIpLong to set
	 */
	public void setEndIpLong(Long endIpLong) {
		this.endIpLong = endIpLong;
	}

	
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @param startIp the startIp to set
	 */
	public void setStartIp(String startIp) {
		startIpLong = IpUtils.ipLongValue(startIp);
		this.startIp = startIp;
	}

	/**
	 * @param endIp the endIp to set
	 */
	public void setEndIp(String endIp) {
		endIpLong = IpUtils.ipLongValue(endIp);
		this.endIp = endIp;
	}
	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the configId
	 */
	public Long getConfigId() {
		return configId;
	}
	/**
	 * @param configId the configId to set
	 */
	public void setConfigId(Long configId) {
		this.configId = configId;
	}
	
	
	
	
	
	
}
