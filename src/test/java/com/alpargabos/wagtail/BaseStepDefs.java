package com.alpargabos.wagtail;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.mock;

public class BaseStepDefs {
    protected String fullName;
    protected Wagtail wagtail;
    protected TwitterSimulator twitterSimulator;
    protected OutputStream output;
    protected Reader input;


    protected void initWagtail() {
        wagtail = new Wagtail();
        output = new OutputStream() {
            StringBuilder content = new StringBuilder();

            @Override
            public void write(int i) throws IOException {
                System.out.print((char) i);
                content.append((char) i);
            }

            public String toString() {
                return content.toString();
            }
        };
        input = mock(Reader.class);
        wagtail.setInput(input);
        wagtail.setOutput(output);
        twitterSimulator = new TwitterSimulator();
    }
}
