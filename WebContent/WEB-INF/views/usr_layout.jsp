<div id="main-wrapper" class="col-xs-12 padd0">

	<!--HEADER-->
    <div id="hdr" class="hdr col-xs-12 bg11">
    	<div class="fltlft">
    		<!-- Ecommerce Company Logo And Link to Home Page -->
    		<h1 class="clr111 curpo" data-bind="click:function(data, event){$root.navigate('Home');}">ECommerce</h1>
    	</div>
    	
    	
    	<div class="hdr-search col-xs-4 paddtb20">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                	<button class="btn icon bg1 icon-search" type="button" style="border:none;"></button>
                </span>
            </div><!-- /input-group -->
        </div>
        
        <div class="col-xs-6 hdr-menu paddtb25">
        	<div class="icon icon-cart fltrgt">
        		<div class="fltlft clr5 txt12 paddlr15" style="padding-top:8px" 
        		data-bind="text:$root.category.order.cart().length, 
        		click:function(data,event){$root.category.navigate('Order')}"></div>
        	</div>
        	<ul>
        		<!-- Check if the user logged in -->
        		<!-- ko if:$root.accountStatus() == true -->
            	<li><a href="#" class="paddlr10" data-bind="text:'Hi '+$root.accountDetail.name()+', '"></a></li>
            	<li><a href="#" class="paddlr10" data-bind="click:function(data, event){$root.navigate('MyAccount');}">My Account</a></li>
            	<!-- /ko -->
            	
            	<!-- Check if the user did not logged in -->
            	<!-- ko if:$root.accountStatus() == false -->
                <li><a href="#" class="paddlr10" 
            	data-bind="click:function(data, event){$root.navigate('SignIn');}">Log In</a></li>
                <li><a href="#" class="paddlr10" 
            	data-bind="click:function(data, event){$root.navigate('SignUp');}">Register</a></li>
            	<!-- /ko -->
            	
                <li style="border:none"><a href="#" class="paddlr10">Your Cart</a></li>
            </ul>
        </div>
    </div>
    
    <!-- 
    	This is the location where the content of each page is displayed 
    -->
    <!--BODY-->
    <div id="bdy" class="bdy"></div>
    
    <!--FOOTER
    <div id="ftr" class="ftr bg11 fltlft">
    	<div class="col-xs-12 row-xs-11 bg11 paddtb25">
        	<div class="col-xs-4 row-xs-12">
            	<div class="col-xs-12">
                	<h1 class="clr111">ECommerce</h1>
                	<p class="clr111 txt14">
                    	Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                        Etiam commodo lacus sed egestas sagittis. 
                        Cras lacinia nibh a ante eleifend bibendum. 
                        Aenean fermentum fringilla eros, quis tempus elit finibus vitae.
                    </p>
                </div>
            </div>
        	<div class="col-xs-4 row-xs-12 padd0" style="border-left:solid #fff 1px;border-right:solid #fff 1px;">
            	<div class="col-xs-6 ftr-menu paddtb5">
                    <ul>
                    	<li><span class="txt24 paddlr10 clr111 paddtb5">Categories</span></li>
                        <li><a href="#" class="paddlr10 paddtb5">About Us</a></li>
                        <li><a href="#" class="paddlr10 paddtb5">Products</a></li>
                        <li><a href="#" class="paddlr10 paddtb5">Downloads</a></li>
                        <li><a href="#" class="paddlr10 paddtb5">Team</a></li>
                    </ul>
                </div>
                <div class="col-xs-6 ftr-menu paddtb5">
                    <ul>
                    	<li><span class="txt24 paddlr10 clr111 paddtb5">Managment</span></li>
                        <li><a href="#" class="paddlr10 paddtb5">Resources</a></li>
                        <li><a href="#" class="paddlr10 paddtb5">Institutions</a></li>
                        <li><a href="#" class="paddlr10 paddtb5">Locations</a></li>
                        <li><a href="#" class="paddlr10 paddtb5">Contact</a></li>
                    </ul>
                </div>
            </div>
        	<div class="col-xs-4 row-xs-12 paddtb5">
            	<div class="fltrgt backtotop"></div>
                <div class=" clear fltrgt txt14 clr111" style="padding-top:140px;">&copy; Unical cs 2017</div>
            </div>
        </div>
        <div class="col-xs-12 row-xs-1 bg1"></div>
    </div>-->
</div>