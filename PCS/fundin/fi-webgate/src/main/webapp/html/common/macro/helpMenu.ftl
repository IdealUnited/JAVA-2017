
<#macro helpMenu channel="0_0">  
<script>
    $(function(){
    	$("#sidermenu h3").click(function(){
    			var ul=$(this).next();
    			var dis=$(ul).css("display");
    			if(dis=="none"){
    				$(ul).show();
    				$(ul).addClass("hide");
    				$(this).find("a").addClass("show");
    			}else{
    				$(ul).hide();
    				$(ul).removeClass("hide");
    				$(this).find("a").removeClass("show");
    			}
    	});
    	
    	var index = ($("#1").val()).substring(0,1);
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
		  </ul>
	    <h3 ><a id="a2" class="hide" href="javascript:void(0)">生活管家</a></h3>
	       <ul style="display:none" id="2">
			    <li><a <#if channel=2+"_"+1>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeShuidianmei">水电煤</a></li>
			    <li><a <#if channel=2+"_"+2>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeRent">交房租</a></li>
			    <li><a <#if channel=2+"_"+3>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifelivingExpenses">生活费</a></li>
			    <li><a <#if channel=2+"_"+4>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeCommunication">赡养费</a></li>
			    <li><a <#if channel=2+"_"+5>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeCreditCardPayment">跨行转账</a></li>
			    <li><a <#if channel=2+"_"+6>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifePresent">AA收款</a></li>
			    <li><a <#if channel=2+"_"+7>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeAACollect">通信账单</a></li>
			    <li><a <#if channel=2+"_"+8>class="cur"</#if> href="<@contextPath/>/baseHelp.htm?method=lifeAlimony">手机充值</a></li>
		  </ul>   
	    <h3 ><a id="a3" class="hide" href="javascript:void(0)">交易记录</a></h3>
	       <ul style="display:none" id="3">
			    <li><a <#if channel=3+"_"+1>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpCheckouts&securityType=1">付款记录</a></li>
			    <li><a <#if channel=3+"_"+2>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpCheckouts&securityType=2">充提记录</a></li>
			    <li><a <#if channel=3+"_"+3>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpCheckouts&securityType=3">退款记录</a></li>
		  </ul>	   
		 <h3 ><a id="a4" class="hide" href="javascript:void(0)">安全中心</a></h3>
	       <ul style="display:none" id="4">
			    <li><a <#if channel=4+"_"+1>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpSecuritys&securityType=1">支付密码</a></li>
			    <li><a <#if channel=4+"_"+2>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpSecuritys&securityType=2">安全问题</a></li>
			    <li><a <#if channel=4+"_"+3>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpSecuritys&securityType=3">口令卡</a></li>
			    <li><a <#if channel=4+"_"+4>class="cur"</#if> href="<@contextPath/>/help.htm?method=helpSecuritys&securityType=4">问候语</a></li>
		  </ul>	  
</div>
</#macro> 			