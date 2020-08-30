package com.youngzy.stackskills.dp.p16state.gumball;

import com.youngzy.stackskills.dp.p16state.gumball.v0.GumballMachine;
import org.junit.Test;

import static org.junit.Assert.*;

public class GumballMachineTest {

    @Test
    public void test() {
        int count = 5;
        GumballMachine gm = new GumballMachine(count);
        assertEquals("糖果机初始化完成，糖果数量为" + count, gm.result);

        // 第一颗
        gm.insertQuarter();
        assertEquals("投币成功", gm.result);
        gm.turnCrank();
        assertEquals("请稍等，正在为您取糖果...糖果已售出", gm.result);

        // 退币
        gm.insertQuarter();
        assertEquals("投币成功", gm.result);
        gm.ejectQuarter();
        assertEquals("退币成功", gm.result);
        gm.turnCrank();
        assertEquals("请先投币，再获取糖果", gm.result);

        // 第2，3颗
        gm.insertQuarter();
        gm.turnCrank();
        assertEquals("请稍等，正在为您取糖果...糖果已售出", gm.result);
        gm.insertQuarter();
        gm.turnCrank();
        assertEquals("请稍等，正在为您取糖果...糖果已售出", gm.result);
        gm.ejectQuarter();
        assertEquals("您并未投币", gm.result);

        // 第4颗
        gm.insertQuarter();
        gm.insertQuarter();
        assertEquals("请不要重复投币", gm.result);
        gm.turnCrank();
        assertEquals("请稍等，正在为您取糖果...糖果已售出", gm.result);
        gm.turnCrank();
        assertEquals("请先投币，再获取糖果", gm.result);

        // 第5颗
        gm.insertQuarter();
        gm.turnCrank();
        assertEquals("请稍等，正在为您取糖果...糖果已售出！！糖果已售罄", gm.result);

        // 售罄
        gm.insertQuarter();
        assertEquals("糖果已售罄，机器暂停使用，无法投币", gm.result);
        gm.turnCrank();
        assertEquals("糖果已售罄，机器暂停使用，无法获取糖果", gm.result);
        gm.ejectQuarter();
        assertEquals("糖果已售罄，机器暂停使用，无法退币", gm.result);
    }

}