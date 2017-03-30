var AccountValidate = function () {

	var handleResource = function () {
         	$('.resource-form').validate({
	            errorElement: 'span', //default input error message container
	            errorClass: 'help-block', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                name: {
	                    required: true,
	                },
	                code: {
	                    required: true
	                },
	                type: {
	                    required: true,
	                },
	                tmodule: {
	                	required: true,
	                },
	            },
	            messages: { // custom messages for radio buttons and checkboxes
					name: {
						required: "请输入名称。",
					},
					code: {
						required: "请输入标识。",
					},
					type: {
						required: "请选择类型。"
					},
					tmodule: {
						required: "请选择模块。"
					}
	            },
	            invalidHandler: function (event, validator) { //display error alert on form submit   
	            },
	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.form-group').addClass('has-error'); // set error class to the control group
	            },
	            success: function (label) {
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },
	            errorPlacement: function (error, element) {
	                if (element.attr("name") == "agreement") { // insert checkbox errors after the container                  
	                    error.insertAfter($('#register_agreement_error'));
	                } else if (element.closest('.input-icon').size() === 1) {
	                    error.insertAfter(element.closest('.input-icon'));
	                } else {
	                	error.insertAfter(element);
	                }
	            },
	            submitHandler: function (form) {
	                form.submit();
	            }
	        });
         	$('.resource-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.resource-form').validate().form()) {
	                    $('.resource-form').submit();
	                }
	                return false;
	            }
	        });
	}
    
    return {
        handleResource: function () {
        	handleResource();               
        }
    };
    
}();

var _userForm;
var _formPassword;
$( function() {	
	//检查密码是否正确
	$("#orgPassword").bind('blur', function(){
		$("#modifyMsg").text("");
		var orgPassword = $(this).val();
		var userId = $(this).attr("attrUserId");
		$.ajax({
			url: _ctx + '/user/checkUserPassword.jhtml',
			type : 'POST',
			data : "userId="+userId+"&userPwd="+orgPassword,
			dataType : 'json',
			success : function(data) {
				if(data.retCode == '0000'){
					if( data.retData == 1 ){
						$("#orgPassword").attr('ispass','false');
						_formPassword.bootstrapValidator('updateStatus', 'orgPassword', 'NOT_VALIDATED');
						_formPassword.bootstrapValidator('validateField', 'orgPassword');
					}else{
						$("#orgPassword").attr('ispass','true');
						_formPassword.bootstrapValidator('updateStatus', 'orgPassword', 'NOT_VALIDATED');
						_formPassword.bootstrapValidator('validateField', 'orgPassword');
					}
					
				}
			}
		});
	});
	
	//登录名是否存在
	$('#loginName').bind('blur', function() {
		var loginName = $(this).val();
		$.ajax({
			url: _ctx + '/user/checkUserByLoginName.jhtml',
			type : 'POST',
			data : "loginName="+loginName,
			dataType : 'json',
			success : function(data) {
				if(data.rtnCode == '0000'){
					var stepType = $("#stepType").val();
					$("#modifyMsg").text("");
					if( data.rtnData == 1 ){
						$("#loginName").attr('ispass','false');
						_userForm.bootstrapValidator('updateStatus', 'loginName', 'NOT_VALIDATED');
						_userForm.bootstrapValidator('validateField', 'loginName');
					}else{
						$("#loginName").attr('ispass','true');
						_userForm.bootstrapValidator('updateStatus', 'loginName', 'NOT_VALIDATED');
						_userForm.bootstrapValidator('validateField', 'loginName');
					}
					
				}
			}
		});
	});
	
	//验证手机号是否存在
	$('#mobile').bind('blur', function() {
		$("#modifyMsg").text("");
		var mobile = $(this).val();
		var stepType = $("#stepType").val();
		var requestUrl = "attrVal="+mobile+"&type=mobile&stepType="+stepType;
		if( stepType == 'modify' ){
			var userId = $(this).attr("attrUserId");
			requestUrl = "attrVal="+mobile+"&type=mobile&userId="+userId+"&stepType="+stepType;
		}
		$.ajax({
			url: _ctx + '/user/checkUserByMobileOrEmail.jhtml',
			type : 'POST',
			data : requestUrl,
			dataType : 'json',
			success : function(data) {
				$("#modifyMsg").text("");
				if(data.rtnCode == '0000'){
					if( data.rtnData == 1 ){
						$("#mobile").attr('ispass','false');
						_userForm.bootstrapValidator('updateStatus', 'mobile', 'NOT_VALIDATED');
						_userForm.bootstrapValidator('validateField', 'mobile');
					}else{
						$("#mobile").attr('ispass','true');
						_userForm.bootstrapValidator('updateStatus', 'mobile', 'NOT_VALIDATED');
						_userForm.bootstrapValidator('validateField', 'mobile');
					}
					
				}
			}
		});
	});
	
	//验证邮箱是否存在
	$('#email').bind('blur', function() {
		$("#modifyMsg").text("");
		var email = $(this).val();
		var stepType = $("#stepType").val();
		var requestUrl = "attrVal="+email+"&type=email&stepType="+stepType;
		if( stepType == 'modify' ){
			var userId = $(this).attr("attrUserId");
			requestUrl = "attrVal="+email+"&type=email&userId="+userId+"&stepType="+stepType;
		}
		$.ajax({
			url: _ctx + '/user/checkUserByMobileOrEmail.jhtml',
			type : 'POST',
			data : requestUrl,
			dataType : 'json',
			success : function(data) {
				if(data.rtnCode == '0000'){
					var stepType = $("#stepType").val();
					if( data.rtnData == 1 ){
						$("#email").attr('ispass','false');
						_userForm.bootstrapValidator('updateStatus', 'email', 'NOT_VALIDATED');
						_userForm.bootstrapValidator('validateField', 'email');
					}else{
						$("#email").attr('ispass','true');
						_userForm.bootstrapValidator('updateStatus', 'email', 'NOT_VALIDATED');
						_userForm.bootstrapValidator('validateField', 'email');
					}
				}
			}
		});
	});
	
	checkPasswordValidate();
	
	//检查用户
	checkUserValidate();
});

