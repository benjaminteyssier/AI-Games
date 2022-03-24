package fr.emse.ai.util;

import java.util.Collections;

public class Minimax {

    int nodesExplored = 0;

    public Integer minimax(SimpleTwoPlyGameTree<Integer> root){
        if (root.isMax())
           return maxMin(root);
        else
            return minMax(root);
    }

    private Integer minMax(SimpleTwoPlyGameTree<Integer> node) {
        if (node.isLeaf()){
            nodesExplored++;
            return node.getValue();
        }
        else{
            nodesExplored++;
            return Collections.min(node.getChildren().stream().map(this::maxMin).toList());
        }
    }

    private Integer maxMin(SimpleTwoPlyGameTree<Integer> node) {
        if (node.isLeaf()){
            nodesExplored++;
            return node.getValue();
        }
        else{
            nodesExplored++;
            return Collections.max(node.getChildren().stream().map(this::minMax).toList());
        }
    }

}
