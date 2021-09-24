<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">Main</li>
                        <li><a class="ajax-link" href="home.jsp"><i class="glyphicon glyphicon-home"></i><span> Home</span></a>
                        </li>
                        <li><a class="ajax-link" href="user.jsp"><i class="glyphicon glyphicon-user"></i><span> User</span></a>
                        </li>
                        <li><a class="ajax-link" href="admin.jsp"><i
                                    class="glyphicon glyphicon-eye-open"></i><span> Admin</span></a></li>
                        
                    </ul>
                    
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        


<div class="row">  
	 	<%
			String errMsg = (String)request.getAttribute("ErrMsg2");
			if(errMsg!=null && errMsg!=""){
		%>
				<p style="color:red"><%=errMsg %></p>
		<%
			}
		%>      
    
        <div class="col-md-8 center login-header">
            <h2 align="center">Forgot Password</h2>
        </div>
        <!--/span-->
    
    <div class="row">
    <div class="col-md-9 center"><div class="col-md-3"></div>
        <div class="well col-md-5 login-box">        
            <div class="alert alert-info">
                Please send email-id for recovering password.
            </div>
            <form action="UserPasswordController" method="post">
                <fieldset>
                    
					<div class="input-group input-group-lg">
                       
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                        <input type="email" class="form-control" placeholder="Email-ID" name="email" value="" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Email-Id must be valid format." required="required">
                    </div>
                    <div class="clearfix"></div><br>
					
                                        				
                    <p class="left col-md-5">
                        <button type="submit" class="btn btn-primary">Send</button>
                   </p>
                   <p class="right col-md-5">
                       <button type="reset" class="btn btn-primary">Reset</button>
                    </p>
                    <div ><a class="alignleft" href="user_register.jsp">Sign-in Your Account!</a>
                    	<a class="alignright" href="user.jsp">Sign-up Your Account!</a>
                    </div>
                </fieldset>
            </form>
        </div>
        <!--/span-->
        <div class="col-md-3" align="right"><img alt="Sign-In" src="img/password.jpg" width="200px" height="200px"></div>
     </div>
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

