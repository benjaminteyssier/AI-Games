package fr.emse.ai.adversarial.tictactoe;

import fr.emse.ai.adversarial.AlphaBetaSearch;
import fr.emse.ai.adversarial.IterativeDeepeningAlphaBetaSearch;
import fr.emse.ai.adversarial.MinimaxSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TictactoeGameplay implements ActionListener {

    public static TictactoeGame game = new TictactoeGame(2);

    public static JFrame frame = new JFrame();
    public static JPanel t_panel = new JPanel();// Pannel for text
    public static JPanel bt_panel = new JPanel();// Pannel for buttons
    public static JLabel textfield = new JLabel();// Label object to display text
    public static JButton[] bton = new JButton[9];// creating an array of button's because in tic tac toe game there are 9 position's

    MinimaxSearch<List<Integer>, Integer, Integer> minimaxSearch = MinimaxSearch.createFor(game);
    AlphaBetaSearch<List<Integer>, Integer, Integer> alphaBetaSearch = AlphaBetaSearch.createFor(game);
    IterativeDeepeningAlphaBetaSearch<List<Integer>, Integer, Integer> iterativeDeepeningAlphaBetaSearch = IterativeDeepeningAlphaBetaSearch.createFor(game, -1, 1, 10);
    public static List<Integer> state = game.getInitialState();
    private static int action;

    public static List<List<Integer>> threeInARow = new ArrayList<>(Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9),
            Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8),
            Arrays.asList(3, 6, 9),
            Arrays.asList(1, 5, 9),
            Arrays.asList(3, 5, 7)
    ));

    public static void main(String[] args) {
        TictactoeGameplay tictactoeGameplay = new TictactoeGameplay();
    }

    TictactoeGameplay() {

        initGui("Tic Tac Toe");
        if (state.get(0) == 2)
            machinePlays();

        t_panel.add(textfield);
        frame.add(t_panel, BorderLayout.NORTH);
        frame.add(bt_panel);
    }

    public void initGui(String title) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setTitle("Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(39, 210, 133));
        textfield.setForeground(new Color(35, 35, 35, 255));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText(title);
        textfield.setOpaque(true);
        t_panel.setLayout(new BorderLayout());
        t_panel.setBounds(0, 0, 800, 100);
        bt_panel.setLayout(new GridLayout(3, 3));// setting layout of bt_pannel as gridlayout
        bt_panel.setBackground(new Color(150, 150, 150));

        t_panel.add(textfield);
        frame.add(t_panel, BorderLayout.NORTH);
        frame.add(bt_panel);

        for (int i = 0; i < 9; i++) {
            bton[i] = new JButton();// creating object for each button element of array
            bt_panel.add(bton[i]);// adding each button to the pannel for buttons
            bton[i].setFont(new Font("Ink Free", Font.BOLD, 120));
            bton[i].setFocusable(false);
            bton[i].addActionListener(this);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!game.isTerminal(state)) {
            List<Integer> actions = game.getActions(state);
            if (state.get(0) == 1) {
                for (int i = 0; i < 9; i++) {
                    if (e.getSource() == bton[i]) {
                        action = i + 1;
                        if (actions.contains(action)) {
                            state = game.getResult(state, action);
                            displayGrid(state);
                            break;
                        }
                    }
                }
                if (state.get(0) == 2 && !game.isTerminal(state))
                    machinePlays();
            }
            else{
                machinePlays();
            }
        }
        displayGrid(state);
    }

    public void displayGrid(List<Integer> state) {
        for (int j = 1; j < 10; j++) {
            bton[j - 1].setForeground(new Color(0, 0, 255));
            if (state.get(j) == 1) {
                bton[j - 1].setText("O");
                textfield.setText("X turn");
            } else if (state.get(j) == 2) {
                bton[j - 1].setText("X");
                textfield.setText("O turn");
            }
        }
        if (game.isTerminal(state)) {
            System.out.println("GAME OVER: ");
            for (List<Integer> winCombination : threeInARow) {
                if (state.get(winCombination.get(0)) == state.get(winCombination.get(1)) && state.get(winCombination.get(0)) == state.get(winCombination.get(2))) {
                    if (state.get(winCombination.get(0)) == 1) {
                        System.out.println("Human wins !");
                        textfield.setText("Human wins !");
                    } else {
                        System.out.println("Machine wins!");
                        textfield.setText("Machine wins !");
                    }
                }
            }
            System.out.println("Draw !");
            textfield.setText("Draw !");
        }
    }

    public void machinePlays() {
        System.out.println("Machine player, what is your action?");
        action = minimaxSearch.makeDecision(state);
        System.out.println("Metrics for Minimax : " + minimaxSearch.getMetrics());
        action = alphaBetaSearch.makeDecision(state);
        System.out.println("Metrics for AlphaBeta : " + alphaBetaSearch.getMetrics());
        action = iterativeDeepeningAlphaBetaSearch.makeDecision(state);
        System.out.println("Metrics for IDAlphaBetaSearch : " + iterativeDeepeningAlphaBetaSearch.getMetrics());
        state = game.getResult(state, action);
        displayGrid(state);
    }
}