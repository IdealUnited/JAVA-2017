/**
 * 验证是否手机号码
 * @param {} mobile
 * @return {Boolean}
 */
function s_validateMobile(mobile) {
	var reMobile = /^1[358][0123457689]\d{8}$/;
	return mobile.match(reMobile) || mobile == "";
}

/**
 * 验证Email地址
 * @param {} mail
 * @return {Boolean}
 */
function s_validateEmail(mail) {
	var reEmail = /^([A-Za-z0-9])(\w)+@(\w)+(\.)(com|com\.cn|net|cn|net\.cn|org|biz|info|gov|gov\.cn|edu|edu\.cn)/;
	return mail.match(reEmail) || mail == "";
}

/*
 * 验证是否数字 @param 要验证的数字变量 return 是否数字
 */
function s_isNumber(numStr) {
	if (numStr > (-100000000000000000000000000000000) && numStr < 100000000000000000000000000000000)
		return true;
	else
		return false;
}

/**
 * 验证电话号码
 * @param {} phone
 * @return {Boolean}
 */
function s_validatePhone(phone) {
	var reg1 = /(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/
	return reg1.test(phone) || phone == "";
}

/**
 * 验证特殊字符(字母,数字和汉字以外)
 * 
 * @param 要验证的字符串
 * @return 有特殊字符返回false, 没有返回true
 */
function s_validateEspeciallyStrAndChinese(strValue) {
	var strpattern = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	return strpattern.test(strValue);
}

/**
 * 验证字符长度
 * 
 * @param 要验证的字符串变量
 * @param 最小字符数
 * @param 最大字符数
 *            return 是否在范围之内
 */
function s_validateStrLength(str, minLength, maxLength) {
	if (str.len_() < minLength || str.len_() > maxLength) {
		return false;
	} else {
		return true;
	}
}

/**
 * 计算字符串长度
 * 
 * @return {}
 */
String.prototype.len_ = function() {
	return this.replace(/[^\x00-\xff]/g, "**").length;
}

/**
 * 验证身份证号码
 * 
 * @param {}
 *            num
 * @return {Boolean}
 */
function s_isIdCardNo(num) {
	num = num.toUpperCase();
	// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
		return "身份证号码长度不对，或者号码不符合规定！<br />15位号码应全为数字，18位号码末位可以为数字或X。";
	}
	// 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
	// 下面分别分析出生日期和校验位
	var len, re;
	len = num.length;
	if (len == 15) {
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
		var arrSplit = num.match(re);

		// 检查生日日期是否正确
		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/'
				+ arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2]))
				&& ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
				&& (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay) {
			return "身份证号码出生日期有误！";
		} else {
			// 将15位身份证转成18位
			// 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
					8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4',
					'3', '2');
			var nTemp = 0, i;
			num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
			for (i = 0; i < 17; i++) {
				nTemp += num.substr(i, 1) * arrInt[i];
			}
			num += arrCh[nTemp % 11];
			return "";
		}
	}
	if (len == 18) {
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
		var arrSplit = num.match(re);

		// 检查生日日期是否正确
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/"
				+ arrSplit[4]);
		var bGoodDay;
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2]))
				&& ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
				&& (dtmBirth.getDate() == Number(arrSplit[4]));
		if (!bGoodDay) {
			// alert(dtmBirth.getYear());
			// alert(arrSplit[2]);
			return "身份证号码出生日期有误！";
		} else {
			// 检验18位身份证的校验码是否正确。
			// 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
			var valnum;
			var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
					8, 4, 2);
			var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4',
					'3', '2');
			var nTemp = 0, i;
			for (i = 0; i < 17; i++) {
				nTemp += num.substr(i, 1) * arrInt[i];
			}
			valnum = arrCh[nTemp % 11];
			if (valnum != num.substr(17, 1)) {
				return "18位身份证的校验码不正确！应该为：" + valnum;
			}
			return "";
		}
	}
	return "身份证号码长度不对, 应该为15或18位!";
}



/**
 * 验证身份证号码, 带地区验证
 * @param {} sId
 * @return {String}
 */
function s_cidInfo(sId) {
	var iSum = 0
	var info = ""
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(sId)))
		return "身份证号码长度不对，或者号码不符合规定！<br />15位号码应全为数字，18位号码末位可以为数字或X。";
	sId = sId.replace(/x$/i, "a");
	if (aCity[parseInt(sId.substr(0, 2))] == null)
		return "身份证号码中地区有误！";
	sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-"
			+ Number(sId.substr(12, 2));
	var d = new Date(sBirthday.replace(/-/g, "/"))
	if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d
			.getDate()))
		return "身份证号码中出生日期有误！";
	for (var i = 17; i >= 0; i--)
		iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11)
	if (iSum % 11 != 1)
		return "身份证号码中的最后几位输入有误!";
	return "";
}

//身份证号码的地区代码
var aCity = {
	11 : "北京", 12 : "天津", 13 : "河北", 14 : "山西", 15 : "内蒙古",
	21 : "辽宁", 22 : "吉林", 23 : "黑龙江", 31 : "上海", 32 : "江苏",
	33 : "浙江", 34 : "安徽", 35 : "福建", 36 : "江西", 37 : "山东",
	41 : "河南", 42 : "湖北", 43 : "湖南", 44 : "广东", 45 : "广西",
	46 : "海南", 50 : "重庆", 51 : "四川", 52 : "贵州", 53 : "云南",
	54 : "西藏", 61 : "陕西", 62 : "甘肃", 63 : "青海", 64 : "宁夏",
	65 : "新疆", 71 : "台湾", 81 : "香港", 82 : "澳门", 91 : "国外"
}