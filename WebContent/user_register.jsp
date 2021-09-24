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
	<script src="validation.js"></script>
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
<script type="text/javascript">
    var datefield=document.createElement("input")
    datefield.setAttribute("type", "date")
    if (datefield.type!="date"){ //if browser doesn't support input type="date", load files for jQuery UI Date Picker
        document.write('<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />\n')
        document.write('<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"><\/script>\n')
        document.write('<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"><\/script>\n') 
    }
</script>
<script>
if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
    jQuery(function($){ //on document.ready
        $('#dob').datepicker();
    })
}
</script>
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
    
        <div class="col-md-8 center login-header">
        <div align="center">
        <%
			String errMsg = (String)request.getAttribute("ErrMsg");
			if(errMsg!=null && errMsg!=""){
		%>
				<p style="color:red"><%=errMsg%></p>
		<%
			}
		%>
        </div>
            <h2 align="center">Sign-up Page</h2>
        </div>
        <!--/span-->
    
    <div class="row">
    <div class="col-md-9 center"><div class="col-md-3"></div>
        <div class="well col-md-5 login-box">
        
            <div class="alert alert-info">
                Please fill all the fields.
            </div>
            <form action="UserRegisterController" method="post" enctype="multipart/form-data">
                <fieldset>                	
					<div class="form-group">
                        <label>First Name</label>
                        <input type="text" class="form-control" placeholder="First Name" name="fname" value="" pattern="[A-Za-z]+" title="Name must be alphabets only" required="required">
                    </div>            
                    <div class="clearfix"></div>					
					<div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control" placeholder="Last Name" name="lname" value="" pattern="[A-Za-z]+" title="Name must be alphabets only" required="required">
                    </div>                    
                    <div class="clearfix"></div>
					<div class="form-group">
                        <label>Email-ID</label>
                        <input type="email" class="form-control" placeholder="Email-ID" name="email" value="" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Email-Id must be valid format." required="required">
                    </div>
                    <div class="clearfix"></div>
					
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" placeholder="********" name="password1" value="" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Password must contain at least one number and one special character and one uppercase and lowercase letter, and at least 8 or more characters" required="required"> 
                    </div>
                    <div class="clearfix"></div>
                    <div class="form-group">
                        <label>Retype Password</label>
                        <input type="password" class="form-control" placeholder="********" name="password2" value="" onchange="return isPasswordMatch(password1,password2)" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Password must contain at least one number and one special character and one uppercase and lowercase letter, and at least 8 or more characters" required="required">
                    </div>
                    <div class="clearfix"></div>
                    <div class="form-group">
                        <label>Date of Birth</label>
                        <input type="date" class="form-control" placeholder="Date of Birth" name="dob" id="dob" value="" pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" title="Date should be month, date and year format (ex. 03/22/1990)" required="required">
                    </div>
                    <div class="clearfix"></div>
                    
                    <div class="form-group">
                        <label>Select Gender</label>&nbsp;&nbsp;
                        <input type="radio" name="gender" value="Male" checked="checked"> Male &nbsp;&nbsp;&nbsp;
                        <input type="radio" name="gender" value="Female"> Female
                    </div>
                    <div class="clearfix"></div>
                    <div class="form-group">
                        <label>Address</label>
                        <textarea class="form-control" placeholder="Address" name="address" rows="2"></textarea>
                    </div>
                    <div class="clearfix"></div>
					<div class="form-group">
                        <label>Mobile No.</label>
                        <input type="text" class="form-control" placeholder="Mobile No." name="contact" value="" pattern="[7-9]{1}[0-9]{9}" title="Mobile number must be starts with 7, 8 or 9 digit and total number of digits are 10" required="required">
                    </div>
                    <div class="clearfix"></div>
					
                    <div class="form-group">
						<label>Profile Image Upload</label>
						<input type="file" name="profilePic">
						<p class="help-block">(Examples of images .jpg, .jpeg, .png, etc format)</p>
					</div>
					                    				
                    <p class="left col-md-5">
                        <button type="submit" class="btn btn-primary">Sign-up</button>
                   </p>
                   <p class="right col-md-5">
                       <button type="reset" class="btn btn-primary">Reset</button>
                    </p>
                    <div ><a class="alignleft" href="user.jsp">Sign-in Your Account!</a>
                    	<a class="alignright" href="user_forgotpassword.jsp">Forgot password?</a>
                    </div>
                </fieldset>
            </form>
        </div>
        <!--/span-->
        <div class="col-md-3" align="right"><img alt="Sign-In" src="img/user_reg2.jpg" width="200px" height="200px"></div>
    </div>
    </div><!--/row-->
    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->

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

