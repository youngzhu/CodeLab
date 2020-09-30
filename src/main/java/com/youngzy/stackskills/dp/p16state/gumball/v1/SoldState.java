package com.youngzy.stackskills.dp.p16state.gumball.v1;

public class SoldState implements GumballMachineState {
    GumballMachine gm;

    public SoldState(GumballMachine gm) {
        this.gm = gm;
    }

    @Override
    public void insertQuarter() {
        gm.result = "请稍等，正在售出";
    }

    @Override
    public void ejectQuarter() {
        gm.result = "对不起，糖果已售出，无法退币";
    }

    @Override
    public void turnCrank() {
        gm.result = "转动再多次也只会给您一颗糖果";

    }

}
