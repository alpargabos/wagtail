package com.alpargabos.wagtail;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class SearchStepdefs extends BaseStepDefs{


    @Before
    public void beforeScenario(){
        initWagtail();
    }

    @After
    public void tearDown(){
        new File(Store.CONFIG_FILE).delete();
    }


    @When("^I search for \"([^\"]*)\"$")
    public void I_search_for(String expression) throws Throwable {
        twitterSimulator = new TwitterSimulator("wagtailbirdapp");
        when(input.getUserInput()).thenReturn("1234567");

        wagtail.setTwitter(twitterSimulator.getTwitterForSearchExpression(expression));
        wagtail.login();

        when(input.getUserInput()).thenReturn(expression);
        wagtail.searchTweets();
    }

    @Then("^I got tweets which contains the word \"([^\"]*)\"$")
    public void I_got_tweets_which_contains_the_word(String expression) throws Throwable {
        assertTrue(output.toString(), output.toString().contains(expression));
    }

    @When("^I search for hashtag \"([^\"]*)\"$")
    public void I_search_for_hashtag(String expression) throws Throwable {
        twitterSimulator = new TwitterSimulator("wagtailbirdapp");
        when(input.getUserInput()).thenReturn("1234567");

        wagtail.setTwitter(twitterSimulator.getTwitterForSearchExpression(expression));
        wagtail.login();

        when(input.getUserInput()).thenReturn(expression);
        wagtail.searchTweets();
    }

    @Then("^I got tweets which tagged with \"([^\"]*)\"$")
    public void I_got_tweets_which_tagged_with(String expression) throws Throwable {
        assertTrue(output.toString(), output.toString().contains("#"+expression));
    }

    @Then("^I got tweets where \"([^\"]*)\" was mentioned$")
    public void I_got_tweets_where_was_mentioned(String expression) throws Throwable {
        assertTrue(output.toString(), output.toString().contains(expression));
    }

    @Then("^I got tweets where user \"([^\"]*)\" was mentioned$")
    public void I_got_tweets_where_user_was_mentioned(String username) throws Throwable {
        assertTrue(output.toString(), output.toString().contains("@"+username));
    }
}
