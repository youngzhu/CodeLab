package com.youngzy.stackskills.ui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class HelloPropertiesAndBindings extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        TextArea textArea1 = new TextArea();
        TextArea textArea2 = new TextArea();

        MyModel model = new MyModel();

        textArea1.textProperty().addListener(e -> {
            model.setSimpleString(textArea1.getText());
        });

        textArea2.textProperty().bind(model.getStringProperty());

        pane.setTop(textArea1);
        pane.setBottom(textArea2);

        primaryStage.setTitle("复读机");
        primaryStage.setScene(new Scene(pane, 300, 400));
        primaryStage.show();

    }

    private static class MyModel {
        private StringProperty simpleString = new SimpleStringProperty();

        public String getSimpleString() {
            return simpleString.get();
        }

        public void setSimpleString(String simpleString) {
            this.simpleString.set(simpleString);
        }

        public StringProperty getStringProperty() {
            return simpleString;
        }

    }

}

