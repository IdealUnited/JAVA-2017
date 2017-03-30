package com.idealunited.poss.systemmanager.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.inf.dao.Page;
import com.idealunited.inf.dao.impl.BaseDAOImpl;
import com.idealunited.poss.systemmanager.dao.IRoleDao;
import com.idealunited.poss.systemmanager.model.Role;
import com.idealunited.poss.systemmanager.model.RoleRes;
import com.idealunited.poss.systemmanager.model.UserRole;

public class RoleDaoImpl extends BaseDAOImpl implements IRoleDao {

	private final Log log = LogFactory.getLog(RoleDaoImpl.class);

	public Long insertRole(Role role) {
		log.debug("RoleDaoImpl.insertRole is running...");
		Long roleId = (Long) create("insertRole", role);
		return roleId;
	}

	// 条件分页查询
	public Page<Role> search(Page<Role> page, Map<String, String> map) {
		return findByQuery("queryRole", page, map);
	}

	public List findAllRole() {
		log.debug("RoleDaoImpl.findAllRole is running...");
		return findByCriteria("findAllRole",new HashMap());
	}

	public List allRole() {
		log.debug("RoleDaoImpl.findAllRole is running...");
		return findByCriteria("allRole",new HashMap());
	}

	public List queryRole(Map map) {
		log.debug("RoleDaoImpl.queryRole is running...");
		return findByCriteria("queryRole", map);
	}

	public boolean dropRoleById(String id) {
		log.debug("RoleDaoImpl.dropRoleById is running...");
		return this.delete(Long.parseLong(id));
	}

	public List queryMenuOfRole(String roleId) {
		log.debug("RoleDaoImpl.queryMenuOfRole is running...");
		return findByCriteria("queryMenuOfRole", roleId);
	}

	public List queryRoleOfUser(String userId) {
		log.debug("RoleDaoImpl.queryRoleOfUser is running...");
		return findByCriteria("queryRoleOfUser", userId);
	}

	public List findAllMenu() {
		log.debug("RoleDaoImpl.findAllMenu is running...");
		return findByCriteria("findAllMenu",new HashMap());
	}

	public void dropMenuOfRole(Map map) {
		log.debug("RoleDaoImpl.dropMenuOfRole is running...");
		delete("dropMenuOfRole", map);
	}

	public void dropRoleOfUser(Map<String, String> map) {
		log.debug("RoleDaoImpl.dropRoleOfUser is running...");
		delete("dropRoleOfUser", map);
	}

	public void insertMenuOfRole(RoleRes roleRes) {
		log.debug("RoleDaoImpl.insertMenuOfRole is running...");
		create("insertMenuOfRole", roleRes);
	}

	public void insertRoleOfUser(UserRole userRole) {
		log.debug("RoleDaoImpl.insertRoleOfUser is running...");
		create("insertRoleOfUser", userRole);
	}

	public List<RoleRes> queryRoleResByRoleKy(Long roleKy) {
		return findByCriteria("queryRoleRes", roleKy);
	}

	public boolean updateRole(Role role) {
		return update("updateRole", role);
	}

	public boolean updateRoleRes(RoleRes roleRes) {
		return update("updateRoleRes", roleRes);
	}

	public boolean deleteRole(String roleKy) {
		return delete("deleteRole", roleKy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.idealunited.poss.systemmanager.dao.IRoleDao#queryUsersRoleByRoleKy(java.lang
	 * .Long)
	 */
	@Override
	public List<Map<String, Object>> queryUsersRoleByRoleCode(String roleCode) {
		log.debug("RoleDaoImpl.queryUsersRoleByRoleKy is running...");
		return findByCriteria("queryUsersRoleByRoleKy", roleCode);
	}
}
