<#macro headApp index=false channel=1 child=0 matrix=matrix mCode="">

<#if Session["userSession"]?exists && Session["userSession"].scaleType==3>
	<#assign verifyName = Session["userSession"].verifyName/>
	<div class="header">
			<h1 class="logo"><a href="${rc.contextPath}/index.htm?method=indexApp"><img alt="安捷支付" title="安捷支付" src="<@sp.static/>/img/ttf/logo.png"></a></h1>
			<div class="header_bar"> <span class="pad"><a href="${rc.contextPath}/app/myAccount.htm"><strong>${verifyName}</strong></a> 您好！</span> | <a class="pad" href="${rc.contextPath}/logout.htm">退出</a>  | <a href="<@sp.home/>/HelpLogin.html" class="help">常见问题</a> 
			</div>
	</div>
	<#if index>
		<#include "/common/include/app_nav.html">
	<#else>
		<#assign menuTemplate = "com.ttf.app.common.template.MenuTemplate"?new()>
	     <#assign selfPmList=menuTemplate()>
		<ul class="nav" id="sideheader">
			<#if  selfPmList?exists && selfPmList?has_content>
			<#list  selfPmList as pm>
			 <#assign  rootIndex=pm_index+1>
		     <li name="f_header" index="${rootIndex}" <#if rootIndex==1>class="short"</#if>><a name="f_header_a" id="subLi_${rootIndex}"    <#if pm.menuUrl?exists && pm.menuUrl!='#'>href="<@contextPath/>${pm.menuUrl}"<#else>href="javascript:void(0);"</#if>><strong>${pm.menuName}</strong><i></i></a>
					<div class="subNav2" name="c_header" id="subNav_${rootIndex}" style="display:none;">
					<#assign  childIndex=0>
					<#if pm.childlist?has_content>
					<#list pm.childlist as ch>
					<#if ch.status==1>
						<#if ch.displayFlag==1>
							<#assign  childIndex=childIndex+1>
							<#if childIndex==1><script>$(function(){$("#subLi_${rootIndex}").attr("href","<@contextPath/>${ch.menuUrl}");})</script></#if>
							<#if sp.hasMenu(ch.menuCode?default(""),mCode)><script>$(function(){$("#subLi_${rootIndex}").addClass("cur");$("#defaultNav").val("${rootIndex}");$("#subNav_${rootIndex}").show();})</script></#if>
								<a class="<#if sp.hasMenu(ch.menuCode?default(""),mCode)>subActive</#if>" href="javascript:void(0);"><span name="enabledMenu" style="cursor: pointer;"   url="<@contextPath/>${ch.menuUrl}" >${ch.menuName}</span></a>
						</#if>
						<#else>
							<a  href="javascript:void(0)"><span name="nofeature">${ch.menuName}</span></a>
						</#if>
						
					</#list>
					</div>
					</#if>
			</li>
			</#list>
			<li class="last"><!--<a href="javascript:void(0);" class="cur">生活助手</a>--> </li>
			</#if>
			<input type="hidden" id="defaultNav" value="1"/>
			<script type="text/javascript" src="<@sp.static/>/js/ttf/common/header.js"></script>
	</#if>
	 
</ul>

<#else>
<div class="bg_fff">
        <div class="wrap980">
            <img src="images/logo_yt.png" alt="">
            <label class="fr mt40 c_666"><span class="lab_icon_home"><a href="${rc.contextPath}">盈通首页</a></span> <span class="mr5 ml5">|</span> <a href="${rc.contextPath}/outapp.htm?mtype=2">登陆</a> <span class="mr5 ml5">|</span> <a href="${rc.contextPath}/register.htm">注册</a></label>
        </div>
</div>
	    <div class="nav_w empty">
    </div>
</#if>

		

</#macro>