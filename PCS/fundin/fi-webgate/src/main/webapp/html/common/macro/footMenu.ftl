<#macro footMenu channel="0_0">  
<script>
    $(function(){
    	$("#sub_nav2 h3").click(function(){
    			var ul=$(this).next();
    			var dis=$(ul).css("display");
    			if(ul.is("h3")){
    				return false;
    			}
    			if(dis=="none"){
    				$(ul).show();
    				$(ul).addClass("hide");
    				$(this).find("a").removeClass("up");
    				$(this).find("a").addClass("down");
    			}else{
    				$(ul).hide();
    				$(ul).removeClass("hide");
    				$(this).find("a").removeClass("down");
    				$(this).find("a").addClass("up");
    			}
    	});
    	var index = ($("#1").val()).substring(0,1);
        if(index!=0){
             $("#"+index).show();
             $("#a"+index).addClass("show");
        }
    });
</script>

<#assign flag=channel[0..0]/> 

<!--侧导航 str -->
	<div class="sub_nav2" id="sub_nav2">
		<h3><a id="a1" class="down" href="javascript:void(0)">关于安捷支付/a></h3>
		<ul <#if flag='1'>class="show solid_red2"</#if> id="1" value="${channel}">
			<li><a <#if channel=1+"_"+1>class="cur"</#if> href="<@contextPath/>/footManage.htm">公司简介</a></li>
			<li><a <#if channel=1+"_"+2>class="cur"</#if> href="<@contextPath/>/footManage.htm?method=socialRespons">社会责任</a></li>
			<li><a <#if channel=1+"_"+3>class="cur"</#if> href="<@contextPath/>/footManage.htm?method=privacyServices">隐私声明</a></li>
		</ul>
		
		<h3><a id="a2" class="down" href="javascript:void(0)">新闻与公告</a></h3>
		<ul <#if flag='2'>class="show solid_red2"</#if>  id="2" value="${channel}">
			<!--<li><a <#if channel=2+"_"+1>class="cur"</#if> href="<@contextPath/>/footManage.htm?method=newsDetail">新闻中心</a></li>-->
			<li><a <#if channel=2+"_"+1>class="cur"</#if> href="<@contextPath/>/announcement.htm">公告板</a></li>
		</ul>
		
		<h3><a id="a3" class="down" href="javascript:void(0)">安全保障</a></h3>
		<ul <#if flag='3'>class="show solid_red2"</#if>  id="3" value="${channel}">
			<li><a <#if channel=3+"_"+1>class="cur"</#if> href="<@contextPath/>/footManage.htm?method=riskControlServices">风险控制体系</a></li>
			<#--<li><a <#if channel=3+"_"+2>class="cur"</#if> href="<@contextPath/>/footManage.htm?method=securityService">安全保障</a></li>-->
			<li><a <#if channel=3+"_"+3>class="cur"</#if> href="<@contextPath/>/footManage.htm?method=riskguideServices">风险防范指南</a></li>
		</ul>
		
		<h3><a id="a4" class="down" href="javascript:void(0)">合作伙伴</a></h3>
		<ul <#if flag='4'>class="show solid_red2"</#if>  id="4" value="${channel}">
			<li><a <#if channel=4+"_"+1>class="cur"</#if> href="${rc.contextPath}/footManage.htm?method=partners">合作银行</a></li>
			<li><a <#if channel=4+"_"+2>class="cur"</#if> href="${rc.contextPath}/footManage.htm?method=partnersMerchant">合作商户</a></li>
		</ul>
		<h3 class="last"><a id="a5" class="down" href="javascript:void(0)">联系我们</a></h3>
		<ul <#if flag='5'>class="show solid_red2"</#if> class="ul_last" id="4" value="${channel}">
			<li><a <#if channel=5+"_"+1>class="cur"</#if> href="${rc.contextPath}/footManage.htm?method=customerService">客服中心</a></li>
		</ul>
	</div>
<!--侧导航 end -->
</#macro> 			