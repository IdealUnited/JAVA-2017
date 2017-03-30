package com.idealunited.poss.systemmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.inf.dao.Page;
import com.idealunited.poss.systemmanager.dao.impl.RoleDaoImpl;
import com.idealunited.poss.systemmanager.dao.impl.UserDaoImpl;
import com.idealunited.poss.systemmanager.formbean.RoleFormBean;
import com.idealunited.poss.systemmanager.formbean.UserFormBean;
import com.idealunited.poss.systemmanager.model.ResMenu;
import com.idealunited.poss.systemmanager.model.Role;
import com.idealunited.poss.systemmanager.model.RoleRes;
import com.idealunited.poss.systemmanager.model.UserInfo;
import com.idealunited.poss.systemmanager.model.UserRole;
import com.idealunited.poss.systemmanager.service.IMenuFactoryService;
import com.idealunited.poss.systemmanager.service.IRoleService;

public class RoleServiceImpl implements IRoleService {

	private final Log log = LogFactory.getLog(RoleServiceImpl.class);
	private RoleDaoImpl roleDao;
	private UserDaoImpl userDao;
	// private BaseDAO daoService;
	private IMenuFactoryService menuFactoryService;

	public boolean insertBatch(String roleKy, RoleFormBean roleFormBean,
			long[] resIds) throws Exception {

		ArrayList<Long> hasAddIds = new ArrayList<Long>();
		ArrayList<Long> hasDelIds = new ArrayList<Long>();
		if (StringUtils.isEmpty(roleKy)) {
			// 新增角色
			Role role = new Role();
			role.setRoleName(roleFormBean.getRoleName());
			role.setRoleRemarks(roleFormBean.getRoleRemarks());
			role.setRoleCode(System.currentTimeMillis() + "");
			role.setStatus(1);
			roleKy = "" + roleDao.insertRole(role);
			if (resIds != null)
				for (int i = 0; i < resIds.length; i++) {
					hasAddIds.add(resIds[i]);
				}
		} else {
			Role role = (Role) roleDao.findObjectByCriteria(
					"queryRoleByRoleKy", roleKy);
			role.setRoleName(roleFormBean.getRoleName());
			role.setRoleRemarks(roleFormBean.getRoleRemarks());
			roleDao.updateRole(role);
			List<Long> checkOldIds = menuFactoryService
					.queryCheckedMenuIdByRoleId(role.getRoleKy());

			for (int i = 0; i < checkOldIds.size(); i++) {
				long uId = checkOldIds.get(i);
				if (!ArrayUtils.contains(resIds, uId)) {
					hasDelIds.add(uId);
				}
			}
			for (int i = 0; i < resIds.length; i++) {
				long rid = resIds[i];
				if (!checkOldIds.contains(rid)) {
					hasAddIds.add(rid);
				}
			}

		}
		Long roleKey_ = Long.parseLong(roleKy);
		if (hasDelIds != null && hasDelIds.size() > 0) {
			List<RoleRes> delList = new ArrayList<RoleRes>();

			for (Long delId : hasDelIds) {
				RoleRes rr = new RoleRes();
				rr.setResKy(delId);
				rr.setRoleKy(roleKey_);
				delList.add(rr);
			}
			roleDao.batchCreate("deleteRoleByRoleAndRes", delList);
		}

		if (hasAddIds != null && hasAddIds.size() > 0) {
			List<RoleRes> rs = new ArrayList<RoleRes>();
			for (int i = 0; i < hasAddIds.size(); i++) {
				long addId = hasAddIds.get(i);
				RoleRes roleRes = new RoleRes();
				roleRes.setResKy(addId);
				roleRes.setRoleKy(new Long(roleKy));
				roleRes.setResTypePrefix("1");
				roleRes.setStatus(1);
				rs.add(roleRes);
			}
			roleDao.batchCreate("insertMenuOfRole", rs);
		}

		return true;
	}

	public boolean deleteRole(String roleKy) throws Exception {
		List<RoleRes> roleResList = roleDao.queryRoleResByRoleKy(new Long(
				roleKy));
		roleDao.deleteBatch("deleteRoleRes", roleResList);
		return roleDao.deleteRole(roleKy);
	}

	public Role queryRoleByRoleKy(String roleKy) {
		return (Role) roleDao.findObjectByCriteria("queryRoleByRoleKy", roleKy);
	}

	public boolean insertRole(Role role) {
		log.debug("RoleServiceImpl.insertRole() is running...");

		// role.setRoleKy(System.currentTimeMillis());
		role.setRoleCode("test");
		role.setStatus(1);

		boolean isSuccess = true;
		try {
			this.roleDao.insertRole(role);

		} catch (Exception e) {
			isSuccess = false;
			log.error("RoleServiceImpl.insertRole error...");
		}

		return isSuccess;
	}

	public List<Role> findAllRole() {
		log.debug("RoleServiceImpl.findAllRole() is running...");
		List<Role> roleList = new ArrayList<Role>();
		try {

			roleList = this.roleDao.findAllRole();

		} catch (Exception e) {
			log.error("RoleServiceImpl.findAllRole error...",e);
		}

		return roleList;
	}

