package com.beginner.projects.MatchGame;

import com.beginner.projects.Hangman.HangmanDrawing;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MatchgameFrame extends JFrame implements ActionListener {
    JPanel buttonGrid = new JPanel(new GridLayout(4, 4));
    JPanel panel = new JPanel(new GridLayout(1, 2));
    JButton b1 = new JButton("");JButton b2 = new JButton("");JButton b3 = new JButton("");JButton b4 = new JButton("");JButton b5 = new JButton("");JButton b6 = new JButton("");JButton b7 = new JButton("");JButton b8 = new JButton("");JButton b9 = new JButton("");JButton b10 = new JButton("");JButton b11 = new JButton("");JButton b12 = new JButton("");JButton b13 = new JButton("");JButton b14 = new JButton("");JButton b15 = new JButton("");JButton b16 = new JButton("");
    JButton[] buttons = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16};
    JButton[] availableButtons = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16};
    ArrayList<Color> shuffledColors = new ArrayList<Color>();
    Color[] allColors;

    Color defaultColor = Color.white;

    JLabel label = new JLabel("      Timer: 0.00");
    JButton reset = new JButton("Start");

    double time = 0;
    Timer timer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            double roundedTime = Math.round(time*100.0)/100.0;
            label.setText("      Time: " + roundedTime);
            time += .015;
        }
    });


    boolean gameStarted = false;
    boolean guessMade = false;

    JButton firstGuess = new JButton();
    JButton response;


    MatchgameFrame() throws InterruptedException {
        this.setTitle("Match Game");  //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(513, 585);
        this.setLayout(null);

        buttonGrid.setBounds(0, 0, 500, 500);

        for (JButton b:buttons) {
            b.setFocusable(false);
            b.setEnabled(false);
            b.addActionListener(this);
            buttonGrid.add(b);

        }



        panel.setBounds(0, 500, 500, 50);

        label.setFont(new Font("Serif", Font.BOLD, 25));
        label.setVerticalAlignment(JLabel.CENTER);


        reset.setFont(new Font("Serif", Font.BOLD, 25));
        reset.setFocusable(false);
        reset.addActionListener(this);
        reset.setEnabled(true);

        panel.add(label);
        panel.add(reset);

        this.add(buttonGrid);
        this.add(panel);
        this.setVisible(true);

        //setUpBoard();

    }

    public void setUpBoard() {
        reset.setEnabled(false);
        reset.setText("Reset");
        availableButtons = buttons.clone();
        allColors = new Color[]{Color.RED, Color.RED, Color.ORANGE, Color.ORANGE,Color.YELLOW, Color.YELLOW,Color.GREEN, Color.GREEN,Color.BLUE, Color.BLUE,Color.gray, Color.gray,Color.BLACK, Color.BLACK,Color.PINK, Color.PINK};
        shuffledColors.clear();

        shuffledColors.addAll(Arrays.asList(allColors));
        Collections.shuffle(shuffledColors);

        for (int i = 0; i<buttons.length;i++) {
            buttons[i].setEnabled(false);
            buttons[i].setBackground(shuffledColors.get(i));
        }
        javax.swing.Timer showAll = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i<buttons.length;i++) {
                    buttons[i].setEnabled(true);
                    buttons[i].setBackground(defaultColor);
                }

            }
        });
        showAll.setRepeats(false);
        showAll.start();



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        response = (JButton) e.getSource();
        //System.out.println(guessMade);

        javax.swing.Timer showBoth = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                response.setBackground(defaultColor);
                firstGuess.setBackground(defaultColor);

                for (JButton b:availableButtons) {
                    if (b != null) {
                        b.setEnabled(true);
                    }
                }

            }
        });
        showBoth.setDelay(500);
        showBoth.setRepeats(false);

        if(!gameStarted && response != reset) {
            gameStarted = true;
            timer.start();
        }

        if(response != reset) {
            response.setBackground(shuffledColors.get(findIndex(response)));
            if(!guessMade) {
                firstGuess = response;
                firstGuess.setEnabled(false);
                guessMade = true;
            } else {
                if(response.getBackground() == firstGuess.getBackground()) {
                    response.setEnabled(false);
                    firstGuess.setEnabled(false);
                    availableButtons[findIndex(response)] =  null;
                    availableButtons[findIndex(firstGuess)] =  null;
                    check();

                } else if(response.getBackground() != firstGuess.getBackground() && guessMade) {
                    for (JButton b:availableButtons) {
                        if(b != null) {
                            b.setEnabled(false);
                        }
                    }
                    showBoth.setRepeats(false);
                    showBoth.start();
                }
                guessMade = false;
            }
        } else {
            gameStarted = false;
            time = 0;
            label.setText("      Time: " + time);
            setUpBoard();

        }


    }

    public int findIndex(JButton b) {
        int index = 0;
        for (int i=0;i<buttons.length;i++) {
            if(buttons[i] == b) {
                index = i;
                break;
            }
        }


        return index;
    }

    public void check() {
        boolean isFinished = true;
        for (JButton b: buttons) {
            if(b.getBackground().equals(defaultColor)) {
                isFinished = false;
                break;
            }
        }

        if(isFinished) {
            timer.stop();
            reset.setEnabled(true);
        }
    }
}
