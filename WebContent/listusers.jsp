<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
        <div class="col-sm-2 col-md-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <!-- <div><img alt="Profile Pic" src="profilepic.jsp" class="hidden-xs"/> -->
                    
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
    	<div>
            <%
			String emailMsg = (String)session.getAttribute("emailMsg");
            //String name=(String)session.getAttribute("Server");
			if(emailMsg!=null && emailMsg!=""){
			%>
                                <div align="right"><strong><%=emailMsg %></strong></div>
            <%} %>
        </div>
        <div class="col-md-8 center login-header">
            <h2 align="center">List of Users</h2>
        </div>
        <!--/span-->
    
    <div class="row">
        <div class="well col-md-9  login-box">
        
            <div class="alert alert-info">
                Maintain Users Records.
            </div>
            <table class="table table-striped table-bordered  responsive">
   			<thead>
    		<tr>
        		<th>Name</th>
        		<th>Email-ID</th>
       			<th>Date of Birth</th>
        		<th>Gender</th>
       			<th>Mobile No.</th>
       			<th>Status</th>
       			<th>View</th>
       			<!-- <th>Edit</th>
       			<th>Delete</th> -->
    		</tr>
    		</thead>
    		<tbody>
    		<%
    			ResultSet rs = (ResultSet)session.getAttribute("listUsers");
    			while(rs.next()){
    		%>
    		<tr>
    			<th><%=rs.getString(2)+" "+rs.getString(3) %></th>
        		<th><%=rs.getString(4) %></th>
       			<th><%=rs.getDate(6) %></th>
        		<th><%=rs.getString(7) %></th>
       			<th><%=rs.getString(9) %></th>
       			<th><a href="UserStatusController?userId=<%=rs.getInt(1)%>&status=<%=rs.getString(12)%>"><%=rs.getString(12) %></a></th>
       			<th><a href="UserViewController?userId=<%=rs.getInt(1)%>"><i class="glyphicon glyphicon-zoom-in icon-white"></i></a></th>
       			<%-- <th><a href="UserEditController?userId=<%=rs.getInt(1)%>"><i class="glyphicon glyphicon-edit icon-white"></i></a></th>
       			<th><a href="UserDeleteController?userId=<%=rs.getInt(1)%>"><i class="glyphicon glyphicon-trash icon-white"></i></a></th> --%>
    		</tr>
    		<%} %>
    		</tbody>
    		</table>
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
