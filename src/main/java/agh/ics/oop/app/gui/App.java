package agh.ics.oop.app.gui;

import agh.ics.oop.app.Engine;
import agh.ics.oop.enums.states.BoardState;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
    private Engine engine;
    private Stage primaryStage;

    public void closeApp() {
        Stage dialog = new Stage();
        Button restartButton = new Button("Restart");
        Button closeButton = new Button("Close");
        Label label = new Label();
        BorderPane borderPane = new BorderPane();

        BoardState boardState = (BoardState) this.engine.bigBoard.getState();

        label.setText(boardState.toString());
        label.setFont(new Font(20));
        BorderPane.setAlignment(label, Pos.CENTER);

        borderPane.setLeft(restartButton);
        borderPane.setRight(closeButton);
        borderPane.setTop(label);

        dialog.setScene(new Scene(borderPane));

        closeButton.setOnAction(event -> System.exit(0));

        restartButton.setOnAction(event -> {
            this.engine = new Engine(this);
            Scene scene = new Scene(engine.getMainBox());
            primaryStage.setScene(scene);
            dialog.close();
        });

        dialog.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) {
        Engine engine = new Engine(this);
        this.primaryStage = primaryStage;
        this.engine = engine;
        Scene scene = new Scene(engine.getMainBox());
        primaryStage.setScene(scene);
        System.out.println("Which small board / Which field");
        primaryStage.show();
    }
}
