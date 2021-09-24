<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.net.URL"%>
<%@page import="edu.cmu.sphinx.api.SpeechResult"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="edu.cmu.sphinx.api.StreamSpeechRecognizer"%>
<%@page import="edu.cmu.sphinx.api.Configuration"%>
<%@page import="java.util.List"%>
<%@page import="com.twitter.model.DuplicateImageFinder"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="com.xuggle.mediatool.ToolFactory"%>
<%@page import="com.xuggle.mediatool.IMediaReader"%>
<%@page import="com.twitter.model.ocr.TesseractException"%>
<%@page import="com.twitter.model.ocr.Tesseract"%>
<%@page import="com.twitter.model.ocr.ITesseract"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.twitter.bean.RealTweetStream"%>
<%@page import="com.twitter.model.Extract_Refrence_Frames"%>
<%@page import="com.twitter.model.Extract_Refrence_Frames.ImageSnapListener"%>

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
        
        
            <div class="table table-striped table-bordered  responsive">
   			<div>
    		<%				ArrayList<String> imglList2= (ArrayList<String>) session.getAttribute("ImageList");
							HashMap<Integer, ArrayList<RealTweetStream>> clusterTwitList = (HashMap<Integer, ArrayList<RealTweetStream>>)session.getAttribute("ClusterTweetList");
							if(clusterTwitList!=null){
								for(Entry<Integer, ArrayList<RealTweetStream>> entry: clusterTwitList.entrySet()){
									int key = entry.getKey();
									ArrayList<RealTweetStream> comList = clusterTwitList.get(key);
						%>			<div><h4>Cluster ID:</h4><%=key %></div>
									
						<%		for(int i=0;i<comList.size();i++){	
									if(comList.get(i).getVideourl()!=null&&!comList.get(i).getVideourl().equals("")){
										if(!comList.get(i).getVideourl().contains(".m3u8")||!comList.get(i).getVideourl().contains(".m4u8"))%>
									<div><video width="400" height="240" controls>  
  					<source src="<%=comList.get(i).getVideourl() %>" type="video/mp4" class="img-square">  </video> </div>
  					
									<%
										String dirPath="C:/Users/hp/Desktop/workspace/microblogsummarization/WebContent/upload/video";
								        File fdir=new File(dirPath);
								        if (fdir.isDirectory()) {
								            for (File deleteMe: fdir.listFiles()) {
								                // recursive delete
								               deleteMe.delete();
								            }
								            File fpdir=new File(dirPath+"/compare");
								            fpdir.mkdir();
								        }
								        URL url = new URL(comList.get(i).getVideourl());
										InputStream is = url.openStream();
										File vfile=new File("C:/Users/hp/Desktop/workspace/microblogsummarization/WebContent/upload/"+"out.mp4");
										OutputStream os = new FileOutputStream(vfile);

										byte[] b = new byte[4096];
										int length;

										while ((length = is.read(b)) != -1) {
											os.write(b, 0, length);
										}

										is.close();
										os.close();
								        
								        IMediaReader mediaReader = ToolFactory.makeReader(vfile.getAbsolutePath());

										// stipulate that we want BufferedImages created in BGR 24bit color space
										mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
										
										mediaReader.addListener(new ImageSnapListener());

									    // read out the contents of the media file and
										// dispatch events to the attached listener
										while (mediaReader.readPacket() == null) ;

								       
								      
								        List<List<String>> imgList=DuplicateImageFinder.findDuplicatePairs(dirPath);
								    	List<String> imgvlList2=DuplicateImageFinder.findAllUniqueImages(dirPath);
								    	String result ="";
								    	
								  
								           
											
									//Audio
									Configuration configuration = new Configuration();
									
									configuration
											.setAcousticModelPath("file:///C:/Users/hp/Desktop/workspace/microblogsummarization/resources/edu/cmu/sphinx/models/en-us/en-us");
									configuration
											.setDictionaryPath("file:///C:/Users/hp/Desktop/workspace/microblogsummarization/resources/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
									configuration
											.setLanguageModelPath("file:///C:/Users/hp/Desktop/workspace/microblogsummarization/resources/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
									String result1="";
									StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(
											configuration);
									InputStream stream = new FileInputStream(new File(
											comList.get(i).getVideourl()));

									recognizer.startRecognition(stream);
									SpeechResult results;
									while ((results = recognizer.getResult()) != null) {
										System.out.format("Hypothesis: %s\n",
												results.getHypothesis());
										result1=result1+results.getHypothesis()+"\n";
									}
									recognizer.stopRecognition();
									
									%>
									<div><h5><%=result1 %></h5></div>
									<%}
									for(int j=0;j<imglList2.size();j++)
					    			{
					    				
					    				String str=imglList2.get(j);
					    				str=str.substring(str.lastIndexOf("\\")+1,str.lastIndexOf("."));
					    				if(Integer.parseInt(str)==comList.get(i).getTwitId()){
					    
					    %>
					    	<div align="center"><img alt="Tweet Pic" class="img-square" src="<%=comList.get(i).getImgurl() %>"  width="300px" height="150px"/></div>
					    	
					    <%		
					   
				   
				      
					    			}
					    		}
						%>
						
									<div>
									
									<div><h4>Tweet ID:</h4><%=comList.get(i).getTwitId() %></div>
									<div><h4>Tweet Content:</h4> <%=comList.get(i).getTweetContent() %></div>
									
									</div>
									
						<%										
									}
						%>
									
									<hr>
						<%
								}
									
							}								
							
						%>
<p class="left col-md-5">
                        <a href="AdminSummaryController" class="btn btn-primary">Summary</a>
                   </p> 

    		</div>
    		</div>
        		  
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

