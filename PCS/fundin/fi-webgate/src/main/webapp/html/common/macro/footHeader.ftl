<#macro footHeader usertype=1>  
	<link href="<@sp.static/>/img/favicon.ico" rel="SHORTCUT ICON" type="image/x-icon">
	<link href="<@sp.static/>/img/favicon.ico" rel="icon" type="image/x-icon" />
<div class="about_header">
	<div class="header">
		<h1 class="logo"><a href="${rc.contextPath}/index.htm"><img alt="安捷支付 src="<@sp.static/>/img/ttf/logo.png"></a></h1>
		<div class="header_bar">
		 <#if Session["userSession"]?exists>
				<#assign verifyName = Session["userSession"].verifyName/>
				<#assign msgCountTemplate = "com.ttf.app.common.template.MsgCountTemplate"?new()>
		       <#assign countUnRead=msgCountTemplate()>
				    <#if Session["userSession"].scaleType==2>
				        <#assign usertype = 2/>
				    <#else>
				        <#assign usertype = 1/>
				    </#if>
		<span class="pad"><a href="${rc.contextPath}/app/myAccount.htm"><strong>${verifyName?default("")}</strong></a> 您好！<a href="${rc.contextPath}/app/messageList.htm?method=messgeList">未读消息(<i>${countUnRead?default(0)}</i>)</a></span> | 
		<#if usertype=1>
		  <a class="pad" href="${rc.contextPath}/logout.htm">退出</a>
		<#else>
		  <a class="pad" href="${rc.contextPath}/logout.htm?mtype=2">退出</a> 
		</#if>
		<#else>  
		 <#if usertype=2>
		 <a class="pad" href="${rc.contextPath}/outapp.htm?mtype=2">登录</a>
		 <#else>
		 <a class="pad" href="${rc.contextPath}/outapp.htm">登录</a>
		 </#if> | <a class="pad" href="${rc.contextPath}/register.htm">注册</a>
		</#if>
		| <a class="help" href="<@sp.home/>/HelpLogin.html">常见问题</a>
	</div>
	</div>
</div>
</#macro> 