<#-- 帮助中心 菜单 -->
<#macro helpCenterMenu channel=0 child=1 item=11>
	<div class="box_side" id="box_side">
		<div class="side_nav side_nav_hack">
			<h5>关于安捷支付</h5>
			<dl>
				<dt <#if child==1>class="dt_cur"</#if>><a href="javascript:void(0)">关于我们</a></dt>
				<dd class="<#if child==1>show<#else>hide</#if>"><a <#if item==11>class="a_cur"</#if> href="<@sp.seo/>/about.htm">公司简介</a></dd>
				<dd class="<#if child==1>show<#else>hide</#if>"><a <#if item==12>class="a_cur"</#if> href="${rc.contextPath}/announcement.htm">新闻公告</a></dd>
				<dd class="<#if child==1>show<#else>hide</#if>"><a <#if item==13>class="a_cur"</#if> href="${rc.contextPath}/about/privacy.htm">隐私条款</a></dd>
				<dd class="<#if child==1>show<#else>hide</#if>"><a <#if item==14>class="a_cur"</#if> href="${rc.contextPath}/about/contact.htm">联系我们</a></dd>
			</dl>
		</div>
		<div class="side_nav ">
			<h5>个人服务导航</h5>
			<dl>
				<dt <#if child==2>class="dt_cur"</#if>><a href="javascript:void(0)">新手教程</a></dt>
				<dd class="<#if child==2>show<#else>hide</#if>"><a <#if item==21>class="a_cur"</#if> href="${rc.contextPath}/help/p/about.htm">了解安捷支付</a></dd>
				<dd class="<#if child==2>show<#else>hide</#if>"><a <#if item==22>class="a_cur"</#if> href="${rc.contextPath}/help/p/guide.htm">快速使用指南</a></dd>
			</dl>
			<dl>
				<dt <#if child==3>class="dt_cur"</#if>><a href="javascript:void(0)">账户管理</a></dt>
				<dd class="<#if child==3>show<#else>hide</#if>"><a <#if item==31>class="a_cur"</#if> href="${rc.contextPath}/help/p/reglogin.htm">注册/登录</a></dd>
				<dd class="<#if child==3>show<#else>hide</#if>"><a <#if item==32>class="a_cur"</#if> href="${rc.contextPath}/help/p/accquery.htm">账户信息查询</a></dd>
				<dd class="<#if child==3>show<#else>hide</#if>"><a <#if item==33>class="a_cur"</#if> href="${rc.contextPath}/help/p/accmodify.htm">账户信息修改</a></dd>
				<dd class="<#if child==3>show<#else>hide</#if>"><a <#if item==34>class="a_cur"</#if> href="${rc.contextPath}/help/p/relnameauth.htm">实名认证</a></dd>
			</dl>
			<dl>
				<dt <#if child==4>class="dt_cur"</#if>><a href="javascript:void(0)">充值/提现</a></dt>
				<dd class="<#if child==4>show<#else>hide</#if>"><a <#if item==41>class="a_cur"</#if> href="${rc.contextPath}/help/p/charge.htm">充值</a></dd>
				<dd class="<#if child==4>show<#else>hide</#if>"><a <#if item==42>class="a_cur"</#if> href="${rc.contextPath}/help/p/withdraw.htm">提现</a></dd>
			</dl>
			<dl>
				<dt <#if child==5>class="dt_cur"</#if>><a href="javascript:void(0)">支付交易</a></dt>
				<dd class="<#if child==5>show<#else>hide</#if>"><a <#if item==51>class="a_cur"</#if> href="${rc.contextPath}/help/p/payment.htm">付款</a></dd>
				<dd class="<#if child==5>show<#else>hide</#if>"><a <#if item==52>class="a_cur"</#if> href="${rc.contextPath}/help/p/trademanage.htm">交易管理</a></dd>
				<dd class="<#if child==5>show<#else>hide</#if>"><a <#if item==53>class="a_cur"</#if> href="${rc.contextPath}/help/p/traderegulation.htm">交易规则</a></dd>
			</dl>
			<dl>
				<dt <#if child==11>class="dt_cur"</#if>><a href="javascript:void(0)">网上银行</a></dt>
				<dd class="<#if child==11>show<#else>hide</#if>"><a <#if item==111>class="a_cur"</#if> href="${rc.contextPath}/help/p/payQuota.htm">支付额度</a></dd>
			</dl>
			<dl>
				<dt <#if child==6>class="dt_cur"</#if>><a href="javascript:void(0)">账户安全</a></dt>
				<dd class="<#if child==6>show<#else>hide</#if>"><a <#if item==61>class="a_cur"</#if> href="${rc.contextPath}/help/p/securityproduct.htm">安全产品</a></dd>
				<dd class="<#if child==6>show<#else>hide</#if>"><a <#if item==62>class="a_cur"</#if> href="${rc.contextPath}/help/p/safeClassroom.htm">安全课堂</a></dd>
			</dl>
			<#--
			<dl>
				<dt <#if child==7>class="dt_cur"</#if>><a href="javascript:void(0)">生活助手</a></dt>
				<dd class="<#if child==7>show<#else>hide</#if>"><a href="#"></a></dd>
			</dl>-->
			<dl>
				<dt <#if child==7>class="dt_cur"</#if>><a href="javascript:void(0)">服务协议</a></dt>
				<dd class="<#if child==7>show<#else>hide</#if>"><a <#if item==62>class="a_cur"</#if> href="${rc.contextPath}/help.htm?method=helpPersonProtocols&type=1">个人协议</a></dd>
			</dl>
		</div>
		<div class="side_nav ">
			<h5>商户服务导航</h5>
			<dl>
				<dt <#if child==8>class="dt_cur"</#if>><a href="javascript:void(0)">签约指南</a></dt>
				<dd class="<#if child==8>show<#else>hide</#if>"><a <#if item==81>class="a_cur"</#if> href="${rc.contextPath}/help/b/serviceexplain.htm">服务说明</a></dd>
				<dd class="<#if child==8>show<#else>hide</#if>"><a <#if item==82>class="a_cur"</#if> href="${rc.contextPath}/help/b/swithconservice.htm">如何接入服务</a></dd>
			</dl>
			<dl>
				<dt <#if child==9>class="dt_cur"</#if>><a href="javascript:void(0)">使用说明</a></dt>
				<dd class="<#if child==9>show<#else>hide</#if>"><a <#if item==91>class="a_cur"</#if> href="${rc.contextPath}/help/b/accmanage.htm">账户管理</a></dd>
				<dd class="<#if child==9>show<#else>hide</#if>"><a <#if item==92>class="a_cur"</#if> href="${rc.contextPath}/help/b/trademanage.htm">交易管理</a></dd>
				<dd class="<#if child==9>show<#else>hide</#if>"><a <#if item==93>class="a_cur"</#if> href="${rc.contextPath}/help/b/fundsmanage.htm">资金管理</a></dd>
				<dd class="<#if child==9>show<#else>hide</#if>"><a <#if item==95>class="a_cur"</#if> href="${rc.contextPath}/help/b/accsafe.htm">账户安全</a></dd>
				<dd class="<#if child==9>show<#else>hide</#if>"><a <#if item==96>class="a_cur"</#if> href="${rc.contextPath}/help/b/serviceswitch.htm">服务接入</a></dd> 
			</dl>
			<dl>
				<dt <#if child==10>class="dt_cur"</#if>><a href="javascript:void(0)">服务协议</a></dt>
				<dd class="<#if child==10>show<#else>hide</#if>"><a <#if item==97>class="a_cur"</#if> href="${rc.contextPath}/help.htm?method=helpCorpProtocols">商户协议</a></dd>
			</dl>
		</div>
		<div class="servers_link mt10">
			<p>客服热线：</p>
			<strong>400-089-0098</strong> </div>
		<div class="advise_link"> <a href="${rc.contextPath}/baseHelp.htm?method=centerHelpAdvice"><img title="给安捷支付提建议 " src="<@sp.static/>/img/ttf/help/advise.png"></a> </div>
	</div>
</#macro>