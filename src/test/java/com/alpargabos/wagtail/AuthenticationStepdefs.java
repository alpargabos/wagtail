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
                content.append((char) i);
            }

            public String toString() {
                return content.toString();
            }
        };
        input = Mockito.mock(Reader.class);
        wagtail.setInput(input);
        wagtail.setOutput(output);
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
        assertThat(output.toString(), is("You are logged in as: "+ fullName));
    }

}
