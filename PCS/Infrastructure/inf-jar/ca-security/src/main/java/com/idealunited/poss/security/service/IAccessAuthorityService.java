package com.idealunited.poss.security.service;

import com.idealunited.inf.dao.Page;
import com.idealunited.poss.security.model.AccessAuthority;

public interface IAccessAuthorityService {
	/**
	 * 创建
	 * 
	 * @param accessAuthority
	 */
	public void createAccessAuthority(AccessAuthority accessAuthority);

	/**
	 * 更新
	 * 
	 * @param accessAuthority
	 * @return 更新的个数
	 * @author user
	 */
	public boolean updateAccessAuthority(AccessAuthority accessAuthority);

	/**
	 * 
	 * @return
	 */
	public Page<AccessAuthority> search(Page<AccessAuthority> page,
			AccessAuthority accessAuthority);

	/**
	 * 通过 id查询对象
	 * 
	 * @param id
	 *            id号
	 * @return 对象
	 */
	public AccessAuthority getAccessAuthorityById(Long id);

	/**
	 * 删除AccessAuthority
	 * 
	 * @param id
	 * @return 删除的个数
	 */
	public boolean deleteAccessAuthority(Long id);

	/**
	 * 验证ip是否可在授权表中，并且状态为1
	 * 
	 * @param ip
	 * @return
	 */
	public boolean validateIp(String ip);

}
