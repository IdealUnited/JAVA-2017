// JavaScript Document
var isMozilla = (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument!='undefined');
var isIE = window.ActiveXObject ? true : false;
var isFirefox = (navigator.userAgent.toLowerCase().indexOf("firefox")!=-1);
var isSafari = (navigator.userAgent.toLowerCase().indexOf("safari")!=-1);
var isOpera = (navigator.userAgent.toLowerCase().indexOf("opera")!=-1);

String.prototype.htmlEncode = function() 
{
	return this.replace(/&/g, '&amp;').replace(/"/g, '&quot;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
}

String.prototype.trim = function() {
	return this.replace(/^\s*|\s*$/g, "");
}

String.prototype.isNumber = function() {
    var reg = /^[\d|\.|,]+$/;
    return reg.test(this);
}

String.prototype.isInt = function() {
    var reg = /^\d+$/;
    return reg.test(this);
}

String.prototype.isEmail = function()
{
    var reg1 = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;

    return reg1.test( this );
}

String.prototype.isTime = function()
{
	var reg = /^\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}$/;

	return reg.test(this);
}

String.prototype.isDate = function()
{
	var reg = /^\d{4}-\d{2}-\d{2}$/;

	return reg.test(this);
}

String.prototype.isEnglish = function()
{
	var reg  = /^[A-Za-z]+$/;
	
	return reg.test(this);
}

String.prototype.isURL = function()
{
	var reg  = new RegExp("[a-zA-z]+://[^\s]*");
	
	return reg.test(this);
}

String.prototype.isPhone = function()
{	
	var reg  = new RegExp("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$");
	
	return reg.test(this);
}

String.prototype.isMobile = function()
{	
	var reg  = new RegExp("^(13|15|18|17|14)[0-9]{9}$");
	
	return reg.test(this);
}
String.prototype.isPost = function()
{	
	var reg  = new RegExp("^\\d{6}$");
	
	return reg.test(this);
}
String.prototype.isOrgCode = function()
{	
	var reg  = new RegExp("^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$");
	
	return reg.test(this);
}

var Global = new Object();

Global.fixEvent = function(e) 
{
    var evt = (typeof e == "undefined") ? window.event : e;
    return evt;
}

Global.srcElement = function(e)
{
    if (typeof e == "undefined") e = window.event;
    var src = document.all ? e.srcElement : e.target;

    return src;
}
