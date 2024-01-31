package com.youngzy.java.defaultmethod.plugin;

/**
 * @author youngzy
 * @since 2024-01-31
 */
public abstract class TemplateMethod implements BreatheSupplier {
    public final void execute() {
        breathe();
        eat();
    }

    protected void eat() {
    }

//    public String breathe() {
//        return "用什么呼吸呢？";
//    }
}
