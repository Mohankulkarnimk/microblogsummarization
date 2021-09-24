package com.twitter.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.ExtendedMediaEntity;
import twitter4j.MediaEntity;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.twitter.bean.RealTweetStream;
import com.twitter.services.AdminService;
import com.twitter.services.AdminServiceImpl;

/**
 * Servlet implementation class AdminRealTweetController
 */
@WebServlet("/AdminRealTweetController")
public class AdminRealTweetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRealTweetController() {
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
		String queryname=request.getParameter("query");
		ConfigurationBuilder cb = new ConfigurationBuilder();
		 cb.setDebugEnabled(true)
      /*   .setOAuthConsumerKey("k7PQSk89oOckA4o9l1oiEYr8D")
         .setOAuthConsumerSecret("yOYXs8ZIIQYPIkhUAX5282J3NwEcHFdYek9FnkNJoX5UifKB5j")
         .setOAuthAccessToken("848033061597986816-d3uOUswVRCpRAfrJxHMKXJe2GVrV622")
         .setOAuthAccessTokenSecret("EJ5pgOLUHBtJ1lqS1McaIeaA2CB3GSNWax9MY6vF5bLeO");
      cb.setDebugEnabled(true)
        .setOAuthConsumerKey("3nYp3H6ndFxV4HuqgzqMB6b6Q")
        .setOAuthConsumerSecret("enOKK2FyAzcYnMU8wtUEKkftOuWtEKEAkddekNAHfOsxGG3zOW")
        .setOAuthAccessToken("857571256761290752-NHOFAU6Oq9jRVDcEHwWt3frU178HAe5")
        .setOAuthAccessTokenSecret("MjYbRGENC1s6hZAUUSYrzA3x0e1Hhz5NUWejNUeDTW6Ic");*/
		 .setOAuthConsumerKey("SYJMtIx7IyALnfLVE11U5Diyh")
        .setOAuthConsumerSecret("TOTC40KvlRnDvNIy6WakKfTYlXowK2wFIKrPtWKwmras7WLIjO")
        .setOAuthAccessToken("1004960913726685184-dSPXjbAmPX64cZrBoSqwqnsRMmClsM")
        .setOAuthAccessTokenSecret("A0K9yFj5C3ZPtjTouTYxCtw4lsNcEay4zCQpLhcyQCuoi");

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter tw = tf.getInstance();
      //  Status st= tw.updateStatus("Welcome to twiiter world@@@@");
        System.out.println("Twitter updated");
        //System.out.println(st.getUser().getName()+":"+st.getText());
        ArrayList<RealTweetStream> realTweetList = new ArrayList<RealTweetStream>();
        
		try {
			 Query query = new Query(queryname);
			 int i = 0;
	         QueryResult result;
	         do {
	               result = tw.search(query);
	               List<Status> tweets = result.getTweets();
			
			
			for (Status status1 : tweets) {
				if (status1.getLang().equals("en")) {
					RealTweetStream real = new RealTweetStream();
					String cont = status1.getText();
					real.setUserName(status1.getUser().getName());
					real.setTweetContent(cont);					
					real.setTwitId(++i);
					System.out.println(i + ":" + status1.getUser().getName()
							+ "\t" + cont + "\t" + status1.getText().length());
					ExtendedMediaEntity[] extendedMediaEntities = status1
							.getExtendedMediaEntities();
					for (int i1 = 0; i1 < extendedMediaEntities.length; i1++) {
						ExtendedMediaEntity extendedMediaEntity = extendedMediaEntities[i1];
						ExtendedMediaEntity.Variant[] variant = extendedMediaEntity
								.getVideoVariants();
						if (extendedMediaEntity.getType().equals("video")) {
							for (int j = 0; j < variant.length; j++) {
								System.out.println("variant url: "
										+ variant[j].getUrl());
							}
							
							if(!variant[0].getUrl().contains(".m3u8")&&!variant[0].getUrl().contains(".m4u8")){
								String vurl=variant[0].getUrl();
								System.out.println("vurl====="+vurl);
								String su=vurl.substring(vurl.lastIndexOf("."));
								if(su.length()>4){
									vurl=vurl.substring(0,vurl.lastIndexOf("."));
									System.out.println(vurl);
									vurl=vurl+".mp4";
									real.setVideourl(vurl);
								}else{
									real.setVideourl(vurl);
								}
								
							}
							
							
							String url = extendedMediaEntity.getURL();
							System.out.println("extended url: "
									+ extendedMediaEntity.getExpandedURL());
						}
					}
					if (real.getVideourl()==null || real.getVideourl().equals("")) {
						MediaEntity[] media = status1.getMediaEntities(); // get
																			// the
																			// media
																			// entities
																			// from
																			// the
																			// status
						for (MediaEntity m : media) { // search trough your
														// entities
							System.out.println("Media::::" + m.getMediaURL()); // get
																				// your
																				// url!
							real.setImgurl(m.getMediaURL());
						}
					}

					realTweetList.add(real);
					
					System.out.println();
				}
			}
			if(i>100){
				break;
			}
	         } while ((query = result.nextQuery()) != null);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         if(realTweetList!=null && realTweetList.size()>0){
        	 AdminService adminService = new AdminServiceImpl();
        	 if(adminService.addRealStreamDataset(realTweetList)){        		
        		 HttpSession session = request.getSession();
        		 session.setAttribute("RealTweetStream", realTweetList);
        		 RequestDispatcher rd = request.getRequestDispatcher("admin_realtweetstream.jsp");
        		 rd.forward(request, response);
        	 }
         }else {
        	 request.setAttribute("ErrMsg", "Can not loaded real time tweets from Tweeter API");
        	 RequestDispatcher rd = request.getRequestDispatcher("admin_home.jsp");
    		 rd.forward(request, response);
		}
	}

}
