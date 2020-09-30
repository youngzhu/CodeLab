package com.youngzy.stackskills.dp.p16state.gumball.v1;

public class SoldOutState implements GumballMachineState {
    GumballMachine gm;

    public SoldOutState(GumballMachine gm) {
        this.gm = gm;
    }

    @Override
    public void insertQuarter() {
        gm.result = "糖果已售罄，机器暂停使用，无法投币";
    }

    @Override
    public void ejectQuarter() {
        // 售罄状态，不会接受投币，当然就不能退币
        gm.result = "糖果已售罄，机器暂停使用，无法退币";
    }

    @Override
    public void turnCrank() {
        gm.result = "糖果已售罄，机器暂停使用，无法获取糖果";
    }
}
