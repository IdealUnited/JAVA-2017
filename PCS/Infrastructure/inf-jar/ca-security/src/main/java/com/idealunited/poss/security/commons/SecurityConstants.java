package com.idealunited.poss.security.commons;

public class SecurityConstants {
	// 载体类型
	public static final String PRINCIPAL_TYPE_USER = "USER";
	public static final String PRINCIPAL_TYPE_ROLE = "ROLE";
	public static final String PRINCIPAL_TYPE_GROUP = "GROUP";

	// 资源类型名称
	public static final String RES_TYPE_ALL = "ALL_KIND";
	public static final String RES_TYPE_MENU = "MENU";
	public static final String RES_TYPE_URL = "URL";

	/*// 异常映射参数
	public static final String ACEGI_SUCCESS = "success"; // 成功
	public static final String ACEGI_EXCEPTION_MAPPING_USER_INACTIVE = "org.springframework.security.InactiveExeption"; // 用户未激活
	public static final String ACEGI_EXCEPTION_MAPPING_USER_NOT_FOUND = "org.springframework.security.BadCredentialsException"; // 用户不存在
	public static final String ACEGI_EXCEPTION_MAPPING_USERAUTH_NOT_FOUND = "org.springframework.security.UserAuthNotFoundException"; // 用户安全信息不存在
	public static final String ACEGI_EXCEPTION_MAPPING_SECURITY_STRATETY_NOT_FOUND = "org.springframework.security.SecurityStratetyNotFoundException";// 安全策略不存在
	public static final String ACEGI_EXCEPTION_MAPPING_USER_LOCKED = "org.springframework.security.LockedExeption";// 用户锁定
	public static final String ACEGI_EXCEPTION_MAPPING_CHANGE_PASSWORD = "org.springframework.security.ChangePasswordExeption";// 更改密码
	public static final String ACEGI_EXCEPTION_MAPPING_ERROR_LOGIN = "org.springframework.security.ErrorLoginExeption";// 登录失败
	public static final String ACEGI_EXCEPTION_MAPPING_ERROR_IMAGE = "org.springframework.security.randomImage.errorExeption";// 验证码错误
	public static final String ACEGI_EXCEPTION_MAPPING_ERROR_EXTEND_AUTH = "org.springframework.security.extendAuth.errorExeption";// 扩展权限信息错误
	 */	
	// 异常映射参数
	public static final String ACEGI_SUCCESS = "success"; // 成功
	public static final String ACEGI_EXCEPTION_MAPPING_USER_INACTIVE = "org.springframework.security.InactiveExeption"; // 用户未激活
	public static final String ACEGI_EXCEPTION_MAPPING_USER_NOT_FOUND = "org.springframework.security.BadCredentialsException"; // 用户不存在
	public static final String ACEGI_EXCEPTION_MAPPING_USERAUTH_NOT_FOUND = "/login.jsp?login_error=userAuth_not_found"; // 用户安全信息不存在
	public static final String ACEGI_EXCEPTION_MAPPING_SECURITY_STRATETY_NOT_FOUND = "org.springframework.security.SecurityStratetyNotFoundException";// 安全策略不存在
	public static final String ACEGI_EXCEPTION_MAPPING_USER_LOCKED = "org.springframework.security.LockedExeption";// 用户锁定
	public static final String ACEGI_EXCEPTION_MAPPING_CHANGE_PASSWORD = "/jsp/security/changePassword.jsp";// 更改密码
	public static final String ACEGI_EXCEPTION_MAPPING_ERROR_LOGIN = "org.springframework.security.ErrorLoginExeption";// 登录失败
	public static final String ACEGI_EXCEPTION_MAPPING_ERROR_IMAGE = "org.springframework.security.randomImage.errorExeption";// 验证码错误
	public static final String ACEGI_EXCEPTION_MAPPING_ERROR_EXTEND_AUTH = "/login.jsp?login_error=error_extend_auth";// 扩展权限信息错误
	public static final String ACEGI_EXCEPTION_MAPPING_ERROR_IN_DB = "/login.jsp?login_error=error_in_db"; // 查询数据库异常
	public static final String ACEGI_EXCEPTION_MAPPING_ERROR_RAND_CODE = "/login.jsp?login_error=error_rand_code"; // 查询数据库异常
	public static final String ACEGI_EXCEPTION_MAPPING_ERROR_AUTH_IP = "/login.jsp?login_error=error_auth_ip"; // 未授权ip地址

	// 缺省参数
	public static final String SUPER_ADMIN_A = "adminA";// 超级管理员A
	public static final String SUPER_ADMIN_B = "adminB";// 超级管理员B

	// 安全策略
	public static final int DEFAULT_SEC_STRATEGY_PASSWORD_VALID_DAYS = 1000;// 密码过期天数
	public static final int DEFAULT_STRATEGY_SESSIONS_PER_USER = 1;// 允许同一用户存在的数量
	public static final String DEFAULT_PERSON_NAME = "UNKNOWED NAME";// 缺省员工姓名
	public static final String DEFAULT_BRANCH = "UNKNOWED ORG";// 缺省机构名
	public static final int DEFAULT_SEC_STRATEGY_MAX_FAILED_LOGIN_COUNT = 3;// 登录失败的最大次数

}
