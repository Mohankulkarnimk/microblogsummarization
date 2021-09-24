package com.twitter.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.twitter.bean.Follower;
import com.twitter.bean.Tweet;
import com.twitter.bean.User;
import com.twitter.connection.ConnectionUtils;

public class UserDaoImpl implements UserDao{
	
	@Override
	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql = "Insert into tblUser values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, user.getFname());
			pstmt.setString(3, user.getLname());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPassword());
			pstmt.setDate(6, new Date(user.getDob().getTime()));
			pstmt.setString(7, user.getGender());
			pstmt.setString(8, user.getAddress());
			pstmt.setString(9, user.getContact());
			pstmt.setBlob(10, user.getProfilePic());
			pstmt.setString(11, user.getProfileName());
			pstmt.setString(12, user.getStatus());
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
	public boolean isAlreadyAvailable(User user) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql = "Select * from tblUser where emailid ='"+user.getEmail()+"'";
		try {
			Statement stmt = ConnectionUtils.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User selectUser(String email, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		String sql = "Select * from tblUser where emailid ='"+email.toLowerCase()+"' and password='"+password+"'";
		try {
			Statement stmt = ConnectionUtils.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				user.setUserId(rs.getInt(1));
				user.setFname(rs.getString(2));
				user.setLname(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setDob(rs.getDate(6));
				user.setGender(rs.getString(7));
				user.setAddress(rs.getString(8));
				user.setContact(rs.getString(9));
				user.setProfilePic(rs.getBinaryStream(10));
				user.setProfileName(rs.getString(11));
				user.setStatus(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User selectUser(String email) {
		// TODO Auto-generated method stub
		User user = new User();
		String sql = "Select * from tblUser where emailid ='"+email.toLowerCase()+"'";
		try {
			Statement stmt = ConnectionUtils.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				user.setUserId(rs.getInt(1));
				user.setFname(rs.getString(2));
				user.setLname(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setDob(rs.getDate(6));
				user.setGender(rs.getString(7));
				user.setAddress(rs.getString(8));
				user.setContact(rs.getString(9));
				user.setProfilePic(rs.getBinaryStream(10));
				user.setProfileName(rs.getString(11));
				user.setStatus(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean updateUser(String oldPassword, String newPassword, String email) {
		// TODO Auto-generated method stub
		boolean flag=false;
		
		String sql = "Update tblUser set password=? where password=? and emailid=?";
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
	public ResultSet selectUser() {
		// TODO Auto-generated method stub
		ResultSet rs= null;
		String sql ="Select * from tblUser";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}

	@Override
	public boolean updateUser(int userId, String status) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(status.equalsIgnoreCase("Inactive"))
			status="Active";
		else
			status = "Inactive";
		
		String sql = "Update tblUser Set status=? Where userid=?";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, userId);
			int index=pstmt.executeUpdate();
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
	public User selectUser(int userId) {
		// TODO Auto-generated method stub
		String sql ="Select * from tblUser Where userid=?";
		User user = new User();
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				user.setUserId(rs.getInt(1));
				user.setFname(rs.getString(2));
				user.setLname(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setDob(rs.getDate(6));
				user.setGender(rs.getString(7));
				user.setAddress(rs.getString(8));
				user.setContact(rs.getString(9));
				user.setProfilePic(rs.getBinaryStream(10));
				user.setProfileName(rs.getString(11));
				user.setStatus(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
	}

	@Override
	public boolean createTweet(Tweet twit) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql = "Insert into tblTweet values(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, twit.getMessage());
			pstmt.setBlob(3, twit.getUploadImg());
			pstmt.setString(4, twit.getUploadName());
			pstmt.setTimestamp(5, (Timestamp) twit.getTweetdate());
			pstmt.setInt(6, twit.getUser().getUserId());
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
	public ResultSet selectTweet() {
		// TODO Auto-generated method stub
		ResultSet rs= null;
		String sql ="SELECT t.tweetid, t.message, t.uploadimg, t.uploadname, t.datetime, u.firstname, u.lastname, u.profilepic,u.userid"
				+" From tbltweet t, tbluser u Where t.userid=u.userid order by t.datetime desc";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ArrayList<User> searchUser(String name, int userId) {
		// TODO Auto-generated method stub
		ArrayList<User> listUser = new ArrayList<User>();
		String sql="Select userid, firstname, lastname, profilepic, address From tblUser Where userid <> ? AND firstname like ? or lastname like ?";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, name +"%");
			pstmt.setString(3, name +"%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setFname(rs.getString(2));
				user.setLname(rs.getString(3));
				user.setProfilePic(rs.getBinaryStream(4));
				user.setAddress(rs.getString(5));
				listUser.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listUser;
	}

	@Override
	public boolean sendRequest(Follower follower) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "Insert Into tblFollower Values(?,?,?,?)";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, follower.getSender().getUserId());
			pstmt.setInt(3, follower.getReceiver().getUserId());
			pstmt.setTimestamp(4, new Timestamp(follower.getRequestDate().getTime()));
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
	public ArrayList<Follower> selectFollowers(int userId) {
		// TODO Auto-generated method stub
		ArrayList<Follower> followerList = new ArrayList<Follower>();
		String sql = "SELECT t.firstname, t.lastname, t.profilepic, t.address, t.emailid,f.userid,f.followerid "
				+" FROM tbluser t Inner Join tblfollower f ON t.userid=f.userid "
				+" WHERE f.followerid=?";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setFname(rs.getString(1));
				user.setLname(rs.getString(2));
				user.setProfilePic(rs.getBinaryStream(3));
				user.setAddress(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setUserId(rs.getInt(6));
				
				User user1 = new User();
				user1.setUserId(rs.getInt(7));
				
				Follower follower = new Follower();
				follower.setSender(user);
				follower.setReceiver(user1);
				followerList.add(follower);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return followerList;
	}

	@Override
	public ArrayList<Follower> selectFollowings(int userId) {
		// TODO Auto-generated method stub
		ArrayList<Follower> followingList = new ArrayList<Follower>();
		String sql = "SELECT t.firstname, t.lastname, t.profilepic, t.address, t.emailid,f.userid,f.followerid "
				+" FROM tbluser t Inner Join tblfollower f ON t.userid=f.followerid "
				+" WHERE f.userid=?";
		try {
			PreparedStatement pstmt = ConnectionUtils.getConnection().prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setFname(rs.getString(1));
				user.setLname(rs.getString(2));
				user.setProfilePic(rs.getBinaryStream(3));
				user.setAddress(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setUserId(rs.getInt(7));
				
				User user1 = new User();
				user1.setUserId(rs.getInt(6));
				
				Follower follower = new Follower();
				follower.setSender(user1);
				follower.setReceiver(user);
				followingList.add(follower);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return followingList;
	}
	
}
