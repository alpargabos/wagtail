package com.alpargabos.wagtail;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

import static org.hamcrest.Matchers.is;

public class ReaderTest{

    @Test
    public void getUserInput() throws Exception {
        //given
        Scanner scanner = new Scanner("hello\nworld");
        Reader reader = new Reader(scanner);
        //when
        String result = reader.getUserInput();
        //then
        Assert.assertThat(result, is("hello"));
    }

}
