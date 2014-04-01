package com.alpargabos.wagtail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import twitter4j.Status;
import twitter4j.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.*;

public class UiTest {

    Ui ui;

    @Before
    public void setUp() throws Exception {
        ui = new Ui();
        ui.printer = Mockito.mock(Printer.class);
        ui.reader = Mockito.mock(Reader.class);
    }

    @Test
    public void acquirePinCodeFor() throws Exception {
        //given
        when(ui.reader.getUserInput()).thenReturn("1234567");
        //when
        String pin = ui.acquirePinCodeFor("auth_url");
        //then
        assertThat("1234567", is(pin));
        verify(ui.printer).printAuthorizationRequest("auth_url");
    }

    @Test
    public void acquirePinCodeUntilAValidPinProvided() throws Exception {
        //given
        when(ui.reader.getUserInput()).thenReturn("123").thenReturn("1234567");
        //when
        String pin = ui.acquirePinCodeFor("auth_url");
        //then
        assertThat("1234567", is(pin));
        verify(ui.printer).printAuthorizationRequest("auth_url");
        verify(ui.printer).println(contains("valid PIN"));
    }

    @Test
    public void warnUserJustPrintsTheWarning() throws Exception {
        //when
        ui.warnUser("warning");
        //then
        verify(ui.printer).println("warning");
    }

    @Test
    public void welcomeUserWelcomesTheUserWithTheProvidedName() throws Exception {
        //when
        ui.welcomeUser("Alpar");
        //then
        verify(ui.printer).println(contains("Alpar"));
    }

    @Test
    public void showStatusPrintsOutTheImportantPartsOfTheStatus() throws Exception {
        //given
        Status status = createStatus(21L, "Alpar", "hello");
        //when
        ui.showStatus(status);
        //then
        verify(ui.printer).println(matches(".*21.*Alpar.*hello"));
    }

    @Test
    public void acquireNewStatusAsksTheUSerForANewTweet() throws Exception {
        //given
        when(ui.reader.getUserInput()).thenReturn("new tweet");
        //when
        String tweet = ui.acquireNewStatus();
        //then
        assertThat(tweet, is("new tweet"));
        verify(ui.printer).println(matches("^Please.*140 char.*"));
    }

    @Test
    public void acquireNewStatusAsksUserUntilItProvidesAvalidTweet() throws Exception {
        //given
        when(ui.reader.getUserInput())
                .thenReturn("This tweet is longer than 140 characters, which is not a valid tweet. orem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .thenReturn("valid tweet with less than 140 characters");
        //when
        String tweet = ui.acquireNewStatus();
        //then
        assertThat(tweet, is("valid tweet with less than 140 characters"));
        verify(ui.printer, times(2)).println(matches("^Please.*140 char.*"));
    }

    @Test
    public void acquireTweetIdForDeletion() throws Exception {
        //given
        when(ui.reader.getUserInput()).thenReturn("123");
        //when
        ui.acquireTweetIdForDeletion();
        //then
        verify(ui.printer).println(matches(".*id.*tweet.*"));
    }

    @Test
    public void acquireTweetIdForDeletionUntilAValidIdProvided() throws Exception {
        //given
        when(ui.reader.getUserInput())
                .thenReturn("not an id")
                .thenReturn("1234566");
        //when
        ui.acquireTweetIdForDeletion();
        //then
        verify(ui.printer, times(2)).println(matches(".*id.*tweet.*"));
    }

    @Test
    public void showTimeLineShowsTheLastXStatuses() throws Exception {
        //given
        List<Status> statuses = new ArrayList<Status>();
        statuses.add(createStatus(21L, "Alpar", "Hi"));
        statuses.add(createStatus(23L, "John", "Good morning"));
        //when
        ui.showTimeLine(statuses);
        //then
        verify(ui.printer).println(contains("time line"));
        verify(ui.printer).println(contains("last 2"));
        verify(ui.printer).println(matches(".*21.*Alpar.*Hi"));
        verify(ui.printer).println(matches(".*23.*John.*Good morning"));

    }

    private Status createStatus(Long id, String userName, String text) {
        Status status = mock(Status.class);
        when(status.getId()).thenReturn(id);
        User user = mock(User.class);
        when(user.getName()).thenReturn(userName);
        when(status.getUser()).thenReturn(user);
        when(status.getText()).thenReturn(text);
        return status;
    }

    @Test
    public void acquireUserAction() throws Exception {
        //given
        when(ui.reader.getUserInput()).thenReturn("h");
        //when
        String key = ui.acquireUserAction();
        //then
        assertThat(key, is("h"));
        verify(ui.printer).printControllKeys();
    }

    @Test
    public void acquireUserActionUntilAValidActionKeyProvided() throws Exception {
        //given
        when(ui.reader.getUserInput()).thenReturn("a").thenReturn("h");
        //when
        String key = ui.acquireUserAction();
        //then
        assertThat(key, is("h"));
        verify(ui.printer).printControllKeys();
        verify(ui.printer).println(matches(".*valid key.*"));
    }
}
