/**
 * 首页登录js
 */
$(function() {
	//点击登录框中任意一个输入框去掉报错信息
	$(".beacon-login-wrap .form-group input").click(function(){
		$("#corp_errtips").fadeOut();
		$("#errorMsg").fadeOut();
	});
});
//商户登录面板
$(function() {
	$("#j_password").val("");
	var t = "";
	$("#j_username").focus(function() {
		if ($(this).val() == t) {
			$(this).val("");
			$(this).removeClass("gray");
		}
	}).blur(function() {
		if ($(this).val() != t && $(this).val() == "") {
			$(this).val(t);
			$(this).addClass("gray");
		}
	});

	$("#loginform").submit(function() {
		var ln = $("#j_username").val();
		var pwd = $("#j_password").val();
		var rand = $("#checkCode").val();
		if (ln == t || ln.trim().length == "") {
			$("#corp_errtips").show();
			$("#corp_errtips").html("用户名不能为空");
			return false;
		}
		if (pwd == t || pwd.trim().length == 0) {
			$("#corp_errtips").html("密码不能为空");
			$("#corp_errtips").show();
			return false;
		}
		if (rand == t || rand.trim().length == 0) {
			$("#corp_errtips").html("验证码不能为空");
			$("#corp_errtips").show();
			return false;
		}
//		$("#corp_randCode").val($("#corp_rand_Code").val());
		//cookie 保存loginName
		$.login.cookie.set("loginForm", ln);
	});
	
//	$("#corp_refush").click(function() {
//		var d = new Date();
//		var src = "validatecode/validatecode.htm?date="+ d.getTime();
//		$("#code").attr("src", src);
//		return false;
//	});
//发送验证码到手机
//	if ($("#corp_rand_Code").length > 0) {
//		$("#corp_rand_Code").keypress(function(e) {
//			if (e.which == 13) {
//				$("#corp_loginForm").submit();
//			}
//		});
//	}

	$("#j_password").keypress(function(e) {
		if (e.which == 13) {
			$("#loginform").submit();
		}
	});

	//cookie 取loginName
//	var ln = $.login.cookie.get("loginName");
//	if (!!c_ln && c_ln.length > 0) {
//		$("#loginName").val(ln);
//		$("#loginName").removeClass("gray");
//	}
});

