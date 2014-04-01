package com.alpargabos.wagtail;

import java.io.PrintWriter;

public class Printer {

    public static final String TAB = "\t";
    private final PrintWriter console;
    private final String newLine;

    public Printer(PrintWriter out) {
        newLine = System.getProperty("line.separator");
        console = out;
    }

    public void print(String text){
        console.write(text);
        console.flush();
    }

    public void println(String text){
        console.write(text + newLine);
        console.flush();
    }


    public void printAuthorizationRequest(String authorizationURL) {
        println("Open the following URL and grant access to your account:");
        println(authorizationURL);
        println("Enter the PIN(if available) or just hit enter.[PIN]:");
    }

    public void printControllKeys() {
        println("Hotkeys:");
        println(TAB + "h - help (brings up this menu)");
        println(TAB + "n - new status");
        println(TAB + "d <id> - delete status with the provided id");
        println(TAB + "t - timeline with the latest 20 tweets");
        println(TAB + "s <search term> - searches with the given search term");
        println(TAB + "ESC - cancels any command");
        println(TAB + "CTRL + C - close the program");
        println("What is your choice?");
    }
}
