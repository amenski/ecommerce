// The website html layout
var layout = null;

var sysData = null;

// Define the pages
var bpages = ["home",  "category", "account_detail"];

jQuery(document).ready(function($){
	
	// A Function to Set Up the General Settings
	function setGeneral(){
		$.extend(m.prototype, {general:{}});
	}
	
	// A Function for loading the page content
	function loader(){
		this.status = 0;
		$('#ldr').animate({width:'0%'});
	}
	
	$.extend(loader.prototype, {
		
		loadLayout:function(){
			var rqstData = {
				url:'LoadLayout', 
				dataType:'html', 
				callback:{
					func:function(b){
						layout = b;
						
						loaderObj.status++;
						loaderObj.start();		
					}, 
					para:1
				}
			};
			
			var rqstObj = new rqst(rqstData);
			rqstObj.send();
		}, 
		
		// A Function for Loading the Data from the Server.
		loadData:function(){
			var rqstData = {
					url:'GetData', 
					callback:{
					func:function(ajaxResponce){
						bmData = ajaxResponce;
						
						loaderObj.status++;
						loaderObj.start();	
					}, 
					para:1
				}
			};
			var rqstObj = new rqst(rqstData);
			rqstObj.send();
		}, 
		
		loadScript:function(pgeIndx){
			var pages = bpages;
			var tag = 'usr_';
			if(pages.length != 0){
				var rqstData = {
					url:'webresources/ViewModel/'+tag+pages[pgeIndx]+'.js', 
					dataType:'script', 
					callback:{
						func:function(b){
							var nxt = b+1;
							var maxVal = pages.length - 1;
							if(nxt <= maxVal){
								loaderObj.loadScript(nxt);
							}else{
								loaderObj.status++;
								loaderObj.start();
							}
									
						}, 
						para:1, 
						data:pgeIndx
					}
				};
				var rqstObj = new rqst(rqstData);
				rqstObj.send();
			}else{
				loaderObj.status++;
				loaderObj.start();
			}
		}, 
		
		start:function(){
			
			var self = this;
			switch(self.status){
				case 0:
					$('#page-loader-rail').animate({width:'25%'}, 'slow');
					self.loadData();
				break;
				case 1:
					$('#page-loader-rail').animate({width:'25%'}, 'slow');
					self.loadLayout();
				break;
				case 2:
					$('#page-loader-rail').animate({width:'75%'}, 'slow');
					self.loadScript(0);
				break;
				case 3:
					$('#page-loader-rail').animate({width:'100%'}, 'slow', function(){
						setGeneral();
						mobj = new m();
						vm = ko.mapping.fromJS(mobj);
						vm.userType('user');
						
						$("body").empty().append(layout);

						ko.applyBindings(vm, $('#main-wrapper')[0]);
						vm.home.index();
					});
				break;
			}
			
		}
			
	});
	
	var loaderObj = new loader();
	loaderObj.start();
});

