package com.youngzy.stackskills.dp.p16state.gumball.v1;

public class HasQuarterState implements GumballMachineState {
    GumballMachine gm;

    public HasQuarterState(GumballMachine gm) {
        this.gm = gm;
    }

    @Override
    public void insertQuarter() {
        gm.result = "请不要重复投币";
    }

    @Override
    public void ejectQuarter() {
        gm.setState(gm.noQuarterState);
        gm.result = "退币成功";
    }

    @Override
    public void turnCrank() {
        gm.setState(gm.soldState);
        gm.result = "请稍等，正在为您取糖果...";
        dispense();
    }


    /**
     * 分发糖果
     */
    private void dispense() {
        gm.count --;
        gm.result += "糖果已售出";

        if (gm.count == 0) {
            gm.result += "！！糖果已售罄";
            gm.setState(gm.soldOutState);
        } else {
            gm.setState(gm.noQuarterState);
        }
    }
}
