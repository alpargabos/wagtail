package com.alpargabos.wagtail;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuthenticationStepdefs {
    String fullName;
    Wagtail wagtail;
    OutputStream stream;

    @Given("^I am a twitter fullName$")
    public void I_am_a_twitter_user() throws Throwable {
        fullName = "wagtail";
    }

    @When("^I provide my credentials$")
    public void I_provide_my_credentials() throws Throwable {
        wagtail = new Wagtail();
        stream = new OutputStream() {
            StringBuilder content = new StringBuilder();

            @Override
            public void write(int i) throws IOException {
                content.append((char) i);
            }

            public String toString() {
                return content.toString();
            }
        };
        wagtail.setUI(stream);
        wagtail.login();
    }

    @Then("^I will be greated on my full name$")
    public void I_will_be_greated_on_my_full_name() throws Throwable {
        assertThat(stream.toString(), is("You are logged in as: "+ fullName));
    }

    @When("^I grant access to my account for Wagtail$")
    public void I_grant_access_to_my_account_for_Wagtail() throws Throwable {
        InputStream input = new InputStream() {
            @Override
            public int read() throws IOException {
                return 555666777;
            }
        };
        wagtail.setInput(input);
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
