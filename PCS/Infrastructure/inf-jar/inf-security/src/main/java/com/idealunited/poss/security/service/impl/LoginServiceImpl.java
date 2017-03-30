package com.idealunited.poss.security.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.poss.security.commons.SecurityConstants;
import com.idealunited.poss.security.service.ILoginService;
import com.idealunited.poss.security.service.ISecurityStrategyService;
import com.idealunited.poss.security.service.IUserService;

public class LoginServiceImpl implements ILoginService {
	private Log logger = LogFactory.getLog(getClass());

	private ISecurityStrategyService securityStrategyService;
	private IUserService delegateUserService;
	private BaseDAO daoService;

	/**
	 * @param daoService
	 *            the daoService to set
	 */
	public void setDaoService(BaseDAO daoService) {
		this.daoService = daoService;
	}

	@Override
	public Map getLoginLogByLoginCode(String userName) throws Exception {
		return delegateUserService.getUserByLoginCode(userName);
	}

	@Override
	public void saveLoginLog(Map loginInfo) throws Exception {

	}

	@Override
	public String checkFailedLoginCountStatus(String userName,
			int failedLoginCount) {
		try {
			// 获取该用户安全策略中允许的最大登录失败次数
			int maxFailedLoginCount = securityStrategyService
					.getMaxFailedLoginCount(userName);
			if (failedLoginCount >= maxFailedLoginCount) {
				return SecurityConstants.ACEGI_EXCEPTION_MAPPING_USER_LOCKED;
			} else {
				return SecurityConstants.ACEGI_SUCCESS;
			}
		} catch (Exception e) {// 找不到对应的安全策略
			logger.error("检查用户失败登录次数出现错误 [" + userName + "]");
			return SecurityConstants.ACEGI_EXCEPTION_MAPPING_SECURITY_STRATETY_NOT_FOUND;
		}
	}

	public void setSecurityStrategyService(
			ISecurityStrategyService securityStrategyService) {
		this.securityStrategyService = securityStrategyService;
	}

	public void setDelegateUserService(IUserService delegateUserService) {
		this.delegateUserService = delegateUserService;
	}

	@Override
	public boolean updateUserLoginInfo(String loginId, String ipAttr,
			Date loginTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("loginId", loginId);
		map.put("loginIp", ipAttr);
		map.put("loginTime", loginTime);
		return daoService.update("security.updateUserLoginInfo", map);
	}

}
