package com.alpargabos.wagtail;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class Wagtail {

    public static final String CONSUMER_KEY = "q9Vhuh8DwgnIG99ULDcS3g";
    public static final String CONSUMER_SECRET = "1evGkZGOympVOmoHDvQLxkbnWCU5PgrBU8oFrTAZw";
    private Twitter twitter;
    private Ui ui;

    public Wagtail() {
        ui = new Ui();
        Configuration conf = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .build();
        twitter = new TwitterFactory(conf).getInstance();
    }

    public void login() throws TwitterException, IOException {
        RequestToken requestToken = twitter.getOAuthRequestToken();
        AccessToken accessToken = getPersistedAccessToken();
        while (null == accessToken) {
            String pin = ui.acquirePinCodeFor(requestToken.getAuthorizationURL());
            try {
                accessToken = twitter.getOAuthAccessToken(requestToken, pin);
            } catch (TwitterException te) {
                if (401 == te.getStatusCode()) {
                    ui.warnUser("Unable to get the access token.");
                }
            }
        }
        twitter.setOAuthAccessToken(accessToken);
        persistAccessToken(accessToken);
        ui.welcomeUser(twitter.getScreenName());
    }

    protected void persistAccessToken(AccessToken accessToken) {
        Store.persistsData(accessToken);
    }

    protected AccessToken getPersistedAccessToken() {
        return Store.getPersistedAccessToken();
    }

    public void printHomeLine() throws TwitterException, IOException {
        List<Status> statuses = twitter.getHomeTimeline();
        ui.showTimeLine(statuses);
    }

    public void writeStatus() throws TwitterException {
        String status = ui.acquireNewStatus();
        Status result = twitter.updateStatus(status);
        ui.showStatus(result);
    }

    public void searchTweets(String expression) throws TwitterException {
        Query query = new Query(expression);
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            ui.showStatus(status);
        }
    }

    public void deleteTweet() throws TwitterException {
        Long id = ui.acquireTweetIdForDeletion();
        twitter.destroyStatus(id);
        ui.warnUser("Successfully deleted status [" + id + "].");
    }

    public void runInReactiveMode(){
        //ui
    }


    void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public void setUserInputSource(Reader reader){
        ui.reader = reader;
    }

    public void setOutput(OutputStream output) {
        ui.printer = new Printer(new PrintWriter(output));
    }

    public void setInput(Reader input) {
        ui.reader = input;
    }

    public void setUI(Ui ui) {
        this.ui = ui;
    }
}
