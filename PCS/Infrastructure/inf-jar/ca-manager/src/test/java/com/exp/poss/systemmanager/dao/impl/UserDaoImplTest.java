package com.exp.poss.systemmanager.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.idealunited.inf.dao.BaseDAO;
import com.idealunited.poss.systemmanager.dao.IUserDao;
import com.idealunited.poss.systemmanager.formbean.UserFormBean;
import com.idealunited.poss.systemmanager.model.Users;
import com.idealunited.poss.systemmanager.service.IOrgService;
import com.idealunited.poss.systemmanager.service.IUserService;

@ContextConfiguration(locations = {
		"classpath*:config/env/test-dataAccessContext.xml",
		"classpath*:context/intra/**/*.xml" })
public class UserDaoImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private IUserDao userDAO;
	@Autowired
	private BaseDAO iBaseDao;
	@Autowired
	private IUserService userService;
	@Autowired
	public IOrgService orgService;

	/*
	 * @Test public void testQueryAll() { userDAO.findByTemplate("allUser",
	 * null); }
	 * 
	 * @Test public void testFindUserById() { userDAO.findById(new
	 * Long("123456")); }
	 */

	// @Test
	public void testSaveUser() {
		Users user = new Users();
		// user.setUserKy("15800686969");
		user.setLoginId("ddddddddd1");
		user.setLoginChannel(2);
		user.setPassword("2222222");
		user.setLastLoginTime(new Date());
		user.setLastLoginIp("123.123.123.123");
		user.setPswdExpiredTime(new Date());
		user.setAllowIp("131.231.21.232");
		user.setFailLoginNum(5);
		user.setStatus(1);
		iBaseDao.create("user.create", user);
		System.out.println(user.getUserKy());
	}

	/*
	 * @Test public void testDeleteUser() { userDAO.delete(new Long("13579")); }
	 */

	/* @Test */
	public void testUpdateUser() {

		/*
		 * Users user = new Users(); user.setUserKy("999999999999999999");
		 * user.setStaffKy("88888888888888888"); user.setLoginId("78979");
		 * user.setLoginChannel(new Long(2)); user.setPassword("44444444");
		 * user.setLoginTime(new Date()); user.setLastLoginTime(new Date());
		 * user.setLoginIp("123.213.321.213");
		 * user.setLastLoginIp("123.123.123.123"); user.setPswdExpiredTime(new
		 * Date()); user.setAllowIp("131.231.21.232"); user.setFailLoginNum(new
		 * Long(5)); user.setStatus(1);
		 */

		UserFormBean userFormBean = new UserFormBean();
		userFormBean.setUserName("angela");
		userFormBean.setUserCode("angela_zheng@staff.payay.com");
		userFormBean.setUserPassword("12345678");
		userFormBean.setUserStatus(1);
		userFormBean.setUserEmail("angela_zheng@stafpayapay.com");
		userFormBean.setUserPhone("51331234");
		userFormBean.setUserMobile("");
		userFormBean.setUserRTX("2194");

		try {
			userService.saveUser(userFormBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOrgService() {
		System.out.println(userDAO.findAllUsers());
	}
}
