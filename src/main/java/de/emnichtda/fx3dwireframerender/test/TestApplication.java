package de.emnichtda.fx3dwireframerender.test;

import de.emnichtda.fx3dwireframerender.renderer.Edge;
import de.emnichtda.fx3dwireframerender.renderer.Wireframe3D;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class TestApplication extends Application {

    Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        GridPane p = new GridPane();
        p.setAlignment(Pos.CENTER);

        mainScene = new Scene(p, 960, 720);

        Wireframe3D frame = new Wireframe3D();

        Point3D start = new Point3D(-100, 0, 100);
        Point3D one = new Point3D(100, 0, 100);
        Point3D two = new Point3D(100, 0, 200);
        Point3D three = new Point3D(-100, 0, 200);

        Edge startOne = new Edge(start, one);
        Edge oneTwo = new Edge(one, two);
        Edge twoThree = new Edge(two, three);
        Edge threeStart = new Edge(three, start);

        frame.addEdge(startOne);
        frame.addEdge(oneTwo);
        frame.addEdge(twoThree);
        frame.addEdge(threeStart);

        Path path = frame.raycastToPath(new Point3D(0, -50, 0), 100);

        p.add(path, 0, 0);

        System.out.println(path.getElements().toString());

        primaryStage.setTitle("BUGGYENGINE");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = -50;
                int focal = 20;
                while(true){
                    focal--;
                    i++;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int finalI = i;
                    int finalFocal = focal;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            path.getElements().setAll(frame.raycastToPath(new Point3D(0, -100, finalI), 20).getElements());
                        }
                    });
                }
            }
        }).start();

    }

    public static void main(String[] args){ launch(); }
}
