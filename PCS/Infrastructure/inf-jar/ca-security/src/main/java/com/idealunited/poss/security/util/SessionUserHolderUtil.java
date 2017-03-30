/**
 * 
 */
package com.idealunited.poss.security.util;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.idealunited.poss.security.model.SessionUserHolder;

/**
 * 得到当前的用户信息相关
 * @author 戴德荣
 *
 */
public class SessionUserHolderUtil {
	
	/**
	 * 得到当前session中的用户，如果没有返回null
	 * @return SessionUserHolder 
	 */
	public static SessionUserHolder getSessionUserHolder(){
		SessionUserHolder holder = null;
		SecurityContext sc =   SecurityContextHolder.getContext();
		Authentication ac = sc.getAuthentication();
		if(ac!=null){
			holder =  (SessionUserHolder) ac.getPrincipal();
		}
		return holder;
	}
	/**
	 * 得到当前登录用户，如果没有返回null
	 * @return loginId
	 */
	public static String getLoginId(){
		String loginId = null;
		SessionUserHolder holder = getSessionUserHolder();
		if(holder!=null){
			loginId = holder.getUsername();
		}
		return loginId;
	} 
	
}
