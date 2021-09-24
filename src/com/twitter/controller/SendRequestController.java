package com.twitter.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twitter.bean.Follower;
import com.twitter.bean.User;
import com.twitter.services.UserService;
import com.twitter.services.UserServiceImpl;


/**
 * Servlet implementation class SendRequestController
 */
@WebServlet("/SendRequestController")
public class SendRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendRequestController() {
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
		int followerId = Integer.parseInt(request.getParameter("followerId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserService userService = new UserServiceImpl();
		User sender = new User();
		sender.setUserId(userId);
		
		User receiver = new User();
		receiver.setUserId(followerId);
		
		Follower follower = new Follower();
		follower.setSender(sender);
		follower.setReceiver(receiver);
		follower.setRequestDate(new Date());
		if(userService.sendRequest(follower)){
			HttpSession session = request.getSession();
			session.setAttribute("SucMsgRequest", "Following");
			session.setAttribute("followerId", follower.getReceiver().getUserId());
			RequestDispatcher rd = request.getRequestDispatcher("user_search.jsp");
			rd.include(request, response);
		}
	}

}
