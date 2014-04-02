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
    Store store;

    @Before
    public void setUp(){
        wagtail = new Wagtail(){
            int i = 0;
            @Override
            protected boolean isRunning(){
                return i++ < 1;
            }
        };
        ui = mock(Ui.class);
        twitter = mock(Twitter.class);
        wagtail.setUI(ui);
        wagtail.setTwitter(twitter);
        store = mock(Store.class);
        wagtail.setStore(store);
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
        when(ui.acquireSearchTerm()).thenReturn("accu");
        //when
        wagtail.searchTweets();
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

    @Test
    public void runInReactiveModeForHShowsHelp() throws Exception {
        //given
        when(ui.acquireUserAction()).thenReturn("h");
        //when
        wagtail.runInReactiveMode();
        //then
        verify(ui, times(2)).acquireUserAction();
    }

    @Test
    public void runInReactiveModeForNPostNewStatus() throws Exception {
        //given
        when(ui.acquireUserAction()).thenReturn("n");
        //when
        wagtail.runInReactiveMode();
        //then
        verify(ui).acquireNewStatus();
        verify(twitter).updateStatus(any(String.class));
    }

    @Test
    public void runInReactiveModeForDDeletesAStatus() throws Exception {
        //given
        when(ui.acquireUserAction()).thenReturn("d");
        //when
        wagtail.runInReactiveMode();
        //then
        verify(ui).acquireTweetIdForDeletion();
        verify(twitter).destroyStatus(any(Long.class));
    }

    @Test
    public void runInReactiveModeForTShowsHomeLine() throws Exception {
        //given
        when(ui.acquireUserAction()).thenReturn("t");
        //when
        wagtail.runInReactiveMode();
        //then
        verify(twitter).getHomeTimeline();
        verify(ui).showTimeLine(anyList());
    }

    @Test
    public void runInReactiveModeForSSearchesAmongsStatuses() throws Exception {
        //given
        when(ui.acquireUserAction()).thenReturn("s");
        when(twitter.search(any(Query.class))).thenReturn(mock(QueryResult.class));
        //when
        wagtail.runInReactiveMode();
        //then
        verify(ui).acquireSearchTerm();
        verify(twitter).search(any(Query.class));
    }

    @Test
    public void runInReactiveModeForXLogsUserOutFromTheApplication() throws Exception {
        //given
        when(ui.acquireUserAction()).thenReturn("x");
        //when
        wagtail.runInReactiveMode();
        //then
        verify(store).removePersistedData();
    }
}