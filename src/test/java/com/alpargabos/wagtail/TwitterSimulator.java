package com.alpargabos.wagtail;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;

public class TwitterSimulator {
    private Twitter twitter;


    public TwitterSimulator() {
    }
    /*
    public Twitter getTwitterForHomeTimeLine() {
        server.enqueue(new MockResponse().setBody(getHomeTimelineJson()));
        try {
            server.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cb.setRestBaseURL(server.getUrl("/twitter/").toString());
        return new TwitterFactory(cb.build()).getInstance();
    } */

    private String getHomeTimelineJson(){
        return "[{\"contributors\":null,\"text\":\"Want to help fight corruption and end anonymous companies? Here's what you can do: http://t.co/84tyvA1IRn cc @TEDprize @charmian\",\"geo\":null,\"retweeted\":false,\"in_reply_to_screen_name\":null,\"possibly_sensitive\":false,\"truncated\":false,\"lang\":\"en\",\"entities\":{\"symbols\":[],\"urls\":[{\"expanded_url\":\"http://on.ted.com/plk3\",\"indices\":[83,105],\"display_url\":\"on.ted.com/plk3\",\"url\":\"http://t.co/84tyvA1IRn\"}],\"hashtags\":[],\"user_mentions\":[{\"id\":16204225,\"name\":\"TED Prize\",\"indices\":[109,118],\"screen_name\":\"tedprize\",\"id_str\":\"16204225\"},{\"id\":2317100748,\"name\":\"Charmian Gooch\",\"indices\":[119,128],\"screen_name\":\"charmian\",\"id_str\":\"2317100748\"}]},\"in_reply_to_status_id_str\":null,\"id\":449661478803877888,\"source\":\"<a href=\\\"http://www.hootsuite.com\\\" rel=\\\"nofollow\\\">HootSuite<\\/a>\",\"in_reply_to_user_id_str\":null,\"favorited\":false,\"in_reply_to_status_id\":null,\"retweet_count\":56,\"created_at\":\"Fri Mar 28 21:37:05 +0000 2014\",\"in_reply_to_user_id\":null,\"favorite_count\":32,\"id_str\":\"449661478803877888\",\"place\":null,\"user\":{\"location\":\"New York\",\"default_profile\":false,\"profile_background_tile\":false,\"statuses_count\":2378,\"lang\":\"en\",\"profile_link_color\":\"FF2B06\",\"id\":15492359,\"following\":true,\"protected\":false,\"favourites_count\":766,\"profile_text_color\":\"333333\",\"description\":\"The official feed for http://t.co/XyGsGk6F0m. Ideas worth spreading.\",\"verified\":true,\"contributors_enabled\":false,\"profile_sidebar_border_color\":\"FFFFFF\",\"name\":\"TED Talks\",\"profile_background_color\":\"000000\",\"created_at\":\"Sat Jul 19 13:22:50 +0000 2008\",\"is_translation_enabled\":false,\"default_profile_image\":false,\"followers_count\":2525320,\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/3278476079/cb1f8cb5c856e6b5dfb9f79558b5728c_normal.png\",\"geo_enabled\":false,\"profile_background_image_url\":\"http://pbs.twimg.com/profile_background_images/795046705/a71fe1893e3a1561abba1bff053e9328.gif\",\"profile_background_image_url_https\":\"https://pbs.twimg.com/profile_background_images/795046705/a71fe1893e3a1561abba1bff053e9328.gif\",\"follow_request_sent\":false,\"entities\":{\"description\":{\"urls\":[{\"expanded_url\":\"http://TED.com\",\"indices\":[22,44],\"display_url\":\"TED.com\",\"url\":\"http://t.co/XyGsGk6F0m\"}]},\"url\":{\"urls\":[{\"expanded_url\":\"http://www.ted.com\",\"indices\":[0,22],\"display_url\":\"ted.com\",\"url\":\"http://t.co/7qVI5vqFrr\"}]}},\"url\":\"http://t.co/7qVI5vqFrr\",\"utc_offset\":-14400,\"time_zone\":\"Eastern Time (US & Canada)\",\"notifications\":false,\"profile_use_background_image\":true,\"friends_count\":213,\"profile_sidebar_fill_color\":\"E0E3DE\",\"screen_name\":\"TEDTalks\",\"id_str\":\"15492359\",\"profile_image_url\":\"http://pbs.twimg.com/profile_images/3278476079/cb1f8cb5c856e6b5dfb9f79558b5728c_normal.png\",\"listed_count\":37339,\"is_translator\":false},\"coordinates\":null}," +
                "{\"contributors\":null,\"text\":\"So what happened This Week @NASA? A Congressional hearing on NASA's budget, the Angry Nerd robot and more! [video] http://t.co/cm2PmTTfPP\",\"geo\":null,\"retweeted\":false,\"in_reply_to_screen_name\":null,\"possibly_sensitive\":false,\"truncated\":false,\"lang\":\"en\",\"entities\":{\"symbols\":[],\"urls\":[{\"expanded_url\":\"http://go.nasa.gov/1g87aqp\",\"indices\":[115,137],\"display_url\":\"go.nasa.gov/1g87aqp\",\"url\":\"http://t.co/cm2PmTTfPP\"}],\"hashtags\":[],\"user_mentions\":[{\"id\":11348282,\"name\":\"NASA\",\"indices\":[27,32],\"screen_name\":\"NASA\",\"id_str\":\"11348282\"}]},\"in_reply_to_status_id_str\":null,\"id\":449652196284456961,\"source\":\"<a href=\\\"http://www.exacttarget.com/social\\\" rel=\\\"nofollow\\\">SocialEngage<\\/a>\",\"in_reply_to_user_id_str\":null,\"favorited\":false,\"in_reply_to_status_id\":null,\"retweet_count\":42,\"created_at\":\"Fri Mar 28 21:00:12 +0000 2014\",\"in_reply_to_user_id\":null,\"favorite_count\":42,\"id_str\":\"449652196284456961\",\"place\":null,\"user\":{\"location\":\"\",\"default_profile\":false,\"profile_background_tile\":false,\"statuses_count\":29872,\"lang\":\"en\",\"profile_link_color\":\"55648C\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/11348282/1395856410\",\"id\":11348282,\"following\":true,\"protected\":false,\"favourites_count\":115,\"profile_text_color\":\"000000\",\"description\":\"Explore the universe and discover our home planet with @NASA. We usually post in EDT (UTC-4).\",\"verified\":true,\"contributors_enabled\":false,\"profile_sidebar_border_color\":\"000000\",\"name\":\"NASA\",\"profile_background_color\":\"FFFFFF\",\"created_at\":\"Wed Dec 19 20:20:32 +0000 2007\",\"is_translation_enabled\":false,\"default_profile_image\":false,\"followers_count\":6196107,\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/188302352/nasalogo_twitter_normal.jpg\",\"geo_enabled\":true,\"profile_background_image_url\":\"http://pbs.twimg.com/profile_background_images/378800000070768280/1e9d3d155ba7cb623d541c764c5ef9c0.jpeg\",\"profile_background_image_url_https\":\"https://pbs.twimg.com/profile_background_images/378800000070768280/1e9d3d155ba7cb623d541c764c5ef9c0.jpeg\",\"follow_request_sent\":false,\"entities\":{\"description\":{\"urls\":[]},\"url\":{\"urls\":[{\"expanded_url\":\"http://www.nasa.gov\",\"indices\":[0,22],\"display_url\":\"nasa.gov\",\"url\":\"http://t.co/TcEE6OahBL\"}]}},\"url\":\"http://t.co/TcEE6OahBL\",\"utc_offset\":-14400,\"time_zone\":\"Eastern Time (US & Canada)\",\"notifications\":false,\"profile_use_background_image\":true,\"friends_count\":211,\"profile_sidebar_fill_color\":\"F3F2F2\",\"screen_name\":\"NASA\",\"id_str\":\"11348282\",\"profile_image_url\":\"http://pbs.twimg.com/profile_images/188302352/nasalogo_twitter_normal.jpg\",\"listed_count\":64378,\"is_translator\":false},\"coordinates\":null}]";
    }

