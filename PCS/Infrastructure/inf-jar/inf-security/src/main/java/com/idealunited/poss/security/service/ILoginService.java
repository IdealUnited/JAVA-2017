package com.idealunited.poss.security.service;

import java.util.Date;
import java.util.Map;

public interface ILoginService {
	public static final String REFER_ID = "LoginService";

	public Map getLoginLogByLoginCode(String userName) throws Exception;

	public String checkFailedLoginCountStatus(String userName,
			int failedLoginCount);

	public void saveLoginLog(Map loginInfo) throws Exception;

	/**
	 * 根据登录名修改最后登录信息
	 * 
	 * @param loginId
	 *            　登录的人
	 * @param ip
	 *            　登录用户ip
	 * @param loginTime
	 *            　登录的时间
	 * @return 　true/false
	 * @author 戴德荣
	 * @date 2011-1-5
	 */
	public boolean updateUserLoginInfo(String loginId, String ip, Date loginTime);

}
