package com.twitter.bean;

import java.util.ArrayList;

public class RealTweetStream {
	private int twitId;
	private String userName;
	private String tweetContent;
	private String imgurl;
	private String videourl;
	private String stemming;
	private ArrayList<String> topicKeywords;
	private ArrayList<Double> topicKeyProb;
	public int getTwitId() {
		return twitId;
	}
	public void setTwitId(int twitId) {
		this.twitId = twitId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTweetContent() {
		return tweetContent;
	}
	public void setTweetContent(String tweetContent) {
		this.tweetContent = tweetContent;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	public String getStemming() {
		return stemming;
	}
	public void setStemming(String stemming) {
		this.stemming = stemming;
	}
	public ArrayList<String> getTopicKeywords() {
		return topicKeywords;
	}
	public void setTopicKeywords(ArrayList<String> topicKeywords) {
		this.topicKeywords = topicKeywords;
	}
	public ArrayList<Double> getTopicKeyProb() {
		return topicKeyProb;
	}
	public void setTopicKeyProb(ArrayList<Double> topicKeyProb) {
		this.topicKeyProb = topicKeyProb;
	}
		
}
