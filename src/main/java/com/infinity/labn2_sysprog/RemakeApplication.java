package com.infinity.labn2_sysprog;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemakeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(10);

        TextArea textArea = new TextArea();
        Label label = new Label("Input");
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

            HBox hBoxText = new HBox();
            hBoxText.setPadding(new Insets(20));
            hBoxText.setSpacing(10);
            Label labelOutput = new Label("Output");
            TextArea textAreaOutput = new TextArea();
            textAreaOutput.setMaxWidth(400);
            hBoxText.getChildren().addAll(labelOutput, textAreaOutput);

            HBox hBoxMenu = new HBox();
            hBoxMenu.setPadding(new Insets(20));
            hBoxMenu.setSpacing(10);
            Button buttonRemake = new Button("Remake");
            Button buttonRemakeAll = new Button("Remake all");
            buttonRemakeAll.setOnMouseClicked(mouseEvent -> {
                if (textFieldMask.getText().isEmpty()) {
                    fontErrorMask.setVisible(true);
                    return;
                } else fontErrorMask.setVisible(false);
                textAreaOutput.clear();

                String[] splited = WordMatch.getSplitedWord(textArea.getText());

                for (String word : splited) {
                    Pattern pattern = Pattern.compile(MaskParser.parseMaskToRegex(textFieldMask.getText()));
                    Matcher matcher = pattern.matcher(word);
                    while (matcher.find()) {
                        textAreaOutput.setText(textAreaOutput.getText() + WordMatch.convertWord(word) + ' ');
                    }
                }
            });

            Button buttonClose = new Button("Close");
            hBoxMenu.getChildren().addAll(buttonRemake, buttonRemakeAll, buttonClose);

            GridPane gridPane = new GridPane();
            gridPane.add(hBoxMask, 0, 0);
            gridPane.add(hBoxText, 0, 1);
            gridPane.add(hBoxMenu, 0, 2);

            Scene newScene = new Scene(gridPane, 500, 350);
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