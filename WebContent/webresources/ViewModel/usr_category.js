// Home View Model
jQuery(document).ready(function($){
	$.extend(
		m.prototype, {
			
			category:{
				v:null, 
				data:[], 
				PageName:'', 
				detail:{category:null, product:null}, 
				onWishList:false, 
				
				productOnCart:false, 
				
				navigate:function(input, data){
					var self = vm.category;
					self.order.orderStatus(false);
					switch(input){
						case 'Category':
							self.detail.category = data;
						break;
						case 'Products':
							self.productOnCart(false);
							self.onWishList(vm.account_detail.myWishList.checkWishList(data.id()));
							self.detail.product = data;
						break;	
						case 'Order':
							break;
					}
					self.PageName(input);	
				}, 
				
				product:{
					review:"", 
					sendReview:function(){
						var self = vm.category.product;
						var today = vm.getDateDtl.now().shrtDateTxt2;
						var dataToSend = {productId:String(vm.category.detail.product.id()), comment:String(self.review()), date:String(today)};
						var rqstData = {
								url:'CreateNewReview', 
								data:{data:JSON.stringify(dataToSend)}, 
								callback:{
									func:function(rtrn){
										self.review('');
									}, 
									para:1
								}
							};
							var rqstObj = new rqst(rqstData);
							rqstObj.send();
						
					}
				}, 
				
				order:{
					cart:[], 
					orderStatus:false, 
					orderDetail:{customerId:'', shipping:'', tax:'', date:'', total:''}, 
					
					chkProductQuantityOnCart:function(){
						var quantity = 1;
						var self = vm.category.order;
						var product = vm.category.detail.product;
						
						if(vm.category.order.cart().length != 0){
							var cartlist = self.cart;
							for(var a = 0; a < cartlist().length; a++){
								if(product.id() == cartlist()[a].productId()){
									cartlist()[a].quantity(cartlist()[a].quantity()+1);
									quantity = cartlist()[a].quantity();
									cartlist()[a].total(parseFloat(cartlist()[a].unitPrice())*quantity);
								}
							}
						}
						return quantity;
					}, 
					
					addToCart:function(input){
						var self = vm.category.order;
						var product = vm.category.detail.product;
						
						if(product.atp() != 0){
							var quantity = parseInt(self.chkProductQuantityOnCart());
							var total = parseFloat(product.unitPrice()) * quantity;
							
							product.atp(product.atp()-1);
							if(quantity == 1){
								self.cart.push(ko.mapping.fromJS({
									productId:product.id(), 
									productName:product.name(), 
									unitPrice:product.unitPrice(), 
									quantity:quantity, 
									total:total
								}));
							}
							
							if(self.cart().length == 1){
								self.orderDetail.customerId(vm.accountDetail.id());
								self.orderDetail.date(vm.getDateDtl.now().shrtDateTxt2);
								self.orderDetail.shipping('FREE');
								self.orderDetail.tax('0');
							}
							
							var orderTotal = 0;
							for(var a = 0; a < self.cart().length; a++){
								orderTotal += parseFloat(self.cart()[a].total());
							}
							self.orderDetail.total(orderTotal);
						}
					}, 
					
					sendOrder:function(){
						var self = vm.category.order;
						
						var dataToSend = {
							customerId:String(self.orderDetail.customerId()), 
							date:String(self.orderDetail.date()), 
							total:String(self.orderDetail.total()), 
							shipping:String(self.orderDetail.shipping()), 
							tax:String(self.orderDetail.tax()), 
							orderItems:[]
						};
						
						for(var a = 0; a < self.cart().length; a++){
							dataToSend.orderItems.push({
								productId:String(self.cart()[a].productId()), 
								quantity:String(self.cart()[a].quantity()), 
								quantity:String(self.cart()[a].quantity()), 
								total:String(self.cart()[a].total())
							});
						}
						
						var rqstData = {
							url:'CreateNewOrder', 
							type:'POST', 
							data:{data:JSON.stringify(dataToSend)}, 
							callback:{
								func:function(rtrn){
									self.cart.removeAll();
									self.orderStatus(true);
									vm.category.index();
									vm.account_detail.setData(rtrn);
								}, 
								para:1
							}
						};
						var rqstObj = new rqst(rqstData);
						rqstObj.send();
					}
					
				}, 
				
				setData:function(input){
					var self = vm.category;
					self.data.removeAll();
					for(var i = 0; i < input.length; i++){
						var ctgrDtl = {id:input[i].id, name:input[i].name, description:input[i].description, products:[]};
						self.data.push(ko.mapping.fromJS(ctgrDtl));
						for(var j = 0; j < input[i].products.length; j++)
							self.data()[i].products.push(ko.mapping.fromJS(input[i].products[j]));
					}
				}, // End of setData
				
				loadView:function(){
					var self = vm.category;

					var rqstData = {
						url:'webresources/View/usr_category.html?a='+new Date().getTime(),
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
				}, // End of load view
				
				index:function(){
					var self = vm.category;
					
					if(self.v() == null){
						self.loadView();
					}else{
						$("#bdy").empty().append(self.v());
						ko.applyBindings(vm, $('#category-wrapper')[0]);
					}
				} // End of index
				
			} // End of category
		}
	)
});
