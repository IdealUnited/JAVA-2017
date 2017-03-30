<#macro area areaId="areaCode" areaName="areaCode" cityId="cityCode" cityName="cityCode" areaSelected="" citySelected="" type="">
<#if type="txt">
<select id="${areaId}" name="${areaName}" class="m_minhalf" disabled="disabled" >
</select>
<select id="${cityId}" name="${cityName}" class="m_minhalf" disabled="disabled">
<option value="">请选择城市</option>
</select>
<#else>
<select id="${areaId}" name="${areaName}" class="m_minhalf select1"  >
</select>
<select id="${cityId}" name="${cityName}" class="m_minhalf select1">
<option value="">请选择城市</option>
</select>
</#if>
<script>
$(function(){
	$("#${areaId}").load("${rc.contextPath}/area.htm?provincecode=${areaSelected}&math="+Math.random());
	$("#${areaId}").change(function(){
		$("#${cityId}").load("${rc.contextPath}/area.htm?method=city&provincecode="+$(this).val()+"&citycode=${citySelected}&math="+Math.random());
	});
});
</script>
<#if areaSelected?has_content>
<script>
$(function(){
	$("#${cityId}").load("${rc.contextPath}/area.htm?method=city&provincecode=${areaSelected}&citycode=${citySelected}&math="+Math.random());
});
</script>
</#if>
</#macro>