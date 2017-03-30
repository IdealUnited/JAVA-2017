package com.idealunited.poss.security.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
	public static final String REFER_ID = "UserService";

	public Map<?, ?> getUserByLoginCode(String username) throws Exception;

	public List<?> queryRolesByLoginCode(String loginCode) throws Exception;

	/**
	 * 能过用户的登录名查找资源列表
	 * 
	 * @param loginCode
	 * @return List<String>
	 * @throws PossUntxException
	 */
	public List<String> queryResesByLoginCode(String loginCode)
			throws Exception;

}
