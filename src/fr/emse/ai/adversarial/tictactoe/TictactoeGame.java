package fr.emse.ai.adversarial.tictactoe;

import fr.emse.ai.adversarial.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TictactoeGame implements Game<List<Integer>, Integer, Integer> {

    public final static Integer[] players = {1, 2};
    public final static List<Integer> initialState = new ArrayList<>(); //[player turn, 1 row 1 column, 1 row 2 column ...]

    public TictactoeGame(int player) {
        initialState.add(player); //player starting the game
        for (int i = 0; i < 9; i++) {
            initialState.add(0);
        }
    }


    @Override
    public List<Integer> getInitialState() {
        return initialState;
    }

    @Override
    public Integer[] getPlayers() {
        return players;
    }

    @Override
    public Integer getPlayer(List<Integer> state) {
        return state.get(0);
    }

    @Override
    public List<Integer> getActions(List<Integer> state) {
        List<Integer> actions = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (state.get(i) == 0)
                actions.add(i);
        }
        return actions;
    }

    @Override
    public List<Integer> getResult(List<Integer> state, Integer action) {
        List<Integer> newState = new ArrayList<>(state);
        if (newState.get(0) == 1) {
            newState.set(action, 1);
            newState.set(0, 2);
        } else if (newState.get(0) == 2) {
            newState.set(action, 2);
            newState.set(0, 1);
        }

        return newState;
    }

    @Override
    public boolean isTerminal(List<Integer> state) {

        for (List<Integer> winCombination : threeInARow) {
            if (state.get(winCombination.get(0)) == state.get(winCombination.get(1)) && state.get(winCombination.get(0)) == state.get(winCombination.get(2))) {
                if (state.get(winCombination.get(0)) != 0)
                    return true;
            }
        }
        for (int i = 1; i < 10; i++) {
            if (state.get(i) == 0)
                return false;
        }
        return true;
    }

    public double getUtility(List<Integer> state, Integer player) {
        int playerScore = 0;
        int opponentScore = 0;
        int score = 0;
        int cell = 0;

        for (List<Integer> combination : threeInARow) {
            playerScore = 0;
            opponentScore = 0;
            for (int i = 0; i < 3; i++) {
                cell = state.get(combination.get(i));
                if (cell == player)
                    playerScore++;
                else if (cell == invert(player))
                    opponentScore++;
            }
            score += coefficientMatrix.get(playerScore).get(opponentScore);
        }
        return score;
    }

    int invert(int x) {
        if (x == 1) return 2;
        else return 1;
    }

    List<List<Integer>> threeInARow = new ArrayList<>(Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9),
            Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8),
            Arrays.asList(3, 6, 9),
            Arrays.asList(1, 5, 9),
            Arrays.asList(3, 5, 7)
    ));

    List<List<Integer>> coefficientMatrix = new ArrayList<>(Arrays.asList(
            Arrays.asList(0, -10, -100, -1000),
            Arrays.asList(10, 0, 0, 0),
            Arrays.asList(100, 0, 0, 0),
            Arrays.asList(1000, 0, 0, 0)
    ));
}
