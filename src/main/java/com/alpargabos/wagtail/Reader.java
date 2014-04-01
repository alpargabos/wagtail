package com.alpargabos.wagtail;

import java.util.Scanner;

public class Reader {
    Scanner scanner;

    public Reader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getUserInput(){
        return scanner.nextLine();
    }

}
