var FormValid = function(frm) {
    this.frm = frm;
    this.errMsg = new Array();

   
    this.required = function(inputObj, msg) {
        if (typeof(inputObj) == "undefined" || inputObj.value.trim() == "") {
            this.addErrorMsg(msg);
        }
    };

    this.isEmail = function(inputObj, msg) {
        inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else if (!inputObj.value.isEmail()) {
            this.addErrorMsg(msg);
        }
    }
    
    this.eqaul = function(fstObj, sndObj, msg) {
        if (fstObj != null && sndObj != null) {
            if (fstObj.value != sndObj.value) {
                this.addErrorMsg(msg);
            }
        }
    }

    this.gt = function(fstObj, sndObj, msg) {
        if (fstObj != null && sndObj != null) {
            if (fstObj.value <= sndObj.value) {
                this.addErrorMsg(msg);
            }
        }
    }

    this.isNumber = function(inputObj, msg) {
        inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
            if (!inputObj.value.isNumber()) {
                this.addErrorMsg(msg);
            }
        }
    }

    this.isInt = function(inputObj, msg) {
        inputObj.value = inputObj.value.trim();
        if (inputObj.value == '') {
            return;
        } else {
            if (!inputObj.value.isInt()) {
				this.addErrorMsg(msg);
			}
		}
    }
	
    this.isTime = function(inputObj, msg) {
        inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
            if (!inputObj.value.isTime()) {
				this.addErrorMsg(msg);
			}
        }
    }
	
    this.isDate = function(inputObj, msg) {
        inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
            if (!inputObj.value.isDate()) {
				this.addErrorMsg(msg);
			}
        }
    }

    this.isEnglish = function(inputObj, msg) {
    	inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
        	if (!inputObj.value.isEnglish()) {
				this.addErrorMsg(msg);
			}
            

        }
             

    }
    this.isURL = function(inputObj, msg) {
    	inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
        	if (!inputObj.value.isURL()) {
				this.addErrorMsg(msg);
			}
            

        }
             

    }
    
    this.isPhone = function(inputObj, msg) {
    	inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
        	if (!inputObj.value.isPhone()) {
				this.addErrorMsg(msg);
			}
            

        }
             

    }
    this.isMobile = function(inputObj, msg) {
    	inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
        	if (!inputObj.value.isMobile()) {
				this.addErrorMsg(msg);
			}
            

        }
             

    }
    this.isPhoneAndMobile = function(inputObj, msg) {
    	inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
        	if (!inputObj.value.isPhone()&&!inputObj.value.isMobile()) {
				this.addErrorMsg(msg);
			}
            

        }
             

    }
    
    this.isPost = function(inputObj, msg) {
    	inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
        	if (!inputObj.value.isPost()) {
				this.addErrorMsg(msg);
			}
            

        }
             

    }
    
    this.passed = function() {
        if (this.errMsg.length > 0) {
            var msg = "";
            for (i = 0; i < this.errMsg.length; i++)
            {
              msg += "- " + this.errMsg[i] + "\n";
            }

            this.showError(msg);
            return false;
        } else {
          return true;
        }
    }
    

    this.addErrorMsg = function(str) {
    	if(this.errMsg.length==0){   
    		this.errMsg.push(str);
    	}
        
    }
	
	this.showError = function(msg) {
		alert(msg);
	}
}

function validator(frm) {
	
	var formElements = frm.elements;
	var fv = new FormValid(frm);
	for (var i=0; i<formElements.length;i++) {
		if(formElements[i].getAttribute('disabled') != ''){
			var validType = formElements[i].getAttribute('valid');
			var errorMsg = formElements[i].getAttribute('errmsg');
			if (validType==null) continue;
			var vts = validType.split('|');
			var ems = errorMsg.split('|');
			for (var j=0; j<vts.length; j++) {
				var curValidType = vts[j];
				var curErrorMsg = ems[j];
				
				switch (curValidType) {
				case 'isEmail':
					fv.isEmail(formElements[i],curErrorMsg);
					break;
				case 'eqaul':
					fv.eqaul(formElements[i],formElements[formElements[i].getAttribute('eqaulName')],curErrorMsg);
					break;
				case 'gt':
					fv.gt(formElements[i],formElements[formElements[i].getAttribute('eqqulName')],curErrorMsg);
					break;
				case 'isNumber':
					fv.isNumber(formElements[i],curErrorMsg);
					break;
				case 'isInt':
					fv.isInt(formElements[i],curErrorMsg);
					break;
				case 'isTime':
					fv.isTime(formElements[i],curErrorMsg);
					break;
				case 'isDate':
					fv.isDate(formElements[i],curErrorMsg);
					break;
				case 'isEnglish':
					fv.isEnglish(formElements[i],curErrorMsg);
					break;
				case 'isURL':
					fv.isURL(formElements[i],curErrorMsg);
					break;
				case 'isPhone':
					fv.isPhone(formElements[i],curErrorMsg);
					break;
				case 'isMobile':
					fv.isMobile(formElements[i],curErrorMsg);
					break;
				case 'isPhoneAndMobile':
					fv.isPhoneAndMobile(formElements[i],curErrorMsg);
					break;
				case 'isPost':
					fv.isPost(formElements[i],curErrorMsg);
					break;
				default :
					fv.required(formElements[i],curErrorMsg);
					break;
				}
			}
		}
	}
	return fv.passed();
}
