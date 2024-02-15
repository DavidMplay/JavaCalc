package com.mycompany.javacalc;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CalcUI {

    private TextField numbers;
    private Button clear;
    ;
    String curOp = "";
    boolean opSelected = false;
    String lastNum = "";

    public CalcUI(TextField numbers, Button clear) {
        this.numbers = numbers;
        this.clear = clear;
    }

    public Parent getView() {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.BOTTOM_LEFT);
        GridPane numButtonGrid = new GridPane();
        numButtonGrid.setVgap(2);
        numButtonGrid.setHgap(2);
        GridPane operations = new GridPane();
        operations.setVgap(2);
        operations.setHgap(2);

        List<Button> numButtons = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < 16; i++) {
            if (i < 9) {
                numButtons.add(new Button(i+1+""));
            }
            switch (i) {
                case (9):
                    numButtons.add(new Button("."));
                    break;
                case (10):
                    numButtons.add(new Button("0"));
                    break;
                case (11):
                    numButtons.add(new Button("="));
                    break;
                case (12):
                    numButtons.add(new Button("+"));
                    break;
                case (13):
                    numButtons.add(new Button("-"));
                    break;
                case (14):
                    numButtons.add(new Button("*"));
                    break;
                case (15):
                    numButtons.add(new Button("/"));
                    break;
            }

        }
        for (Button button : numButtons) {
            button.setPrefSize(50, 50);
        }
        for (int i = 0; i < 11; i++) {
            String temp = numButtons.get(i).getText();
            if (i == 9) {
                numButtons.get(i).setOnMouseClicked(event -> {
                    if (!numbers.getText().contains(".")) {
                        numbers.setText(numbers.getText() + temp);
                    }
                });
            } else {
                numButtons.get(i).setOnMouseClicked(event -> {
                    numbers.setText(numbers.getText() + temp);
                });
            }
        }
        for (int i = 12; i < 16; i++) {
            String temp = numButtons.get(i).getText();
            numButtons.get(i).setOnMouseClicked(event -> {
                this.curOp = temp;
                if (!opSelected) {
                    this.lastNum = numbers.getText();
                    numbers.clear();
                }
                this.opSelected = true;

            });
        }

        numButtons.get(11).setOnMouseClicked(event -> {
            switch (curOp) {
                case ("+"):
                    numbers.setText(Double.parseDouble(lastNum) + Double.parseDouble(numbers.getText()) + "");
                    break;
                case ("-"):
                    numbers.setText(Double.parseDouble(lastNum) - Double.parseDouble(numbers.getText()) + "");
                    break;
                case ("*"):
                    numbers.setText(Double.parseDouble(lastNum) * Double.parseDouble(numbers.getText()) + "");
                    break;
                case ("/"):
                    numbers.setText(Double.parseDouble(lastNum) / Double.parseDouble(numbers.getText()) + "");
                    break;
                default:
                    break;
            }
            curOp = ""; 
            this.opSelected = false;
        });

        counter = 0;
        for (int i = 12; i < 16; i++) {
            operations.add(numButtons.get(i), 0, counter);
            counter++;
        }
        counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                numButtonGrid.add(numButtons.get(counter), j, i);
                counter++;
            }
        }

        clear.setOnMouseClicked(event -> {
            curOp = "";
            opSelected = false;
            lastNum = "";
            numbers.clear();
        });

        layout.add(numButtonGrid, 0, 0);
        layout.add(operations, 1, 0);

        return layout;
    }

}
