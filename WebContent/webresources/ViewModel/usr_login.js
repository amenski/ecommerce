// Home View Model
jQuery(document).ready(function($){
	$.extend(
		m.prototype, {
			home:{
				N:null,
				v:null,
				data:[],
				formData:{
					createAccount:{Name:'', Email:'', Password:'', ConfirmPassword:''},
					accessAccount:{Email:'', Password:''}
				},

				setData:function(input){}, // End of setData

				loadView:function(){
					var self = vm.home;

					var rqstData = {
						url:'View/usr_home.html?a='+new Date().getTime(),
						dataType:'html',
						callback:{
							func:function(a){
								self.v(a);
								self.index();
							},
							para:1
						}
					};
					var rqstObj = new rqst(rqstData);
					rqstObj.send();
				}, // End of ladView

				index:function(){
					var self = vm.home;

					if(self.v() == null){
						self.loadView();
					}else{
						$("body").empty().append(self.v());
						//self.N(kontext('#usr_home'));
						ko.applyBindings(vm, $('#usr_home')[0]);
					}
				}, // End of Index

				navigate:function(input){
					var self = vm.home;
				},

				manageAccount:function(input){

					var self = vm.home;

					switch(input){
						case 'Create':
							var valid = true;
							var inputData = self.formData.createAccount;

							if(inputData.ConfirmPassword() != inputData.Password()){
								valid = false;
								$('#frm_sup_cpass').addClass("has-error");
								$('#frm_sup_cpass').append('<span class="help-block txt12">Please confirm your password.</span>');
							}else{
								$('#frm_sup_cpass').removeClass('has-error').addClass("has-success");
								$('#frm_sup_cpass span').remove();
							}

							var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
							if (!filter.test(inputData.Email())){
								valid = false;
								$('#frm_sup_email').addClass("has-error");
								$('#frm_sup_email').append('<span class="help-block txt12">Please enter a valid email address.</span>');
							}else{
								$('#frm_sup_email').removeClass('has-error').addClass("has-success");
								$('#frm_sup_email span').remove();
							}

							if(15 < inputData.Password().length || inputData.Password().length < 8){
								valid = false;
								$('#frm_sup_pass').addClass("has-error");
								$('#frm_sup_pass').append('<span class="help-block txt12">Your password must be between 8 upto 15 characters.</span>');
							}else{
								$('#frm_sup_pass').removeClass('has-error').addClass("has-success");
								$('#frm_sup_pass span').remove();
							}

							if(inputData.ConfirmPassword() != inputData.Password()){
								valid = false;
								$('#frm_sup_cpass').addClass("has-error");
								$('#frm_sup_cpass').append('<span class="help-block txt12">Please confirm your password.</span>');
							}else{
								$('#frm_sup_cpass').removeClass('has-error').addClass("has-success");
								$('#frm_sup_cpass span').remove();
							}

							if(valid === true){
								var dataToSend = {
									Name:inputData.Name(),
									Email:inputData.Email(),
									Password:inputData.Password()
								}
								var waiting = vm.loading.index_1('create_account_btn', 'sm', 'line', 'Creating...');
								vm.postData("SignUp", dataToSend, function(rtrn){
									if(rtrn["Status"] == "Done"){
										vm.loading.destroy_1(waiting, 'Create Your Account');
										location.reload();
									}else
										$('#frm_sup_error').append('<span class="help-block txt12 clrred">'+rtrn["Status"]+'</span>');
								});
							}
						break;
						case 'Cancel':

						break;
						case 'Access':
							var valid = true;
							var inputData = self.formData.accessAccount;

							var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
							if (!filter.test(inputData.Email())){
								valid = false;
								$('#frm_sin_email').addClass("has-error");
								$('#frm_sin_email').append('<span class="help-block txt12">Please enter a valid email address.</span>');
							}else{
								$('#frm_sin_email').removeClass('has-error').addClass("has-success");
								$('#frm_sin_email span').remove();
							}

							if(15 < inputData.Password().length || inputData.Password().length < 8){
								valid = false;
								$('#frm_sin_pass').addClass("has-error");
								$('#frm_sin_pass').append('<span class="help-block txt12">Your password must be between 8 upto 15 characters.</span>');
							}else{
								$('#frm_sin_pass').removeClass('has-error').addClass("has-success");
								$('#frm_sin_pass span').remove();
							}

							if(valid === true){
								var dataToSend = {
									Email:inputData.Email(),
									Password:inputData.Password()
								}
								var waiting = vm.loading.index_1('sign-in-btn', 'sm', 'line', 'Accessing Account...');
								vm.postData("SignIn", dataToSend, function(rtrn){
									if(rtrn["Status"] == "Done"){
										vm.loading.destroy_1(waiting, 'Sign In to Yukel');
										location.reload();
									}else
										$('#frm_sin_error').append('<span class="help-block txt12 clrred">'+rtrn["Status"]+'</span>');
								});
							}
						break;
					}
				}
			}
		}
	)
});
