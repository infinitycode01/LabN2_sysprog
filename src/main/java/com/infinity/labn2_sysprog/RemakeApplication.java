package com.infinity.labn2_sysprog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// В новому вікні видалити вікно output і заміняти слова в input вікні
public class RemakeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(10);

        TextArea textArea = new TextArea();
        Label label = new Label("Text area");
        Button button = new Button("Remake");

        vBox.getChildren().addAll(label, textArea, button);
        VBox.setVgrow(textArea, Priority.ALWAYS);

        button.setOnAction(e -> {
            Stage newStage = new Stage();

            HBox hBoxMask = new HBox();
            hBoxMask.setPadding(new Insets(32));
            hBoxMask.setSpacing(10);
            Label labelMask = new Label("Mask");
            Text fontErrorMask = new Text("Write mask");
            fontErrorMask.setVisible(false);
            fontErrorMask.setFill(Color.RED);
            TextField textFieldMask = new TextField();
            hBoxMask.getChildren().addAll(labelMask, textFieldMask, fontErrorMask);

            HBox hBoxMenu = new HBox();
            hBoxMenu.setPadding(new Insets(20));
            hBoxMenu.setSpacing(10);

            Button buttonNext = new Button("Next");
            buttonNext.setDisable(true);

            Button buttonRemake = new Button("Remake");
            buttonRemake.setOnMouseClicked(mouseEvent -> {
                if (textFieldMask.getText().isEmpty()) {
                    fontErrorMask.setVisible(true);
                    return;
                } else fontErrorMask.setVisible(false);

                String[] splited = WordMatch.getSplitedWord(textArea.getText());

                //AtomicInteger currentIndex = new AtomicInteger(0);


                buttonNext.setDisable(false);

                int[] currentIndex = {0};

                buttonNext.setOnMouseClicked(mouseEvent1 -> {
                    while (currentIndex[0] < splited.length) {
                        String word = splited[currentIndex[0]];
                        Pattern pattern = Pattern.compile(MaskParser.parseMaskToRegex(textFieldMask.getText()));
                        Matcher matcher = pattern.matcher(word);
                        if (matcher.find()) {
                            textArea.setText(textArea.getText().replace(word, WordMatch.convertWord(word)));
                            currentIndex[0] = (currentIndex[0] + 1) % splited.length;
                            break;
                        }
                        currentIndex[0] = (currentIndex[0] + 1) % splited.length;
                    }
                });

            });

            Button buttonRemakeAll = new Button("Remake all");
            buttonRemakeAll.setOnMouseClicked(mouseEvent -> {
                if (textFieldMask.getText().isEmpty()) {
                    fontErrorMask.setVisible(true);
                    return;
                } else fontErrorMask.setVisible(false);

                String[] splited = WordMatch.getSplitedWord(textArea.getText());

                for (String word : splited) {
                    Pattern pattern = Pattern.compile(MaskParser.parseMaskToRegex(textFieldMask.getText()));
                    Matcher matcher = pattern.matcher(word);
                    if (matcher.find()) {
                        textArea.setText(textArea.getText().replace(word, WordMatch.convertWord(word)));
                    }
                }
            });

            Button buttonClose = new Button("Close");
            buttonClose.setOnMouseClicked(mouseEvent -> {
                newStage.close();
            });

            hBoxMenu.getChildren().addAll(buttonNext, buttonRemake, buttonRemakeAll, buttonClose);

            GridPane gridPane = new GridPane();
            gridPane.add(hBoxMask, 0, 0);
            gridPane.add(hBoxMenu, 0, 1);

            Scene newScene = new Scene(gridPane, 500, 150);
            newStage.setScene(newScene);
            newStage.show();
        });

        Scene scene = new Scene(vBox, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}

