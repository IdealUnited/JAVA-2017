package com.idealunited.poss.systemmanager.dao;

import java.util.List;
import java.util.Map;

public interface IRoleDao {
	/**
	 * 得到对应角色所有userKy以及角色姓名
	 * @param roleCode String 角色id 
	 * @return  <Map<String,Object>>
	 * key value
	 * USER_KEY 用户id Long
	 * ROLE_NAME 角色名称 String
	 */
	List<Map<String,Object>> queryUsersRoleByRoleCode(String roleCode);
}
