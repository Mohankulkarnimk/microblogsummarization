package com.twitter.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twitter.bean.User;
import com.twitter.services.UserService;
import com.twitter.services.UserServiceImpl;

/**
 * Servlet implementation class UserViewController
 */
@WebServlet("/UserViewController")
public class UserViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserViewController() {
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
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserService userService = new UserServiceImpl();
		User user = userService.selectUser(userId);
		if(user!=null){
			HttpSession session = request.getSession();
			session.setAttribute("UserData", user);
			RequestDispatcher rd = request.getRequestDispatcher("admin_viewuser.jsp");
			rd.forward(request, response);
		}
		
	}

}
