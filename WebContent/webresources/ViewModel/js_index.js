
// View Model 
var vm = null;

// Model
function m(){};

// Model Object
var mobj = null;

var errorStatus;

//Business data and system data
var bmData = {"Categories":[], "LoginStatus":false, "AccountDetail":null};

// REQUEST PROCESSING UNIT
function rqst(input){
	
	// The type of request
	this.rqstType = 'default';
	
	// The url request
	this.url = 'GetData';
	
	// The type of sent procedure used 
	this.type = 'GET';
	
	// The data to be sent with the request
	this.data = {};
	
	// The type of data expected to be recived from the server.
	this.dataType = 'json';
	
	// The callback data
	this.callback = {func:null, data:null, para:0};
	
	// The server responce
	this.responce = null;
	
	
	if(input != null && input != undefined){
		
		if(input.rqstType != null && input.rqstType != undefined)
			this.rqstType = input.rqstType;
			
		if(input.url != null && input.url != undefined)
			this.url = input.url;
		
		if(input.type != null && input.type != undefined)
			this.type = input.type;
		
		if(input.data != null && input.data != undefined)
			this.data = input.data;
		
		if(input.dataType != null && input.dataType != undefined)
			this.dataType = input.dataType;
		
		if(input.callback.func != null && input.callback.func != undefined)
			this.callback.func = input.callback.func;
		if(input.callback.data != null && input.callback.data != undefined)
			this.callback.data = input.callback.data;
		if(input.callback.para != null && input.callback.para != undefined)
			this.callback.para = input.callback.para;
	}
		
}