	public List<RoleFormBean> allRole() {
		log.debug("RoleServiceImpl.findAllRole() is running...");
		List<RoleFormBean> roleList = new ArrayList<RoleFormBean>();
		try {

			roleList = this.roleDao.allRole();

		} catch (Exception e) {
			log.error("RoleServiceImpl.findAllRole error...");
		}

		return roleList;
	}

	public List<ResMenu> queryMenuOfRole(String roleId) {
		log.debug("RoleServiceImpl.queryMenuOfRole() is running...");
		List<ResMenu> menuList = new ArrayList<ResMenu>();
		try {

			menuList = this.roleDao.queryMenuOfRole(roleId);
			log.debug("RoleServiceImpl.queryMenuOfRole().menuList.size=="
					+ menuList.size());
		} catch (Exception e) {
			log.error("RoleServiceImpl.queryMenuOfRole error...");
		}

		return menuList;
	}

	public List<ResMenu> findAllMenu() {
		log.debug("RoleServiceImpl.findAllMenu() is running...");
		List<ResMenu> menuList = new ArrayList<ResMenu>();
		try {

			menuList = this.roleDao.findAllMenu();
			log.debug("RoleServiceImpl.findAllMenu().menuList.size=="
					+ menuList.size());

		} catch (Exception e) {
			log.error("RoleServiceImpl.findAllMenu error...");
		}

		return menuList;
	}

	public List<Role> queryRole(Map<String, String> map) {
		log.debug("RoleServiceImpl.queryRole is running...");
		List<Role> roleList = new ArrayList<Role>();
		try {

			roleList = this.roleDao.queryRole(map);

		} catch (Exception e) {
			log.error("RoleServiceImpl.queryRole error...");
		}

		return roleList;

	}

	// 条件查询分页
	public Page<Role> search(Page<Role> page, Map<String, String> map) {
		return roleDao.search(page, map);
	}

	public boolean dropRoleById(String id) {
		log.debug("RoleServiceImpl.dropRoleById is running...");
		boolean sign = true;
		try {

			sign = this.roleDao.dropRoleById(id);

		} catch (Exception e) {
			log.error("RoleServiceImpl.dropRoleById error...");

		}

		return sign;
	}

	public boolean insertMenuOfRole(RoleRes roleRes) {
		log.debug("RoleServiceImpl.insertMenuOfRole is running...");
		boolean sign = true;
		try {

			this.roleDao.insertMenuOfRole(roleRes);

		} catch (Exception e) {
			log.error("RoleServiceImpl.insertMenuOfRole error...");
			sign = false;
		}

		return sign;

	}

	public boolean insertRoleOfUser(UserRole userRole) {
		log.debug("RoleServiceImpl.insertRoleOfUser is running...");
		boolean sign = true;
		try {

			this.roleDao.insertRoleOfUser(userRole);

		} catch (Exception e) {
			log.error("RoleServiceImpl.insertRoleOfUser error...",e);
			sign = false;
		}

		return sign;

	}

	public boolean dropMenuOfRole(Map<String, String> map) {
		log.debug("RoleServiceImpl.dropMenuOfRole is running...");
		boolean sign = true;
		try {

			this.roleDao.dropMenuOfRole(map);

		} catch (Exception e) {
			log.error("RoleServiceImpl.dropMenuOfRole error...",e);
			sign = false;
		}

		return sign;

	}

	public boolean dropRoleOfUser(Map<String, String> map) {
		log.debug("RoleServiceImpl.dropRoleOfUser is running...");
		boolean sign = true;
		try {

			this.roleDao.dropRoleOfUser(map);

		} catch (Exception e) {
			log.error("RoleServiceImpl.dropRoleOfUser error...",e);
			sign = false;
		}

		return sign;

	}

	public List<UserInfo> findAllUser() {
		log.debug("RoleServiceImpl.findAllUser() is running...");
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		try {

			List<UserFormBean> userList = this.userDao.queryByMap(null);
			for (int i = 0; i < userList.size(); i++) {
				UserFormBean userFormBean = userList.get(i);
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(userFormBean.getUserKy());
				userInfo.setUserName(userFormBean.getUserName());
				userInfoList.add(userInfo);
			}

		} catch (Exception e) {
			log.error("RoleServiceImpl.findAllUser error...",e);
		}

		return userInfoList;

	}

	public List<Role> queryRoleOfUser(String userId) {
		log.debug("RoleServiceImpl.queryRoleOfUser() is running...");
		List<Role> roleList = new ArrayList<Role>();
		try {

			roleList = this.roleDao.queryRoleOfUser(userId);
			log.debug("RoleServiceImpl.queryRoleOfUser().roleList.size=="
					+ roleList.size());
		} catch (Exception e) {
			log.error("RoleServiceImpl.queryRoleOfUser error...",e);
		}

		return roleList;

	}

	public void setRoleDao(RoleDaoImpl roleDao) {
		this.roleDao = roleDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public void setMenuFactoryService(IMenuFactoryService menuFactoryService) {
		this.menuFactoryService = menuFactoryService;
	}

	@Override
	public List<Map<String, Object>> queryUsersRoleByRoleCode(String roleCode) {
		return roleDao.queryUsersRoleByRoleCode(roleCode);
	}

}
