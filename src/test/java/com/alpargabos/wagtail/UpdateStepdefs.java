package com.alpargabos.wagtail;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class UpdateStepdefs extends BaseStepDefs{

    @Before
    public void setUp() {
        initWagtail();
    }

    @After
    public void tearDown(){
        new File(Store.CONFIG_FILE).delete();
    }

    @When("^I update my status to: \"([^\"]*)\"$")
    public void I_update_my_status_to(String tweet) throws Throwable {
        twitterSimulator = new TwitterSimulator("wagtailbirdapp");
        when(input.getUserInput()).thenReturn("1234567");

        wagtail.setTwitter(twitterSimulator.getTwitterForStatusUpdate(tweet));
        wagtail.login();

        when(input.getUserInput()).thenReturn(tweet);
        wagtail.writeStatus();
    }

    @Then("^the following will appear on my time line$")
    public void the_following_will_appear_on_my_time_line(String status) throws Throwable {
        assertTrue(output.toString(), output.toString().contains(status));
    }

    @Then("^nothing happens on my time line$")
    public void nothing_happens_on_my_time_line() throws Throwable {
        assertTrue(output.toString(), output.toString().contains("cannot write"));
    }

    @When("^I try to update my status with a tweet more than 140 characters$")
    public void I_try_to_update_my_status_with_a_tweet_more_than_140_characters() throws Throwable {
        twitterSimulator = new TwitterSimulator("wagtailbirdapp");
        when(input.getUserInput()).thenReturn("1234567");

        String tooLongTweet = "This is a too long tweet, which means it is invalid. Therefore wont appear on my time line! Ho ho ho ho ho hi hi hi hi hi hi hi grrr grgrrrgrrr";

        wagtail.setTwitter(twitterSimulator.getTwitterForInvalidStatusUpdate(tooLongTweet));
        wagtail.login();

        when(input.getUserInput()).thenReturn(tooLongTweet);
        wagtail.writeStatus();
    }

}
