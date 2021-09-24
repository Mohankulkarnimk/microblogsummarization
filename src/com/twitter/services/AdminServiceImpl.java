package com.twitter.services;

import java.util.ArrayList;

import com.twitter.bean.Admin;
import com.twitter.bean.RealTweetStream;
import com.twitter.bean.Tweet;

import com.twitter.dao.AdminDao;
import com.twitter.dao.AdminDaoImpl;

public class AdminServiceImpl implements AdminService{
	AdminDao adminDao = new AdminDaoImpl();
	@Override
	public Admin selectAdmin(String email, String password) {
		// TODO Auto-generated method stub
		
		return adminDao.selectAdmin(email, password);
	}

	@Override
	public boolean updateAdmin(String oldPassword, String newPassword,String email) {
		// TODO Auto-generated method stub
		
		return adminDao.updateAdmin(oldPassword, newPassword, email);
	}

	@Override
	public ArrayList<Tweet> selectTweet() {
		// TODO Auto-generated method stub
		return adminDao.selectTweet();
	}

	
	@Override
	public boolean addRealStreamDataset(ArrayList<RealTweetStream> realTweetList) {
		// TODO Auto-generated method stub
		return adminDao.addRealStreamDataset(realTweetList);
	}
	
}
