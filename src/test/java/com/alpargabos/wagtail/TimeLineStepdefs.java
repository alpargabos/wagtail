package com.alpargabos.wagtail;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TimeLineStepdefs extends BaseStepDefs{

    @Before
    public void beforeScenario(){
        initWagtail();
    }

    @After
    public void tearDown(){
        new File(Store.CONFIG_FILE).delete();
    }


    @When("^I ask for my time line$")
    public void I_ask_for_my_time_line() throws Throwable {
        twitterSimulator = new TwitterSimulator("wagtailbirdapp");
        when(input.getUserInput()).thenReturn("1234567");

        wagtail.setTwitter(twitterSimulator.getTwitterForTimeLine());
        wagtail.login();

        wagtail.printHomeLine();
    }

    @Then("^I got the latest tweets$")
    public void I_got_the_latest_tweets() throws Throwable {
        assertEquals(2, output.toString().split("id:").length-1);
    }
}
