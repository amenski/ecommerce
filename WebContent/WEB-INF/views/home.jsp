<%@ page isELIgnored="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- tag srping:url Style -->
    <spring:url var="bootstrapCss" value="/webresources/plugin/bootstrap/css/bootstrap.css" />
    <spring:url var="bootstrapThemeCss" value="/webresources/plugin/bootstrap/css/bootstrap-theme.css" />
    <spring:url var="mainCss" value="/webresources/style/css/layout.css" />
    <spring:url var="scrollCss" value="/webresources/plugin/scroll/css/jquery.mCustomScrollbar.css" />
    <spring:url var="jqueryUiCss" value="/webresources/plugin/jquery/css/jquery-ui.min.css" />	
	<!--<spring:url var="jqueryUiDatepickerCss" value="/webresources/plugin/jquery/css/jquery.ui.datepicker.min.css" />
    --><spring:url var="jqueryUiTimepickerCss" value="/webresources/plugin/jquery/jquery.ui.timepicker.css?v=0.3.3"  />
    
    <!-- tag spring:url Script -->
    <spring:url var="ie8-responsive-file-warningJs" value="/webresources/plugin/bootstrap/assets/js/ie8-responsive-file-warning.js"/>
    <spring:url var="ie-emulation-modes-warningJs" value="/webresources/plugin/bootstrap/assets/js/ie-emulation-modes-warning.js"/>
	
    <spring:url var="jqueryJs" value="/webresources/plugin/jquery/jquery-1.11.0.min.js"/>
    <spring:url var="bootstrapJs" value="/webresources/plugin/bootstrap/js/bootstrap.js"/>
    <spring:url var="docsJs" value="/webresources/plugin/bootstrap/assets/js/docs.min.js"/>
    <spring:url var="jqueryUiJs" value="/webresources/plugin/jquery/jquery-ui.js"/>
    <spring:url var="jqueryUiDatepickerJs" value="/webresources/plugin/jquery/jquery.ui.datepicker.js"/>
    <spring:url var="jqueryUiTimepickerJs" value="/webresources/plugin/jquery/jquery.ui.timepicker.js"/>
    <spring:url var="jqueryUiWheelJs" value="/webresources/plugin/scroll/js/jquery.mousewheel-3.0.6.js"/>
    <spring:url var="scrollJs" value="/webresources/plugin/scroll/js/jquery.mCustomScrollbar.js"/>
    <spring:url var="ie10-viewport-bug-workaroundJs" value="/webresources/plugin/bootstrap/assets/js/ie10-viewport-bug-workaround.js"/>
    <spring:url var="knockoutJs" value="/webresources/plugin/knockout/knockout-3.2.0.js"/>
    <spring:url var="knockout_mappingJs" value="/webresources/plugin/knockout/knockout_mapping.js"/>
    <spring:url var="js_indexJs" value="/webresources/ViewModel/js_index.js"/>
  	<spring:url var="ldr_user_accountJs" value="/webresources/ViewModel/ldr_user_account.js"/>
  	<spring:url var="ldr_homeJs" value="/webresources/ViewModel/ldr_home.js"/>
   
	<title>Ecommerce</title>

    <!-- Bootstrap core CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    
    <!-- Bootstrap theme -->
    <link href="${bootstrapThemeCss}" rel="stylesheet">
	
    <link href="${mainCss}" rel="stylesheet">
    
    <link href="${scrollCss}" rel="stylesheet">
    
    <link rel="stylesheet" href="${jqueryUiCss}" />	
	<link rel="stylesheet" href="${jqueryUiDatepickerCss}" />
    <link rel="stylesheet" href="${jqueryUiTimepicker}"  />
    
      
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="${ie8-responsive-file-warningJs}"></script><![endif]
    
    <script src="${ie-emulation-modes-warningJs}"></script>-->
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!--
    <link rel="stylesheet" href="http://openlayers.org/en/v3.1.1/css/ol.css" type="text/css">
    <script src="http://openlayers.org/en/v3.1.1/build/ol.js" type="text/javascript"></script>
    -->
    
  </head>

  <body class="bg5">
  	
    <div class="col-xs-12 col-sm-12 col-md-12" style="margin-top:0px;">
        <div id="page-loader" class="wid-12" style="height:5px; background:#F8F8F8">
            <div class="bg1 hgt-xs-12" id="page-loader-rail" style="width:5%;height:100%;"></div> 
        </div>
    </div>
    
    
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${jqueryJs}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${docsJs}"></script>
    <script src="${jqueryUiJs}"></script>
    <script src="${jqueryUiDatepickerJs}"></script>
    <script src="${jqueryUiTimepickerJs}"></script>
    <script src="${jqueryUiWheelJs}"></script>
    <script src="${jqueryUiDatepickerCss}"></script>
    
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug 
    <script src="${ie10-viewport-bug-workaroundJs}"></script>-->
    
    
    <!-- Knockout : For the single page application -->
    <script src="${knockoutJs}"></script>
    <script src="${knockout_mappingJs}"></script>
    
    <script src="${js_indexJs}"></script>
    <% 
    boolean loginStatus = (boolean) session.getAttribute("loginStatus");
    if(loginStatus){
    	%>
    	<script src="${ldr_user_accountJs}"></script>
    	<a href="<spring:url value='/LogOut' />">Log out</a>
    <% 	
    }else{
    %>
  		<script src="${ldr_homeJs}"></script>
  	<%} %>
   
    
  </body>
</html>