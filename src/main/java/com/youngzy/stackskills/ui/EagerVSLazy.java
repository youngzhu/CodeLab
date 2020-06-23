package com.youngzy.stackskills.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EagerVSLazy {

    class EagerModel {
        // 立即创建
        private StringProperty simpleString = new SimpleStringProperty();

        public String getSimpleString() {
            return simpleString.get();
        }

        public void setSimpleString(String simpleString) {
            this.simpleString.set(simpleString);
        }

        public StringProperty stringProperty() {
            return simpleString;
        }
    }

    /**
     * 缺点1：需要多余字段
     * 缺点2：非线程安全
     */
    static class LazyModel {
        private static String defaultValue = "boo!";

        private StringProperty simpleString;
        private String underlyingValue = defaultValue;

        public String  getSimpleString() {
            if (simpleString == null) {
                return underlyingValue;
            } else {
                return simpleString.get();
            }
        }

        public void setSimpleString(String someString) {
            if (simpleString == null) {
                underlyingValue = someString;
            } else {
                simpleString.set(someString);
            }
        }

        public StringProperty stringProperty() {
            if (null == simpleString) {
                simpleString = new SimpleStringProperty(underlyingValue);
            }

            return simpleString;
        }
    }

}
