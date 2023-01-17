package de.emnichtda.fx3dwireframerender.renderer;

import javafx.geometry.Point3D;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.ArrayList;

public class Wireframe3D {
    private ArrayList<Edge> edgeTable;

    public Wireframe3D(ArrayList<Edge> edgeTable){
        setEdgeTable(edgeTable);
    }

    public Wireframe3D(){
        edgeTable = new ArrayList<>();
    }

    public ArrayList<Edge> getEdgeTable() {
        return edgeTable;
    }

    public void setEdgeTable(ArrayList<Edge> edgeTable) {
        if(edgeTable == null) throw new NullPointerException("EdgeTable can't be null!");
        this.edgeTable = edgeTable;
    }

    public void addEdge(Edge edge){
        edgeTable.add(edge);
    }

    public Wireframe3D translate(double xDelta, double yDelta, double zDelta){
        Wireframe3D translatedWireframe = new Wireframe3D();
        for(Edge edge : edgeTable){
            translatedWireframe.addEdge(edge.translate(xDelta, yDelta, zDelta));
        }
        return translatedWireframe;
    }

    public Wireframe3D subtract(Point3D point){
        Wireframe3D translatedWireframe = new Wireframe3D();
        for(Edge edge : edgeTable){
            translatedWireframe.addEdge(edge.subtract(point));
        }
        return translatedWireframe;
    }

    public Path raycastToPath(Point3D screenCenter, double focalLength){

        Wireframe3D translatedWireframe = subtract(screenCenter);

        Path path = new Path();

        if(translatedWireframe.getEdgeTable().size()==0){
            return path;
        }

        path.getElements().add(translatedWireframe.getEdgeTable().get(0).raycastToMoveToA(focalLength));

        for(Edge edge : translatedWireframe.getEdgeTable()){
            path.getElements().add(edge.raycastToLineToB(focalLength));
        }

        return path;
    }
}
