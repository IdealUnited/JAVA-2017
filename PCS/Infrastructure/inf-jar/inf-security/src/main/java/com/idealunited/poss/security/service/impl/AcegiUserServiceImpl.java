package com.idealunited.poss.security.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.poss.security.model.SessionUserHolder;
import com.idealunited.poss.security.service.IUserService;

public class AcegiUserServiceImpl implements IUserService {
	private Log logger = LogFactory.getLog(getClass());

	private BaseDAO daoService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Map userInfo;
		try {
			userInfo = getUserByLoginCode(username);
		} catch (Exception e) {
			logger.error("查找用户失败 [" + username + "]", e);
			throw new UsernameNotFoundException("查找用户失败 [" + username + "]");
		}
		if (userInfo == null) {
			logger.error("找不到用户 [" + username + "]");
			throw new UsernameNotFoundException("找不到用户 [" + username + "]");
		}

		List<String> roles = new ArrayList<String>();
		try {
			roles = queryRolesByLoginCode(username);
		} catch (Exception e) {
			logger.error("查找用户对应角色失败 [" + username + "]", e);
			throw new UsernameNotFoundException("查找用户对应角色失败 [" + username + "]");
		}
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		for (String roleCode : roles) {
			auths.add(new GrantedAuthorityImpl(roleCode));
		}
		GrantedAuthority[] arrayAuths = auths
				.toArray(new GrantedAuthority[auths.size()]);

		SessionUserHolder suh = new SessionUserHolder(userInfo.get("USER_KY")
				+ "", username, userInfo.get("PASSWORD") + "", arrayAuths);
		suh.setLoginTime(new Date());
		return suh;
	}

	@Override
	public List<String> queryRolesByLoginCode(String username) throws Exception {
		try {
			return daoService.findByQuery("security.queryRolesByLoginCode",
					username);
		} catch (DataAccessException e) {
			logger.error("无法查询到登录号对应的角色",e);
			throw new Exception("无法查询到登录号对应的角色");
		}
	}

	@Override
	public Map<?, ?> getUserByLoginCode(String username) throws Exception {
		try {
			return (Map<?, ?>) daoService.findObjectByCriteria(
					"security.getUserByLoginCode", username);
		} catch (Exception e) {
			logger.error("查询数据库异常:",e);
			throw new Exception("查询数据库出异常");
		}
	}

	@Override
	public List<String> queryResesByLoginCode(String loginCode)
			throws Exception {
		try {
			return daoService.findByQuery("security.queryResesByLoginCode",
					loginCode);
		} catch (Exception e) {
			logger.error("security.queryResesByLoginCode：查询权限异常", e);
			throw new Exception("security.queryResesByLoginCode：查询权限异常");
		}
	}

	public void setDaoService(BaseDAO<?> daoService) {
		this.daoService = daoService;
	}

}
