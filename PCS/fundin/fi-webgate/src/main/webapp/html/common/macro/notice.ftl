<#macro notice>
	<style type="text/css">
ul,li{margin:0;padding:0}
#scrollDiv{overflow:hidden;}
#scrollDiv li{height:28px;}
</style>
    <script>
        $(function(){
			$("#scrollDiv").textSlider({line:1,speed:500,timer:6000});
		});
     </script>
<div id="scrollDiv" class="index_notice"> <strong>公告：</strong>
<div class="notice_con">
<ul>
			<#if ggls?exists>
					<#list ggls as ls>
					<#if ls_index lte 8>
					<li>
					<p><a href="${rc.contextPath}/infomation.htm?method=infoAnnouncement&id=${ls.id?default(0)}" target="_blank">${ls.subject?default('')}</a></p>
					<span class="time">${ls.startTime?string('yyyy-MM-dd HH:mm')}</span>
					</li>
					</#if>
					</#list>
					</#if>
	</ul>
</div>
</div>
</#macro> 

<#--

<ul>
			<#if ggls?exists>
					<#list ggls as ls>
					<#if ls_index lte 8>
					<li>
					<p><a href="<@sp.contextPath/>/infomation.htm?method=infoAnnouncement&id=${ls.id?default(0)}" target="_blank">${ls.subject?default('')} ${ls.message?default('')}</a></p>
					<span class="time">2010-07-01</span>
					</li>
					</#if>
					</#list>
					</#if>
	</ul>
-->