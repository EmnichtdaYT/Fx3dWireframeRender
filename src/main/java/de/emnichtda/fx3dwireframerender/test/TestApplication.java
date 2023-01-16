package de.emnichtda.fx3dwireframerender.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestApplication extends Application {

    Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        Pane p = new Pane();
        mainScene = new Scene(p, 960, 720);
    }

    public static void main(String[] args){ launch(); }
}
