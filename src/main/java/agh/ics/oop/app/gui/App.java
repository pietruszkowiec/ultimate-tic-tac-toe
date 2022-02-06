package agh.ics.oop.app.gui;

import agh.ics.oop.app.Engine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    private Engine engine;
    private Stage primaryStage;

    public void closeApp() {
        Stage dialog = new Stage();
        Button restartButton = new Button("Restart");
        Button closeButton = new Button("Close");
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(restartButton);
        borderPane.setRight(closeButton);
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
        primaryStage.show();
    }
}
