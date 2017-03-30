package com.idealunited.poss.systemmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.poss.systemmanager.dao.impl.MenuDaoImpl;
import com.idealunited.poss.systemmanager.dao.impl.RoleDaoImpl;
import com.idealunited.poss.systemmanager.model.ResMenu;
import com.idealunited.poss.systemmanager.service.IMenuService;

public class MenuServiceImpl implements IMenuService {

	private final Log log = LogFactory.getLog(MenuServiceImpl.class);
	private RoleDaoImpl roleDao;
	private MenuDaoImpl menuDao;

	public ResMenu findById(String id) {
		return menuDao.findById(new Long(id));
	}

	public boolean updateMenu(ResMenu resMenu) {
		return menuDao.update(resMenu);
	}

	public long insertMenu(ResMenu menu) {
		log.debug("MenuServiceImpl.insertMenu() is running...");

		try {
			return this.menuDao.insertMenu(menu);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("MenuServiceImpl.insertMenu error...");
		}

		return -1L;
	}

	public List<ResMenu> findAllMenu() {
		log.debug("MenuServiceImpl.findAllMenu() is running...");
		List<ResMenu> menuList = new ArrayList<ResMenu>();
		try {

			menuList = this.roleDao.findAllMenu();
			log.debug("MenuServiceImpl.findAllMenu().menuList.size=="
					+ menuList.size());

		} catch (Exception e) {
			log.error("MenuServiceImpl.findAllMenu error...");
		}

		return menuList;
	}

	public void dropMenuById(String id) {
		log.debug("MenuServiceImpl.dropMenuById is running...");
		boolean sign = true;
		try {

			sign = this.menuDao.dropMenuById(id);

		} catch (Exception e) {
			log.error(e);
			log.error("MenuServiceImpl.dropMenuById error...");

		}

	}

	public void setMenuDao(MenuDaoImpl menuDao) {
		this.menuDao = menuDao;
	}

	public void setRoleDao(RoleDaoImpl roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public int getNewPosition(Long parentId) {
		return menuDao.queryMaxPositionByPerant(parentId);
	}

}
