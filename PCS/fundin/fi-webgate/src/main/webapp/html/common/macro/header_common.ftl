<#macro headCommon txt="收银台">

<#if Session["userSession"]?exists >
	<#assign verifyName = Session["userSession"].verifyName/>
	<div class="header">
			<h1 class="logo"><a href="http://220.168.94.226:8080/website/index.htm?method=indexApp"><img alt="安捷支付" title="安捷支付" src="<@sp.static/>/img/ttf/logo.png"></a>
			<i class="ver_txt" title="${txt}">${txt}</i>
			</h1>
			<div class="header_bar"> <span class="pad"><strong>${verifyName}</strong> 您好！</span> | <a class="pad" href="http://220.168.94.226:8080/website/logout.htm">退出</a>  | <a href="http://220.168.94.226:8080/website/help/index.htm" class="help">帮助中心</a> | <a href="#" class="pad">客服</a>
			</div>
	</div>
<#else>
<div class="header">
		<h1 class="logo"><a href="http://220.168.94.226:8080/website/index.htm?method=indexApp"><img alt="安捷支付" title="安捷支付" src="<@sp.static/>/img/ttf/logo_s.png"></a>
		<i class="ver_txt" title="${txt}">${txt}</i>
		</h1>
		<div class="header_bar"><a class="pad" href="http://220.168.94.226:8080/website/outapp.htm">登录</a> | <a href="http://220.168.94.226:8080/website/register.htm" class="pad">注册</a> | <a href="http://220.168.94.226:8080/website/help/index.htm" class="help">帮助中心</a> | <a href="#" class="pad">客服</a></div>
</div>


</#if>
</#macro>