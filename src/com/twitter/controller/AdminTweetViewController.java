package com.twitter.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twitter.services.UserService;
import com.twitter.services.UserServiceImpl;

/**
 * Servlet implementation class AdminTweetViewController
 */
@WebServlet("/AdminTweetViewController")
public class AdminTweetViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTweetViewController() {
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
		UserService userService = new UserServiceImpl();
		ResultSet rs = userService.selectTweet();
		if(rs!=null){
			HttpSession session = request.getSession();
			session.setAttribute("TweetData", rs);
			RequestDispatcher rd = request.getRequestDispatcher("admin_viewtweet.jsp");
			rd.forward(request, response);
		}else{
			request.setAttribute("ErrMsg", "No One Tweet Record Here");
			RequestDispatcher rd = request.getRequestDispatcher("admin_viewtweet.jsp");
			rd.forward(request, response);
		}
	}

}
