package com.twitter.bean;

import java.io.InputStream;
import java.util.Date;

public class Tweet {
	private int tweetId;
	private String message;
	private InputStream uploadImg;
	private String uploadName;
	private Date tweetdate;
	private User user;
	
	
	public int getTweetId() {
		return tweetId;
	}
	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public InputStream getUploadImg() {
		return uploadImg;
	}
	public void setUploadImg(InputStream uploadImg) {
		this.uploadImg = uploadImg;
	}
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public Date getTweetdate() {
		return tweetdate;
	}
	public void setTweetdate(Date tweetdate) {
		this.tweetdate = tweetdate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}	
		
}
