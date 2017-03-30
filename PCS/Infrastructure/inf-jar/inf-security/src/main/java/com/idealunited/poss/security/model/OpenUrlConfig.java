/**
 * 
 */
package com.idealunited.poss.security.model;

import java.util.List;

/**
 * 开放的urlDTO
 * @author 戴德荣
 * @date 2011-1-14 
 *
 */
public class OpenUrlConfig {
	private Long  configId;
	private String openUrl;
	private Integer ipCheck;
	private String ips;
	private List<OpenUrlIpGroup> ipGroups;
	private String remark;
	
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
	/**
	 * @return the openUrl
	 */
	public String getOpenUrl() {
		return openUrl;
	}
	/**
	 * @param openUrl the openUrl to set
	 */
	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}
	/**
	 * @return the ipCheck
	 */
	public Integer getIpCheck() {
		return ipCheck;
	}
	/**
	 * @param ipCheck the ipCheck to set
	 */
	public void setIpCheck(Integer ipCheck) {
		this.ipCheck = ipCheck;
	}
	/**
	 * @return the ips
	 */
	public String getIps() {
		return ips;
	}
	/**
	 * @param ips the ips to set
	 */
	public void setIps(String ips) {
		this.ips = ips;
	}
	/**
	 * @return the ipGroups
	 */
	public List<OpenUrlIpGroup> getIpGroups() {
		return ipGroups;
	}
	/**
	 * @param ipGroups the ipGroups to set
	 */
	public void setIpGroups(List<OpenUrlIpGroup> ipGroups) {
		this.ipGroups = ipGroups;
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
	
	
	
	
}
