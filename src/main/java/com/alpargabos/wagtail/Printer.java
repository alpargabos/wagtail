package com.alpargabos.wagtail;

import java.io.PrintWriter;

public class Printer {

    public static final String TAB = "\t";
    private final PrintWriter console;
    private final String newLine;

    public static final String ANSI_RESET = "";//"\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "";//"\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "";//"\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Printer(PrintWriter out) {
        newLine = System.getProperty("line.separator");
        console = out;
    }

    public void print(String text) {
        console.write(text);
        console.flush();
    }

    public void println(String text) {
        console.write(text + newLine);
        console.flush();
    }

    //TODO: move to ui
    public void printAuthorizationRequest(String authorizationURL) {
        println("Open the following URL and grant access to your account:");
        println(authorizationURL);
        println("Enter the PIN and hit enter.[PIN]:");
    }

    //TODO: move to ui
    public void printControllKeys() {
        println(ANSI_CYAN + "Hotkeys:");
        println(TAB + ANSI_CYAN + "h " + ANSI_RESET + "- help (brings up this menu)");
        println(TAB + ANSI_CYAN + "n " + ANSI_RESET + "- new status");
        println(TAB + ANSI_CYAN + "d " + ANSI_RESET + "- delete status");
        println(TAB + ANSI_CYAN + "t " + ANSI_RESET + "- timeline with the latest 20 tweets");
        println(TAB + ANSI_CYAN + "s " + ANSI_RESET + "- search");
        println("What is your choice?");
    }

    public void printlnWarning(String warning) {
        println(ANSI_YELLOW + warning + ANSI_RESET);
    }
}
