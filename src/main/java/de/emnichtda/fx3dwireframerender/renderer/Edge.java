package de.emnichtda.fx3dwireframerender.renderer;

import javafx.geometry.Point3D;

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
}
