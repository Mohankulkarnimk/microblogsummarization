package com.twitter.services;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.twitter.bean.Follower;
import com.twitter.bean.Tweet;
import com.twitter.bean.User;

public interface UserService {
	boolean createUser(User user);
	boolean isAlreadyAvailable(User user);
	boolean updateUSer(String oldPassword,String newPassword,String email);
	User selectUser(String email, String Password);
	User selectUser(String email);
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
