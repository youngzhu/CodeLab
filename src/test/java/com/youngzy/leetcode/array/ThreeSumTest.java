package com.youngzy.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ThreeSumTest {

    IThreeSum ts = new ThreeSum4();

    @Test
    public void test() {
//        int[] input = {-1,0,1,2,-1,-4};
        int[] input = {0, 0, 0};

        List<List<Integer>> result = ts.threeSum(input);

        Integer[][] expected = {{-1,-1,2},{-1,0,1}};
        Integer[][] actual = new Integer[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            actual[i] = result.get(0).toArray(new Integer[0]);
        }

//        assertArrayEquals(expected, actual);
        // 达不到 无序判断的效果
        assertTrue(Arrays.deepEquals(expected, actual));
    }

}