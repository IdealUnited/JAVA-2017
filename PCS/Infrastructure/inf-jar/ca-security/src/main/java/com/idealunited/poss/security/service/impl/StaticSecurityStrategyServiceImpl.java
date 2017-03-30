package com.idealunited.poss.security.service.impl;

import com.idealunited.poss.security.commons.SecurityConstants;
import com.idealunited.poss.security.service.ISecurityStrategyService;

public class StaticSecurityStrategyServiceImpl implements
		ISecurityStrategyService {

	@Override
	public int getMaxFailedLoginCount(String loginId) throws Exception {
		return SecurityConstants.DEFAULT_SEC_STRATEGY_MAX_FAILED_LOGIN_COUNT;
	}

	@Override
	public int getPasswordValidDays(String loginId) throws Exception {
		return SecurityConstants.DEFAULT_SEC_STRATEGY_PASSWORD_VALID_DAYS;
	}

}
