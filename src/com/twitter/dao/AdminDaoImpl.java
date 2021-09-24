package com.twitter.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.twitter.bean.Admin;
import com.twitter.bean.RealTweetStream;
import com.twitter.bean.Tweet;
import com.twitter.connection.ConnectionUtils;

public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin selectAdmin(String email, String password) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		String sql = "Select * from tbladmin where emailid ='"+email.toLowerCase()+"' and password='"+password+"'";
		try {
			Statement stmt = ConnectionUtils.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				
				admin.setEmail(rs.getString(2));
				admin.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public boolean updateAdmin(String oldPassword, String newPassword,String email) {
		// TODO Auto-generated method stub
		boolean flag=false;
		
		String sql = "Update tbladmin set password=? where password=? and emailid=?";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, oldPassword);
			pstmt.setString(3, email);
			int index = pstmt.executeUpdate();
			if(index>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public ArrayList<Tweet> selectTweet() {
		// TODO Auto-generated method stub
		ArrayList<Tweet> twitList = new ArrayList<Tweet>();
		String sql = "Select * From tblTweet";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Tweet twit = new Tweet();
				twit.setTweetId(rs.getInt(1));
				twit.setMessage(rs.getString(2));
				twitList.add(twit);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return twitList;
	}

	@Override
	public boolean addRealStreamDataset(ArrayList<RealTweetStream> realTweetList) {
		// TODO Auto-generated method stub
		
		String sql1 = "Drop Table If Exists tblRealTwitStream";
		try {
			PreparedStatement pstmt1 = ConnectionUtils.getConnection().prepareStatement(sql1);
			boolean f1=pstmt1.execute();
			if(f1){
				System.out.println("Deleted....");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql2 = "CREATE TABLE  tblrealtwitstream (twitid int(10) unsigned NOT NULL AUTO_INCREMENT,"
				+" username varchar(100) NOT NULL, tweetcontent varchar(255) NOT NULL, imgurl varchar(255),"
				+" videourl varchar(255), PRIMARY KEY (twitid))";
		try {
			PreparedStatement pstmt2 = ConnectionUtils.getConnection().prepareStatement(sql2);
			boolean f2 = pstmt2.execute();
			if(f2){
				System.out.println("Created.....");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean flag=false;
		int count=0;
		for(int i=0;i<realTweetList.size();i++){
			String sql = "Insert Into tblRealTwitStream values(?,?,?,?,?)";
			try{
				PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
				pstmt.setInt(1, 0);
				pstmt.setString(2, realTweetList.get(i).getUserName());
				pstmt.setString(3, realTweetList.get(i).getTweetContent());
				pstmt.setString(4, realTweetList.get(i).getImgurl());
				pstmt.setString(5, realTweetList.get(i).getVideourl());
				int index=pstmt.executeUpdate();
				if(index>0){
					count++;
				}
				pstmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		if(count==realTweetList.size()){
			flag=true;
		}
		
		return flag;
	}
	
}
