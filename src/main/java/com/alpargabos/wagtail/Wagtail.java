package com.alpargabos.wagtail;

import java.io.*;

public class Wagtail {

    private Writer consoleOut;
    private BufferedReader consoleIn;

    public Wagtail() {
        consoleOut = new PrintWriter(System.out);
    }

    public boolean login() {
        return false;
    }

    public void setUI(OutputStream output) {
        consoleOut = new PrintWriter(output);
    }

    public void setInput(InputStream input) {
        consoleIn = new BufferedReader(new InputStreamReader(System.in));
    }
}
