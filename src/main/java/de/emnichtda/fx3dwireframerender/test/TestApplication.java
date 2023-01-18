package de.emnichtda.fx3dwireframerender.test;

import de.emnichtda.fx3dwireframerender.renderer.Edge;
import de.emnichtda.fx3dwireframerender.renderer.Wireframe3D;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class TestApplication extends Application {

    Scene mainScene;

    private boolean isClosed = false;

    public double cameraX = 0;
    public double cameraY = -100;
    public double cameraZ = 0;

    public double deltaX = 0;
    public double deltaY = 0;
    public double deltaZ = 0;

    @Override
    public void start(Stage primaryStage) {
        GridPane p = new GridPane();
        p.setAlignment(Pos.CENTER);

        mainScene = new Scene(p, 960, 720);

        Wireframe3D frame = new Wireframe3D();

        Point3D start = new Point3D(-100, 0, 100);
        Point3D one = new Point3D(100, 0, 100);
        Point3D two = new Point3D(100, 0, 300);
        Point3D three = new Point3D(-100, 0, 300);

        Point3D topStart = new Point3D(-100, 100, 100);
        Point3D topOne = new Point3D(100, 100, 100);
        Point3D topTwo = new Point3D(100, 100, 300);
        Point3D topThree = new Point3D(-100, 100, 300);

        Edge startOne = new Edge(start, one);
        Edge oneTwo = new Edge(one, two);
        Edge twoThree = new Edge(two, three);
        Edge threeStart = new Edge(three, start);

        Edge topStartTopOne = new Edge(topStart, topOne);
        Edge topOneTopTwo = new Edge(topOne, topTwo);
        Edge topTwoTopThree = new Edge(topTwo, topThree);
        Edge topThreeTopStart = new Edge(topThree, topStart);

        Edge startTopStart = new Edge(start, topStart);
        Edge oneTopOne = new Edge(one, topOne);
        Edge twoTopTwo = new Edge(two, topTwo);
        Edge threeTopThree = new Edge(three, topThree);

        frame.addEdge(startOne);
        frame.addEdge(oneTwo);
        frame.addEdge(twoThree);
        frame.addEdge(threeStart);

        frame.addEdge(topStartTopOne);
        frame.addEdge(topOneTopTwo);
        frame.addEdge(topTwoTopThree);
        frame.addEdge(topThreeTopStart);

        frame.addEdge(startTopStart);
        frame.addEdge(oneTopOne);
        frame.addEdge(twoTopTwo);
        frame.addEdge(threeTopThree);


        Path path = frame.raycastToPath(new Point3D(cameraX, cameraY, cameraZ), 500);

        p.add(path, 0, 0);

        System.out.println(path.getElements().toString());

        primaryStage.setTitle("BUGGYENGINE");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest((windowEvent) -> isClosed = true);

        mainScene.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode().equals(KeyCode.W)) {
                deltaY = 1;
            } else if (keyEvent.getCode().equals(KeyCode.S)) {
                deltaY = -1;
            } else if (keyEvent.getCode().equals(KeyCode.A)) {
                deltaX = -1;
            } else if (keyEvent.getCode().equals(KeyCode.D)) {
                deltaX = 1;
            }
        });

        mainScene.setOnKeyReleased((keyEvent) -> {
            if (keyEvent.getCode().equals(KeyCode.W)) {
                deltaY = 0;
            } else if (keyEvent.getCode().equals(KeyCode.S)) {
                deltaY = 0;
            } else if (keyEvent.getCode().equals(KeyCode.A)) {
                deltaX = 0;
            } else if (keyEvent.getCode().equals(KeyCode.D)) {
                deltaX = 0;
            }
        });

        new Thread(() -> {
            while (!isClosed) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (deltaX != 0 || deltaY != 0 || deltaZ != 0) {
                    cameraX += deltaX;
                    cameraY += deltaY;
                    cameraZ += deltaZ;

                    Platform.runLater(() -> p.getChildren().set(0, frame.raycastToPath(new Point3D(cameraX, cameraY, cameraZ), 500)));
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        launch();
    }
}
