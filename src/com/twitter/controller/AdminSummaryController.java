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

import com.twitter.bean.RealTweetStream;
import com.twitter.model.TextSummarizer;

/**
 * Servlet implementation class AdminSummaryController
 */
@WebServlet("/AdminSummaryController")
public class AdminSummaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSummaryController() {
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
		HttpSession session=request.getSession();
		ArrayList<RealTweetStream> twitList=(ArrayList<RealTweetStream>)session.getAttribute("RealTweetStream");
		String msg="";
		if(twitList!=null){
			for(int i=0;i<twitList.size();i++){
				msg=msg+twitList.get(i).getTweetContent()+"\n";
			}
			TextSummarizer sum=new TextSummarizer(msg);
			String summary=sum.summarize(1);
			session.setAttribute("FinalSummary", summary);
			RequestDispatcher rd=request.getRequestDispatcher("admin_summary.jsp");
			rd.forward(request, response);
		}else{
			request.setAttribute("ErrMSg", "Please try again");
			RequestDispatcher rd=request.getRequestDispatcher("admin_summary.jsp");
			rd.forward(request, response);
		}
		
	
	}

}
