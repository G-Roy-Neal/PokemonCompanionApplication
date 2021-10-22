package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PokeMonApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(LocationsGUI());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}