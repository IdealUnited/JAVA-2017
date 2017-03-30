<#macro headCorp index=false channel=1 child=1 matrix=false mCode="">
<#if Session["userSession"]?exists && (Session["userSession"].scaleType==2 || Session["userSession"].scaleType==10)>
	<#assign verifyName = Session["userSession"].verifyName/>
	<#--<#assign msgCountTemplate = "com.ttf.app.common.template.MsgCountTemplate"?new()>
    <#assign countUnRead=msgCountTemplate()>  -->
	<div class="header">
			<h1 class="logo"><a href="${rc.contextPath}/index.htm"><img alt="安捷支付" title="安捷支付" src="<@sp.static/>/img/ttf/logo.png"></a>
			<i class="ver_bus" title="商户版">商户版</i>
			</h1>
			<div class="header_bar"> <span class="pad"><a href="${rc.contextPath}/corp/myAccount.htm"><strong>${verifyName}</strong></a> 您好！</span> | <a class="pad" href="${rc.contextPath}/logout.htm?mtype=2">退出</a> <#if Session["userSession"].scaleType==2> | <a href="${rc.contextPath}/help/index.htm" class="help">帮助中心</a></#if>
			</div>
	</div>
	<#if index>
		<#include "/common/include/corp_nav.html">
	<#else>
		<#assign menuTemplate = "com.ttf.app.common.template.MenuTemplate"?new()>
	     <#assign selfPmList=menuTemplate()>
		<ul class="nav com_nav" id="sideheader">
			<#if  selfPmList?exists && selfPmList?has_content>
			<#list  selfPmList as pm>
			 <#assign  rootIndex=pm_index+1>
		     <li name="f_header" index="${rootIndex}"><a name="f_header_a" id="subLi_${rootIndex}"   <#if pm.menuUrl?exists && pm.menuUrl!='#'>href="<@contextPath/>${pm.menuUrl}"<#else>href="javascript:void(0);"</#if>><strong>${pm.menuName}</strong><i></i></a>
					<div class="subNav2" name="c_header" id="subNav_${rootIndex}"  style="display:none;<#if pm.menuCode?default("")=='ecard_manager'>left:40%;</#if>">
					<#assign  childIndex=0>
					<#if pm.childlist?has_content>
					<#list pm.childlist as ch>

						<#if ch.status==1>
						<#if ch.displayFlag==1>
						<#assign  childIndex=childIndex+1>
						<#if childIndex==1><script>$(function(){$("#subLi_${rootIndex}").attr("href","<@contextPath/>${ch.menuUrl}");})</script></#if>
						<#if sp.hasMenu(ch.menuCode?default(""),mCode)><script>$(function(){$("#subLi_${rootIndex}").addClass("cur");$("#defaultNav").val("${rootIndex}");$("#subNav_${rootIndex}").show();})</script></#if>
							<a  href="javascript:void(0);" class="<#if sp.hasMenu(ch.menuCode?default(""),mCode)>subActive</#if>" ><span  name="enabledMenu"   url="<@contextPath/>${ch.menuUrl}">${ch.menuName}</span></a>
						</#if>
						<#else>
							<a class="subNav3" href="javascript:void(0)"><span  name="nofeature">${ch.menuName}</span></a>
						</#if>
						
					</#list>
					</div>
					</#if>
			</li>
			</#list>
			<li class="last"><!-- <a href="javascript:void(0);" class="cur">商户助手</a>--> </li>
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