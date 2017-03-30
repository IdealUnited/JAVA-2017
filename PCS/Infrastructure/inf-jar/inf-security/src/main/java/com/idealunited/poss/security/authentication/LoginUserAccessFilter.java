package com.idealunited.poss.security.authentication;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.SpringSecurityFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestUtils;

import com.idealunited.poss.security.commons.SecurityConstants;
import com.idealunited.poss.security.model.AccessLog;
import com.idealunited.poss.security.model.OpenUrlConfig;
import com.idealunited.poss.security.model.OpenUrlIpGroup;
import com.idealunited.poss.security.service.IAccessLogService;
import com.idealunited.poss.security.service.IOpenUrlConfigService;
import com.idealunited.poss.security.util.IpUtils;
import com.idealunited.poss.security.util.SecutiryRequestUtil;
import com.idealunited.poss.security.util.SessionUserHolderUtil;

public class LoginUserAccessFilter extends SpringSecurityFilter implements InitializingBean {

	private String faildUrl = "/";
	private String noResourceUrl = "/";
	private List<String> notFilterUrls = null;
	private Map<String,OpenUrlConfig> openUrlConfigs  = new HashMap<String,OpenUrlConfig>();
	private IAccessLogService accessLogService;
	private IOpenUrlConfigService openUrlConfigService;
	
	
	/**
	 * @return the openUrlConfigs
	 */
	public Map<String, OpenUrlConfig> getOpenUrlConfigs() {
		return openUrlConfigs;
	}


	//初始化
	public void initNotFilterUrls(){
		notFilterUrls = openUrlConfigService.findAllOpenUrls();
		openUrlConfigs.clear();
		for(String url:notFilterUrls){
			openUrlConfigs.put(url,openUrlConfigService.findOpenUrlConfigByUrl(url));
		}
	}
	

	/**
	 * @param accessLogService the accessLogService to set
	 */
	public void setAccessLogService(IAccessLogService accessLogService) {
		this.accessLogService = accessLogService;
	}

	
	
	/**
	 * @param openUrlConfigService the openUrlConfigService to set
	 */
	public void setOpenUrlConfigService(IOpenUrlConfigService openUrlConfigService) {
		this.openUrlConfigService = openUrlConfigService;
	}



	public String getFaildUrl() {
		return faildUrl;
	}

	public void setFaildUrl(String faildUrl) {
		this.faildUrl = faildUrl;
	}


	

	public List<String> getNotFilterUrls() {
		if(notFilterUrls == null){
			notFilterUrls = openUrlConfigService.findAllOpenUrls();
		}
		return notFilterUrls;
	}

	public void setNotFilterUrls(List<String> notFilterUrls) {
		this.notFilterUrls = notFilterUrls;
	}

	public String getNoResourceUrl() {
		return noResourceUrl;
	}

	public void setNoResourceUrl(String noResourceUrl) {
		this.noResourceUrl = noResourceUrl;
	}

