/**
 * 下拉框对象
 * 参数说明
 * pid：父下拉框id 
 * id：(子)下拉框id 
 * name：(子)下拉框显示名称
 * 
 */
function dropDownListMode(pid, id, name) {
	this.pid = pid;
	this.id = id;
	this.name = name;
}

/**
 * 下拉框联动
 * demo详见merchantList.jsp的function changeProvince()
 * 
 * 参数说明 
 * String fatherId：父下拉框id
 * String sonId：根据父下拉框值的变化而变化的子下拉框的id
 * Array[] relationDataArray：父子关系数据,Array<dropDownListMode> 
 * Array[] sonDataArray：子下拉框原始数据,Array<dropDownListMode> 可以为空
 */
function changeFatherSelect(fatherId, sonId, relationDataArray,	sonDataArray) {
	
	var obj = document.getElementById(fatherId);
	var toobj = document.getElementById(sonId);
	var relationArray = relationDataArray;
	var sonArray = sonDataArray;
	/*for (i = toobj.options.length - 1; i > 0; i--) {
		toobj.options[i] = null;
	}
	*/
	var ln = toobj.options.length;
	while(ln--){
		toobj.options[ln] = null;
	}
	if (obj.value != "") {
		for ( var i = 0; i < relationArray.length; i++) {
			if (relationArray[i].pid == obj.value) {
				toobj.options[toobj.options.length] = new Option(
						relationArray[i].name, relationArray[i].id);
			}
		}
	} else {
		for ( var i = 0; i < sonArray.length; i++) {
			toobj.options[toobj.options.length] = new Option(sonArray[i].name,
					sonArray[i].id);
		}
	}

}
function appealChangeFatherSelect(fatherId, sonId, relationDataArray,sonDataArray) {
	var obj = document.getElementById(fatherId);
	var toobj = document.getElementById(sonId);
	var relationArray = relationDataArray;
	var sonArray = sonDataArray;
	/*for (i = toobj.options.length - 1; i > 0; i--) {
		toobj.options[i] = null;
	}
	*/
	var ln = toobj.options.length;
	while(ln--){
		toobj.options[ln] = null;
	}
	toobj.options[0] = new Option('---请选择---', '');
	if (obj.value != "") {
		
		for ( var i = 0; i < relationArray.length; i++) {
			if (relationArray[i].pid == obj.value) {
				toobj.options[toobj.options.length] = new Option(
						relationArray[i].name, relationArray[i].id);
			}
		}
	} else {
		
		for ( var i = 0; i < sonArray.length; i++) {
			toobj.options[toobj.options.length] = new Option(sonArray[i].name,
					sonArray[i].id);
		}
	}
}


/*
 *输入框最大长度校验，避免js的maxlength对字母和汉字不区分的问题
 */  
function getCharLength4Str(str){
		
	var i,sum,str; 
	sum=0; 
	for(i=0;i<str.length;i++) 
	{ 
	if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255)) 
		sum=sum+1; 
	else 
		sum=sum+2; 
	} 
	return sum;
}
function checkMaxLength(elementId,maxLen){
	if(null == elementId || null == maxLen){
		return false;
	}
	var obj = document.getElementById(elementId);
	var str = obj.value;
	if (getCharLength4Str(str)>maxLen ){
	    var len = parseInt(maxLen/2);
	    alert("输入的长度超长，最多只能录入"+len+"个汉字，或"+maxLen+"个字符，请重新录入！");
	    obj.focus();
	    return false;
	}else{
	    return true;
  	}

	}

//验证日期
function validDate(strDate1,strDate2){
	if("" != strDate1 && "" != strDate2 &&
			0 != strDate1.length && 0 != strDate2.length){
		var date1 = new Date(strDate1.replace("-","/"));
		var date2 = new Date(strDate2.replace("-","/"));
		if(date1 > date2){
			return false;
		}
	}
	return true;
}

//判断正整数
function isPositiveNum(s){
	var r = /^\+?[1-9][0-9]*$/;
	return r.test(s); 
}

//判断金额
function isAmount(s){
	var r = /^\d+(\.\d{1,2})?$/;
	return r.test(s); 
}







