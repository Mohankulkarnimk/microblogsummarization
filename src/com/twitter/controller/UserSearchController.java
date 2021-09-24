package com.twitter.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class UserSearchController
 */
@WebServlet("/UserSearchController")
public class UserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchController() {
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
		String name = request.getParameter("searchname");
		int userId = Integer.parseInt(request.getParameter("userId"));
		if(name!=null && name!=""){
			UserService userService = new UserServiceImpl();
			ArrayList<User> listUser = userService.searchUser(name,userId);
			HttpSession session = request.getSession();
			
			if(listUser.size()!=0){			
				session.setAttribute("friendlist", listUser);
				RequestDispatcher rd = request.getRequestDispatcher("user_search.jsp");
				rd.include(request, response);
				
			} else {
				session.setAttribute("ErrMsgRecord", "No record found");
				RequestDispatcher rd = request.getRequestDispatcher("user_search.jsp");
				rd.include(request, response);
					
			}
			
		}else{
			request.setAttribute("ErrMsg", "Please enter name");
			RequestDispatcher rd = request.getRequestDispatcher("user_search.jsp");
			rd.include(request, response);
		}
	}

}
