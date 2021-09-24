<%@page import="com.twitter.bean.Follower"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.twitter.bean.User"%>
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
    
   		<%
			String emailMsg = (String)session.getAttribute("emailMsg");
   			Object uId = session.getAttribute("userId");
			int userId = Integer.parseInt(uId.toString());	
            String name=(String)session.getAttribute("User");
			if(name!=null && name!=""){
		%>
                                
<div class="ch-container">
    <div class="row">
        
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <div align="center"><br><img alt="Profile Pic" class="img-circle" src="profilepic.jsp?id=<%=userId %>"  width="150px" height="100px"/>
                    <br>
                    <div>Welcome <strong><%=name %>,</strong></div>
        <%} %>
                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">Main</li>
                        <li><a class="ajax-link" href="user_home.jsp"><i class="glyphicon glyphicon-home"></i><span> Home</span></a>
                        </li>
                        <li><a class="ajax-link" href="tweet.jsp"><i
                                    class="glyphicon glyphicon-star"></i><span> Send Tweet</span></a></li>
                        <li><a class="ajax-link" href="TweetViewController"><i
                                    class="glyphicon glyphicon-star"></i><span> View Tweet</span></a></li>
                        <li><a class="ajax-link" href="user_search.jsp"><i
                                    class="glyphicon glyphicon-eye-open"></i><span> Search </span></a></li>
                        
                        <li><a class="ajax-link" href="UserFollowerController?userId=<%= userId%>"><i class="glyphicon glyphicon-user"></i><span> View Followers</span></a>
                        </li>
                        <li><a class="ajax-link" href="UserFollowingController?userId=<%= userId%>"><i
                                    class="glyphicon glyphicon-user"></i><span> View Followings</span></a></li>
                        
                    </ul>
                    
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->


	<div class="row">        
    
        <div class="col-md-8 center login-header">
        <div align="center">
        
		<%
			String errMsg = (String)request.getAttribute("ErrMsg");
			if(errMsg!=null && errMsg!=""){
		%>
				<p style="color:red"><%=errMsg %></p>
		<%
			}
		%>
		
        </div>
            <h2 align="center">Followers : <%=request.getAttribute("Count") %></h2>
        </div>
        <!--/span-->
    
   <div class="row">
        <div class="well col-md-9  login-box">
        
            <div class="alert alert-info">
                List of Followers
            </div>
            <table class="table table-striped table-bordered  responsive">
   			<thead>
    		<tr>
    			<th>Profile Pic</th>
        		<th>Name</th>
        		<th>Email-ID</th>
       			<th>Address</th>       			
    		</tr>
    		</thead>
    		<tbody>
    		<%
    			ArrayList<Follower> followerList = (ArrayList<Follower>)request.getAttribute("followerList");
    			if(followerList!=null && followerList.size()>0){
    				for(int i=0;i<followerList.size();i++){
    		%>
    		<tr>
    			<td><img alt="Profile Pic" class="img-square" src="profilepic.jsp?id=<%=followerList.get(i).getSender().getUserId() %>"  width="80px" height="50px"/></td>
    			<td><%=followerList.get(i).getSender().getFname()+" "+followerList.get(i).getSender().getLname() %></td>
        		<td><%=followerList.get(i).getSender().getEmail() %></td>
        		<td><%=followerList.get(i).getSender().getAddress() %></td>
    		</tr>
    		<%		}
    			}
    		%>
    		</tbody>
    		</table>
        </div>
        <!--/span-->
        
    </div><!--/row-->
    <!-- content ends -->
    </div><!--/#content.col-md-0-->
	
        </div>
       




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
	response.sendRedirect("user.jsp");
}
%>
