package de.emnichtda.fx3dwireframerender.renderer;

public class Tree3D {

    Tree3DNode root;

    public Tree3D(Tree3DNode root) {
        this.root = root;
    }

    public Tree3D() { }

    public Tree3DNode getRoot() {
        return root;
    }

    public void setRoot(Tree3DNode root) {
        this.root = root;
    }

    public Tree3D translate(double xDelta, double yDelta, double zDelta){
        return new Tree3D(root.translate(xDelta, yDelta, zDelta));
    }

}
