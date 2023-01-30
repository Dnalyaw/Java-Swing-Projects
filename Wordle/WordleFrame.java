package com.beginner.projects.Wordle;

import com.beginner.projects.Hangman.HangmanDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordleFrame extends JFrame implements KeyListener, ActionListener {

    JButton b1_1 = new JButton("");JButton b1_2 = new JButton("");JButton b1_3 = new JButton("");JButton b1_4 = new JButton("");JButton b1_5 = new JButton("");
    JButton b2_1 = new JButton("");JButton b2_2 = new JButton("");JButton b2_3 = new JButton("");JButton b2_4 = new JButton("");JButton b2_5 = new JButton("");
    JButton b3_1 = new JButton("");JButton b3_2 = new JButton("");JButton b3_3 = new JButton("");JButton b3_4 = new JButton("");JButton b3_5 = new JButton("");
    JButton b4_1 = new JButton("");JButton b4_2 = new JButton("");JButton b4_3 = new JButton("");JButton b4_4 = new JButton("");JButton b4_5 = new JButton("");
    JButton b5_1 = new JButton("");JButton b5_2 = new JButton("");JButton b5_3 = new JButton("");JButton b5_4 = new JButton("");JButton b5_5 = new JButton("");
    JButton b6_1 = new JButton("");JButton b6_2 = new JButton("");JButton b6_3 = new JButton("");JButton b6_4 = new JButton("");JButton b6_5 = new JButton("");

    JButton[][] wordleGrid = {
            {b1_1, b1_2, b1_3, b1_4, b1_5},
            {b2_1, b2_2, b2_3, b2_4, b2_5},
            {b3_1, b3_2, b3_3, b3_4, b3_5},
            {b4_1, b4_2, b4_3, b4_4, b4_5},
            {b5_1, b5_2, b5_3, b5_4, b5_5},
            {b6_1, b6_2, b6_3, b6_4, b6_5},
    };

    JPanel panel = new JPanel(new GridLayout(6,5));
    int attemptNm = 1;

    BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\richa\\IdeaProjects\\Swing_Basics\\src\\com\\beginner\\projects\\Wordle\\five-letter-words.txt"));
    BufferedReader allAnswers = new BufferedReader(new FileReader("C:\\Users\\richa\\IdeaProjects\\Swing_Basics\\src\\com\\beginner\\projects\\Wordle\\wordle_answers.txt"));

    ArrayList<String> possibleWds = loadWords();
    ArrayList<String> possibleAnswers = loadAnswers();
    String answer = possibleAnswers.get((int) (Math.random()*possibleAnswers.size()));
    //String answer = "WAGER";

    ArrayList<Character> possibleWrdLetters = new ArrayList<>();
    ArrayList<Character> copy = new ArrayList<>();

    JPanel extraButtonsGrid = new JPanel(new GridLayout(1, 3));
    JButton cont = new JButton("Continue");
    JButton playAgain = new JButton("Play Again");
    JLabel text = new JLabel("Score: 0");
    int score;



    WordleFrame() throws IOException {
        this.setTitle("Wordle");  //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 750);
        this.setLayout(null);

        this.addKeyListener(this);

        panel.setBounds(0, 0, 488, 565);


        for(int i=0;i<wordleGrid.length;i++){
            for(int j=0;j<wordleGrid[i].length;j++){
                wordleGrid[i][j].setFocusable(false);
                wordleGrid[i][j].setEnabled(false);
                wordleGrid[i][j].setFont(new Font("Serif", Font.BOLD, 50));
                wordleGrid[i][j].setForeground(Color.BLACK);
                //wordleGrid[i][j].addActionListener(this);
                panel.add(wordleGrid[i][j]);
            }
        }

        text.setFont(new Font("Serif", Font.BOLD, 25));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);

        playAgain.setFont(new Font("Serif", Font.BOLD, 25));
        playAgain.setFocusable(false);
        playAgain.setEnabled(false);
        playAgain.addActionListener(this);

        cont.setFont(new Font("Serif", Font.BOLD, 25));
        cont.setFocusable(false);
        cont.setEnabled(false);
        cont.addActionListener(this);

        extraButtonsGrid.setBounds(0, 565, 488, this.getHeight()-panel.getHeight()-30);
        extraButtonsGrid.add(text);
        extraButtonsGrid.add(playAgain);
        extraButtonsGrid.add(cont);



        this.add(panel);
        this.add(extraButtonsGrid);
        this.setVisible(true);
    }

    public ArrayList<String> loadWords() throws IOException {
        ArrayList <String> words = new ArrayList<String>();
        String wrd = reader.readLine();
        while(wrd != null) {
            words.add(wrd.toUpperCase());
            wrd = reader.readLine();
            //System.out.println(wrd);
        }
        return words;
    }

    public ArrayList<String> loadAnswers() throws IOException {
        ArrayList <String> words = new ArrayList<String>();
        String wrd = allAnswers.readLine();
        while(wrd != null) {
            words.add(wrd.toUpperCase());
            wrd = allAnswers.readLine();
            //System.out.println(wrd);
        }
        return words;
    }

    public void check(String p, String a, int r) {//possibleWrd, answer, row
        possibleWrdLetters.clear(); //arrayList {F,R,E,E,D}
        copy.clear(); //These are used find out which ones to set ones to yellow

        for (int i = 0; i < p.length(); i++) {
            possibleWrdLetters.add(p.charAt(i));    //letters of the word typed
            copy.add(a.charAt(i));  //copy of the answer
        }

        for (int i = 0; i < p.length(); i++) {              //iterates through our letters in the word typed
            if(possibleWrdLetters.get(i) == a.charAt(i)) {  //if the letter is in the correct space
                wordleGrid[r][i].setBackground(Color.green);    //make that color green
                //System.out.println(copy);
                copy.set(i, '_');                              //track so that if the possible word is a double lettered word, then the correct one turns green while the other turns gray
                //System.out.println(copy);
            }

        }

        for (int i = 0; i < p.length(); i++) {                      //new iteration (now that the copy has deleted all letters already in their correct spaces
            if (wordleGrid[r][i].getBackground() == Color.green){   //we already colored these ones
            } else if (copy.contains(possibleWrdLetters.get(i))) {  //if the copy still has a letter that is in the possibleWrdLetters list, since we already linked the ones in a correct position, these are in different positions
                wordleGrid[r][i].setBackground(Color.yellow);
                //System.out.println(copy);
                copy.set(copy.indexOf(possibleWrdLetters.get(i)), '_'); //delete this letter from copy list so that is the letter reappears, it doesn't also change color ex) if ans=troll and you guessed radar, both Rs shouldn't be yellow
                //System.out.println(copy);
            } else {
                wordleGrid[r][i].setBackground(new Color(200, 200,200));
            }

        }

        //System.out.println(copy);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        String response = "" + e.getKeyChar();
        int row = attemptNm-1;

        for(int j=0;j<wordleGrid[row].length;j++){//iterates through just the row in which the user is typing, but doesn't leave the row until the person types an answer
            if (wordleGrid[row][j].getText().equals("")){   //if there is a blank on the row (makes sure that the NEXT blank button changes
                if(e.getKeyCode() == 10) {  //enter should do nothing
                    break;
                }  else if (e.getKeyCode() == 8) {  //backspace should delete the item before
                    if (!wordleGrid[row][0].getText().equals("")) {//this should happen only when there is something to click backspace to
                        wordleGrid[row][j-1].setText("");
                        break;
                    } else {    //if the player hasn't typed anything in the first letter column
                        break;
                    }
                }else if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) {//if a letter has been typed
                    wordleGrid[row][j].setText(response.toUpperCase());//a letter is typed in the current row
                    break;
                }
                break;
            } else if (!wordleGrid[row][4].getText().equals("")) {//if enter is clicked and a 5 letter word is entered
                String possibleWrd = wordleGrid[row][0].getText() + wordleGrid[row][1].getText() +wordleGrid[row][2].getText() +wordleGrid[row][3].getText() +wordleGrid[row][4].getText();

                if (e.getKeyCode() == 10 && possibleWds.contains(possibleWrd)) {//if enter is clicked with a 5 letter word entered
                    check(possibleWrd, answer, row);
                    updating(row);
                    attemptNm += 1;
                    break;
                } else if (e.getKeyCode() == 8) {//if backspace is clicked
                    wordleGrid[row][4].setText("");
                    break;
                }
            }
        }
    }

    public void updating(int r) {
        boolean isCorrect = false;
        for(int i = 0; i < wordleGrid[r].length;i++) {
            if (r<=5 && !wordleGrid[r][i].getBackground().equals(Color.green)) {
                isCorrect = false;
                break;
            }else if (r<=5 && wordleGrid[r][i].getBackground().equals(Color.green)) {
                isCorrect = true;
            }
        }

        if(isCorrect) {
            score += 7-attemptNm;
            text.setText("<html><body>Good Job!<br>Score: " + score + "</body></html>");
            cont.setEnabled(true);
        } else {
            if(r == 5) {
                text.setText("<html><body>You Lost!<br>Word: " + answer + "<br>Score: " + score + "</body></html>");
                playAgain.setEnabled(true);

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton response = (JButton) e.getSource();

        if(response == playAgain) {
            score = 0;
            playAgain.setEnabled(false);
        }
        if(response == cont) {
            cont.setEnabled(false);
        }

        reset();


    }

    public void reset() {
        for(int i=0;i<wordleGrid.length;i++){
            for(int j=0;j<wordleGrid[i].length;j++){
                wordleGrid[i][j].setText("");
                wordleGrid[i][j].setBackground(new JButton().getBackground());
            }
        }

        answer = possibleAnswers.get((int) (Math.random()*possibleAnswers.size()));
        attemptNm = 1;
        text.setText("Score: " + score);
    }
}
