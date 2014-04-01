package com.alpargabos.wagtail;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationStepdefs {
    public static final String PIN_CODE = "1234567";
    String fullName;
    Wagtail wagtail;
    TwitterSimulator twitterSimulator;
    OutputStream output;
    Reader input;

    @Before
    public void beforeScenario(){
        wagtail = new Wagtail();
        output = new OutputStream() {
            StringBuilder content = new StringBuilder();

            @Override
            public void write(int i) throws IOException {
                System.out.print((char) i);
                content.append((char) i);
            }

            public String toString() {
                return content.toString();
            }
        };
        input = Mockito.mock(Reader.class);
        wagtail.setInput(input);
        wagtail.setOutput(output);
        wagtail.setStore(mock(Store.class));
        twitterSimulator = new TwitterSimulator();
    }

    @Given("^I am a twitter user")
    public void I_am_a_twitter_user() throws Throwable {
        fullName = "wagtailbirdapp";
    }

    @When("^I grant access to my account for Wagtail$")
    public void I_grant_access_to_my_account_for_Wagtail() throws Throwable {
        wagtail.setTwitter(twitterSimulator.getTwitterForLogin(fullName));
        when(input.getUserInput()).thenReturn(PIN_CODE);
        wagtail.login();
    }

    @Then("^I will be greeted on my full name$")
    public void I_will_be_greeted_on_my_full_name() throws Throwable {
        assertTrue(output.toString().contains("You are logged in as: " + fullName));
    }

    @When("^I don't grant access to my account for Wagtail$")
    public void I_don_t_grant_access_to_my_account_for_Wagtail() throws Throwable {
        //wagtail.setTwitter(twitterSimulator.getTwitterForInvalidLogin());
        //when(input.getUserInput()).thenReturn("7654321");
        //wagtail.login();
    }

    @Then("^I will be asked to grant access to my twitter account$")
    public void I_will_be_asked_to_grant_access_to_my_twitter_account() throws Throwable {
        assertThat(output.toString(), is(contains("Enter the PIN")));
    }
}
