
<#macro showMenu channel="0_0">  
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
		       <li><a <#if channel=1+"_"+1>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=myAccountRegister">注册与激活</a></li>
		       <li><a <#if channel=1+"_"+2>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=myAccountRecharge">充值</a></li>
		       <li><a <#if channel=1+"_"+3>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=myAccountPayment">支付</a></li>
		  </ul>
	    <h3 ><a id="a2" class="hide" href="javascript:void(0)">生活管家</a></h3>
	       <ul style="display:none" id="2">
			    <li><a <#if channel=2+"_"+1>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifeShuidianmei">水电煤</a></li>
			    <li><a <#if channel=2+"_"+2>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifeRent">交房租</a></li>
			    <li><a <#if channel=2+"_"+3>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifelivingExpenses">生活费</a></li>
			    <li><a <#if channel=2+"_"+4>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifeAlimony">赡养费</a></li>
			    <li><a <#if channel=2+"_"+5>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifeMortgagerPayment">还房贷</a></li>
			    <li><a <#if channel=2+"_"+6>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifeInterBank">跨行转账</a></li>
		 		<li><a <#if channel=2+"_"+7>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifePresent">送礼金</a></li>
			    <li><a <#if channel=2+"_"+8>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifeAACollect">AA收款</a></li>
			    <li><a <#if channel=2+"_"+9>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifeCommunication">通信账单</a></li>
			    <li><a <#if channel=2+"_"+10>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifeMobileRecharge">手机充值</a></li>
			    <li><a <#if channel=2+"_"+11>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=lifeCreditCardPayment">信用卡还款</a></li>
		  </ul>   
	    <h3 ><a id="a3" class="hide" href="javascript:void(0)">交易记录</a></h3>
	       <ul style="display:none" id="3">
			    <li><a <#if channel=3+"_"+1>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=transactionPayment">付款记录</a></li>
			    <li><a <#if channel=3+"_"+2>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=transactionRechargeAndWithdraw">充提记录</a></li>
			    <li><a <#if channel=3+"_"+3>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=transactionRefund">退款记录</a></li>
		  </ul>	   
		 <h3 ><a id="a4" class="hide" href="javascript:void(0)">安全中心</a></h3>
	       <ul style="display:none" id="4">
			    <li><a <#if channel=4+"_"+1>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=securityPayPassword">支付密码</a></li>
			    <li><a <#if channel=4+"_"+2>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=securityQuestion">安全问题</a></li>
			    <li><a <#if channel=4+"_"+3>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=securityMatrixCard">口令卡</a></li>
			    <li><a <#if channel=4+"_"+4>class="cur"</#if> href="<@contextPath/>/showHelp.htm?method=securityGreet">问候语</a></li>
		  </ul>	  
</div>
</#macro> 			