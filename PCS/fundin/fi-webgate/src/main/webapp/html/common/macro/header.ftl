<#macro head index=false channel=1 child=0 type=2 matrix=false mCode="" v=1> 
<!-- 360版本默认极速核ie标准内核 -->
<meta name="renderer" content="webkit">
<#--第一版本 -->
<#if v = 1>
	<#setting number_format="0">
	<#assign utype=type>
	<#if Session["userSession"]?exists && index=false>
	<#if Session["userSession"].scaleType==2 || Session["userSession"].scaleType==10>
	<#assign utype=2>
	<#else>
	<#assign utype=1>
	</#if>
	</#if>
	<#if utype=1>
	<@sp.headApp index=index channel=channel child=child matrix=matrix mCode=mCode/>
	<#else>
	<@sp.headCorp index=index channel=channel child=child matrix=matrix  mCode=mCode/>
	</#if>
	<!--导航 end--> 
	  <form method="post" id="menuForm" name="sidermenuForm" action="">
	<input type="hidden" name="menuId"/>
	</form>
	<script type="text/javascript" src="<@sp.static/>/js/ttf/common/index.js"></script>
<#else>
<#--第二版本 -->
	<!--header-->
	<#if Session["userSession"]?exists>
		<#assign verifyName = Session["userSession"].verifyName/>
		<#if Session["userSession"].scaleType==3>
		<div class="header">
			<h1 class="logo">
				<a href="${rc.contextPath}/index.htm"><img alt="安捷支付" title="安捷支付"	src="<@sp.static/>/img/v2/ttf/logo.jpg"></a>
			</h1>
			<div class="header_bar">
				<a class="pad" href="${rc.contextPath}/app/myAccount.htm"><strong>${verifyName}</strong></a> 您好！ | <a class="pad" href="${rc.contextPath}/logout.htm">退出</a> | <#--<a class="moblie" href="#">手机支付</a> |--> <a class="help" href="<@sp.home/>/HelpLogin.html">常见问题</a>
			</div>
		</div>
		<#else>
		<div class="header">
			<h1 class="logo">
				<a href="${rc.contextPath}/index.htm"><img alt="安捷支付" title="安捷支付"	src="<@sp.static/>/img/v2/ttf/logo.jpg"></a>
			</h1>
			<div class="header_bar">
				<a class="pad" href="${rc.contextPath}/corp/myAccount.htm"><strong>${verifyName}</strong></a> 您好！ | <a class="pad" href="${rc.contextPath}/logout.htm?mtype=2">退出</a> | <#--<a class="moblie" href="#">手机支付</a> |--> <a class="help" href="<@sp.home/>/HelpLogin.html">常见问题</a>
			</div>
		</div>
		</#if>
	<#else>
		<div class="header">
			<h1 class="logo">
				<a href="${rc.contextPath}/index.htm"><img alt="安捷支付" title="安捷支付"	src="<@sp.static/>/img/v2/ttf/logo.jpg"></a>
			</h1>
			<div class="header_bar">
				<a class="log" href="${rc.contextPath}/outapp.htm?mtype=2">登录</a> - <a class="reg" href="${rc.contextPath}/register.htm">注册</a> | <#--<a class="moblie" href="#">手机支付</a> |--> <a class="help" href="<@sp.home/>/HelpLogin.html">常见问题</a>
			</div>
		</div>
	</#if>
	<!--/header-->

	<!--导航-->
	<div class="nav">
		<div class="nav_con">
			<ul class="nav_list">
				<li><a <#if child ==0>class="cur"</#if> href="${rc.contextPath}/index.htm"><strong>首 页</strong></a></li>
				<li><a <#if child ==1>class="cur"</#if> href="<@sp.seo/>/merchant/onlinebank.htm"><strong>商户服务</strong></a></li>
				<li><a <#if child ==2>class="cur"</#if> href="<@sp.seo/>/perservice.htm"><strong>个人服务</strong></a></li>
				<li><a <#if child ==4>class="cur"</#if> href="<@sp.seo/>/safe/helper.htm"><strong>安全中心</strong></a></li>
				<li><a <#if child ==5>class="cur"</#if> href="<@sp.seo/>/actarea/actarea.htm"><strong>活动专区</strong></a></li>
			</ul>
		</div>
	</div>
	<!--/导航-->
	
</#if>
</#macro>