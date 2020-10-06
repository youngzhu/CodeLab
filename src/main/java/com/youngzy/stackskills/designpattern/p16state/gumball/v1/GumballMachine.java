package com.youngzy.stackskills.designpattern.p16state.gumball.v1;

public class GumballMachine {
    GumballMachineState soldOutState;
    GumballMachineState soldState;
    GumballMachineState noQuarterState;
    GumballMachineState hasQuarterState;

    GumballMachineState state = soldOutState; // 默认售罄状态
    int count = 0;


    // 糖果机的执行结果
    // 测试时用
    public String result;

    public GumballMachine(int count) {
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);

        this.count = count;
        if (count > 0) {
            // 如果糖果的数量大于0，则表示糖果机处于代售状态
            state = noQuarterState;
        }
        result = "糖果机初始化完成，糖果数量为" + count;
    }

    /**
     * 投币
     *
     * @return
     */
    public void insertQuarter() {
        state.insertQuarter();
    }

    /**
     * 退币
     *
     * @return
     */
    public void ejectQuarter() {
        state.ejectQuarter();
    }

    /**
     * 转动曲柄取糖果
     */
    public void turnCrank() {
        state.turnCrank();
    }



    public void setState(GumballMachineState state) {
        this.state = state;
    }

    public GumballMachineState getSoldOutState() {
        return soldOutState;
    }

    public GumballMachineState getSoldState() {
        return soldState;
    }

    public GumballMachineState getNoQuarterState() {
        return noQuarterState;
    }

    public GumballMachineState getHasQuarterState() {
        return hasQuarterState;
    }
}
