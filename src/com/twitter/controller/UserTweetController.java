package com.twitter.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.twitter.bean.Tweet;
import com.twitter.bean.User;
import com.twitter.dao.UserDao;
import com.twitter.services.UserService;
import com.twitter.services.UserServiceImpl;

/**
 * Servlet implementation class UserTweetController
 */
@WebServlet("/UserTweetController")
@MultipartConfig(maxFileSize=1024*1024*50)
public class UserTweetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTweetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message = request.getParameter("message");
		int userId = Integer.parseInt(request.getParameter("userId"));
		Part part=request.getPart("uploadImg");
		InputStream uploadImg=null;		
		String uploadName="";
		if(part!=null)
		{
			uploadImg=part.getInputStream();
			System.out.println("is size:"+uploadImg.available());
			uploadName=extractFileName(part);
			System.out.println("name:"+uploadName);
		}
		User user= new User();
		user.setUserId(userId);
		Tweet twit=new Tweet();
		twit.setMessage(message);
		twit.setUploadImg(uploadImg);
		twit.setUploadName(uploadName);
		twit.setTweetdate(new java.sql.Timestamp(new java.util.Date().getTime()));
		twit.setUser(user);
		UserService userService = new UserServiceImpl();
		
		if(userService.createTweet(twit)){
			HttpSession session = request.getSession();
			session.setAttribute("SucMsg","Your tweet send successfully");
			RequestDispatcher rd = request.getRequestDispatcher("tweet.jsp");
			//request.setAttribute("ErrMsg", "Your account is not registered. Please try again!...");
			rd.include(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("tweet.jsp");
			request.setAttribute("ErrMsg", "Your tweet is not send. Please try again!...");
			rd.include(request, response);
		}
		
	}
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("contentDisp:"+contentDisp);
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}