//验证密码 
function checkPasswordValidate(){
	_formPassword = $('#formPassword').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			orgPassword: {
				validators: {
					notEmpty: {
						message: '密码不能为空'
					},
                    callback: {
                        message: '密码不正确 ',
                        callback: function(value, validator) {
                        	if( value == "" ) return true;
                        	if($("#orgPassword").attr("ispass") == "false"){
                        		return false;
                        	}else{
                        		return true;
                        	}
                        }
                    }
				}
			},
			newPassword: {
				validators: {
					notEmpty: {
						message: '新密码不能为空'
					},
					regexp: {
						regexp: /^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{6,255}$/,
						message: '至少需要6个字符，包含字母和数字，区分大小写字母'
					},
                    callback: {
                        message: '密码不正确 ',
                        callback: function(value, validator) {
                        	$("#modifyMsg").text("");
                        	if( $("#confirmPassword").val() != "" ){
                        		$('#formPassword').data("bootstrapValidator").updateStatus("confirmPassword", 'NOT_VALIDATED').validateField('confirmPassword');
                        	}
                        	return true;
                        }
                    }
				}
			},
			confirmPassword: {
				validators: {
					notEmpty: {
						message: '确认密码不能为空'
					},
					identical: {
						field: 'newPassword',
						message: '两次输入密码不一致'
					},
                    callback: {
                        message: '密码不正确 ',
                        callback: function(value, validator) {
                        	$("#modifyMsg").text("");
                        	return true;
                        }
                    }
				}
			}
		}   
	});
}

//根据手机号，邮箱检查用户是否存在
function checkUserValidate(){
	_userForm = $('#userForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			loginName: {
				validators: {
					notEmpty: {
						message: '登录名不能为空'
					},
					stringLength: {
						min: 1,
						max: 255,
						message: '登录名必须在1到255位之间'
					},
					regexp: {
						regexp: /^[a-zA-Z\s\n]*$/,
						message: '登录名只能为字母'
					},
                    callback: {
                        message: '登录名已存在，请修改。',
                        callback: function(value, validator) {
                        	if($("#loginName").attr("ispass") == "false"){
                        		return false;
                        	}else{
                        		return true;
                        	}
                        }
                    }
				}
			},
			userName: {
				validators: {
					notEmpty: {
						message: '中文名不能为空'
					},
					stringLength: {
						min: 2,
						max: 10,
						message: '中文名必须在2到10位之间'
					},
					regexp: {
						regexp: /^[\u4E00-\u9FA5]+$/,
						message: '只能为中文'
					},
                    callback: {
                        message: '登录名已存在，请修改。',
                        callback: function(value, validator) {
                        	$("#modifyMsg").text("");
                        	return true;
                        }
                    }
				}
			},
			mobile: {
				validators: {
					notEmpty: {
						message: '手机号不能为空'
					},
					regexp: {
						//regexp: /^(1[3|5|8|7])[\d]{9}$/,
						regexp: /^[\d]{11}$/,
						message: '手机号格式不正确'
					},
                    callback: {
                        message: '手机号已存在，请修改。',
                        callback: function(value, validator) {
                        	if($("#mobile").attr("ispass") == "false"){
                        		return false;
                        	}else{
                        		return true;
                        	}
                        }
                    }
				}
			},
			email: {
				validators: {
					notEmpty: {
						message: '邮箱不能为空'
					},
					stringLength: {
						min: 1,
						max: 255,
						message: '邮箱必须在1到255位之间'
					},
					regexp:{
						regexp:  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+(?:com|cn|net|org|gov)+$/,
						message: '请输入正确的邮箱'
					},
                    callback: {
                        message: '邮箱已存在，请修改。',
                        callback: function(value, validator) {
                        	if($("#email").attr("isPass") === "false"){
                        		return false;
                        	}else{
                        		return true;
                        	}
                        }
                    }
				}
			}
		}   
	});
}

