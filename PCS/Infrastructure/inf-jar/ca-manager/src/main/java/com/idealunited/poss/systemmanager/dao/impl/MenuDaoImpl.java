package com.idealunited.poss.systemmanager.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.inf.dao.impl.BaseDAOImpl;
import com.idealunited.poss.systemmanager.dao.IMenuDao;
import com.idealunited.poss.systemmanager.model.ResMenu;

public class MenuDaoImpl extends BaseDAOImpl<ResMenu> implements IMenuDao {

	private final Log log = LogFactory.getLog(MenuDaoImpl.class);

	public long insertMenu(ResMenu menu) {
		log.debug("MenuDaoImpl.insertMenu is running...");
		return (Long) create("insertMenu", menu);
	}

	public boolean dropMenuById(String id) {
		log.debug("MenuDaoImpl.dropMenuById is running...");
		return this.delete(Long.parseLong(id));
	}

	@Override
	public int queryMaxPositionByPerant(Long parentId) {
		Object obj = findObjectByCriteria("maxPositionByPerant", parentId);
		return new Integer(obj.toString());
	}

}
