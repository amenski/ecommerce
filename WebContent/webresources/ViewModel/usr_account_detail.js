// Home View Model
jQuery(document).ready(function($){
	$.extend(
		m.prototype, {
			
			account_detail:{
				v:null, 
				detail:null, 
				data:null, 
				entity:"",
				
				PageName:'Portfolio', 
				
				setDetail:function(input){
					var self = vm.account_detail;
					
					vm.accountStatus(true);
					vm.accountDetail.id(input.accountId);
					vm.accountDetail.name(input.name);
					vm.accountDetail.email(input.email);
					
					if(input.lastName != null && input.lastName != undefined){
						self.detail = ko.mapping.fromJS({
							accountId:'', email:'', image:'', 
							accountType:'', accountStatus:'', name:'', 
							middleName:'', lastName:'', dob:'', gender:''
						});
					}else{
						self.detail = ko.mapping.fromJS({
							accountId:'', email:'', image:'', 
							accountType:'', accountStatus:'', name:'', 
							legalStatus:'', establishedDate:'', establishedBy:'', 
							description:''
						});
					}
					
					if(input.accountId != undefined && input.accountId != null)
						self.detail.accountId(input.accountId);
					if(input.email != undefined && input.email != null)
						self.detail.email(input.email);
					if(input.image != undefined && input.image != null)
						self.detail.image(input.image);
					if(input.accountType != undefined && input.accountType != null)
						self.detail.accountType(input.accountType);

					if(input.accountStatus != undefined && input.accountStatus != null)
					self.detail.accountStatus(input.accountStatus);
					if(input.name != undefined && input.name != null)
					self.detail.name(input.name);
					
					if(input.lastName != null && input.lastName != undefined){
						if(input.middleName != undefined && input.middleName != null)
						self.detail.middleName(input.middleName);
						
						self.detail.lastName(input.lastName);
						if(input.dob != undefined && input.dob != null)
						self.detail.dob(input.dob);
						if(input.gender != undefined && input.gender != null)
						self.detail.gender(input.gender);
						self.entity('Individual');
					}else{
						if(input.legalStatus != undefined && input.legalStatus != null)
						self.detail.legalStatus(input.legalStatus);
						if(input.establishedDate != undefined && input.establishedDate != null)
						self.detail.establishedDate(input.establishedDate);
						if(input.establishedBy != undefined && input.establishedBy != null)
						self.detail.establishedBy(input.establishedBy);
						if(input.description != undefined && input.description != null)
						self.detail.description(input.description);
						self.entity('Organization');
					}
					
					if(input.accountType == 'Purchase products'){
						self.data = ko.mapping.fromJS({MyOrders:[], MyWishList:[]});
					}else if(input.accountType == 'Sale Products'){
						self.data = ko.mapping.fromJS({MyProducts:[], MySales:[]});
					}else if(input.accountType == 'Both'){
						self.data = ko.mapping.fromJS({MyOrders:[], MyWishList:[], MyProducts:[], MySales:[]});
					}
				}, // End of setData
				
				setData:function(input){
					var self = vm.account_detail;
					var type = self.detail.accountType();
					
					if(type == 'Purchase products' || type == 'Both'){
						// Get the orders
						self.data.MyOrders.removeAll();
						var myOrders = input.MyOrders;
						for(var i = 0; i < myOrders.length; i++){
							var orderDtl ={ 
								orderItems:[], id:myOrders[i].id, tax:myOrders[i].tax, status:myOrders[i].status, 
								shipping:myOrders[i].shipping, orderDate:myOrders[i].orderDate, 
								total:myOrders[i].total, remark:myOrders[i].remark};
							
								self.data.MyOrders.push(ko.mapping.fromJS(orderDtl));
								
								for(var j = 0; j < myOrders[i].orderItems.length; j++)
									self.data.MyOrders()[i].orderItems.push(ko.mapping.fromJS(myOrders[i].orderItems[j]));
							
						}
						
						// WishList population
						self.data.MyWishList.removeAll();
						var myWishlist = input.MyWishList;
						for(var i = 0; i < myWishlist.length; i++){
							self.data.MyWishList.push(ko.mapping.fromJS(myWishlist[i].product));
						}
					}
					
					if(type == 'Sale Products' || type == 'Both'){
						// Get the products
						self.data.MyProducts.removeAll();
						var myProducts = input.MyProducts;
						
						for(var i = 0; i < myProducts.length; i++){
							var prDtl = {
								id:myProducts[i].id, name:myProducts[i].name, 
								images:myProducts[i].images, unitPrice:myProducts[i].unitPrice, 
								atp:myProducts[i].atp, description:myProducts[i].description};
							self.data.MyProducts.push(ko.mapping.fromJS(prDtl));
							
						}
						
					}
				}, 
				
				loadData:function(){
					var self = vm.account_detail;
					var rqstData = {
							url:'GetAccountData', 
							callback:{
							func:function(ajaxResponce){
								self.setData(ajaxResponce);
									console.log(ajaxResponce);
								self.index();
							}, 
							para:1
						}
					};
					var rqstObj = new rqst(rqstData);
					rqstObj.send();
				}, 
				
				loadView:function(){
					var self = vm.account_detail;

					var rqstData = {
						url:'webresources/View/usr_account_detail.html?a='+new Date().getTime(),
						dataType:'html',
						callback:{
							func:function(a){
								if(a != null){
									self.v(a);
									self.loadData();
								}
							},
							para:1
						}
					};
					var rqstObj = new rqst(rqstData);
					rqstObj.send();
				}, // End of loadView
				
				navigate:function(input){
					var self = vm.account_detail;
					switch(input){
						case 'MyProducts':
							self.myProduct.index();
							break;
						
					}
					//Change PageName's based on what is clicked on the side menu
					self.PageName(input);
				}, 
				
				index:function(){
					var self = vm.account_detail;
					if(self.v() == null){
						self.loadView();
					}else{
						
						$("#bdy").empty().append(self.v());
						ko.applyBindings(vm, $('#account-detail-wrapper')[0]);
					}
				}, 
				
				myWishList:{
					
					addToCart:function(data){
						var self = vm.account_detail.myWishList;
						vm.category.navigate('Products', data);
						vm.category.productOnCart(true);
						vm.category.order.addToCart();
					}, 
					
					checkWishList:function(id){
						var rtrn = false;
						var myList = vm.account_detail.data.MyWishList;
						if(myList != null && myList != undefined){
						for(var a = 0; a < myList().length; a++){
							if(id == myList()[a].id())
								rtrn = true;
						}
						}
						return rtrn;
					}, 
					manageWishList:function(action, data){

						var self = vm.account_detail.myWishList;
						var dataToSend = {id:String(data.id())};
						//alert(dataToSend.category);
						var rqstData = {
							url:action, 
							data:{data:JSON.stringify(dataToSend)}, 
							callback:{
								func:function(rtrn){
									vm.account_detail.setData(rtrn);
									vm.account_detail.index();
									vm.account_detail.navigate('MyWishList');
								}, 
								para:1
							}
						};
						var rqstObj = new rqst(rqstData);
						rqstObj.send();
						
					}
				}, 
				
				myProfile:{
					logoImgMode:'View',
					logoImgNew:'',
					chngLogoImgMode:function(input){
					    var self = vm.account_detail.myProfile;
					    self.logoMode(input);
					    if(input == 'Edit'){
					        $('#logo_browse').click(function(){
					        	$('#logo_file').click();
					        });
					        $('#logo_file').change(function(event) {
                           $('#logo_browse').empty().append("Uploading...");
                           if(self.logoImgNew() == "")
                           $('#logo_add_image').submit();
                           else
                           self.cancelImgChange(function(){
                                                self.logoImgNew('');
                                                $('#logo_add_image').submit();
                                                }, self.logoImgNew());
                           
                           });
					    }
					},
					stopUpldLogo:function(input){
					    var self = vm.account_detail.myProfile;
					    $('#logo_error').empty();
					    $('#logo_browse').empty().append("Upload Logo");
					    if(input.Status == "Done"){
					        self.logoImgNew(input["FileName"])
					       /* $('#logo-thumb')
					        .empty()
					        .append('<img src="webresources/file_temp/'+input["FileName"]+'" class="img-responsive"  />');*/
					    }else{
					        vm.account_detail.myProfile.logoImgNew('');
					        $('#logo_error').append(input["FileName"]);
					    }
					},

					logoMode:'View',
					frmLogoData:{Logo:''},
					setEditLogoData:function(input, index){
					    var self = vm.account_detail.myProfile;
					    if(input == null || input == undefined)
					        self.frmLogoData.Logo('');
					    else
					        self.frmLogoData.Logo(input.image());
					},
					setLogoDataToSend:function(type, input){
					    var self = vm.account_detail.myProfile;
					    
					    var logo = self.data.UserProfile.Logo;
					    var dataToSend = {Logo:logo()}
					    
					    return dataToSend;
					},
					chngLogoMode:function(action){
					    var self = vm.account_detail.myProfile;
					    var input = vm.account_detail.detail;
					    
					    if(action == 'Edit')
					        self.setEditLogoData(input);
					    
					    self.logoMode(action);
					},
					manageLogo:function(action){
					    var self = vm.account_detail.myProfile;
					    
					    var valid = true;
					    var inputData = self.frmLogoData;
					    
					    if(valid === true){
					        var dataToSend = null;
					        var logo = self.data.UserProfile.Logo;
					        
					        var logo = self.data.UserProfile.Logo;
					        logo(inputData.Logo());
					        dataToSend = self.setLogoDataToSend();
					        
					        var waiting = vm.loading.index_1('manage_profile_logo', 'sm', 'line', 'Saving Changes...');
					        vm.postData("PrfManageLogo", dataToSend, function(rtrn){
					                    if(rtrn["Status"] == "Done"){
					                    vm.loading.destroy_1(waiting, '');
					                    self.data.UserProfile = ko.mapping.fromJS(rtrn['Data']);
					                    self.cancelLogo();
					                    
					                    }else
					                    $('#prf_logo_error').append('<span class="help-block txt12 clrred">'+rtrn["Status"]+'</span>');
										});	
					    }
					},
					cancelLogo:function(){
					    var self = vm.account_detail.myProfile;
					    self.setEditLogoData();
					    self.logoMode('View');	
					}
				}, 
				
				myPortfolio:{
					PageName:'List',
					dtlData:'',
					
					sendData:function(data){
						var self = vm.account_detail.myPortfolio;
						var prof = vm.account_detail.detail;
						var dataToSend ={};
						if(vm.account_detail.entity() == "Individual"){
							dataToSend = {
								id:prof.accountId(), email:prof.email(), 
								image:vm.account_detail.myProfile.logoImgNew(), name:prof.name(), 
								lastName:prof.lastName(), dob:prof.dob(), gender:prof.gender()
							};
						}else{
							dataToSend = {
								id:prof.accountId(), email:prof.email(), 
								image:vm.account_detail.myProfile.logoImgNew(), 
								name:prof.name(), legalStatus:prof.legalStatus(), 
								establishedDate:prof.establishedDate(), establishedBy:prof.establishedBy(), 
								description:prof.description()
							};
						}
						
						var rqstData = {
							url:'UpdatePortfolio', 
							data:{data:JSON.stringify(dataToSend), entity:vm.account_detail.entity()}, 
							callback:{
								func:function(rtrn){
									window.location.assign("http://localhost:8080/spring-project-1/");
								}, 
								para:1
							}
						};
						var rqstObj = new rqst(rqstData);
						rqstObj.send();
						
						
					},
					
					navigate:function(action){
						var self = vm.account_detail.myPortfolio;
						var update = true;
						self.PageName(action);
						if(self.PageName() == 'Edit'){
							var selector = $( "#inputestablishedDate" );
							if(vm.account_detail.entity() == "Individual"){
								selector = $( "#inputDob" );
							}
							selector.datepicker({
								  dateFormat: 'yy-mm-dd', 
							      changeMonth: true,
							      changeYear: true
							    });
						}

					},
					index:function(){
						var self = vm.account_detail.myPortfolio;
						
						self.PageName('List');
					}
				}, 
				
				myOrder:{
					PageName:'List', 
					availableCategories:[], 
					dtlData:{id:'', shipping:'', tax:'', orderDate:'', total:'', status:'', remark:'', orderItems:[]}, 
					
					setDtlData:function(input){
						var self = vm.account_detail.myOrder;
						
						self.dtlData.id(input.id());
						self.dtlData.shipping(input.shipping());
						self.dtlData.tax(input.tax());
						self.dtlData.orderDate(input.orderDate())
						self.dtlData.total(input.total());
						self.dtlData.status(input.status());
						self.dtlData.remark(input.remark());
						
						self.dtlData.orderItems.removeAll();
						for(var a = 0; a < input.orderItems().length; a++){
							var orderItemDtl = {
									product:input.orderItems()[a].product.name(), 
									unitPrice:input.orderItems()[a].product.unitPrice(), 
									quantity:input.orderItems()[a].quantity(), 
									total:input.orderItems()[a].total()
								};
							self.dtlData.orderItems.push(ko.mapping.fromJS(orderItemDtl));
						}
						
					}, 
					
					navigate:function(input, data){
						var self = vm.account_detail.myOrder;
						self.PageName(input);
						self.setDtlData(data);
					}, 
					
					index:function(){
						
						var self = vm.account_detail.myOrder;
						self.PageName('List');
						//self.setDtlData();
					}, 
					
					manageOrder:function(action){

						var self = vm.account_detail.myOrder;
						var dataToSend = {id:String(self.dtlData.id())};
						//alert(dataToSend.category);
						var rqstData = {
							url:action, 
							data:{data:JSON.stringify(dataToSend)}, 
							callback:{
								func:function(rtrn){
										self.index();
										vm.account_detail.setData(rtrn);
								}, 
								para:1
							}
						};
						var rqstObj = new rqst(rqstData);
						rqstObj.send();
						
					}
				}, 
				
				myProduct:{
					PageName:'List', 
					availableCategories:[], 
					dtlData:{id:0, name:'', images:'', unitPrice:0.00, atp:'', description:'', category:0}, 
					
					
					setDtlData:function(input){
						var self = vm.account_detail.myProduct;
						if(input == null || input == undefined){
							self.dtlData.id(0);
							self.dtlData.name('');
							self.dtlData.images('');
							self.dtlData.unitPrice(0.00);
							self.dtlData.atp('');
							self.dtlData.description('');
							self.dtlData.category(0);
						}else{
							self.dtlData.id(parseInt(input.id()));
							self.dtlData.name(input.name());
							self.dtlData.images(input.images());
							self.dtlData.unitPrice(parseFloat(input.unitPrice()));
							self.dtlData.atp(input.atp());
							self.dtlData.description(input.description());
						}
						
						self.availableCategories.removeAll();
						for(var i = 0; i < vm.category.data().length; i++){
							var ctgrDtl = vm.category.data()[i];
							self.availableCategories.push(ko.mapping.fromJS({
								id:ctgrDtl.id(), 
								name:ctgrDtl.name()
							}));
							
							
							if(input != null && input != undefined){
								for(var j = 0; j < ctgrDtl.products().length; j++){
									var prDtl = ctgrDtl.products()[j];
									if(prDtl.id() == self.dtlData.id())
										self.dtlData.category(parseInt(ctgrDtl.id()));
								}
							}
								
						}
						
					}, 
					
					proImgNew:'',
					stopUpldProduct:function(input){
					    var self = vm.account_detail.myProduct;
					    $('#proimg_error').empty();
					    $('#proimg_browse').empty().append("Upload Product");
					    if(input.Status == "Done"){
					        self.proImgNew(input["FileName"])
					      $('#pro-thumb')
					        .empty()
					        .append('<img src="webresources/file_temp/'+input["FileName"]+'" class="img-responsive"  />');
					    }else{
					        vm.account_detail.myProfile.proImgNew('');
					        $('#proimg_error').append(input["FileName"]);
					    }
					},
					navigate:function(input, data){
						var self = vm.account_detail.myProduct;
						self.PageName(input);
						if(input == 'Add')
							self.setDtlData();
						else
							self.setDtlData(data);
						
						if(input != "Detail"){
					        $('#proimg_browse').click(function(){
					        	$('#proimg_file').click();
					        });
					        $('#proimg_file').change(function(event) {
			               $('#proimg_browse').empty().append("Uploading...");
			               
			               $('#proimg_add_image').submit();
			               
			               
			               });
						}
					}, 
					
					index:function(){
						
						var self = vm.account_detail.myProduct;
						self.PageName('List');
						//self.setDtlData();
					}, 
					
					manageProduct:function(action){
						var self = vm.account_detail.myProduct;
						
						var valid = true;
						var inputData = self.dtlData;
						if(action != 'Delete'){
							if(self.proImgNew() != "")
								self.dtlData.images(self.proImgNew());
							else
								self.dtlData.images("3.png");
									
							if(inputData.name() === ''){
								valid = false;
								$('#prd_frm_name').addClass("has-error");
								$('#prd_frm_name').append('<span class="help-block txt12">Write the name of the request.</span>');
							}else{
								$('#prd_frm_name').removeClass('has-error').addClass("has-success");
								$('#prd_frm_name span').remove();
							}// End of else
							
							if(inputData.unitPrice() === ''){
								valid = false;
								$('#prd_frm_unit').addClass("has-error");
								$('#prd_frm_unit').append('<span class="help-block txt12">Write unit you need.</span>');
							}else{
								$('#prd_frm_unit').removeClass('has-error').addClass("has-success");
								$('#prd_frm_unit span').remove();
							}// End of else
							
							if(inputData.atp() === ''){
								valid = false;
								$('#prd_frm_atp').addClass("has-error");
								$('#prd_frm_atp').append('<span class="help-block txt12">Write the atp.</span>');
							}else{
								$('#prd_frm_atp').removeClass('has-error').addClass("has-success");
								$('#prd_frm_atp span').remove();
							}// End of else
							
							if(inputData.description() === ''){
								valid = false;
								$('#prd_frm_description').addClass("has-error");
								$('#prd_frm_description').append('<span class="help-block txt12">Select the date.</span>');
							}else{
								$('#prd_frm_description').removeClass('has-error').addClass("has-success");
								$('#prd_frm_description span').remove();
							}// End of else
							
						} 
						
						if(valid === true){
							
							var dataToSend = {
								id:String(self.dtlData.id()), 
								name:String(self.dtlData.name()), 
								images:String(self.dtlData.images()), 
								unitPrice:String(self.dtlData.unitPrice()), 
								atp:String(self.dtlData.atp()), 
								description:self.dtlData.description(), 
								category:String(self.dtlData.category()), 
							};
							//alert(dataToSend.category);
							var rqstData = {
								url:action, 
								type:'POST', 
								data:{data:JSON.stringify(dataToSend)}, 
								callback:{
									func:function(rtrn){

											self.index();
											vm.category.setData(rtrn["Categories"]);
											vm.account_detail.setData(rtrn);
										
									}, 
									para:1
								}
							};
							var rqstObj = new rqst(rqstData);
							rqstObj.send();
						}
					}
				},//end of myProduct
				
				privacy:{
					
					dtlData:{currentPassword:'', newPassword:'', confirmPassword:''}, 
					
					managePrivacy:function(action){
						var self = vm.account_detail.privacy;

						var inputData = self.dtlData;
						var dataToSend = { 
								newPassword:inputData.newPassword(), 
								currentPassword:inputData.currentPassword()
							};
						
						var valid = true;
						if(dataToSend.currentPassword == ''){
							valid = false;
							$('#prv_frm_current').addClass("has-error");
							$('#prv_frm_current').append('<span class="help-block txt12">Write the current password.</span>');
						}else{
							$('#prv_frm_current').removeClass('has-error').addClass("has-success");
							$('#prv_frm_current span').remove();
						}// End of else
						
						if(15 < dataToSend.newPassword.length || dataToSend.newPassword.length < 8){
							valid = false;
							$('#prv_frm_new').addClass("has-error");
							$('#prv_frm_new').append('<span class="help-block txt12">Write the new password.</span>');
						}else{
							$('#prv_frm_new').removeClass('has-error').addClass("has-success");
							$('#prv_frm_new span').remove();
						}// End of else
						
						if(dataToSend.newPassword != inputData.confirmPassword() || inputData.confirmPassword().length==0){
							valid = false;
							$('#prv_frm_confirm').addClass("has-error");
							$('#prv_frm_confirm').append('<span class="help-block txt12">Confirm the password.</span>');
						}else{
							$('#prv_frm_confirm').removeClass('has-error').addClass("has-success");
							$('#prv_frm_confirm span').remove();
						}// End of else
							
							
						if(valid === true){
							var rqstData = {
								url:action, 
								type:'POST', 
								data:{data:JSON.stringify(dataToSend)}, 
								callback:{
									func:function(ajaxResponce){
										$('#srvError').empty()
												.append('<span class="help-block txt12 clrred">'+ajaxResponce["Data"]+'</span>');
										
									}, 
									para:1
								}
							};
							var rqstObj = new rqst(rqstData);
							rqstObj.send();
						}
					}
				} //end of privacy
			
			} 
		}
	)
});
