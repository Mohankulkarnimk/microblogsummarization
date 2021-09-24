package com.twitter.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twitter.bean.RealTweetStream;
import com.twitter.model.DuplicateImageFinder;
import com.twitter.model.RunLDA;

/**
 * Servlet implementation class AdminSubtopicController
 */
@WebServlet("/AdminSubtopicController")
public class AdminSubtopicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSubtopicController() {
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
		HttpSession session = request.getSession();
		ArrayList<RealTweetStream> twitList=(ArrayList<RealTweetStream>)session.getAttribute("RealTweetStream");		
		HashMap<Integer, ArrayList<RealTweetStream>> clusterTweetList=(HashMap<Integer, ArrayList<RealTweetStream>>)session.getAttribute("ClusterTweetList");
		HashSet<Integer> clusterSet=(HashSet<Integer>)session.getAttribute("ClusterSet");

		RunLDA run = new RunLDA();
		
		if(clusterTweetList!=null){
			for(Entry<Integer, ArrayList<RealTweetStream>> entry: clusterTweetList.entrySet()){
				int key = entry.getKey();
				ArrayList<RealTweetStream> comList = clusterTweetList.get(key);	
				ArrayList<String> twList=new ArrayList<String>();
				for(int i=0;i<comList.size();i++){					
					for(int j=0;j<comList.size();j++){
						twList.add(comList.get(i).getTweetContent());
					}
				}
				ArrayList<ArrayList<String>> topicKeywords=run.comprehensiveLDATest1(twList.size(),twList);
				ArrayList<String> topkey=new ArrayList<String>();
		        ArrayList<Double> topProb=new ArrayList<Double>();
		        for(int i=0;i<comList.size();i++){
		        	for(int j=0;j<topicKeywords.get(i).size();j++){
		            	  String splitStr=topicKeywords.get(i).get(j);
		            	  String sStr[]=splitStr.split(":");
		            	  topkey.add(sStr[0]);
		            	  topProb.add(Double.parseDouble(sStr[1]));
		        	}
		        	comList.get(i).setTopicKeywords(topkey);
		        	comList.get(i).setTopicKeyProb(topProb);
		        }
		        clusterTweetList.put(key, comList);
			}
		}
		
        String dirPath="C:/Users/hp/Desktop/workspace/microblogsummarization/WebContent/compare";
        File fdir=new File(dirPath);
        if (fdir.isDirectory()) {
            for (File deleteMe: fdir.listFiles()) {
                // recursive delete
               deleteMe.delete();
            }
        }
        for(int i=0;i<twitList.size();i++){
        	if(twitList.get(i).getImgurl()!=null && !twitList.get(i).getImgurl().equals("")){
        		String imageUrl = twitList.get(i).getImgurl();
        		String destinationFile = dirPath+"/"+twitList.get(i).getTwitId()+".jpg";
        		saveImage(imageUrl, destinationFile);
        	}
        }
        DuplicateImageFinder dup =new DuplicateImageFinder();
        List<List<String>> imgList=DuplicateImageFinder.findDuplicatePairs(dirPath);
    	List<String> imglList2=DuplicateImageFinder.findAllUniqueImages(dirPath);
    	for(int i=0;i<imglList2.size();i++)
    	{    		
    		System.out.println("List="+imglList2.get(i));
    		
    	}
    	session.setAttribute("RealTweetStream", twitList);
    	session.setAttribute("ClusterTweetList", clusterTweetList);
		session.setAttribute("ClusterSet", clusterSet);
		session.setAttribute("ImageList", imglList2);
		RequestDispatcher rd = request.getRequestDispatcher("admin_subtopic.jsp");
		rd.forward(request, response);
        
	}
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
}

	
}
