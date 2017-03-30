<#macro ebillheader pagename="" glide=false> 
<link href="<@sp.static/>/img/favicon.ico" rel="SHORTCUT ICON"> 
<div class="steward_header">
	<div class="header">
		<h1 class="logo"><a title="安捷支付首页" class="home" href="${rc.contextPath}/index.htm"></a> <a title="生活管家" class="live_home" href="${rc.contextPath}/lifemanage.htm"></a></h1>
		<div style="width: 255px; margin: 8px 0 0 10px; font-weight:700; color:#b10000; float:left;">郑重承诺:凡安捷支付支付支付平台所发生的任何<p style="padding-left: 56px;">款项风险，均由安捷网承诺担保。</p></div>
		<!--<span class="logo_txt">
		<#if pagename?has_content && pagename!="">
			 ${pagename}
		</#if>
		</span>-->
		<div class="header_bar">
		<#if Session["userSession"]?exists>
		<#assign verifyName = Session["userSession"].verifyName/>
		<strong>${verifyName?default("")}</strong> 您好！|<a class="pad red" href="${rc.contextPath}/logout.htm">退出</a>|
		</#if>
		<a class="pad red" href="${rc.contextPath}/index.htm安捷支付首页</a> | <a class="help" href="<@sp.home/>/HelpLogin.html">常见问题</a></div>
	</div>
</div>
</#macro> 

