package com.idealunited.poss.security.model;

import java.util.Date;


/**
 * 操作日志表
 * @author 戴德荣
 * @date 2010-11-15 
 *
 */
public class AccessLog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Long sequenceId;//
	
	private String loginUser; //操作用户的登录名称
	
	private String userIp; //操作用户的ip
	
	private Date creationDate;
	
	private String actionUrl; //
	
	private String urlMethod;
	
	private String postParams; 
	
	

	
	
	/**
	 * @return the sequenceId
	 */
	public Long getSequenceId() {
		return sequenceId;
	}

	/**
	 * @param sequenceId the sequenceId to set
	 */
	public void setSequenceId(Long sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * @return the loginUser
	 */
	public String getLoginUser() {
		return loginUser;
	}

	/**
	 * @param loginUser the loginUser to set
	 */
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * @return the userIp
	 */
	public String getUserIp() {
		return userIp;
	}

	/**
	 * @param userIp the userIp to set
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the actionUrl
	 */
	public String getActionUrl() {
		return actionUrl;
	}

	/**
	 * @param actionUrl the actionUrl to set
	 */
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	/**
	 * @return the urlMethod
	 */
	public String getUrlMethod() {
		return urlMethod;
	}

	/**
	 * @param urlMethod the urlMethod to set
	 */
	public void setUrlMethod(String urlMethod) {
		this.urlMethod = urlMethod;
	}

	/**
	 * @return the postParams
	 */
	public String getPostParams() {
		return postParams;
	}

	/**
	 * @param postParams the postParams to set
	 */
	public void setPostParams(String postParams) {
		this.postParams = postParams;
	}

	
	

}
