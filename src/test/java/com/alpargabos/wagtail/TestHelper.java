package com.alpargabos.wagtail;

import static org.mockito.Matchers.matches;

/**
 * Created by gabos on 4/1/14.
 */
public class TestHelper {
    private static final String ANYTHING = "(?s).*";

    public static String containsAll(String... strings){
        String expression = ANYTHING;
        for (String s:strings){
            expression += s + ANYTHING;
        }
        return matches(expression);
    }

}
