package de.emnichtda.fx3dwireframerender.renderer;

import javafx.geometry.Point3D;

import java.util.ArrayList;

public class Tree3DNode {

    private ArrayList<Tree3DNode> children;
    private Point3D point;

    public Tree3DNode(Point3D point, ArrayList<Tree3DNode> children) {
        setChildren(children);
        setPoint(point);
    }

    public Tree3DNode(Point3D point) {
        children = new ArrayList<>();
        setPoint(point);
    }

    public Point3D getPoint() {
        return point;
    }

    public void setPoint(Point3D point) throws NullPointerException {
        if (point == null) throw new NullPointerException("Point can't be null!");
        this.point = point;
    }

    public ArrayList<Tree3DNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Tree3DNode> children) {
        if (children == null) throw new NullPointerException("Children can't be null!");
        this.children = children;
    }

    public void addNode(Tree3DNode node) {
        children.add(node);
    }

    public Tree3DNode translate(double xDelta, double yDelta, double zDelta) {
        Tree3DNode translated = new Tree3DNode(point.add(xDelta, yDelta, zDelta));

        for(Tree3DNode child : children){
            translated.addNode(child.translate(xDelta, yDelta, zDelta));
        }

        return translated;
    }

}
