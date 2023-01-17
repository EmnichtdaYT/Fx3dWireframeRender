package de.emnichtda.fx3dwireframerender.renderer;

public final class Utils {
    private Utils() { }

    public static double project(double x, double depth, double focalLength){
        return (focalLength * x) / (focalLength + depth);
    }

}
