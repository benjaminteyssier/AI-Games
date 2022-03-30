package fr.emse.ai.util;

import java.util.ArrayList;

public class MinimaxAndAlphabeta {
	public static void main(String[] args) {
		// niveau 4
		ArrayList<SimpleTwoPlyGameTree<Integer>> sublist41 = new ArrayList<SimpleTwoPlyGameTree<Integer>>();
		sublist41.add(new SimpleTwoPlyGameTree<Integer>(20, true));
		sublist41.add(new SimpleTwoPlyGameTree<Integer>(22, true));

		ArrayList<SimpleTwoPlyGameTree<Integer>> sublist42 = new ArrayList<SimpleTwoPlyGameTree<Integer>>();
		sublist42.add(new SimpleTwoPlyGameTree<Integer>(25, true));
		sublist42.add(new SimpleTwoPlyGameTree<Integer>(30, true));

		ArrayList<SimpleTwoPlyGameTree<Integer>> sublist43 = new ArrayList<SimpleTwoPlyGameTree<Integer>>();
		sublist43.add(new SimpleTwoPlyGameTree<Integer>(17, true));
		sublist43.add(new SimpleTwoPlyGameTree<Integer>(0, true));
		sublist43.add(new SimpleTwoPlyGameTree<Integer>(30, true));
		sublist43.add(new SimpleTwoPlyGameTree<Integer>(15, true));
		
		ArrayList<SimpleTwoPlyGameTree<Integer>> sublist44 = new ArrayList<SimpleTwoPlyGameTree<Integer>>();
		sublist44.add(new SimpleTwoPlyGameTree<Integer>(50, true));
		sublist44.add(new SimpleTwoPlyGameTree<Integer>(53, true));

		ArrayList<SimpleTwoPlyGameTree<Integer>> sublist45 = new ArrayList<SimpleTwoPlyGameTree<Integer>>();
		sublist45.add(new SimpleTwoPlyGameTree<Integer>(65, true));
		sublist45.add(new SimpleTwoPlyGameTree<Integer>(20, true));
		
		ArrayList<SimpleTwoPlyGameTree<Integer>> sublist46 = new ArrayList<SimpleTwoPlyGameTree<Integer>>();
		sublist46.add(new SimpleTwoPlyGameTree<Integer>(10, true));
		sublist46.add(new SimpleTwoPlyGameTree<Integer>(8, true));

		ArrayList<SimpleTwoPlyGameTree<Integer>> sublist47 = new ArrayList<SimpleTwoPlyGameTree<Integer>>();
		sublist47.add(new SimpleTwoPlyGameTree<Integer>(5, true));
		sublist47.add(new SimpleTwoPlyGameTree<Integer>(2, true));
		
		ArrayList<SimpleTwoPlyGameTree<Integer>> sublist48 = new ArrayList<SimpleTwoPlyGameTree<Integer>>();
		sublist48.add(new SimpleTwoPlyGameTree<Integer>(92, true));
		sublist48.add(new SimpleTwoPlyGameTree<Integer>(1, true));
		
		ArrayList<SimpleTwoPlyGameTree<Integer>> sublist49 = new ArrayList<SimpleTwoPlyGameTree<Integer>>();
		sublist49.add(new SimpleTwoPlyGameTree<Integer>(25, true));
		sublist49.add(new SimpleTwoPlyGameTree<Integer>(30, true));

// niveau 3	
		SimpleTwoPlyGameTree<Integer> subTree31 = new SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false,
				sublist41);
		SimpleTwoPlyGameTree<Integer> subTree32 = new SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false,
				sublist42);
		SimpleTwoPlyGameTree<Integer> subTree33 = new SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false,
				sublist43);
		SimpleTwoPlyGameTree<Integer> subTree34 = new SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false,
				sublist44);
		SimpleTwoPlyGameTree<Integer> subTree35 = new SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false,
				sublist45);
		SimpleTwoPlyGameTree<Integer> subTree36 = new SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false,
				sublist46);
		SimpleTwoPlyGameTree<Integer> subTree37 = new SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false,
				sublist47);
		SimpleTwoPlyGameTree<Integer> subTree38 = new SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false,
				sublist48);
		SimpleTwoPlyGameTree<Integer> subTree39 = new SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false,
				sublist49);

// niveau 2		
		SimpleTwoPlyGameTree<Integer> subTree21 = new   SimpleTwoPlyGameTree<Integer>(Integer.MAX_VALUE, true);
		subTree21.addChild(subTree31);
		subTree21.addChild(subTree32);
		subTree21.addChild(subTree33);
		SimpleTwoPlyGameTree<Integer> subTree22 = new   SimpleTwoPlyGameTree<Integer>(Integer.MAX_VALUE, true);
		subTree22.addChild(subTree34);
		subTree22.addChild(subTree35);
		
		SimpleTwoPlyGameTree<Integer> subTree23 = new   SimpleTwoPlyGameTree<Integer>(Integer.MAX_VALUE, true);
		subTree23.addChild(subTree36);
		subTree23.addChild (subTree37);
		
		SimpleTwoPlyGameTree<Integer> subTree24 = new   SimpleTwoPlyGameTree<Integer>(Integer.MAX_VALUE, true);
		subTree24.addChild(subTree38);
		subTree24.addChild(subTree39);

		
// niveau 1
		SimpleTwoPlyGameTree<Integer> subTree11 = new   SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false);
		subTree11.addChild(subTree21);
		subTree11.addChild(subTree22);
		
		SimpleTwoPlyGameTree<Integer> subTree12 = new   SimpleTwoPlyGameTree<Integer>(Integer.MIN_VALUE, false);
		subTree12.addChild(subTree23);
		subTree12.addChild(subTree24);

// Niveau 0
		SimpleTwoPlyGameTree<Integer> tree = new   SimpleTwoPlyGameTree<Integer>(Integer.MAX_VALUE, true);
		tree.addChild(subTree11);

		tree.addChild(subTree12);

		System.out.println("Minimax :");
		Minimax minimax = new Minimax();
		System.out.print("Result : ");
		System.out.println(minimax.minimax(tree));
		System.out.print("Node explored : ");
		System.out.println(minimax.nodesExplored);

		System.out.println("Alphabeta :");
		Alphabeta alphaBeta = new Alphabeta();
		System.out.print("Result : ");
		System.out.println(alphaBeta.alphabeta(tree,Integer.MIN_VALUE,Integer.MAX_VALUE));
		System.out.print("Node explored : ");
		System.out.println(alphaBeta.nodesExplored);
	}
}
