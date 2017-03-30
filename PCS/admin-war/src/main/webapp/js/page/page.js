<!--
/*

showPages v1.1
=================================

Infomation
----------------------
Author : Lapuasi
E-Mail : lapuasi@gmail.com
Web : http://www.lapuasi.com
Date : 2005-11-17


Example
----------------------
var pg = new showPages('pg');
pg.pageCount = 12; //定义总页数(必要)
pg.argName = 'p';    //定义参数名(可选,缺省为page)
pg.printHtml();        //显示页数


Supported in Internet Explorer, Mozilla Firefox
*/

function showPages(name) { //初始化属性
	this.name = name;      //对象名称
	this.page = 1;         //当前页数
	this.pageCount = 1;    //总页数
	this.argName = 'page'; //参数名
	this.showTimes = 1;    //打印次数
	this.totalCount = -1;  //总记录数
}

showPages.prototype.getPage = function(){ //丛url获得当前页数,如果变量重复只获取最后一个
	var args = location.search;
	var reg = new RegExp('[\?&]?' + this.argName + '=([^&]*)[&$]?', 'gi');
	var chk = args.match(reg);
	this.page = RegExp.$1;
}
showPages.prototype.checkPages = function(){ //进行当前页数和总页数的验证
	if (isNaN(parseInt(this.page))) this.page = 1;
	if (isNaN(parseInt(this.pageCount))) this.pageCount = 1;
	if (this.page < 1) this.page = 1;
	if (this.pageCount < 1) this.pageCount = 1;
	if (this.page > this.pageCount) this.page = this.pageCount;
	this.page = parseInt(this.page);
	this.pageCount = parseInt(this.pageCount);
}
showPages.prototype.createHtml = function(className){ //生成html代码
	
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1;
	strHtml += '<div class="'+className+'">';
	strHtml += '<span>总计：' + this.totalCount + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
	strHtml += '<span>当前页/总页数：' + this.page + ' / ' + this.pageCount + '</span>';
	if (prevPage < 1) {
		strHtml += '<span title="First Page" class="disabled">&laquo;</span>';
		strHtml += '<span title="Prev Page" class="disabled">&#8249;</span>';
	} else {
		strHtml += '<span title="First Page"><a href="javascript:' + this.name + '.toPage(1);">&laquo;</a></span>';
		strHtml += '<span title="Prev Page"><a href="javascript:' + this.name + '.toPage(' + prevPage + ');">&#8249;</a></span>';
	}
	if (this.page != 1) strHtml += '<span title="Page 1"><a href="javascript:' + this.name + '.toPage(1);">1</a></span>';
	if (this.page >= 5) strHtml += '<span>...</span>';
	if (this.pageCount > this.page + 2) {
		var endPage = this.page + 2;
	} else {
		var endPage = this.pageCount;
	}
	for (var i = this.page - 2; i <= endPage; i++) {
		if (i > 0) {
			if (i == this.page) {
				strHtml += '<span title="Page ' + i + '" class="current">' + i + '</span>';
			} else {
				if (i != 1 && i != this.pageCount) {
					strHtml += '<span title="Page ' + i + '"><a href="javascript:' + this.name + '.toPage(' + i + ');">' + i + '</a></span>';
				}
			}
		}
	}
	if (this.page + 3 < this.pageCount) strHtml += '<span>...</span>';
	if (this.page != this.pageCount) strHtml += '<span title="Page ' + this.pageCount + '"><a href="javascript:' + this.name + '.toPage(' + this.pageCount + ');">' + this.pageCount + '</a></span>';
	if (nextPage > this.pageCount) {
		strHtml += '<span title="Next Page" class="disabled">&#8250;</span>';
		strHtml += '<span title="Last Page" class="disabled">&raquo;</span>';
	} else {
		strHtml += '<span title="Next Page"><a href="javascript:' + this.name + '.toPage(' + nextPage + ');">&#8250;</a></span>';
		strHtml += '<span title="Last Page"><a href="javascript:' + this.name + '.toPage(' + this.pageCount + ');">&raquo;</a></span>';
	}
	
	//模式5 (输入框)
	strHtml += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>';
	if (this.pageCount < 1) {
		strHtml += '<input type="text" name="toPage" value="No Pages" class="itext" disabled="disabled">';
		strHtml += '<input type="button" name="go" value="GO" class="ibutton" disabled="disabled"></option>';
	} else {
		//strHtml += '<input type="text" value="Input Page:" class="ititle" readonly="readonly">';
		strHtml += '<input type="text" id="pageInput' + this.showTimes + '" value="' + this.page + '" class="itext" title="Input page" onkeypress="return ' + this.name + '.formatInputPage(event);" onfocus="this.select()">';
		//strHtml += '<input type="text" value=" / ' + this.pageCount + '" class="icount" readonly="readonly">';
		strHtml += '<input type="button" name="go" value="GO" class="ibutton" onclick="' + this.name + '.toPage(document.getElementById(\'pageInput' + this.showTimes + '\').value);"></option>';
	}
	strHtml += '</span>';
	
	strHtml += '</div><br />';
	return strHtml;
}
showPages.prototype.createUrl = function (page) { //生成页面跳转url
	if (isNaN(parseInt(page))) page = 1;
	if (page < 1) page = 1;
	if (page > this.pageCount) page = this.pageCount;
	var url = location.protocol + '//' + location.host + location.pathname;
	var args = location.search;
	var reg = new RegExp('([\?&]?)' + this.argName + '=[^&]*[&$]?', 'gi');
	args = args.replace(reg,'$1');
	if (args == '' || args == null) {
		args += '?' + this.argName + '=' + page;
	} else if (args.substr(args.length - 1,1) == '?' || args.substr(args.length - 1,1) == '&') {
			args += this.argName + '=' + page;
	} else {
			args += '&' + this.argName + '=' + page;
	}
	return url + args;
}
showPages.prototype.toPage = function(page){ //页面跳转
	var turnTo = 1;
	if (typeof(page) == 'object') {
		turnTo = page.options[page.selectedIndex].value;
	} else {
		turnTo = page;
	}
	self.location.href = this.createUrl(turnTo);
}
showPages.prototype.printHtml = function(mode){ //显示html代码
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	document.write('<div id="pages_' + this.name + '_' + this.showTimes + '" class="pages"></div>');
	document.getElementById('pages_' + this.name + '_' + this.showTimes).innerHTML = this.createHtml(mode);
	
}
showPages.prototype.formatInputPage = function(e){ //限定输入页数格式
	var ie = navigator.appName=="Microsoft Internet Explorer"?true:false;
	if(!ie) var key = e.which;
	else var key = event.keyCode;
	if (key == 8 || key == 46 || (key >= 48 && key <= 57)) return true;
	return false;
}
//-->