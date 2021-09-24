
<%@ page import="java.sql.*,java.io.*,java.util.*" %> 

<%@ page trimDirectiveWhitespaces="true" %>
<%
		ResultSet rs = (ResultSet)session.getAttribute("TweetData");
		int id= rs.getInt(9);
		InputStream is = rs.getBinaryStream(8);
	//int id=Integer.parseInt(id1);
		byte[] profilePic = null;
   			//OutputStream out = response.getOutputStream();
   		byte[] buffer = new byte[1024*1024*50];
		int bytesRead;		
		if(is!=null){
			while ((bytesRead =is.read(buffer)) != -1)
			{
			    	response.reset();
					response.setContentType("image/jpg");
					response.setHeader("Content-disposition","attachment; filename=" );
			        response.getOutputStream().write(buffer, 0, bytesRead);
			        response.getOutputStream().flush(); 

			 }
		}		

%>