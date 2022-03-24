package fr.emse.ai.util;

import java.util.ArrayList;
import java.util.Collections;

public class Alphabeta {

    int nodesExplored = 0;

    public Integer alphabeta(SimpleTwoPlyGameTree<Integer> root, int alpha, int beta) {
        int value;
        if (root.isLeaf()){
            return root.getValue();
        }
        if(root.isMax()) {
            value = Integer.MIN_VALUE;
            ArrayList<SimpleTwoPlyGameTree<Integer>> childrenList = root.getChildren();
            for (SimpleTwoPlyGameTree<Integer> node : childrenList) {
                value = Math.max(value, alphabeta(node, alpha, beta));
                nodesExplored++;
                if (value >= beta)
                    break;
                alpha = Math.max(alpha, value);
            }
            return value;
        } else {
            value = Integer.MAX_VALUE;
            ArrayList<SimpleTwoPlyGameTree<Integer>> childrenList = root.getChildren();
            for (SimpleTwoPlyGameTree<Integer> node : childrenList) {
                value = Math.min(value, alphabeta(node, alpha, beta));
                nodesExplored++;
                if (value <= alpha)
                    break;
                beta = Math.min(beta, value);
            }
            return value;
        }

    }

}
