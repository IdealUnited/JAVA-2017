package com.idealunited.poss.systemmanager.controller;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.idealunited.poss.systemmanager.formbean.RoleJoinUserFormBean;
import com.idealunited.poss.systemmanager.model.Role;
import com.idealunited.poss.systemmanager.model.UserInfo;
import com.idealunited.poss.systemmanager.model.UserRole;
import com.idealunited.poss.systemmanager.service.IRoleService;


public class RoleJoinUserController extends SimpleFormController{
	private Log log = LogFactory.getLog(RoleJoinUserController.class);
	private IRoleService roleService;
	
    @Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
    	log.debug("RoleJoinUserController.referenceData() is running...");
    	Map<String, Object> dataMap = new Hashtable<String, Object>();
    	
    	List<UserInfo> userInfoList = this.roleService.findAllUser();
    	dataMap.put("userInfoList", userInfoList);
    	
    	if(request.getParameter("userId")!=null&&!request.getParameter("userId").equals("")){
    		String userId=(String)request.getParameter("userId");
    		for(int i=0;i<userInfoList.size();i++){
    			UserInfo userInfo =userInfoList.get(i);
    			if(userId.equals(userInfo.getUserId())){
    				dataMap.put("userName", userInfo.getUserName());
    			}
    			
    		}
    		
    		log.debug("RoleJoinUserController.referenceData().userId=="+userId);
    		List<Role> roleList = this.roleService.queryRoleOfUser(userId);
    		List<Role> roleAllList = this.roleService.findAllRole();
    		List<Role> roleNoList =new ArrayList<Role>();
    		
    		for(int i=0;i<roleAllList.size();i++){
    			boolean sign = true;
    			Role roleAll = roleAllList.get(i);
    			for(int j=0;j<roleList.size();j++){
    				Role role = roleList.get(j);
    				if(roleAll.getRoleKy().toString().equals(role.getRoleKy().toString())){
    					sign = false;
    				}
    			}
    			if(sign){
    				roleNoList.add(roleAll);
    			}
    			
    		}
    		    		   		
    		dataMap.put("roleList", roleList);
    		dataMap.put("roleNoList", roleNoList);
    		dataMap.put("userId", userId);
    	}
    	    	   	
    	
		return dataMap;
	}

	public ModelAndView onSubmit(Object command, BindException errors){    	
		log.debug("RoleJoinUserController.onSubmit() is running...");
		RoleJoinUserFormBean roleJoinUserFormBean = (RoleJoinUserFormBean)command;
		
		List<UserInfo> userInfoList = this.roleService.findAllUser();
    	
		String userId = roleJoinUserFormBean.getUserId();
		String [] roleArray = roleJoinUserFormBean.getFuncno();
		List<Role> roleList = this.roleService.queryRoleOfUser(userId);
		
		if(roleArray!=null){
			for(int i=0;i<roleArray.length;i++){
				boolean signAdd = true;
				for(int j=0;j<roleList.size();j++){
					Role role=roleList.get(j);
					if(roleArray[i].equals(role.getRoleKy().toString())){
						signAdd = false;
					}
				}
				if(signAdd){
					UserRole userRole = new UserRole();
					userRole.setUserKy((Long.valueOf(userId)));
					userRole.setRoleKy(Long.valueOf(roleArray[i]));			
					userRole.setStatus(1);
					this.roleService.insertRoleOfUser(userRole);
				}
				
			}
		}
		for(int i=0;i<roleList.size();i++){
			boolean signDelete = true;
			Role role=roleList.get(i);
			if(roleArray!=null){
				for(int j=0;j<roleArray.length;j++){				
					if(roleArray[j].equals(role.getRoleKy().toString())){
						signDelete = false;
					}
				}
			}
			if(signDelete){
				Map<String, String> dataMap = new Hashtable<String, String>();
				dataMap.put("userId", userId);
				dataMap.put("roleId", role.getRoleKy().toString());
				this.roleService.dropRoleOfUser(dataMap);
			}
			
		}
		
		return new ModelAndView("redirect:roleForUser.do");
        //return new ModelAndView(getSuccessView()).addObject("userInfoList", userInfoList);
    }

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
}
