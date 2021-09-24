<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.twitter.bean.RealTweetStream"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
if(session!=null){
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Twitter Application</title>
    <!-- The styles -->
    <link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="css/charisma-app.css" rel="stylesheet">
    <link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='css/jquery.noty.css' rel='stylesheet'>
    <link href='css/noty_theme_default.css' rel='stylesheet'>
    <link href='css/elfinder.min.css' rel='stylesheet'>
    <link href='css/elfinder.theme.css' rel='stylesheet'>
    <link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='css/uploadify.css' rel='stylesheet'>
    <link href='css/animate.min.css' rel='stylesheet'>
	<link href='css/own.css' rel='stylesheet'>
    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>
	<script src="validation.js"></script>
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home.jsp"> <img alt="Twitter Logo" src="img/twitterlogo.png" class="hidden-sm"/>
                </a>
			<h2 style="color:#FFD700;" class="collapse navbar-collapse nav navbar-nav top-menu" align="center" >Microblog Summarization Framework </h2>
           

            <!-- theme selector starts -->
           <div class="btn-group pull-right theme-container animated tada">                
                <ul class="dropdown-menu" id="themes">
                    
                    <li><a data-value="cerulean" href="#"><i class="whitespace"></i> Cerulean</a></li>
                   
                </ul>
            </div>
            <!-- theme selector ends -->

            

        </div>
    </div>
    <!-- topbar ends -->
    
   		                                
<div class="ch-container">
    <div class="row">
        
        <!-- left menu starts -->
        <div class="col-md-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <!-- <div><img alt="Profile Pic" src="profilepic.jsp" class="hidden-xs"/> -->
                    <div>
            <%
			String emailMsg = (String)session.getAttribute("emailMsg");
            //String name=(String)session.getAttribute("Server");
			if(emailMsg!=null && emailMsg!=""){
			%>
                                <div><strong><%=emailMsg %></strong></div>
            <%} %>
                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                       <li class="nav-header">Main</li>
                        <li><a class="ajax-link" href="admin_home.jsp"><i class="glyphicon glyphicon-home"></i><span> Home</span></a>
                        </li>
                        <li><a class="ajax-link" href="AdminUserController"><i class="glyphicon glyphicon-user"></i><span> List Users</span></a>
                        </li>
                        <li><a class="ajax-link" href="AdminTweetViewController"><i
                                    class="glyphicon glyphicon-eye-open"></i><span> View Tweets</span></a></li>
                       
                        <li><a class="ajax-link" href="admin_search.jsp"><i
                                    class="glyphicon glyphicon-eye-open"></i><span> Real-time Tweets</span></a></li> 
                        <li><a class="ajax-link" href="AdminLogoutController"><i
                                    class="glyphicon glyphicon-user"></i><span> Logout</span></a></li> 
                    </ul>
                    
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        
	<div class="row">        
    
        <div class="col-md-6 center login-header">
            <h2 align="center">View Tweets</h2>
        </div>
        <!--/span-->
    
    <div class="row">
        <div class="well col-md-6 center login-box">
        
        
            <table class="table table-striped table-bordered  responsive">
   			<thead>
    		<tr>
    			<th>Cluster ID</th>
        		<th>Tweet ID</th>
        		<th>Tweet Content</th> 
        		     			
    		</tr>
    		</thead>
    		<tbody>
    		<%
							HashMap<Integer, ArrayList<RealTweetStream>> clusterTwitList = (HashMap<Integer, ArrayList<RealTweetStream>>)session.getAttribute("ClusterTweetList");
							if(clusterTwitList!=null){
								for(Entry<Integer, ArrayList<RealTweetStream>> entry: clusterTwitList.entrySet()){
									int key = entry.getKey();
									ArrayList<RealTweetStream> comList = clusterTwitList.get(key);
									
									for(int i=0;i<comList.size();i++){
						%>
						
									<tr>
									<td><%=key %></td>
									<td><%=comList.get(i).getTwitId() %></td>
									<td><%=comList.get(i).getTweetContent() %></td>
									</tr>
						<%										
									}
								}
									
							}								
							
						%>

    		</tbody>
    		</table>
        		  <p class="left col-md-5">
                        <a href="AdminSubtopicController" class="btn btn-primary">MMLDA</a>
                   </p> 
        </div>
        <!--/span-->
        
    </div><!--/row-->
    <!-- content ends -->
    </div><!--/#content.col-md-0-->

    
</div><!--/fluid-row-->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<hr>

    


</div><!--/.fluid-container-->

<!-- external javascript -->

<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="js/charisma.js"></script>


</body>
</html>
<%
}else{
	response.sendRedirect("admin.jsp");
}
%>

