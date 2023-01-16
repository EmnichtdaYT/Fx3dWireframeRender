package de.emnichtda.fx3dwireframerender.test;

import de.emnichtda.fx3dwireframerender.renderer.Edge;
import de.emnichtda.fx3dwireframerender.renderer.Wireframe3D;
import javafx.geometry.Point3D;

import java.util.Random;

public class Test {
    public static void main(String[] args) {

        Random r = new Random();

        Wireframe3D frame = new Wireframe3D();
        for(int i = 0; i < 500; i++){
            frame.addEdge(new Edge(new Point3D(r.nextDouble(200)-100, r.nextDouble(200)-100, r.nextDouble(200)-100), new Point3D(r.nextDouble(200)-100, r.nextDouble(200)-100, r.nextDouble(200)-100)));
        }

        System.out.println("[*] Translated size: " + frame.translate(r.nextDouble(30), r.nextDouble(30), r.nextDouble(30)).getEdgeTable().size());
        System.out.println("[*] Original size: " + frame.getEdgeTable().size());

    }
}
