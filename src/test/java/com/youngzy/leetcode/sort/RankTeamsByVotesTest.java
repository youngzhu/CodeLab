package com.youngzy.leetcode.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class RankTeamsByVotesTest {

    RankTeamsByVotes rank = new RankTeamsByVotes();

    @Test
    public void test3() {
        String expected = "AXYB";

        // 票数完全一样，按字母顺序排列
        assertEquals(expected, rank.rankTeams(new String[]{"AXYB","AYXB","AXYB","AYXB"}));
    }
    @Test
    public void test2() {
        String expected = "ABC";

        // 票数完全一样，按字母顺序排列
        assertEquals(expected, rank.rankTeams(new String[]{"BCA","CAB","CBA","ABC","ACB","BAC"}));
    }

    @Test
    public void test1() {
        String expected = "AB";

        assertEquals(expected, rank.rankTeams(new String[]{"AB"}));
    }

    @Test
    public void test() {
        String expected = "XWYZ";

        assertEquals(expected, rank.rankTeams(new String[]{"WXYZ","XYZW"}));
    }
    @Test
    public void rankTeams() {
        String expected = "ACB";

        assertEquals(expected, rank.rankTeams(new String[]{"ABC","ACB","ABC","ACB","ACB"}));
    }
}