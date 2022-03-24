package fr.emse.ai.adversarial.tictactoe;

import fr.emse.ai.adversarial.AlphaBetaSearch;
import fr.emse.ai.adversarial.IterativeDeepeningAlphaBetaSearch;
import fr.emse.ai.adversarial.MinimaxSearch;
import fr.emse.ai.adversarial.nim.NimGame;

import java.util.List;
import java.util.Scanner;

public class TictactoeGameplay {
    public static void main(String[] args) {

        TictactoeGame game = new TictactoeGame(0);

        MinimaxSearch<List<Integer>, Integer, Integer> minimaxSearch = MinimaxSearch.createFor(game);
        AlphaBetaSearch<List<Integer>, Integer, Integer> alphaBetaSearch = AlphaBetaSearch.createFor(game);
        IterativeDeepeningAlphaBetaSearch<List<Integer>, Integer, Integer> iterativeDeepeningAlphaBetaSearch = IterativeDeepeningAlphaBetaSearch.createFor(game, -1, 1, 10);
        List<Integer> state = game.getInitialState();
        while (!game.isTerminal(state)) {
            System.out.println("======================");
            for (int i = 0; i < 3; i++) {
                System.out.print(state.get(3 * i + 1));
                System.out.print(" ");
                System.out.print(state.get(3 * i + 2));
                System.out.print(" ");
                System.out.print(state.get(3 * i + 3));
                System.out.print(" ");
                System.out.println();
            }
            int action = -1;
            if (state.get(0) == 0) {
                //Human turn
                List<Integer> actions = game.getActions(state);
                Scanner in = new Scanner(System.in);

                while (!actions.contains(action)) {
                    System.out.println("Human player, which cell of the grid do you want to play ?");
                    action = in.nextInt();
                }
            } else {
                //machine
                System.out.println("Machine player, what is your action?");
                action = minimaxSearch.makeDecision(state);
                System.out.println("Metrics for Minimax : " + minimaxSearch.getMetrics());
                //alphaBetaSearch.makeDecision(state);
                System.out.println("Metrics for AlphaBeta : " + alphaBetaSearch.getMetrics());
                //iterativeDeepeningAlphaBetaSearch.makeDecision(state);
                //System.out.println("Metrics for IDAlphaBetaSearch : " + iterativeDeepeningAlphaBetaSearch.getMetrics());
            }
            System.out.println("Chosen action is " + action);
            state = game.getResult(state, action);
        }
        System.out.println("GAME OVER: ");
        if (state.get(0) == 0)
            System.out.println("Human wins!");
        else
            System.out.println("Machine wins!");

    }
}

