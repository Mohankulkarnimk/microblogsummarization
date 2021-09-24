package com.twitter.model;


import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("SYJMtIx7IyALnfLVE11U5Diyh")
                .setOAuthConsumerSecret("TOTC40KvlRnDvNIy6WakKfTYlXowK2wFIKrPtWKwmras7WLIjO")
                .setOAuthAccessToken("1004960913726685184-dSPXjbAmPX64cZrBoSqwqnsRMmClsM")
                .setOAuthAccessTokenSecret("A0K9yFj5C3ZPtjTouTYxCtw4lsNcEay4zCQpLhcyQCuoi");
                //.setTweetModeExtended(true);
                
                
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Paging paging = new Paging(1, 200);
        List<Status> statuses;
        try {
			statuses = twitter.getHomeTimeline(paging);
			int i=0;
	         for(Status status1 : statuses){
	             if(status1.getLang().equals("en")){
	            	 String cont = status1.getText();
	            	 
	            	 String url12="";
	            	 System.out.println(i++ +":"+status1.getUser().getName()+"\t"+cont+"\t"+status1.getText().length());
	            	 ExtendedMediaEntity[] extendedMediaEntities = status1.getExtendedMediaEntities();
	                 for (int i1 = 0; i1 < extendedMediaEntities.length; i1++) {
	                     ExtendedMediaEntity extendedMediaEntity = extendedMediaEntities[i1];
	                     ExtendedMediaEntity.Variant[] variant = extendedMediaEntity.getVideoVariants();
	                     if (extendedMediaEntity.getType().equals("video")) {
	                         for (int j = 0; j < variant.length; j++) {
	                             System.out.println("variant url: " + variant[j].getUrl());
	                         }
	                         url12=variant[0].getUrl();
	                         String url = extendedMediaEntity.getURL();
	                         System.out.println("extended url: " + extendedMediaEntity.getExpandedURL());
	                     }
	                 }if (url12==null || url12.equals("")) {
	            	 MediaEntity[] media = status1.getMediaEntities(); //get the media entities from the status
	            	 for(MediaEntity m : media){ //search trough your entities
	            	     System.out.println("Media::::"+m.getMediaURL()); //get your url!
	            	 }
	                 }
	            	  
	             }
	         }
        }catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*Query query = new Query("#hashtag");
        QueryResult result = twitter.search(query);
        Pattern pattern = Pattern.compile("http://t.co/\\w{10}");
        Pattern imagePattern = Pattern.compile("https\\:\\/\\/pbs\\.twimg\\.com/media/\\w+\\.(png | jpg | gif)(:large)?");
        for (Status status : result.getTweets()) {
            if (status.isRetweet())
                continue;
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            Matcher matcher = pattern.matcher(status.getText());

                if (matcher.find()) {
                    System.out.println("found a t.co url");
                    URL oracle = new URL(matcher.group());
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(oracle.openStream()));

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        matcher = imagePattern.matcher(inputLine);

                        if (matcher.find())
                            System.out.println("YAYAAYAYAYYAYAYAYAYAYAYAYAYAAYAYYAYAAYYAYAYAYA: " + matcher.group());
                    }

                    in.close();

            }

        }*/
    }
}