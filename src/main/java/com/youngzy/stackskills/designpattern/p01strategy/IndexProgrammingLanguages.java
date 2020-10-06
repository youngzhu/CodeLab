package com.youngzy.stackskills.designpattern.p01strategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IndexProgrammingLanguages {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "JavaScript", "C",
                "C++", "Python", "C#", "basic", "go");

        Comparator<String> comparator;

        comparator = new AlphaBeta();
        Collections.sort(languages, comparator);
        System.out.println(languages);

        comparator = new JavaFirst();
        Collections.sort(languages, comparator);
        System.out.println(languages);

        comparator = new JavaFirstIgnoreCase();
        Collections.sort(languages, comparator);
        System.out.println(languages);
    }
}

class AlphaBeta implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

class JavaFirst implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String first = "java";

        if (first.equalsIgnoreCase(o1) && !first.equalsIgnoreCase(o2)) {
            return -1;
        } else if (!first.equalsIgnoreCase(o1) && first.equalsIgnoreCase(o2)) {
            return 1;
        } else {
            return o1.compareTo(o2);
        }
    }
}

class JavaFirstIgnoreCase implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String first = "java";

        o1 = o1.toLowerCase();
        o2 = o2.toLowerCase();

        if (first.equals(o1) && !first.equals(o2)) {
            return -1;
        } else if (first.equals(o2) && !first.equals(o1)) {
            return 1;
        } else {
            return o1.compareTo(o2);
        }
    }
}
