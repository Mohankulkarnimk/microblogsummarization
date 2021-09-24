package com.twitter.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.twitter.bean.Follower;
import com.twitter.bean.Tweet;
import com.twitter.bean.User;

public interface UserDao {
	boolean createUser(User user);
	boolean isAlreadyAvailable(User user);
	User selectUser(String email,String password);
	User selectUser(String email);
	boolean updateUser(String oldPassword, String newPassword, String email);
	ResultSet selectUser();
	boolean updateUser(int userId, String status);
	User selectUser(int userId);
	boolean createTweet(Tweet twit);
	ResultSet selectTweet();
	ArrayList<User> searchUser(String name, int userId);
	boolean sendRequest(Follower follower);
	ArrayList<Follower> selectFollowers(int userId);
	ArrayList<Follower> selectFollowings(int userId);
}
