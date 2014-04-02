package com.alpargabos.wagtail;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AuthenticationStepdefs extends  BaseStepDefs{
    public static final String PIN_CODE = "1234567";

    @Before
    public void beforeScenario(){
        initWagtail();
    }

    @After
    public void tearDown(){
        new File(Store.CONFIG_FILE).delete();
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
        wagtail.setTwitter(twitterSimulator.getTwitterForInvalidLogin());
        when(input.getUserInput()).thenReturn("7654321");
        wagtail.login();
    }


    @Given("^I am an authenticated user who closed the application$")
    public void I_am_an_authenticated_user_who_closed_the_application() throws Throwable {
        I_am_a_twitter_user();
        I_grant_access_to_my_account_for_Wagtail();
    }


    @When("^I start the application again$")
    public void I_start_the_application_again() throws Throwable {
        initWagtail();
        when(input.getUserInput()).thenReturn(PIN_CODE);
        wagtail.login();
    }

    @Given("^I am an authenticated user who logs out$")
    public void I_am_an_authenticated_user_who_logs_out() throws Throwable {
        I_am_a_twitter_user();
        I_grant_access_to_my_account_for_Wagtail();
        wagtail.logout();

    }

    @Then("^I will be notified about the unsuccessful login$")
    public void I_will_be_notified_about_the_unsuccessful_login() throws Throwable {
        assertTrue(output.toString().contains("Unable to get the access token"));
    }

    @Then("^I will be asked to grant access to my twitter account$")
    public void I_will_be_asked_to_grant_access_to_my_twitter_account() throws Throwable {
        assertTrue(output.toString().contains("Unable to get the access token"));
    }
}
