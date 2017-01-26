
// The layout
var layout = null;

// Business data and system data
var bmData = null;
var sysData = null;

// List of scripts for each page
var bpages = ["home", "category"];

jQuery(document).ready(function($){
	
	// A Function to Set Up the General Settings
	function setGeneral(){
		$.extend(m.prototype, {general:{}});
	}
	
	// An object for loading the content of the page.
	function loader(){
		this.status = 0;
		$('#ldr').animate({width:'0%'});
	}
	
	$.extend(loader.prototype, {
		
		// Function used for loading the layout
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
		
		// Function used for loading the data
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
		
		// Function used for loading the script
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
		
		// For initiating and updating the load.
		start:function(){
			
			var self = this;
			switch(self.status){
				case 0:
					// Load Date
					$('#page-loader-rail').animate({width:'25%'}, 'slow');
					self.loadData();
				break;
				case 1:
					// Load Layout
					$('#page-loader-rail').animate({width:'25%'}, 'slow');
					self.loadLayout();
				break;
				case 2:
					// Load Script
					$('#page-loader-rail').animate({width:'75%'}, 'slow');
					self.loadScript(0);
				break;
				case 3:
					
					$('#page-loader-rail').animate({width:'100%'}, 'slow', function(){
						setGeneral();
						mobj = new m();
						
						// To change the mode into view model
						// by making each variable observable
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

