// Home View Model
jQuery(document).ready(function($){
	$.extend(
		m.prototype, {
			PageName:'Home', 
			accountStatus:false, 
			accountDetail:{id:'', name:'', email:''}, 
			
			signInFrom:'', 
			
			// Navigating the page
			navigate:function(input, data){
				switch(input){
					case 'MyAccount':
						vm.account_detail.index();
					break;
					case 'Home':
						vm.home.index();
						vm.PageName(input);
					break;
					case 'Products':
						vm.category.index();
						vm.category.navigate(input, data);
						//vm.PageName(input);
					break;
					case 'Category':
						vm.category.index();
						vm.category.navigate(input, data);
						//vm.PageName(input);
					break;
					case 'SignIn':
						if(data!=null && data != undefined)
							vm.signInForm(data);
						vm.account.navigate('SignIn');
						vm.account.index();
						break;
					case 'SignUp':
						if(data!=null && data != undefined)
							vm.signInForm(data);
						vm.account.navigate('SignUp');
						vm.account.index();
						break;
				}
				
			}, 
			
			// Account Sign In and Sign Up
			account:{
				v:null, 
				signInData:{Email:'', Password:''}, 
				accountTypes:['Purchase products', 'Sale Products', 'Both'], 
				signUpData:{Name:'', Email:'', Password:'', CPassword:'', AccountType:'', AccountEntity:'Individual'}, 
				
				PageName:'', 

				signIn:function(){
					var self = vm.account;
					var valid = true;
					var dataToSend = {
							email:self.signInData.Email(), 
							password:self.signInData.Password(), 
					};
					$('#inputEmailFrm span, #inputPasswordFrm span').remove();
					var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
					if ( !filter.test(dataToSend.email) ){	
						valid = false;
						$('#inputEmailFrm').addClass("has-error");
						$('#inputEmailFrm').append('<span class="help-block txt12">Please enter a valid email address.</span>');
					}else{
						$('#inputEmailFrm').removeClass('has-error').addClass("has-success");
						$('#inputEmailFrm span').remove();
					}
					
					if(15 < dataToSend.password.length || dataToSend.password.length < 8){
						valid = false;
						$('#inputPasswordFrm').addClass("has-error");
						$('#inputPasswordFrm').append('<span class="help-block txt12">Your password must be between 8 upto 15 characters.</span>');
					}else{
						$('#inputPasswordFrm').removeClass('has-error').addClass("has-success");
						$('#inputPasswordFrm span').remove();
					}
					
					if(valid){
						var rqstData = {
							url:'LogIn', 
							type:'POST', 
							data:{data:JSON.stringify(dataToSend)}, 
							callback:{
								func:function(rtrn){
									if(rtrn["Status"] == 'Done')
										window.location.assign("http://localhost:8080/spring-project-1/");
									else
										$('#srvError')
											.empty()
											.append('<span class="help-block txt12 clrred">'+rtrn["Data"]+'</span>');
								}, 
								para:1
							}
						};
						var rqstObj = new rqst(rqstData);
						rqstObj.send();
					}
				}, 
				
				signUp:function(){
					var self = vm.account;
					var valid = true;
					var dataToSend = {
							name:self.signUpData.Name(), 
							email:self.signUpData.Email(), 
							password:self.signUpData.Password(), 
							accountType:self.signUpData.AccountType(), 
							accountStatus:'Active'
					};
					
					$('#inputEmailFrm span, #inputPasswordFrm span, #inputCPasswordFrm span, #inputAccountTypeFrm span, #inputNameFrm span').remove();
					var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
					if ( !filter.test(dataToSend.email) ){	
						valid = false;
						$('#inputEmailFrm').addClass("has-error");
						$('#inputEmailFrm').append('<span class="help-block txt12">Please enter a valid email address.</span>');
					}else{
						$('#inputEmailFrm').removeClass('has-error').addClass("has-success");
					}
					
					if(15 < dataToSend.password.length || dataToSend.password.length < 8){
						valid = false;
						$('#inputPasswordFrm').addClass("has-error");
						$('#inputPasswordFrm').append('<span class="help-block txt12">Your password must be between 8 upto 15 characters.</span>');
					}else{
						$('#inputPasswordFrm').removeClass('has-error').addClass("has-success");
					}
					
					if(dataToSend.password != self.signUpData.CPassword() || self.signUpData.CPassword().length==0){
						valid = false;
						$('#inputCPasswordFrm').addClass("has-error");
						$('#inputCPasswordFrm').append('<span class="help-block txt12">Confirm your password by entering the same thing as you did for the passowrd field.</span>');
					}else{
						$('#inputCPasswordFrm').removeClass('has-error').addClass("has-success");
					}
					
					if(dataToSend.accountType == undefined || dataToSend.accountType == null || dataToSend.accountType == ''){
						valid = false;
						$('#inputAccountTypeFrm').addClass("has-error");
						$('#inputAccountTypeFrm').append('<span class="help-block txt12">Select your account type.</span>');
					}else{
						$('#inputAccountTypeFrm').removeClass('has-error').addClass("has-success");
					}
					
					if(dataToSend.name == undefined || dataToSend.name == null || dataToSend.name == ''){
						valid = false;
						$('#inputNameFrm').addClass("has-error");
						if(self.signUpData.AccountEntity() == "Individual")
							$('#inputNameFrm').append('<span class="help-block txt12">Please enter your name or name of the organization</span>');
						else
							$('#inputNameFrm').append('<span class="help-block txt12">Please enter the name of the organization</span>');
							
					}else{
						$('#inputNameFrm').removeClass('has-error').addClass("has-success");
					}
					
					
					if(valid){
						var rqstData = {
							url:'Register', type:'POST', data:{entity:self.signUpData.AccountEntity(), data:JSON.stringify(dataToSend)}, 
							callback:{
								func:function(rtrn){
									if(rtrn["Status"] == 'Done'){
										self.navigate('SignIn');
										$('#srvError')
										.empty()
										.append('<span class="help-block txt18 clr11">You have successfully created your own account.</span>');
									}else
										$('#srvError')
											.empty()
											.append('<span class="help-block txt12 clrred">'+
													rtrn["Data"]+'</span>');
								}, 
								para:1
							}
						};
						var rqstObj = new rqst(rqstData);
						rqstObj.send();
					}
				}, 
				
				loadView:function(){
					var self = vm.account;

					var rqstData = {
						url:'webresources/View/usr_account.html?a='+new Date().getTime(),
						dataType:'html',
						callback:{
							func:function(a){
								if(a != null){
									self.v(a);
									self.index();
								}
							},
							para:1
						}
					};
					var rqstObj = new rqst(rqstData);
					rqstObj.send();
				}, // End of loadView
				
				navigate:function(input){
					var self = vm.account;
					self.PageName(input);
				}, 
				
				index:function(){
					var self = vm.account;
					if(self.v() == null){
						self.loadView();
					}else{
						$("#bdy").empty().append(self.v());
						ko.applyBindings(vm, $('#account-wrapper')[0]);
					}
				}
			},  
			
			// Home Page View Model
			home:{
				v:null, // Holds the html view element
				data:[], // Holds the date 

				// Load View
				loadView:function(){
					var self = vm.home;

					var rqstData = {
						url:'webresources/View/usr_home.html?a='+new Date().getTime(),
						dataType:'html',
						callback:{
							func:function(a){
								if(a != null){
									self.v(a);
									if(bmData.Categories != null){
										vm.category.setData(bmData.Categories);
										if(bmData.LogedIn){
											vm.account_detail.setDetail(bmData.AccountDetail);
										}
										self.index();
									}
								}
							},
							para:1
						}
					};
					var rqstObj = new rqst(rqstData);
					rqstObj.send();
				}, // End of ladView

				// Index
				index:function(){
					var self = vm.home;
					
					if(self.v() == null){
						self.loadView();
					}else{
						$("#bdy").empty().append(self.v());
						ko.applyBindings(vm, $('#home-wrapper')[0]);
					}
				} // End of Index

			}//End of home
		}
	)
});
