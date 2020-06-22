package com.youngzy.stackskills.ui;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

public class DiggingBinding {
    TextArea top, bottom, left, right, center;

    public void staticMethod() {
        ObservableValue<String> concat = Bindings.concat(top.textProperty()
                , bottom.textProperty()
                , left.textProperty()
                , right.textProperty());

        center.textProperty().bind(concat);
    }

    public void bindingObject() {
        ObservableValue<String> concat = new MyStringBinding(top.textProperty()
                , bottom.textProperty()
                , left.textProperty()
                , right.textProperty());

        center.textProperty().bind(concat);

    }

    private class MyStringBinding extends StringBinding {
        StringProperty p1, p2, p3, p4;

        public MyStringBinding(StringProperty p1, StringProperty p2, StringProperty p3, StringProperty p4) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
            this.p4 = p4;

            super.bind(p1, p2, p3, p4);
        }

        @Override
        protected String computeValue() {
            return p1.getValue() + ", "
                    + p2.getValue() + ", "
                    + p3.getValue() + ", "
                    + p4.getValue() + ", "
                    ;
        }
    }

    public void fluentAPI() {
        ObservableValue<String> concat = top.textProperty().concat(
                bottom.textProperty().concat(
                        left.textProperty().concat(
                                right.textProperty()
                        )
                ));

        center.textProperty().bind(concat);
    }
}
