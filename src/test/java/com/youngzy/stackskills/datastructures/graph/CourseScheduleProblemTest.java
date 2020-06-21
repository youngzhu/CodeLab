package com.youngzy.stackskills.datastructures.graph;

import com.sun.javafx.tools.packager.MakeAllParams;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CourseScheduleProblemTest {

    @Test
    public void schedule() {
        CourseScheduleProblem csp = new CourseScheduleProblem();

        List<String> courses = new ArrayList<>(
                Arrays.asList("CS 101", "CS 102", "CS 103", "CS 104", "CS 105", "CS 106", "CS 107"));

        Map<String, List<String>> preReqs = new HashMap<>();
        preReqs.put("CS 102", new ArrayList<>(Arrays.asList("CS 101")));
        preReqs.put("CS 105", new ArrayList<>(Arrays.asList("CS 101", "CS 104")));
        preReqs.put("CS 103", new ArrayList<>(Arrays.asList("CS 101")));
        preReqs.put("CS 107", new ArrayList<>(Arrays.asList("CS 105")));

        List<String> scheduledList = csp.schedule(courses, preReqs);
        System.out.print(scheduledList);
    }
}