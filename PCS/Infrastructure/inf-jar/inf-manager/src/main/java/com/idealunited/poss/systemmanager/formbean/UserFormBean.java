package com.idealunited.poss.systemmanager.formbean;

import org.springframework.util.StringUtils;

/**
 * 用户管理FormBean
 * 
 * @author wucan
 * @descript
 * @data 2010-7-23 上午09:38:12
 */
public class UserFormBean {

	private String userKy;
	private Long userId; // 用户ky
	private String userName; // 用户姓名
	private String userCode; // 用户账号
	private String userPassword; // 密码
	private String changePassword; // 修改密码
	private int userStatus; // 状态
	private String userOrgCode; // 部门/机构
	private String userDutyCode; // 职位
	private String userOrgName; // 部门名称
	private String userDutyName; // 职位名称
	private String userEmail; // mail
	private String userPhone; // 电话
	private String userMobile; // 手机
	private String userRTX; // RTX号码
	private String lastLoginTime; // 最后登录时间

	private String repositoryId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserKy() {
		return userKy;
	}

	public void setUserKy(String userKy) {
		this.userKy = userKy;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userOrgCode
	 */
	public String getUserOrgCode() {
		return userOrgCode;
	}

	/**
	 * @param userOrgCode
	 *            the userOrgCode to set
	 */
	public void setUserOrgCode(String userOrgCode) {
		this.userOrgCode = userOrgCode;
	}

	/**
	 * @return the userDutyCode
	 */
	public String getUserDutyCode() {
		return userDutyCode;
	}

	/**
	 * @param userDutyCode
	 *            the userDutyCode to set
	 */
	public void setUserDutyCode(String userDutyCode) {
		this.userDutyCode = userDutyCode;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(String changePassword) {
		this.changePassword = changePassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserRTX() {
		return userRTX;
	}

	public void setUserRTX(String userRTX) {
		this.userRTX = userRTX;
	}

	public String getUserStatusName() {
		String statusName = "";
		switch (userStatus) {
		case 1:
			statusName = "激活";
			break;
		case 0:
			statusName = "禁用";
			break;
		}
		return statusName;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserOrgName() {
		return userOrgName;
	}

	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}

	public String getUserDutyName() {
		return userDutyName;
	}

	public void setUserDutyName(String userDutyName) {
		this.userDutyName = userDutyName;
	}

	public String getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	public String getLastLoginTimeFmt() {
		if (StringUtils.hasText(lastLoginTime)
				&& lastLoginTime.indexOf(".") != -1) {
			return lastLoginTime.substring(0, lastLoginTime.lastIndexOf("."));
		}
		return lastLoginTime;
	}

}
