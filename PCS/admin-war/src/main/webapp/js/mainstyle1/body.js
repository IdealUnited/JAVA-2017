function showPage(obj){
	for(i=0;i<frameTable.rows.length;i++){
	   if(obj.parentNode.cellIndex==i){
	     frameTable.rows(i).style.display="block";
		 menuList.rows[0].cells(i).childNodes(0).rows[0].cells(0).background="images/menu-1.gif";
	     menuList.rows[0].cells(i).childNodes(0).rows[0].cells(1).background="images/menu-2.gif";
		 menuList.rows[0].cells(i).childNodes(0).rows[0].cells(2).background="images/menu-3.gif";
	   }else{
	     frameTable.rows(i).style.display="none";
		 menuList.rows[0].cells(i).childNodes(0).rows[0].cells(0).background="images/menu2-1.gif";
	     menuList.rows[0].cells(i).childNodes(0).rows[0].cells(1).background="images/menu2-2.gif";
		 menuList.rows[0].cells(i).childNodes(0).rows[0].cells(2).background="images/menu2-3.gif";
	   }
	} 
}
function addMenu(str,aUrl){
  var tableName=aUrl;
	var obj = document.all.namedItem(tableName);
	var width=menuList.clientWidth;
	if(aUrl == '#'){
		alert("未指定链接页面");
		return;
	}
	if(obj==null){	//判断对象不存在添加一项
		if(width<=1024){
			menuList.rows[0].insertCell().innerHTML="<table id=\""+tableName+"\" onDblClick=\"delMenu(this)\" onClick=\"showPage(this)\" border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"cursor:hand\"><tr><td background=\"images/menu-1.gif\" width=\"23\" height=\"23\"></td><td background=\"images/menu-2.gif\" align=\"center\" valign=\"baseline\" nowrap class=\"titlemenu\">"+str+"</td><td background=\"images/menu-3.gif\" width=\"20\" height=\"23\"></td></tr></table>";
			createIframePage(aUrl);
			for(i=0;i<frameTable.rows.length-1;i++){
			   menuList.rows[0].cells(i).childNodes(0).rows[0].cells(0).background="images/menu2-1.gif";
			   menuList.rows[0].cells(i).childNodes(0).rows[0].cells(1).background="images/menu2-2.gif";
			   menuList.rows[0].cells(i).childNodes(0).rows[0].cells(2).background="images/menu2-3.gif";
			}
		}else{
			  alert("打开页面过多请先关闭一些然后再打开!");
		} 	
	}else{//对象存在直接打开
	  obj.fireEvent("onclick"); 
	}
}
function delMenu(thisObject){
	frameTable.deleteRow(thisObject.parentNode.cellIndex);
	frameTable.rows(thisObject.parentNode.cellIndex-1).style.display="block";
	
	menuList.rows[0].cells(thisObject.parentNode.cellIndex-1).childNodes(0).rows[0].cells(0).background="images/menu-1.gif";
	menuList.rows[0].cells(thisObject.parentNode.cellIndex-1).childNodes(0).rows[0].cells(1).background="images/menu-2.gif";
	menuList.rows[0].cells(thisObject.parentNode.cellIndex-1).childNodes(0).rows[0].cells(2).background="images/menu-3.gif";
	
	thisObject.parentNode.removeNode(thisObject);		
}
function createIframePage(Url){
	var frameName="frame"+frameTable.rows.length;
	frameTable.insertRow().insertCell().innerHTML="<iframe name=\""+frameName+"\" src=\""+Url+"\" frameborder=\"0\" scrolling=\"auto\" width=\"100%\" height=\"550\"></iframe>";
	for(i=0;i<frameTable.rows.length-1;i++){
	    frameTable.rows(i).style.display="none";
    } 
}
function closePage(aUrl){
    var obj = document.all.namedItem(aUrl);
    delMenu(obj);		
}

function addTabMenu(str,aUrl,tabId){
	  var tableName = tabId;
		var obj = document.all.namedItem(tableName);
		var width = menuList.clientWidth;
		if(aUrl == '#'){
			alert("未指定链接页面");
			return;
		}
		if(obj == null){	//判断对象不存在添加一项
			if(width <= 1024){
				menuList.rows[0].insertCell().innerHTML = "<table id=\""+tableName+"\" onDblClick=\"delMenu(this)\" onClick=\"showPage(this)\" border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"cursor:hand\"><tr><td background=\"images/menu-1.gif\" width=\"23\" height=\"23\"></td><td background=\"images/menu-2.gif\" align=\"center\" valign=\"baseline\" nowrap class=\"titlemenu\">"+str+"</td><td background=\"images/menu-3.gif\" width=\"20\" height=\"23\"></td></tr></table>";
				createIframePage(aUrl);
				for(i=0;i<frameTable.rows.length-1;i++){
				   menuList.rows[0].cells(i).childNodes(0).rows[0].cells(0).background="images/menu2-1.gif";
				   menuList.rows[0].cells(i).childNodes(0).rows[0].cells(1).background="images/menu2-2.gif";
				   menuList.rows[0].cells(i).childNodes(0).rows[0].cells(2).background="images/menu2-3.gif";
				}
			}else{
				  alert("打开页面过多请先关闭一些然后再打开!");
			} 	
		}else{//对象存在直接打开
		  obj.fireEvent("onclick"); 
		}
}