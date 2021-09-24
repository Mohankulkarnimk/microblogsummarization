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
 * Servlet implementation class UserFollowingController
 */
@WebServlet("/UserFollowingController")
public class UserFollowingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFollowingController() {
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
		ArrayList<Follower> followingList = userService.selectFollowings(userId);
		if(followingList!=null && followingList.size()>0){
			request.setAttribute("followingList", followingList);
			request.setAttribute("Count", followingList.size());
			RequestDispatcher rd = request.getRequestDispatcher("user_listfollowings.jsp");
			rd.forward(request, response);
		}else{
			request.setAttribute("ErrMsg", "Ther is no one following");
			request.setAttribute("Count", "0");
			RequestDispatcher rd = request.getRequestDispatcher("user_listfollowings.jsp");
			rd.forward(request, response);
		}
	}

}
