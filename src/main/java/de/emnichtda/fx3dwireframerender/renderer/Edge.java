package de.emnichtda.fx3dwireframerender.renderer;

import javafx.geometry.Point3D;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

public class Edge {
    private final Point3D pointA;
    private final Point3D pointB;

    public Edge(Point3D pointA, Point3D pointB) throws NullPointerException, IllegalArgumentException{
        if(pointA==null||pointB==null) throw new NullPointerException("Points can't be null!");
        if(pointA.equals(pointB)) throw new IllegalArgumentException("Points can't be the same!");

        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Point3D getPointA() {
        return pointA;
    }

    public Point3D getPointB() {
        return pointB;
    }

    public Edge translate(double xDelta, double yDelta, double zDelta){
        return new Edge(pointA.add(xDelta, yDelta, zDelta), pointB.add(xDelta, yDelta, zDelta));
    }

    public Edge subtract(Point3D point){
        return new Edge(pointA.subtract(point), pointB.subtract(point));
    }

    public Line raycastToLine(double focalLength){

        double aXprojected = Utils.project(pointA.getX(), pointA.getZ(), focalLength);
        double aYprojected = Utils.project(pointA.getY(), pointA.getZ(), focalLength);
        double bXprojected = Utils.project(pointB.getX(), pointB.getZ(), focalLength);
        double bYprojected = Utils.project(pointB.getY(), pointB.getZ(), focalLength);

        return new Line(aXprojected, aYprojected, bXprojected, bYprojected);
    }

    public LineTo raycastToLineToB(double focalLength){

        double bXprojected = Utils.project(pointB.getX(), pointB.getZ(), focalLength);
        double bYprojected = Utils.project(pointB.getY(), pointB.getZ(), focalLength);

        return new LineTo(bXprojected, bYprojected);
    }

    public MoveTo raycastToMoveToA(double focalLength){
        double aXprojected = Utils.project(pointA.getX(), pointA.getZ(), focalLength);
        double aYprojected = Utils.project(pointA.getY(), pointA.getZ(), focalLength);

        return new MoveTo(aXprojected, aYprojected);
    }
}
