package com.infinity.labn2_sysprog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

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
            TextField textFieldMask = new TextField();
            hBoxMask.getChildren().addAll(labelMask, textFieldMask);

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