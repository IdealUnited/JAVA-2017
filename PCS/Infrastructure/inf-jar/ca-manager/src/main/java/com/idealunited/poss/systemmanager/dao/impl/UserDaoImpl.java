package com.idealunited.poss.systemmanager.dao.impl;

import java.util.List;
import java.util.Map;

import com.idealunited.inf.dao.Page;
import com.idealunited.inf.dao.impl.BaseDAOImpl;
import com.idealunited.poss.systemmanager.dao.IUserDao;
import com.idealunited.poss.systemmanager.formbean.UserFormBean;
import com.idealunited.poss.systemmanager.model.Duty;
import com.idealunited.poss.systemmanager.model.Org;
import com.idealunited.poss.systemmanager.model.Users;
import com.idealunited.util.MapUtil;

public class UserDaoImpl extends BaseDAOImpl implements IUserDao {

	public Page<UserFormBean> search(Page<UserFormBean> page, Map map) {

		return findByQuery("search", page, map);
	}

	public List<UserFormBean> queryByMap(Map<String, String> map) {
		return (List<UserFormBean>) findByCriteria("search", map);
	}

	@Override
	public List<Duty> queryAllDuty() {
		return (List<Duty>) super.findAll("queryAllDuty");
	}

	@Override
	public List<Org> queryAllOrg() {
		return (List<Org>) findAll("queryAllOrg");
	}

	@Override
	public boolean deleteUser(Users user) {
		return update("delete", user);
	}

	@Override
	public UserFormBean queryUserByKy(Users user) {
		Map paraMap = MapUtil.bean2map(user);
		return (UserFormBean) super.findObjectByCriteria("queryUserByKy",
				paraMap);
	}

	public boolean updatePassword(Users user) {
		return update("updatePassword", user);
	}

	@Override
	public List<Users> queryUsersByRole(Long roleKey) {
		return super.findByCriteria("queryUsersByRole", roleKey);
	}

	@Override
	public Users findByLoginId(String loginId) {
		return (Users) super.findObjectByCriteria("findByLoginId", loginId);
	}

	@Override
	public List<Users> findAllUsers() {
		return findAll("allUser");
	}
}
