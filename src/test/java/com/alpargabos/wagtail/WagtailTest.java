package com.alpargabos.wagtail;

import org.junit.Before;
import org.junit.Test;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class WagtailTest {
    Wagtail wagtail;
    Twitter twitter;
    Ui ui;

    @Before
    public void setUp(){
        wagtail = new Wagtail(){
            @Override
            protected void persistAccessToken(AccessToken accessToken) {
            }
            @Override
            protected AccessToken getPersistedAccessToken() {
                return null;
            }
        };
        ui = mock(Ui.class);
        twitter = mock(Twitter.class);
        wagtail.setUI(ui);
        wagtail.setTwitter(twitter);
    }

    @Test
    public void login() throws Exception {
        //given
        when(twitter.getOAuthRequestToken()).thenReturn(new RequestToken("",""));
        when(ui.acquirePinCodeFor(anyString())).thenReturn("123");
        when(twitter.getOAuthAccessToken(any(RequestToken.class), anyString())).thenReturn(new AccessToken("",""));
        //when
        wagtail.login();
        //then
        verify(ui).welcomeUser(anyString());
        verify(ui, times(0)).warnUser(anyString());
    }

    @Test
    public void printHomeLineAsksUiToShowIt() throws Exception {
        //given
        ResponseList<Status> statuses  = mock(ResponseList.class);
        when(twitter.getHomeTimeline()).thenReturn(statuses);
        //when
        wagtail.printHomeLine();
        //then
        verify(ui).showTimeLine(statuses);
    }

    @Test
    public void writeStatusSendsTheCorrectStatusToTwitter() throws Exception {
        //given
        when(ui.acquireNewStatus()).thenReturn("hello");
        Status status = mock(Status.class);
        when(twitter.updateStatus("hello")).thenReturn(status);
        //when
        wagtail.writeStatus();
        //then
        verify(twitter).updateStatus("hello");
        verify(ui).showStatus(status);
    }

    @Test
    public void searchTweetsWithTheGivenExpression() throws Exception {
        //given
        QueryResult result = mock(QueryResult.class);
        Status status1 = mock(Status.class);
        Status status2 = mock(Status.class);
        when(result.getTweets()).thenReturn(Arrays.asList(status1, status2));
        when(twitter.search(any(Query.class))).thenReturn(result);
        //when
        wagtail.searchTweets("accu");
        //then
        verify(ui).showStatus(status1);
        verify(ui).showStatus(status2);
    }

    @Test
    public void deleteTweetWithTheGivenId() throws Exception {
        //given
        when(ui.acquireTweetIdForDeletion()).thenReturn(21l);
        //when
        wagtail.deleteTweet();
        //then
        verify(twitter).destroyStatus(21l);
        verify(ui).warnUser(anyString());
    }

}
