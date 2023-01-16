package de.emnichtda.fx3dwireframerender.test;

import de.emnichtda.fx3dwireframerender.renderer.Tree3D;
import de.emnichtda.fx3dwireframerender.renderer.Tree3DNode;
import javafx.geometry.Point3D;

import java.util.Random;

public class Test {

    private static final Random random = new Random();
    private static int maxRandomNodeCount = -1;

    public static Tree3DNode generateRandomTree() {
        Tree3DNode node = new Tree3DNode(new Point3D(random.nextDouble(), random.nextDouble(), random.nextDouble()));

        for (int i = maxRandomNodeCount; i > 0; i--) {
            maxRandomNodeCount--;
            node.addNode(generateRandomTree());
        }

        return node;
    }

    public static void main(String[] args) {

        int nodesCompared = 0;
        int nodesSeen = 0;

        for(int i = 0; i < 5000; i++) {
            maxRandomNodeCount = random.nextInt(99)+1;
            Tree3D tree = new Tree3D(generateRandomTree());
            Tree3D treeTranslated = tree.translate(-1, 0, 0);

            int dive = random.nextInt(40) + 1;
            Tree3DNode translatedCurrent = treeTranslated.getRoot();
            Tree3DNode originalCurrent = tree.getRoot();
            for (; dive > 0; dive--) {
                nodesCompared += 2;
                if (!translatedCurrent.getPoint().equals(originalCurrent.getPoint().add(-1, 0, 0))) {
                    System.out.println("[FAILED] POINTS MISMATCH");
                    return;
                }
                if (originalCurrent.getChildren().size() != translatedCurrent.getChildren().size()) {
                    System.out.println("[FAILED] LENGTH MISMATCH!");
                    return;
                }

                if (originalCurrent.getChildren().size() == 0) {
                    break;
                }

                nodesSeen += originalCurrent.getChildren().size()*2;

                int d = random.nextInt(originalCurrent.getChildren().size());

                originalCurrent = originalCurrent.getChildren().get(d);
                translatedCurrent = translatedCurrent.getChildren().get(d);
            }
        }

        System.out.println("[*] CHECK COMPLETE!");
        System.out.println("[*] Nodes seen: " + nodesSeen);
        System.out.println("[*] Nodes compared: " + nodesCompared);
    }
}
