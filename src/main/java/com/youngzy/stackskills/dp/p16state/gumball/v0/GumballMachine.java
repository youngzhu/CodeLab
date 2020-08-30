package com.youngzy.stackskills.dp.p16state.gumball.v0;

public class GumballMachine {
    final static int SOLD_OUT = 0; // 售罄
    final static int NO_QUARTER = 1; // 未投币
    final static int HAS_QUARTER = 2; // 已投币
    final static int SOLD = 3; // 出售

    int state = SOLD_OUT; // 默认售罄状态
    int count = 0;

    // 糖果机的执行结果
    // 测试时用
    public String result;

    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            // 如果糖果的数量大于0，则表示糖果机处于代售状态
            state = NO_QUARTER;
        }
        result = "糖果机初始化完成，糖果数量为" + count;
    }

    /**
     * 投币
     *
     * @return
     */
    public void insertQuarter() {
        if (state == SOLD_OUT) {
            result = "糖果已售罄，机器暂停使用，无法投币";
        } else if (state == NO_QUARTER) {
            state = HAS_QUARTER;
            result = "投币成功";
        } else if (state == HAS_QUARTER) {
            result = "请不要重复投币";
        } else if (state == SOLD) {
            result = "请稍等，正在售出";
        }
    }

    /**
     * 退币
     *
     * @return
     */
    public void ejectQuarter() {
        if (state == SOLD_OUT) {
            // 售罄状态，不会接受投币，当然就不能退币
            result = "糖果已售罄，机器暂停使用，无法退币";
        } else if (state == NO_QUARTER) {
            result = "您并未投币";
        } else if (state == HAS_QUARTER) {
            state = NO_QUARTER;
            result = "退币成功";
        } else if (state == SOLD) {
            result = "对不起，糖果已售出，无法退币";
        }
    }

    /**
     * 转动曲柄取糖果
     */
    public void turnCrank() {
        if (state == SOLD_OUT) {
            result = "糖果已售罄，机器暂停使用，无法获取糖果";
        } else if (state == NO_QUARTER) {
            result = "请先投币，再获取糖果";
        } else if (state == HAS_QUARTER) {
            state = SOLD;
            result = "请稍等，正在为您取糖果...";
            dispense();
        } else if (state == SOLD) {
            result = "转动再多次也只会给您一颗糖果";
        }
    }

    /**
     * 分发糖果
     */
    private void dispense() {
        count --;
        result += "糖果已售出";

        if (count == 0) {
            result += "！！糖果已售罄";
            state = SOLD_OUT;
        } else {
            state = NO_QUARTER;
        }
    }
}
