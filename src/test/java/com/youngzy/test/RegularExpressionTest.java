package com.youngzy.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.*;

/**
 * @see https://www.javatpoint.com/java-regex
 */
public class RegularExpressionTest {

    @Test
    public void test() {
        Pattern p = Pattern.compile("([a-z]),(\1), ... ,(\1)");
        Matcher m = p.matcher("a,a,a");
        boolean b = m.matches();
        System.out.println(b);
    }

    @Test
    public void test1() {
        String input = "abxxefyyg";
        input = input.replaceAll("[^xy]+", "");
        String[] parts = input.split("(?<=(.))(?!\\1)");
        System.out.println(Arrays.toString(parts));
        if (parts[0].length() == parts[1].length()) {
            System.out.println("MATCH");
        }
        else {
            System.out.println("NO MATCH");
        }
    }
}
