package com.alpargabos.wagtail;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class DeleteStatusStepdefs extends BaseStepDefs {
    String id = "12345";


    @Before
    public void setUp() {
        initWagtail();
    }

    @After
    public void tearDown(){
        new File(Store.CONFIG_FILE).delete();
    }


    @Given("^I am wagtail user$")
    public void I_am_wagtail_user() throws Throwable {
        twitterSimulator = new TwitterSimulator("wagtailbirdapp");
        when(input.getUserInput()).thenReturn("1234567");

    }

    @When("^I delete one of my tweet$")
    public void I_delete_one_of_my_tweet() throws Throwable {
        wagtail.setTwitter(twitterSimulator.getTwitterForDeletion(id));
        wagtail.login();
        when(input.getUserInput()).thenReturn(id);
        wagtail.deleteTweet();
    }

    @Then("^the tweet deleted from twitter$")
    public void the_tweet_deleted_from_twitter() throws Throwable {
        assertTrue(output.toString().contains("deleted status [" + id + "]."));
    }

    @When("^I try to delete one of my friends tweet$")
    public void I_try_to_delete_one_of_my_friends_tweet() throws Throwable {
        wagtail.setTwitter(twitterSimulator.getTwitterForInvalidDeletion(id));
        wagtail.login();
        when(input.getUserInput()).thenReturn(id);
        wagtail.deleteTweet();
    }

    @Then("^nothing happens with that tweet$")
    public void nothing_happens_with_that_tweet() throws Throwable {
        assertTrue(output.toString(),output.toString().contains("cannot delete"));
    }
}
