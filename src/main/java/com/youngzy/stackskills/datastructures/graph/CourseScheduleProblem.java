package com.youngzy.stackskills.datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程安排问题：学课程B之前，必须先学A
 */
public class CourseScheduleProblem {
    /**
     *
     * @param courses 课程列表
     * @param preReqs 课程间的关系
     * @return
     */
    public List<String> schedule(List<String> courses, Map<String, List<String>> preReqs) {
        Graph courseGraph = new AdjacentMatrixGraph(courses.size(), Graph.GraphType.DIRECTED);

        Map<String, Integer> nameToId = new HashMap<>();
        Map<Integer, String> idToName = new HashMap<>();

        for (int i = 0; i < courses.size(); i++) {
            nameToId.put(courses.get(i), i);
            idToName.put(i, courses.get(i));
        }

        for (Map.Entry<String, List<String>> preReq : preReqs.entrySet()) {
            for (String course : preReq.getValue()) {
                courseGraph.addEdge(nameToId.get(preReq.getKey()), nameToId.get(course));
            }
        }

        TopologicalSort sort = new TopologicalSort();
        List<Integer> scheduledList = sort.sort(courseGraph);

        List<String> result = new ArrayList<>();
        for (int courseId : scheduledList) {
            result.add(idToName.get(courseId));
        }

        return result;

    }
}
