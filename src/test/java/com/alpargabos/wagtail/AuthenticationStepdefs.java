package com.alpargabos.wagtail;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class AuthenticationStepdefs {
    User user;
    Wagtail wagtail;

    @Given("^I am a twitter user$")
    public void I_am_a_twitter_user() throws Throwable {
        user = new User("wagtail", "WhiteBlackBird");
    }

    @When("^I provide my credentials$")
    public void I_provide_my_credentials() throws Throwable {
        wagtail = new Wagtail(user);
        wagtail.login();
    }

    @Then("^I will be logged in to my twitter account from command line$")
    public void I_will_be_logged_in_to_my_twitter_account_from_command_line() throws Throwable {
        Assert.assertTrue(wagtail.isConnected());
    }

    @When("^I provide invalid credentials$")
    public void I_provide_invalid_credentials() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^I am notified about my invalid credentials$")
    public void I_am_notified_about_my_invalid_credentials() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }


    @Then("^I can type my credentials again$")
    public void I_can_type_my_credentials_again() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

}
