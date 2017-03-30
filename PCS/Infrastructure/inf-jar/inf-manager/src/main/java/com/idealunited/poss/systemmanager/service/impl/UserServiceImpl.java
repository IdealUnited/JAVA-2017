package com.idealunited.poss.systemmanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;

import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.model.SessionUserHolder;
import com.idealunited.poss.systemmanager.dao.IUserDao;
import com.idealunited.poss.systemmanager.formbean.UserFormBean;
import com.idealunited.poss.systemmanager.model.Duty;
import com.idealunited.poss.systemmanager.model.Org;
import com.idealunited.poss.systemmanager.model.Users;
import com.idealunited.poss.systemmanager.service.IUserService;

/**
 * 用户管理Service
 * 
 * @author wucan
 * @descript
 * @data 2010-7-22 下午05:58:47
 */
public class UserServiceImpl implements IUserService {

	private Map DutyAndOrgmap = new HashMap();

	private final Log logger = LogFactory.getLog(getClass());

	private IUserDao userDAO;

	// 注入DAO
	public void setUserDAO(IUserDao userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public boolean deleteUser(String id) {
		Users user = new Users();
		user.setStatus(0);
		user.setUserKy(new Long(id));
		return userDAO.deleteUser(user);
	}

	@Override
	public Users findUserById(String id) {
		return (Users) userDAO.findById(new Long(id));
	}

	@Override
	public List<Users> findAllUsers() {
		return userDAO.findAllUsers();
	}

	@Override
	public void saveUser(UserFormBean userFormBean) throws Exception {

		try {
			// 新增用户
			Users user = new Users();
			setFormBeanToUser(userFormBean, user);
			userDAO.create(user);
			logger.info("新增用户" + user.getLoginId() + "--" + user.getUserName());
		} catch (Exception ex) {
			logger.error("新增用户关联自然人、员工、岗位");
			ex.printStackTrace();
			throw new Exception("新增用户关联自然人、员工、岗位");
		}
	}

	@Override
	public boolean updateUser(UserFormBean userFormBean) throws Exception {

		try {
			// 新增用户
			Users user = (Users) userDAO.findById(new Long(userFormBean
					.getUserKy()));
			setFormBeanToUser(userFormBean, user);
			userDAO.update(user);
		} catch (Exception ex) {
			logger.error(ex);
			logger.error("修改用户关联自然人、员工、岗位", ex);
			throw new Exception("修改用户关联自然人、员工、岗位");
		}

		return true;
	}

	@Override
	public List<UserFormBean> queryAll(Map<String, String> map) {
		return userDAO.queryByMap(map);
	}

	// 条件查询分页
	public Page<UserFormBean> search(Page<UserFormBean> page,
			Map<String, String> map) {
		return userDAO.search(page, map);
	}

	@Override
	public Map queryAllDutyAndOrg() {

		if (!DutyAndOrgmap.containsKey("dutys")) {
			List<Duty> dutyList = userDAO.queryAllDuty();
			DutyAndOrgmap.put("dutys", dutyList);
		}
		if (!DutyAndOrgmap.containsKey("org")) {
			List<Org> orgList = userDAO.queryAllOrg();
			DutyAndOrgmap.put("orgs", orgList);
		}
		return DutyAndOrgmap;
	}

	@Override
	public UserFormBean queryUserByKy(String id) {
		Users user = new Users();
		user.setUserKy(new Long(id));
		return userDAO.queryUserByKy(user);
	}

	public boolean changePassword(String currentPassword, String newPassword) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		SessionUserHolder sessionUserHolder = (SessionUserHolder) authentication
				.getPrincipal();
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String currentPwd = sessionUserHolder.getPassword();
		currentPassword = md5.encodePassword(currentPassword, null);
		if (currentPassword.equals(currentPwd)) {
			Users user = new Users();
			user.setUserKy(new Long(sessionUserHolder.getUserKy()));
			newPassword = md5.encodePassword(newPassword, null);
			user.setPassword(newPassword);
			return userDAO.updatePassword(user);
		} else {
			return false;
		}
	}

	public Users findUserByLoginId(String loginId) {
		return userDAO.findByLoginId(loginId);
	}

	/**
	 * 将userFormBean的值设置到user里
	 * 
	 * @param userFormBean
	 * @param user
	 * @date 2011-1-5
	 */
	private void setFormBeanToUser(UserFormBean userFormBean, Users user) {
		user.setLoginId(userFormBean.getUserCode());
		if (StringUtils.isNotEmpty(userFormBean.getUserPassword())) {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String password = md5.encodePassword(
					userFormBean.getUserPassword(), null);
			user.setPassword(password);
		}
		user.setStatus(userFormBean.getUserStatus());
		user.setUserEmail(userFormBean.getUserEmail());
		user.setUserName(userFormBean.getUserName());
		user.setUserMobile(userFormBean.getUserMobile());
		user.setUserPhone(userFormBean.getUserPhone());
		user.setUserRTX(userFormBean.getUserRTX());
		user.setDutyCode(userFormBean.getUserDutyCode());
		user.setOrgCode(userFormBean.getUserOrgCode());
		user.setRepositoryId(userFormBean.getRepositoryId());
	}

}
