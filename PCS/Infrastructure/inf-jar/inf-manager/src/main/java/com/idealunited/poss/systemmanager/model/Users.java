package com.idealunited.poss.systemmanager.model;

import java.util.Date;

import com.idealunited.inf.model.BaseObject;

/**
 * 用户
 * 
 * @author wucan
 * @version
 * @data 2010-7-22 上午10:51:31
 */
public class Users extends BaseObject {

	private static final long serialVersionUID = 1L;
	private Long userKy;
	private String loginId;
	private Integer loginChannel;
	private String password;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Date pswdExpiredTime;
	private String allowIp;
	private Integer failLoginNum;
	private int status;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userMobile;
	private String userRTX;
	private String orgCode;
	private String dutyCode;
	private String repositoryId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Integer getLoginChannel() {
		return loginChannel;
	}

	public void setLoginChannel(Integer loginChannel) {
		this.loginChannel = loginChannel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getPswdExpiredTime() {
		return pswdExpiredTime;
	}

	public void setPswdExpiredTime(Date pswdExpiredTime) {
		this.pswdExpiredTime = pswdExpiredTime;
	}

	public String getAllowIp() {
		return allowIp;
	}

	public void setAllowIp(String allowIp) {
		this.allowIp = allowIp;
	}

	public Integer getFailLoginNum() {
		return failLoginNum;
	}

	public void setFailLoginNum(Integer failLoginNum) {
		this.failLoginNum = failLoginNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail
	 *            the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone
	 *            the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * @return the userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}

	/**
	 * @param userMobile
	 *            the userMobile to set
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	/**
	 * @return the userRTX
	 */
	public String getUserRTX() {
		return userRTX;
	}

	/**
	 * @param userRTX
	 *            the userRTX to set
	 */
	public void setUserRTX(String userRTX) {
		this.userRTX = userRTX;
	}

	/**
	 * @return the userKy
	 */
	public Long getUserKy() {
		return userKy;
	}

	/**
	 * @param userKy
	 *            the userKy to set
	 */
	public void setUserKy(Long userKy) {
		this.userKy = userKy;
	}

	/**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode
	 *            the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return the dutyCode
	 */
	public String getDutyCode() {
		return dutyCode;
	}

	/**
	 * @param dutyCode
	 *            the dutyCode to set
	 */
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

}