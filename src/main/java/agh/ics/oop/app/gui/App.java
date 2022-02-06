package agh.ics.oop.app.gui;

import agh.ics.oop.app.Engine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public void closeApp() {

    }

    @Override
    public void start(Stage primaryStage) {
        Engine engine = new Engine(this);
        Scene scene = new Scene(engine.getMainBox());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
