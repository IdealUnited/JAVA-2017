<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>showpages</title>
    <style type="text/css">
    	/*CSS black2 style pagination*/

		DIV.black2 {
			PADDING-RIGHT: 7px; PADDING-LEFT: 7px; PADDING-BOTTOM: 7px; MARGIN: 3px; PADDING-TOP: 7px; TEXT-ALIGN: center
		}
		DIV.black2 A {
			BORDER-RIGHT: #000000 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #000000 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #000000 1px solid; COLOR: #000000; PADDING-TOP: 2px; BORDER-BOTTOM: #000000 1px solid; TEXT-DECORATION: none
		}
		DIV.black2 A:hover {
			BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; COLOR: #fff; BORDER-BOTTOM: #000000 1px solid; BACKGROUND-COLOR: #000
		}
		DIV.black2 A:active {
			BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; COLOR: #fff; BORDER-BOTTOM: #000000 1px solid; BACKGROUND-COLOR: #000
		}
		DIV.black2 SPAN.current {
			BORDER-RIGHT: #000000 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #000000 1px solid; PADDING-LEFT: 5px; FONT-WEIGHT: bold; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #000000 1px solid; COLOR: #fff; PADDING-TOP: 2px; BORDER-BOTTOM: #000000 1px solid; BACKGROUND-COLOR: #000000
		}
		DIV.black2 SPAN.disabled {
			BORDER-RIGHT: #eee 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #eee 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #eee 1px solid; COLOR: #ddd; PADDING-TOP: 2px; BORDER-BOTTOM: #eee 1px solid
		}
		DIV.black2 input.itext {
			width: 60px;
		}
    </style>
    
	<!--<link href="${ctx}/css/page.css" type="text/css" rel="StyleSheet"/>-->
	<script src="${ctx}/js/page.js" type="text/javascript"></script>
  </head>
  
  <body>
	<script type="text/javascript">
		<!--
			var pg = new showPages('pg');
			pg.page = ${page.pageNo}; //当前页数
			pg.argName = 'pageNo'; //参数名
			pg.pageCount = ${page.totalPages}; // 定义总页数(必要)
			pg.totalCount = ${page.totalCount}; //总记录数
			pg.printHtml('black2'); //样式
		//-->
	</script>
	
  </body>
</html>
