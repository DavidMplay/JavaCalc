package com.mycompany.javacalc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();
        TextField calculations = new TextField("");
        Button clear = new Button("Clear");
        CalcUI calcUI = new CalcUI(calculations, clear);
        calculations.setPrefWidth(204);
        calculations.setEditable(false);
        layout.setPrefHeight(300);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setMargin(calculations, new Insets(0, 0, 5, 0));
        layout.setTop(clear);
        layout.setBottom(calcUI.getView());
        layout.setLeft(calculations);
        BorderPane.setAlignment(calculations, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