jQuery(document).ready(function($){
	
	// Extend Request
	$.extend(rqst.prototype, {
		
		send:function(){
			
			var self = this;
			
			// Check
			if(self.rqstType != 'default')
				self.url = 'script/rqst_'+self.rqstType+'.php';
				
			
			//alert(self.dataType);
			// Send data to server using jquery Ajax request
			$.ajax({
				type: self.type, 
				url:self.url, 
				dataType: self.dataType, 
				data:self.data, 
				error: function (jqXHR, textStatus, errorThrown) {
				    errorStatus = textStatus;
				}, 
				success: function(responce){
					if(self.callback.func != null){
						switch(self.callback.para){
							case 1:
								if(self.callback.data == null)
									self.callback.func(responce);
								else
									self.callback.func(self.callback.data);
							break;
							case 2:
								self.callback.func(responce, self.callback.data);
							break;
						} // End of switch
					} // End of if
				}// End of success
			});// End of Ajax
		}// End of Send
		
	}); // End of Extend
	
	// Extend Model
	$.extend(m.prototype, {
		
		userType:'user', 
		
		RTE:{
			
			multi:[], 
			
			content:function(i){
				var self = this.multi()[i];
				return $('#'+self.hldr()+'_rte_bdy').contents().find('body').html();
			}, // End of content
			
			getIndex:function(hldr){
				var self = this;
				var i = null;
				for(var a = 0; a < self.multi().length; a++)
					if(self.multi()[a].hldr() === hldr)
						i = a;
					
				return i;
			}, // End of get Index
			 
			exeCmd:function(cmd, i){
				var self = this.multi()[i];
				var editor = document.getElementById (self.hldr()+'_rte_bdy');
            	editorDoc = editor.contentWindow.document; 
				editorDoc.execCommand (cmd, false, null);
			}, // End of exeCmd
			
			init:function(i, cont){
				var self = this.multi()[i];
				
				var editor = document.getElementById (self.hldr()+'_rte_bdy');
				self.editorDoc(editor.contentWindow.document);          
				
				
				var html = '<html>'+
					'<head>'+
						'<style type="text/css">'+
							'.rte_bdy_style{font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#2473b7; background-color:#fff;}'+
						'</style>'+
					'</head>'+
					'<body class="rte_bdy_style">'+cont+'</body>'+
				'</html>';
				self.editorDoc().write(html);
				var editorBody = self.editorDoc().body;
					// turn off spellcheck
				if ('spellcheck' in editorBody) {    // Firefox
					editorBody.spellcheck = false;
				}
	
				if ('contentEditable' in editorBody) {
						// allow contentEditable
					editorBody.contentEditable = true;
				}
				else {  // Firefox earlier than version 3
					if ('designMode' in self.editorDoc()) {
							// turn on designMode
						self.editorDoc().designMode = "on";                
					}
				}
			}, // End of init
			 
			index:function(hldr, content){
				var self = this;
				
				self.multi.push(ko.mapping.fromJS({hldr:hldr, editorDoc:null, content:''}, {}));
				
				var index = self.multi().length-1;
				
				$('#'+hldr).empty().append('<div class="rte-hldr">'+
					'<div class="rte-menu">'+
						'<ul>'+
							'<li class="txtstyle" '+
							'data-bind="click:function(data,event){$root.RTE.exeCmd(\'bold\', '+index+');}" style="font-weight:bold;">Bold</li>'+
							'<li class="txtstyle" '+
							'data-bind="click:function(data,event){$root.RTE.exeCmd(\'italic\', '+index+');}" '+
							'style="padding-left:9px; font-style:italic">Italic</li>'+
							'<li class="txtstyle" '+
							'data-bind="click:function(data,event){$root.RTE.exeCmd(\'underline\', '+index+');}" '+
							' style="text-decoration:underline">Underline</li>'+
						'</ul>'+
					'</div>'+
					
						'<iframe id="'+hldr+'_rte_bdy" class="rte-body" contenteditable="true">'+
							
						'</iframe>'+
					
				'</div>');
				var cont = '';
				if(content != null && content != undefined)
					cont = content;
				self.init(index, cont);
				
				ko.applyBindings(vm, $('#'+hldr+' .rte-hldr')[0]);
			} // End of index
			
		}, // End of Rich Text Editor
		
		
		loading:{
			v:null, 
			status:0, 
			tag:'', 
			hldr:'', 
			type:'', 
			time:{}, 
			
			multi:[],
			
			// For a multiple progress bars
			destroy_1:function(index, rplc){
				var self = this;
				
				$('#'+self.multi()[index].hldr()).empty().append(rplc);
				clearInterval(self.multi()[index].time)
				self.multi.remove(self.multi()[index]);
			}, // End of destroy_1
			
			changeClass_1:function(val, lodr){
				var self = this;
				lodr.status(val);
				
				var rmv = lodr.status() - 5;
				if(val == 5)
					rmv = 100;
				
				$('#'+lodr.hldr()+' #ldr-img')
					.removeClass('ldr-'+lodr.tag()+'-'+lodr.type()+'-'+rmv)
					.addClass('ldr-'+lodr.tag()+'-'+lodr.type()+'-'+lodr.status());
					
				//alert($('#'+lodr.hldr()).html());
				
				
				//$('#'+lodr.hldr()+' #ldr-txt').empty().append(lodr.status());
			}, // End of changeClass_1
			
			update_1:function(val, lodr){
				
				var self = this;
				var i = parseInt(val / 5);
				lodr.time = setInterval(function(){
					if(lodr.status() < 100){
						var newval = lodr.status()+5;
						self.changeClass_1(newval, lodr);
					}else if(lodr.status() >= 100){
						self.changeClass_1(5, lodr);
					}
				}, 40);
				
				
			}, // End of update_1
			
			index_1:function(hldr, tag, type, text){
				var self = this;
				self.multi.push(ko.mapping.fromJS({tag:tag, hldr:hldr, type:type, time:null, status:0}, {}));
				var index = self.multi().length-1;
				$('#'+hldr).empty().append('<div id="'+hldr+'-ldr" class="fltlft">'+
					'<div id="ldr-img" class="fltlft zind2 fwid fhgt ldr-img ldr-'+tag+'"></div>'+
				'</div>'+
				'<div id="ldr-txt" class="fltlft clrwhite" style="margin-left:5px;font-size:14px;">'+text+'</div>');
				
				self.update_1(5, self.multi()[index]);
				return index;
			}, // End of index_1
			
			// For a single progress bar
			destroy:function(){
				var self = this;
				self.v(null);
				self.tag('');
				self.hldr('');
				//self.time.stop();
			}, // End of Destroy
			
			changeClass:function(val){
				var self = this;
				self.status(val);
				if(self.status() <= 25)
					$('#'+self.hldr()+' #ldr-first').addClass('ldr-'+self.tag()+'-'+self.type()+'-'+self.status());
				else if(self.status() <= 50)
					$('#'+self.hldr()+' #ldr-second').addClass('ldr-'+self.tag()+'-'+self.type()+'-'+self.status());
				else if(self.status() <= 75)
					$('#'+self.hldr()+' #ldr-third').addClass('ldr-'+self.tag()+'-'+self.type()+'-'+self.status());
				else if(self.status() <= 100)
					$('#'+self.hldr()+' #ldr-fourth').addClass('ldr-'+self.tag()+'-'+self.type()+'-'+self.status());
				
			}, // End of changeClass
			
			update:function(val, func){
				var self = this;
				var i = parseInt(val / 5);
				var maxval = i * 5;
				var time = setInterval(function(){
					if(self.status() < maxval){
						var newval = self.status()+5;
						self.changeClass(newval);
					}else if(self.status() >= maxval){
						self.changeClass(maxval);
						clearInterval(time);
						if(func != null && func != undefined)
							func();
						//alert(1);	
					}
				}, 50);
				
			}, // End of update
			
			initiate:function(tag){
				var self = this;
				self.status(5);
				//alert('ldr-'+self.tag()+'-'+self.type()+'-'+self.status());
				$('#'+self.hldr()+' #ldr-first')
					.addClass('ldr-'+self.tag()+'-'+self.type()+'-'+self.status());
			}, // End of initiate
			
			index:function(hldr, tag, type){
				var self = this;
				self.tag(tag);
				self.hldr(hldr); 
				self.type(type);
				
				$('#'+hldr).empty().append('<div id="'+hldr+'-ldr" class="ldr-lg">'+
					
					'<div id="ldr-img" class="abslt toplft zind1 fwid fhgt">'+
						'<div id="ldr-first" class="fltlft ldr-img"></div>'+
						'<div id="ldr-second" class="fltrgt ldr-img "></div>'+
						'<div id="ldr-third" class="fltrgt ldr-img "></div>'+
						'<div id="ldr-fourth" class="fltlft ldr-img "></div>'+
					'</div>'+
					'<div class="clear"></div>'+
				'</div>'+
				'<div class="clear"></div>');
				$('#'+hldr+' #ldr-first, #'+hldr+' #ldr-second, #'+hldr+' #ldr-third, #'+hldr+' #ldr-fourth').addClass('ldr-'+tag+'-quad');
				self.initiate();
			} // End of index
		}, // End of Loading
		
		// Manage Date and Date Formats
		getDateDtl : {
			shrtDay:["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"], 
			longDay:["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"], 
			
			shrtMonth:["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
			shrtUppMonth:["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"], 
			longMonth:["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"], 
			
			now:function(){
			    var today = new Date();
			    
				return {
					json:{"Month":today.getMonth()+1, "Date":today.getDate(), "Year":today.getFullYear()}, 
				    numDate: today.getDate(),

					hour:today.getHours(),
					minute:today.getMinutes(),
					
					fullYear:today.getFullYear(),
					year:today.getFullYear().toString().substr(2,2),
					 
					shrtDay:vm.getDateDtl.shrtDay()[today.getDay()], 
					longDay:vm.getDateDtl.longDay()[today.getDay()], 
					
					shrtMonth: vm.getDateDtl.shrtMonth()[today.getMonth()-1],
					longMonth:vm.getDateDtl.longMonth()[today.getMonth()-1], 
					
					longDateTxt: vm.getDateDtl.longMonth()[today.getMonth()-1] + " " + today.getDate() + ", " + today.getFullYear(),
					shrtDateTxt: vm.getDateDtl.shrtMonth()[today.getMonth()-1] + " " + today.getDate() + ", " + today.getFullYear(),
					shrtDateTxt1: today.getMonth() + "/" + today.getDate() + "/" + today.getFullYear(),
					shrtDateTxt2: today.getFullYear() + "/" + today.getMonth()+1 + "/" + today.getDate(),

					longMonthYear: vm.getDateDtl.longMonth()[today.getMonth()-1] + ", " + today.getFullYear()
					
				};
			}, 
			
			any:function(input){
				
				var dateArray = input.split('-');
				var dateObj = new Date(dateArray[0], dateArray[1]-1, dateArray[2]);
				
				return {
					numDate: dateObj.getDate(),

					hour:dateObj.getHours(),
					minute:dateObj.getMinutes(),

					fullYear:dateObj.getFullYear(),
					 
					shrtDay:vm.getDateDtl.shrtDay()[dateObj.getDay()], 
					longDay:vm.getDateDtl.longDay()[dateObj.getDay()], 
					
					shrtMonth: vm.getDateDtl.shrtMonth()[dateObj.getMonth()],
					longMonth:vm.getDateDtl.longMonth[dateObj.getMonth()], 
					
					longDateTxt: vm.getDateDtl.longMonth()[dateObj.getMonth()] + " " + 
						dateObj.getDate() + ", " + dateObj.getFullYear(),
					shrtDateTxt: vm.getDateDtl.shrtMonth()[dateObj.getMonth()] + " " + 
						dateObj.getDate() + ", " + dateObj.getFullYear(),
					shrtDateTxt1: dateObj.getMonth()+1 + "/" + dateObj.getDate() + "/" + dateObj.getFullYear(),
					shrtDateTxt2: dateObj.getFullYear() + "/" + dateObj.getMonth()+1 + "/" + dateObj.getDate(),

					longMonthYear: vm.getDateDtl.longMonth()[dateObj.getMonth()] + ", " + dateObj.getFullYear()
				};
			}, 
			
			dateOpt:function(type){
				var result = [];
				switch(type){
					case 'date':
						for(var a = 1; a <= 31; a++)
							result.push({'lbl':a, 'val':a});
					break;
					case 'month':
						var smoth = ko.toJS(vm.getDateDtl.shrtUppMonth);
						for(var a = 0; a < smoth.length; a++){
							lbl = a+1;
							if(lbl<=9)
								lbl = '0'+lbl;
							result.push({'lbl':lbl+' '+smoth[a], 'val':lbl+' '+smoth[a]});
						}
					break;
					case 'year':
						//alert(vm.getDateDtl.now().year);
						for(var a = vm.getDateDtl.now().year; a <= 26; a++)
							result.push({'lbl':a, 'val':a});
					break;	
				}
				
				return result;
			}
		},// End of getDateDtl
		
		getData:function(type, data, callback){
			var rqstData = {
				url:'script/rqst_'+vm.userType()+'.php', 
				type:'GET', 
				data:{"RqstType":type, "RqstData":data}, 
				callback:{
					func:function(ajaxResponce){
						callback(ajaxResponce);
					}, 
					para:1
				}
			};
			var rqstObj = new rqst(rqstData);
			rqstObj.send();
		}, // End of Get Data
		
		postData:function(type, data, callback){
			var rqstData = {
				url:'script/rqst_'+vm.userType()+'.php', 
				type:'POST', 
				data:{"RqstType":type, "RqstData":data}, 
				callback:{
					func:function(ajaxResponce){
						callback(ajaxResponce);
					}, 
					para:1
				}
			};
			var rqstObj = new rqst(rqstData);
			rqstObj.send();
		} // End of Post Data
		
	});
	
	
});
