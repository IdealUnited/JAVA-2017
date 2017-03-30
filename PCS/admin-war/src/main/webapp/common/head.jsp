<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="li_new" uri="page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/bootstrap/css/flat-ui.min.css" rel="stylesheet">
<link href="${ctx}/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="${ctx}/bootstrap/css/jquery.bs_pagination.css"
	rel="stylesheet">
<link href="${ctx}/bootstrap/css/jquery.bs_grid.css" rel="stylesheet">
<link href="${ctx}/css/beacon.css" rel="stylesheet">
<link href="${ctx}/css/page.css" rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${ctx}/javascripts/pages/My97DatePicker/skin/WdatePicker.css">
<link type="text/css" rel="stylesheet" href="${ctx}/css/xcConfirm.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${ctx}/bootstrap/js/jquery.min.js"></script>

<!--   <script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
   Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/bootstrap/js/flat-ui.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/bootstrap/js/jquery.bs_pagination.js"></script>
<script src="${ctx}/bootstrap/js/jquery.bs_pagination.zh_CN.js"></script>
<script src="${ctx}/bootstrap/js/jquery.bs_grid.js"></script>
<script src="${ctx}/bootstrap/js/jquery.bs_grid.zh_CN.js"></script>
<script src="${ctx}/javascripts/pages/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/bootstrap/js/application.js"></script>
<script src="${ctx}/javascripts/xcConfirm.js"></script>
<!-- jquery validate -->
<script src="${ctx}/javascripts/validate/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctx}/javascripts/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/javascripts/validate/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/javascripts/validate/jquery.form.js" type="text/javascript"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="${ctx}/bootstrap/js/html5shiv.js"></script>
    <![endif]-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<!-- theme -->

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script language="javascript">
$(function(){
	
	$('#loginOut').click(function() {
		location.href = "${ctx}/main.do?method=logout";
	});
	
	$('#userInfo').click(function() {
		location.href = "${ctx}/userInfo.do?method=userInfo&id=${userKy}";
	});

	$('#editPwd').click(function() {
		location.href="${ctx}/updatepassword.do";
	});
});
</script>

<div class="beacon-top">
	<div class="container-beacon">
		<img src="${ctx}/images/beacon_logo.png" class="beacon-logo"
			alt="beacon" />
		<div class="username-label pull-right">${userName }</div>
		<div class="portrait pull-right">
			<img src="${ctx}/images/portrait_sample.png" alt="portrait" />
		</div>
		<div class="pull-right">
			<div class="btn-group util-group">
				<a href="#" id="userInfo" class="btn btn-primary " data-toggle="tooltip"
					data-placement="bottom" title="账户信息"><i
					class="glyphicon glyphicon-user"></i></a> <a href="#" id="editPwd"
					class="btn btn-primary" data-toggle="tooltip"
					data-placement="bottom" title="修改密码"><i
					class="glyphicon glyphicon-lock"></i></a> <a href="#" id="loginOut"
					class="btn btn-primary btn-group-last" data-toggle="tooltip"
					data-placement="bottom" title="退出"><i
					class="glyphicon glyphicon-off"></i></a>
			</div>
		</div>
	</div>
</div>

<nav class="beacon-navbar">
	<ul class="beacon-nav clearfix" id="bar">
		<c:forEach items="${menuList}" var="menuRoot">
			<li class="dropdown"><a id="" role="button"
				data-toggle="dropdown" class="btn btn-primary " data-target="#"
				href="#">${menuRoot.name }<span class="caret"></span></a>

				<ul class="dropdown-menu multi-level" role="menu"
					aria-labelledby="dropdownMenu">
					<c:forEach items="${menuRoot.childList}" var="menuS">
						<div class="dropdown-menu-arrow"></div>
						<c:choose>
							<c:when test="${empty menuS.childList}">
								<li><a href="${ctx}${menuS.url}">${menuS.name}</a></li>
							</c:when>
							<c:otherwise>
								<li class="dropdown-submenu"><a tabindex="-1" href="#">${menuS.name }</a>
									<ul class="dropdown-menu ml-88">
										<c:forEach items="${menuS.childList}" var="menu">
											<li><a tabindex="-1" href="${ctx}${menu.url}">${menu.name}</a></li>
										</c:forEach>
									</ul>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul></li>
		</c:forEach>
	</ul>
</nav>
</div>