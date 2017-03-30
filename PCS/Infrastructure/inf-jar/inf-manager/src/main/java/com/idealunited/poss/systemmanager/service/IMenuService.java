package com.idealunited.poss.systemmanager.service;

import com.idealunited.poss.systemmanager.model.ResMenu;



public interface IMenuService {

	/**
	 * 得到新结点默认的Position
	 * @param parentId　父结点的id号
	 * @return 
	 * @date 2010-11-26
	 */
	public int getNewPosition(Long parentId);

	public void dropMenuById(String id);

	public ResMenu findById(String resKy);

	public boolean updateMenu(ResMenu menu);

	public long insertMenu(ResMenu menu);
	
}
