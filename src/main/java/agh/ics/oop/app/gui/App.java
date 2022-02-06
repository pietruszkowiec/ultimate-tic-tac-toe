package agh.ics.oop.app.gui;

import agh.ics.oop.app.engine.Engine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Engine engine = new Engine();
        Scene scene = new Scene(engine.getMainBox());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
