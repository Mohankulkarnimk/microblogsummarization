package com.twitter.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twitter.bean.Follower;
import com.twitter.services.UserService;
import com.twitter.services.UserServiceImpl;

/**
 * Servlet implementation class UserFollowerController
 */
@WebServlet("/UserFollowerController")
public class UserFollowerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFollowerController() {
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
		int userId=Integer.parseInt(request.getParameter("userId"));
		UserService userService = new UserServiceImpl();
		ArrayList<Follower> followerList = userService.selectFollowers(userId);
		if(followerList!=null && followerList.size()>0){
			request.setAttribute("followerList", followerList);
			request.setAttribute("Count", followerList.size());
			RequestDispatcher rd = request.getRequestDispatcher("user_listfollowers.jsp");
			rd.forward(request, response);
		}else{
			request.setAttribute("ErrMsg", "Ther is no one follower");
			request.setAttribute("Count", "0");
			RequestDispatcher rd = request.getRequestDispatcher("user_listfollowers.jsp");
			rd.forward(request, response);
		}
	}

}
