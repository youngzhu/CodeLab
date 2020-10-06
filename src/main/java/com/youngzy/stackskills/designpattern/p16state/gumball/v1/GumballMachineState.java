package com.youngzy.stackskills.designpattern.p16state.gumball.v1;

/**
 * 不同的状态，针对用户不同的操作会有不同的反应或提升
 */
public interface GumballMachineState {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
}
