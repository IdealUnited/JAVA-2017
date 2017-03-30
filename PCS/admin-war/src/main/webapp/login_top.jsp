<%@ page contentType="text/html;charset=UTF-8"%>

<% 
	String ctxPath = request.getContextPath();
	String queryString = request.getQueryString(); 
	queryString = queryString==null?"":"?"+queryString;
%>
<script type="text/javascript">
	
<!--
	window.top.location.href = "<%=ctxPath%>"+"/login.jsp"+"<%=queryString%>";
//-->
</script>

