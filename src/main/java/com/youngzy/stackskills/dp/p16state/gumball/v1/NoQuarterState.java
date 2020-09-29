package com.youngzy.stackskills.dp.p16state.gumball.v1;

public class NoQuarterState implements GumballMachineState {
    GumballMachine gm;

    public NoQuarterState(GumballMachine gm) {
        this.gm = gm;
    }

    @Override
    public void insertQuarter() {
        gm.result = "投币成功";
        gm.setState(gm.hasQuarterState);
    }

    @Override
    public void ejectQuarter() {
        gm.result = "您并未投币";
    }

    @Override
    public void turnCrank() {
        gm.result = "请先投币，再获取糖果";
    }
}
