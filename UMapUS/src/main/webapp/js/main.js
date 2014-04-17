var Account = function() {
	var self = this;

	self.signUpValidation = function() {
		var valid = true;
		var familyname = $('#familyname');
		var firstname = $('#firstname');
		var lastname = $('#lastname');
		var email = $('#emailsignup');
		var passwd = $('#passwdsignup');

		if (familyname.attr('onchange') == null || familyname.attr('onchange').trim().length == 0) {
			familyname.attr('onchange', 'account.signUpValidation();');
			firstname.attr('oninput', 'account.signUpValidation();');
			lastname.attr('oninput', 'account.signUpValidation();');
			email.attr('oninput', 'account.signUpValidation();');
			passwd.attr('oninput', 'account.signUpValidation();');
		}

		if (familyname.val() == "-Select-") {
			self.updateValidationStatus(familyname, errorMessages.signup.err_familyname, false);
			valid = false;
		} else
			self.updateValidationStatus(familyname, null, true);

		if (validator.isStringEmpty(firstname.val())) {
			self.updateValidationStatus(firstname, errorMessages.signup.err_firstname, false);
			valid = false;
		} else
			self.updateValidationStatus(firstname, null, true);

		if (validator.isStringEmpty(lastname.val())) {
			self.updateValidationStatus(lastname, errorMessages.signup.err_lastname, false);
			valid = false;
		} else
			self.updateValidationStatus(lastname, null, true);

		if (!validator.isValidEmail(email.val())) {
			self.updateValidationStatus(email, errorMessages.signup.err_emailadd, false);
			valid = false;
		} else
			self.updateValidationStatus(email, null, true);

		if (validator.isStringEmpty(passwd.val())) {
			self.updateValidationStatus(passwd, errorMessages.signup.err_emailpasswd, false);
			valid = false;
		} else
			self.updateValidationStatus(passwd, null, true);

		return valid;
	};

	self.updateValidationStatus = function(domelement, strmessage, vstatus) {
		if (!vstatus) {
			domelement.parent().parent().addClass('has-error');
			var test = $('#span' + domelement.attr("id"));
			if ($('#span' + domelement.attr("id")).length == 0)
				domelement.parent().append('<span id="span' + domelement.attr("id") + '" class="error_msg">' + strmessage + '</span>');
		} else {
			if (domelement.parent().parent().hasClass('has-error')) {
				domelement.parent().parent().removeClass('has-error');
				$('#span' + domelement.attr("id")).remove();
			}
		}
	};

	self.accountSignup = function(event) {
		event.preventDefault();

		if (self.signUpValidation()) {
			var familyname = $('#familyname').val();
			var firstname = $('#firstname').val();
			var lastname = $('#lastname').val();
			var email = $('#emailsignup').val();
			var passwd = $('#passwdsignup').val();

			var formdata = {
				familyname : familyname,
				firstname : firstname,
				lastname : lastname,
				email : email,
				passwd : passwd
			};

			console.log(JSON.stringify(formdata));
			// Add Test

			var url = "http://localhost:9094/UMapUsService/umapusservice/signup";
			$.ajax({
				type : "POST",
				url : url,
				contentType : "application/json",
				data : JSON.stringify(formdata),
				success : function(data, textStatus, jqXHR) {
					alert("Successful" + data);
				},
				error : function(data) {
					alert("Sign up failure" + data);
				}
				
			});
		}
	};

    self.loginValidation = function()
    {
    	var loginemail = $('#loginemail');
    	var passwd = $('#loginpasswd');
    	
    	if (loginemail.attr('oninput') == null || loginemail.attr('oninput').trim().length == 0) {
			
			loginemail.attr('oninput', 'account.loginValidation();');
			passwd.attr('oninput', 'account.loginValidation();');
			
		}

    	
    		if (!validator.isValidEmail(loginemail.val())) 
    		{
    			self.updateLoginValidationStatus(loginemail,errorMessages.login.err_emailadd);
    		}
    		else
    		     self.updateLoginValidationStatus(loginemail, null, true);
    		     
    		if (validator.isStringEmpty(passwd.val())) 
    		{
    			self.updateLoginValidationStatus(passwd,errorMessages.login.err_passwd);
    		}
    		else
    		     self.updateLoginValidationStatus(passwd, null, true);     
    };
    
    self.updateLoginValidationStatus = function(domelement, strmessage, vstatus)
    {
    	
    	if (!vstatus) {
    		domelement.parent().addClass('has-error');
    		var test = $('#span' + domelement.attr("id"));
			if ($('#span' + domelement.attr("id")).length == 0)
				 $('#loginerrpanel').append('<div><span id="span' + domelement.attr("id") + '" class="error_msg">' + strmessage + '</span></div>');
    	}
    	 else {
			if (domelement.parent().hasClass('has-error')) {
				domelement.parent().removeClass('has-error');
				$('#span' + domelement.attr("id")).parent().remove();
			}
		}
    };
    
    self.login = function()
    {
    	if(self.loginValidation())
    	{
    		
    	}
    };
     
};

// create Objects to be referred from html
var account = new Account();
