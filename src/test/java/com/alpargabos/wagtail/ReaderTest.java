package com.alpargabos.wagtail;

import org.junit.Test;

import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReaderTest{

    @Test
    public void getUserInput() throws Exception {
        //given
        Scanner scanner = mock(Scanner.class);
        Reader reader = new Reader(scanner);
        //when
        reader.getUserInput();
        //then
        verify(scanner).nextLine();
    }

}