    public Twitter getTwitterForLogin(String username) {
        ConfigurationBuilder cb = getConfigurationBuilder();
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(getRequestToken()));
        server.enqueue(new MockResponse().setBody(getAuthToken(username)));
        server.enqueue(new MockResponse().setBody(getLoginJsonWithUsername(username)));
        return createTwitter(cb, server);
    }

    private String getRequestToken() {
        return "oauth_token=mc9kRRv8z05KkDX9AJxPvQ6wRucCMvlBU1liC987s0&oauth_token_secret=qQN6oRLaxpvevJT8kvqo5IPS7JQnWekco4KPVYnLls&oauth_callback_confirmed=true";
    }

    private String getAuthToken(String username){
        return "oauth_token=2405056022-zWhCwEGhvIyr5WKQbOnbH9gxsDmcUBGxxnhGI6s&oauth_token_secret=bcqSUSBZvDbSmVvfUHzCA5cI05BnVBvOe3WM7wz085M1b&user_id=2405056022&screen_name="+username;
    }

    public Twitter getTwitterForInvalidLogin() {
        ConfigurationBuilder cb = getConfigurationBuilder();
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(401));
        return createTwitter(cb, server);
    }

    private Twitter createTwitter(ConfigurationBuilder cb, MockWebServer server) {
        try {
            server.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cb.setRestBaseURL(server.getUrl("/twitter/RestBaseURL/").toString());
        cb.setOAuthRequestTokenURL(server.getUrl("/twitter/OAuthRequestTokenURL/").toString());
        cb.setOAuthAccessTokenURL(server.getUrl("/twitter/OAuthAccessTokenURL/").toString());
        cb.setOAuthAuthenticationURL(server.getUrl("/twitter/OAuthAuthenticationURL/").toString());
        cb.setOAuthAuthorizationURL(server.getUrl("/twitter/OAuthAuthorizationURL/").toString());
        cb.setOAuth2InvalidateTokenURL(server.getUrl("/twitter/OAuth2InvalidateTokenURL/").toString());
        cb.setOAuth2TokenURL(server.getUrl("/twitter/OAuth2TokenURL/").toString());
        return new TwitterFactory(cb.build()).getInstance();
    }

    private ConfigurationBuilder getConfigurationBuilder() {
        ConfigurationBuilder confBuilder = new ConfigurationBuilder();
        confBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(Wagtail.CONSUMER_KEY)
                .setOAuthConsumerSecret(Wagtail.CONSUMER_SECRET);
        return confBuilder;
    }

    private String getLoginJsonWithUsername(String username) {
        return "{\"location\":\"\",\"default_profile\":true,\"profile_background_tile\":false,\"statuses_count\":1,\"lang\":\"en\",\"profile_link_color\":\"0084B4\",\"id\":2405056022,\"following\":false,\"protected\":false,\"favourites_count\":0,\"profile_text_color\":\"333333\",\"description\":\"wagtail, twitter console app, great, useful, made with BDD\",\"verified\":false,\"contributors_enabled\":false,\"profile_sidebar_border_color\":\"C0DEED\",\"name\":\"Wagtail\",\"profile_background_color\":\"C0DEED\",\"created_at\":\"Sat Mar 22 21:18:59 +0000 2014\",\"is_translation_enabled\":false,\"default_profile_image\":false,\"followers_count\":0,\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/447485222125199360/_T9H7blD_normal.jpeg\",\"geo_enabled\":false,\"status\":{\"contributors\":null,\"text\":\"my very first tweet\",\"geo\":null,\"retweeted\":false,\"in_reply_to_screen_name\":null,\"truncated\":false,\"lang\":\"en\",\"entities\":{\"symbols\":[],\"urls\":[],\"hashtags\":[],\"user_mentions\":[]},\"in_reply_to_status_id_str\":null,\"id\":447486650495827970,\"source\":\"web\",\"in_reply_to_user_id_str\":null,\"favorited\":false,\"in_reply_to_status_id\":null,\"retweet_count\":0,\"created_at\":\"Sat Mar 22 21:35:06 +0000 2014\",\"in_reply_to_user_id\":null,\"favorite_count\":0,\"id_str\":\"447486650495827970\",\"place\":null,\"coordinates\":null},\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/bg.png\",\"follow_request_sent\":false,\"entities\":{\"description\":{\"urls\":[]}},\"url\":null,\"utc_offset\":7200,\"time_zone\":\"Amsterdam\",\"notifications\":false,\"profile_use_background_image\":true,\"friends_count\":16,\"profile_sidebar_fill_color\":\"DDEEF6\",\"screen_name\":\""+username+"\",\"id_str\":\"2405056022\",\"profile_image_url\":\"http://pbs.twimg.com/profile_images/447485222125199360/_T9H7blD_normal.jpeg\",\"listed_count\":0,\"is_translator\":false}";
    }
}
