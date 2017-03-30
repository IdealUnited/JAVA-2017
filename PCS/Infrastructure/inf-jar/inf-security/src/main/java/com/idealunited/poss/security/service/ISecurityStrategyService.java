package com.idealunited.poss.security.service;

/**
 * 安全策略接口
 * 
 * @author rick_lv
 * @create 下午03:01:51
 * @version 0.8
 */
public interface ISecurityStrategyService {
	public static final String REFER_ID = "SecurityStrategyService";

	/**
	 * 获取用户当前安全策略中的最大登录失败次数
	 * 
	 * @param loginId
	 * @return
	 * @throws PlatformDaoException
	 */
	public int getMaxFailedLoginCount(String loginId) throws Exception;

	/**
	 * 获取用户当前安全策略中密码有效天数
	 * 
	 * @param loginId
	 * @return
	 * @throws PlatformDaoException
	 */
	public int getPasswordValidDays(String loginId) throws Exception;
}
