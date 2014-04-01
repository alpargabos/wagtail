package com.alpargabos.wagtail;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrinterTest {
    PrintWriter writer;
    Printer printer;

    @Before
    public void setUp() {
        writer = mock(PrintWriter.class);
        printer = new Printer(writer);
    }

    @Test
    public void printWritesOutTheGivenString() throws Exception {
        //when
        printer.print("text");
        //then
        verify(writer).write("text");
        verify(writer).flush();
    }

    @Test
    public void printlnWritesOutTheGivenStringWithANewLineAtTheEnd() throws Exception {
        //when
        printer.println("text");
        //then
        verify(writer).write("text" + System.getProperty("line.separator"));
        verify(writer).flush();
    }

    @Test
    public void printAuthorizationRequestInstructsUserWhatToDo() throws Exception {
        //when
        printer.printAuthorizationRequest("auth_url");
        //then
        verify(writer).write(contains("grant access"));
        verify(writer).write(contains("auth_url"));
        verify(writer).write(contains("PIN"));
    }

    @Test
    public void printControllKeys() throws Exception {
        //when
        printer.printControllKeys();
        //then
        verify(writer).write(contains("Hotkeys"));
        verify(writer).write(contains("h - help"));
        verify(writer).write(contains("n - new status"));
        verify(writer).write(contains("d - delete status"));
        verify(writer).write(contains("t - timeline"));
        verify(writer).write(contains("s - search"));
        verify(writer).write(contains("choice?"));
    }
}
