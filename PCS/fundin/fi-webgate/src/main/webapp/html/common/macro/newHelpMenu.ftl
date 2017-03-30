
<#macro newHelpMenu channel="0_0">  
<script>
    $(function(){
    	$("#sidermenu h3").click(function(){
    			var ul=$(this).next();
    			var dis=$(ul).css("display");
    			if(dis=="none"){
    				$(ul).show();
    				$(ul).addClass("show");
    				$(this).find("a").addClass("show");
    			}else{
    				$(ul).hide();
    				$(ul).removeClass("show");
    				$(this).find("a").removeClass("show");
    			}
    	});
    	
    	var str=$("#1").attr("value");
    	var index = str.substring(0,str.lastIndexOf('_'));
        if(index!=0){
             $("#"+index).show();
             $("#a"+index).addClass("show");
        }
    	
    
    });
</script>
<div id="sidermenu" class="sub_nav fl">
	     <#assign  channelIndex=channel?index_of("_")>
	     <#if channelIndex gte 0>
	 		<#assign  channelIndex=channel[0..channelIndex]>
	     </#if>
		<h3 class="first"><a id="a1" class="hide" href="javascript:void(0);">我的账户</a></h3>
		  <ul style="display:none" id="1" value="${channel}">
		       <li><a <#if channel=1+"_"+1>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=myAccountRegister">注册与激活</a></li>
		       <li><a <#if channel=1+"_"+2>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=myAccountVerify">实名认证</a></li>
		       <li><a <#if channel=1+"_"+3>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=myAccountPassword">密码问题</a></li>
		       <li><a <#if channel=1+"_"+4>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=myAccountException">账户异常</a></li>
		  </ul>
		<h3 ><a id="a2" class="hide" href="javascript:void(0)">交易规则</a></h3>
		   <ul style="display:none" id="2">
			    <li><a <#if channel=2+"_"+1>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=transactionProcess">交易流程</a></li>
			    <li><a <#if channel=2+"_"+2>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=transactionRule">交易规则</a></li>
			    <li><a <#if channel=2+"_"+3>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=transactionPayment">交易付款</a></li>
			    <li><a <#if channel=2+"_"+4>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=transactionQuestion">交易问题</a></li>
		  </ul>
	    <h3 ><a id="a3" class="hide" href="javascript:void(0)">交易记录</a></h3>
	       <ul style="display:none" id="3">
			    <li><a <#if channel=3+"_"+1>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=consumptionRecord">交易记录</a></li>
			    <li><a <#if channel=3+"_"+2>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=consumptionRechargeAndWithdraw">充提记录</a></li>
			    <#--<li><a <#if channel=3+"_"+3>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=consumptionEvaluation">交易评价</a></li>-->
		  </ul>
	    <h3 ><a id="a4" class="hide" href="javascript:void(0)">生活管家</a></h3>
	       <ul style="display:none" id="4">
			    <#--<li><a <#if channel=4+"_"+1>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeMyPayment">安捷支付</a></li>
			    <li><a <#if channel=4+"_"+2>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeMyCollect">我要收款</a></li>
			    <li><a <#if channel=4+"_"+3>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeSecuredTransaction">担保交易收款</a></li>-->
			    <li><a <#if channel=4+"_"+4>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeShuidianmei">水电煤</a></li>
			    <li><a <#if channel=4+"_"+5>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeCommunication">通讯费</a></li>
			    <li><a <#if channel=4+"_"+6>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeCreditCardPayment">信用卡还款</a></li>
			    <li><a <#if channel=4+"_"+7>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifePresent">送礼金</a></li>
			    <li><a <#if channel=4+"_"+8>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeRent">交房租</a></li>
			    <li><a <#if channel=4+"_"+9>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeAACollect">AA收款</a></li>
			    <li><a <#if channel=4+"_"+10>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifelivingExpenses">生活费</a></li>
			    <li><a <#if channel=4+"_"+11>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeAlimony">赡养费</a></li>
			    <li><a <#if channel=4+"_"+12>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeInterBank">跨行转账</a></li>
			    <li><a <#if channel=4+"_"+13>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeMortgagerPayment">缴房贷</a></li>
		  </ul>   
	    <h3 ><a id="a5" class="hide" href="javascript:void(0)">安全中心</a></h3>
	       <ul style="display:none" id="5">
			    <li><a <#if channel=5+"_"+1>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpSecuritys&securityType=1">安全控件</a></li>
			    <li><a <#if channel=5+"_"+2>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpSecuritys&securityType=2">安全产品</a></li>
			    <li><a <#if channel=5+"_"+3>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpSecuritys&securityType=3">安全合作</a></li>
			    <li><a <#if channel=5+"_"+4>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpSecuritys&securityType=4">安全保护问题</a></li>
			    <li><a <#if channel=5+"_"+5>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpSecuritys&securityType=5">安全策略</a></li>
		  </ul>	    
	    <h3 ><a id="a6" class="hide" href="javascript:void(0)">收银台</a></h3>
	       <ul style="display:none" id="6">
			    <li><a <#if channel=6+"_"+1>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpCheckouts&securityType=1">充值</a></li>
			    <li><a <#if channel=6+"_"+2>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpCheckouts&securityType=2">充值回退</a></li>
			    <li><a <#if channel=6+"_"+3>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpCheckouts&securityType=3">提现</a></li>
		  </ul>	    
	    <#--<h3 ><a class="hide" href="<@contextPath/>/help.htm?method=helppayayPays&securityType=0"安捷支付支付款</a></h3>   -->
	    <h3 ><a id="a8" class="hide" href="javascript:void(0)">其他问题</a></h3>
	       <ul style="display:none" id="8">
			    <li><a <#if channel=8+"_"+1>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpOther&securityType=1">实用技巧</a></li>
			    <li><a <#if channel=8+"_"+2>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpOther&securityType=2">其他问题</a></li>
			    <li><a <#if channel=8+"_"+3>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpOther&securityType=3">联系我们</a></li>
		  </ul>	    
	    <h3 ><a id="a9" class="hide" href="javascript:void(0)">个人协议</a></h3>
	      <ul style="display:none" id="9">
			    <li><a <#if channel=9+"_"+1>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpPersonProtocols&type=1">服务协议</a></li>
		  </ul>
		 <h3 ><a id="a10" class="hide" href="javascript:void(0)">企业服务</a></h3>
		   <ul style="display:none" id="10">
			    <li><a <#if channel=10+"_"+1>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=enterpriseAccess">商户接入</a></li>
			    <li><a <#if channel=10+"_"+2>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=enterpriseCalculate">清算帮助</a></li>
			    <li><a <#if channel=10+"_"+3>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=enterpriseWithdraw">企业提现</a></li>
		  	    <li><a <#if channel=10+"_"+4>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=enterpriseProtocols">企业协议</a></li>
		  </ul>	
</div>
</#macro> 			