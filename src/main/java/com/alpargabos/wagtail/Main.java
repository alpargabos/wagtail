package com.alpargabos.wagtail;

import twitter4j.TwitterException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TwitterException, IOException {
        Wagtail wagtail = new Wagtail();
        wagtail.login();
        wagtail.runInReactiveMode();
    }
}
