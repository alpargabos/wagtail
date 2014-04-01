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
        printer.println(warning);
    }

    public void welcomeUser(String name) {
        printer.println("You are logged in as: " + name);
    }

    public void showStatus(Status status) {
        printer.println("id:"+status.getId()+" "+status.getUser().getName() + ":" + status.getText());
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
}
