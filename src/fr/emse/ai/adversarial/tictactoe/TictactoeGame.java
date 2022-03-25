package fr.emse.ai.adversarial.tictactoe;

import fr.emse.ai.adversarial.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TictactoeGame implements Game<List<Integer>, Integer, Integer> {

    public final static Integer[] players = {0, 1};
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
        if (newState.get(0) == 0) {
            newState.set(action, 1);
            newState.set(0, 1);
        } else if (newState.get(0) == 1) {
            newState.set(action, 2);
            newState.set(0, 0);
        }
        return newState;
    }

    @Override
    public boolean isTerminal(List<Integer> state) {

        for (int lineNumber = 1; lineNumber < 4; lineNumber++) {
            if (state.get(3 * lineNumber - 2) == state.get(3 * lineNumber - 1) && state.get(3 * lineNumber - 2) == state.get(3 * lineNumber) && state.get(3*lineNumber)!=0)
                return true;
        }
        for (int columnNumber = 1; columnNumber < 3; columnNumber++) {
            if (state.get(columnNumber) == state.get(columnNumber + 3) && state.get(columnNumber) == state.get(columnNumber + 6) && state.get(columnNumber)!=0)
                return true;
        }
        if (state.get(1) == state.get(5) && state.get(1) == state.get(9) && state.get(1)!=0)
            return true;
        if (state.get(3) == state.get(5) && state.get(3) == state.get(7) && state.get(3)!=0)
            return true;
        else
            return false;

    }

    @Override
    public double getUtility(List<Integer> state, Integer player) {
        double score = 0;
        for (int i = 1; i < 4; i++) {
            score += evaluateLine(i, state);
            score += evaluateColumn(i, state);
        }
        score += evaluateDiagonal(1, state);
        score += evaluateDiagonal(2, state);
        return score;
    }

    public double evaluateLine(int lineNumber, List<Integer> state) {
        double score = 0;
        for (int i = 0; i < 3; i++) {
            List<Integer> line = state.subList(3 * lineNumber - 2, 3 * lineNumber + 1);
            if (line.get(i) == 1)
                score++;
            else if (line.get(i) == 2)
                score--;
        }
        return score;
    }

    public double evaluateColumn(int columnNumber, List<Integer> state) {
        double score = 0;
        for (int i = 0; i < 3; i++) {
            if (state.get(columnNumber + 3 * i) == 1)
                score++;
            else if (state.get(columnNumber + 3 * i) == 2)
                score--;
        }
        return score;
    }

    public double evaluateDiagonal(int diagonalNumber, List<Integer> state) {
        double score = 0;
        if (diagonalNumber == 1) {
            if (state.get(1) == 1)
                score++;
            else if (state.get(1) == 2)
                score--;
            if (state.get(9) == 1)
                score++;
            else if (state.get(9) == 2)
                score--;
        } else if (diagonalNumber == 2) {
            if (state.get(3) == 1)
                score++;
            else if (state.get(3) == 2)
                score--;
            if (state.get(7) == 1)
                score++;
            else if (state.get(7) == 2)
                score--;
        }
        if (state.get(5) == 1)
            score++;
        else if (state.get(5) == 2)
            score--;

        return score;
    }
}