	@Override
	public int getOrder() {
		return 2000;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
	
	
	@Override
	protected void doFilterHttp(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String uri = request.getRequestURI().replaceFirst(request.getContextPath(), "");
		String ip = SecutiryRequestUtil.getIp(request);
		
		if( ((!uri.endsWith(".do"))&&(!uri.endsWith(".jsp")&&(!uri.endsWith(".htm")))) ||
				uri.indexOf("j_spring_security")>=0 || uri.indexOf("login")>=0 || uri.indexOf("validatecode/validatecode.htm")>=0
				||isNoFilterUrl(uri,SecutiryRequestUtil.getIp(request))|| isCommonUrl(uri))
		{
			chain.doFilter(request, response);
			return ;
		}
		logger.info("from IP:"+ip+"访问 ：URI"+request.getRequestURI()+"访问:"+uri);	
		//记录日志
		saveAccessLog(request);
		SecurityContext sc =   SecurityContextHolder.getContext();
		Authentication ac = sc.getAuthentication();
		if(ac==null ||  ac.getPrincipal() ==null  ){
			if(logger.isDebugEnabled()){
				logger.debug("from IP:"+ip+" 未登录，转至 "+faildUrl);	
			}
			response.sendRedirect(request.getContextPath()+faildUrl);
			return ;
		}
		if(SessionUserHolderUtil.getSessionUserHolder() !=null  ){//判断权限
			//记录用户的ip信息
			SessionUserHolderUtil.getSessionUserHolder().setLoginIp(request.getRemoteAddr());
			//判断是否为管理员
				if(SessionUserHolderUtil.getLoginId().equals(SecurityConstants.SUPER_ADMIN_A)||SessionUserHolderUtil.getLoginId().equals(SecurityConstants.SUPER_ADMIN_B)){
					chain.doFilter(request, response);
					return ;
				}
				List<String> list  =  SessionUserHolderUtil.getSessionUserHolder().getResources();
				
				//默认可以查看修改密码 主页面
				if(("/forwordMain.do,/main.do,/updatepassword.do").indexOf(uri)>=0 ||  list.contains(uri)   ){
					chain.doFilter(request, response);
					return ;
				}else{
					if(logger.isDebugEnabled()){
						logger.info("请求地址:"+request.getRequestURI());	
						logger.info("from IP:"+request.getRemoteAddr()+" 没有足够的权限访问，跳转"+noResourceUrl);
					}
					
					response.sendRedirect(request.getContextPath() + noResourceUrl);
					 return;
				}
			}
		
		chain.doFilter(request, response);
	}
	/**
	 * 判断权限
	 * @param url
	 * @return 
	 * @date 2010-11-17
	 */
	private boolean isNoFilterUrl(String url,String ip){
		
		List<String> notFilUrls = getNotFilterUrls();
		if(CollectionUtils.isEmpty(notFilUrls) ){
			return false;
		}

		for(String notFil : notFilterUrls){
			String notFilReg = "^"+(notFil.replaceAll("\\*", "(\\.*)"))+"$";
			 if(url.matches(notFilReg)){//如果此
				OpenUrlConfig urlConfig =  getOpenUrlConfigByUrl(notFil);
				if(urlConfig.getIpCheck()==1){
					if(! IpUtils.validateIp(ip)){
						return false;
					}
					//判断精确的ip地址
					if(urlConfig.getIps()!=null && urlConfig.getIps().indexOf(ip)>=0){
						return true;
					}
					//判断ip段
					if(urlConfig.getIpGroups()!=null){
						for(OpenUrlIpGroup ipGroup : urlConfig.getIpGroups()){
							if(ipGroup.containIp(ip)){
								return true;
							}
						}
					}
				}else{
					return true;
				}
			 }
		}
		return false;
	}
	
	
	private void saveAccessLog(HttpServletRequest request){
		
//		
//			Runnable r1 = new Runnable(){
//				public void run() {
		try {
			HttpSession session = (request.getSession());
			String uri = request.getRequestURI().replaceAll(
					request.getContextPath(), "");
			if (uri.equals("/main.do")) {
				return;
			}
			String queryString = request.getQueryString();
			String ip = SecutiryRequestUtil.getIp(request);
			final AccessLog log = new AccessLog();
			log.setActionUrl(uri
					+ (queryString == null ? "" : ("?" + queryString)));
			log.setCreationDate(new Date());
			log.setUserIp(ip);
			log.setUrlMethod(request.getMethod().toUpperCase());
			String loginUser = String.valueOf(session
					.getAttribute("SPRING_SECURITY_LAST_USERNAME"));
			if (loginUser != null) {
				log.setLoginUser(loginUser);
			}

			// 记录参数，暂时重要的参数method和前20个参数
			if (request.getMethod().toUpperCase().equals("POST")) {
				StringBuffer bf = new StringBuffer("");
				String method = ServletRequestUtils.getStringParameter(request,
						"method", "");
				if (method != "") {
					bf.append("method=" + method + ";");
				}
				Map<String, Object> mp = request.getParameterMap();
				int i = 0;
				for (String ky : mp.keySet()) {
					i++;
					if (!ky.equals("method")) {
						bf.append(ky + "=" + request.getParameter(ky) + ";");
					}
					if (i >= 20) {
						break;
					}
				}
				log.setPostParams(bf.toString());
			}
			accessLogService.createAccessLog(log);
		} catch (Exception e) {
			logger.error("LoginUserAccessFilter----记录日志异常", e);
			// logger.error(e);
		}
					
//				}
//			};
//			Thread t1 = new Thread(r1);
//			t1.start();
//			
		
	}
	
	public OpenUrlConfig getOpenUrlConfigByUrl(String url){
		if(openUrlConfigs == null || openUrlConfigs.size() == 0){
			openUrlConfigs = new HashMap<String,OpenUrlConfig>(); 
			openUrlConfigs.put(url, openUrlConfigService.findOpenUrlConfigByUrl(url));
		}
		OpenUrlConfig obj =  openUrlConfigs.get(url);
		if(obj==null){
			obj = openUrlConfigService.findOpenUrlConfigByUrl(url);
			openUrlConfigs.put(url, obj);
		}
		return obj;
	}
	
	
	private  boolean isCommonUrl(String url){
		if(url.matches("^/common/(.+)(\\.jsp)$")){
			return true;
		}
		return false;
	}
	
}




