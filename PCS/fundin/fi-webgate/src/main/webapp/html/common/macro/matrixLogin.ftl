<#macro matrixlogin >  
<link href="<@sp.static/>/css/dialog/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<@sp.static/>/js/ttf/common/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<@sp.static/>/js/ttf/common/plugins/jquery-ui.js"></script>
<script type="text/javascript" src="<@sp.static/>/js/ttf/common/pay-dialog.js"></script>
<script type="text/javascript">
var jmpUrl="";
var mWin;
$(function(){
      mWin=$.pay.window("<@sp.contextPath/>/app/matrixLogin.htm",{title:"口令卡验证",w:600,h:350,url:true,iframe:true,close:function(){
      	var redirect="<@sp.contextPath/>/app/matrixLogin.htm?method=redirect";
      	if(jmpUrl!=""){
      		redirect+="&jumpUrl="+jmpUrl;
      	}
      
          location.href=redirect;
    }});
});
</script>
</#macro> 