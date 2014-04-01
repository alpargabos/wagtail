package com.alpargabos.wagtail;

import twitter4j.Status;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Ui {
    Printer printer;
    Reader reader;

    public Ui(){
        printer = new Printer(new PrintWriter(System.out));
        reader = new Reader(new Scanner(System.in));
    }

    public String acquirePinCodeFor(String authorizationURL) {
        printer.printAuthorizationRequest(authorizationURL);
        String pin = reader.getUserInput();
        while(pin.length() != 7){
            printer.println("Please give a valid PIN code!");
            pin = reader.getUserInput();
        }
        return pin;
    }

    public void warnUser(String warning) {
        printer.printlnWarning(warning);
    }

    public void welcomeUser(String name) {
        printer.println("You are logged in as: " + name);
    }

    public void showStatus(Status status) {
        printer.println("[\\033[44;1;31m\\] id:"+status.getId()+" "+status.getUser().getName() + ":" + status.getText());
    }

    public String acquireNewStatus() {
        String tweet;
        do{
            printer.println("Please write your tweet(in max. 140 characters):");
            tweet = reader.getUserInput();
        } while (tweet.length() > 140);
        return tweet;
    }

    public Long acquireTweetIdForDeletion() {
        Long id = null;
        do{
            printer.println("Provide the id of the tweet you want to delete:");
            try{
                id = Long.valueOf(reader.getUserInput());
            } catch (NumberFormatException ex){}
        } while(id == null);
        return id;
    }

    public void showTimeLine(List<Status> statuses) {
        printer.println("Your precious time line:");
        printer.println("The last " + statuses.size() + " tweets.");
        for (Status status : statuses) {
            showStatus(status);
        }
    }

    public String acquireUserAction() {
        String goodKeys = "h,n,d,t,s";
        String key;
        printer.printControllKeys();
        key = reader.getUserInput();
        while(!goodKeys.contains(key)){
           printer.println("Please provide a valid key!");
            key = reader.getUserInput();
        }
        return key;
    }

    public String acquireSearchTerm() {
        String searchTerm;
        printer.println("Please give something to search(press \"e\" for possible search operators):");
        searchTerm = reader.getUserInput();
        if("e".equals(searchTerm)){
            printer.println("Search operators:");
            printer.println("twitter search\tcontaining both \"twitter\" and \"search\". This is the default operator.\n" +
                    "\"happy hour\"\tcontaining the exact phrase \"happy hour\".\n" +
                    "love OR hate\tcontaining either \"love\" or \"hate\" (or both).\n" +
                    "beer -root\tcontaining \"beer\" but not \"root\".\n" +
                    "#haiku\tcontaining the hashtag \"haiku\".\n" +
                    "from:alexiskold\tsent from person \"alexiskold\".\n" +
                    "to:techcrunch\tsent to person \"techcrunch\".\n" +
                    "@mashable\treferencing person \"mashable\".\n" +
                    "\"happy hour\" near:\"san francisco\"\tcontaining the exact phrase \"happy hour\" and sent near \"san francisco\".\n" +
                    "near:NYC within:15mi\tsent within 15 miles of \"NYC\".\n" +
                    "superhero since:2010-12-27\tcontaining \"superhero\" and sent since date \"2010-12-27\" (year-month-day).\n" +
                    "ftw until:2010-12-27\tcontaining \"ftw\" and sent up to date \"2010-12-27\".\n" +
                    "movie -scary :)\tcontaining \"movie\", but not \"scary\", and with a positive attitude.\n" +
                    "flight :(\tcontaining \"flight\" and with a negative attitude.\n" +
                    "traffic ?\tcontaining \"traffic\" and asking a question.\n" +
                    "hilarious filter:links\tcontaining \"hilarious\" and linking to URLs.\n" +
                    "news source:twitterfeed\tcontaining \"news\" and entered via TwitterFeed\n");
            searchTerm = reader.getUserInput();
        }
        return searchTerm;
    }
}
