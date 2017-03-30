package com.idealunited.poss.systemmanager.dao;

public interface IMenuDao {
	
	/**
	 * 得到当前父结点的最大的顺序号
	 * @param parent 
	 * @return 
	 * @date 2010-11-26
	 * @author 戴德荣
	 */
	public int queryMaxPositionByPerant(Long parentId);
}
