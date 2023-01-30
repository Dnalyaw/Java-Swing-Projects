package com.beginner.projects.Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanFrame extends JFrame implements ActionListener {

    JPanel phrasePanel;
    HangmanDrawing hangman;
    JPanel lettersGrid;

    JButton a = new JButton("a");
    JButton b = new JButton("b");
    JButton c = new JButton("c");
    JButton d = new JButton("d");
    JButton e = new JButton("e");
    JButton f = new JButton("f");
    JButton g = new JButton("g");
    JButton h = new JButton("h");
    JButton i = new JButton("i");
    JButton j = new JButton("j");
    JButton k = new JButton("k");
    JButton l = new JButton("l");
    JButton m = new JButton("m");
    JButton n = new JButton("n");
    JButton o = new JButton("o");
    JButton p = new JButton("p");
    JButton q = new JButton("q");
    JButton r = new JButton("r");
    JButton s = new JButton("s");
    JButton t = new JButton("t");
    JButton u = new JButton("u");
    JButton v = new JButton("v");
    JButton w = new JButton("w");
    JButton x = new JButton("x");
    JButton y = new JButton("y");
    JButton z = new JButton("z");
    JButton[] alphabet = {a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z};
    JPanel buttonsGrid = new JPanel(new GridLayout(1, 2));
    JButton playAgain = new JButton("Play Again");
    JButton cont = new JButton("Next Word");
    JButton[] extras = {playAgain, cont};

    int strikes = 0;
    JButton response;

    BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\richa\\IdeaProjects\\Swing_Basics\\src\\com\\beginner\\projects\\Hangman\\1-1000.txt"));
    ArrayList<String> allWords;


    String blankWord = "";

    JLabel phrase;

    ArrayList <String> foundLetters = new ArrayList<String>();

    String word;
    int score = 0;


    HangmanFrame() throws IOException {
        this.setTitle("Hangman");  //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1000, 500);
        this.setLayout(null);

        phrasePanel = new JPanel(new GridBagLayout());
        phrasePanel.setBounds(0, 0,this.getWidth()/2, this.getHeight()*2/5);
        phrasePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));

        allWords = loadWords();
        word = pickRandomWord(allWords);
        setBoard();
        phrase = new JLabel(blankWord);
        phrase.setFont(new Font("Serif", Font.BOLD, 50));
        phrase.setHorizontalAlignment(JLabel.CENTER);
        phrase.setBounds(0, 0,this.getWidth()/2, this.getHeight());

        phrasePanel.add(phrase);
        phrasePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        buttonsGrid.setBounds(0, this.getWidth()/4,this.getWidth()/2, this.getHeight()/10);
        for(JButton b: extras) {
            b.setFocusable(false);
            b.setFont(new Font("Serif", Font.BOLD, 25));
            b.addActionListener(this);
            b.setEnabled(false);
            buttonsGrid.add(b);
        }

        hangman = new HangmanDrawing();
        hangman.setBounds(this.getWidth()/2, 0,this.getWidth()/2, this.getHeight()*3/5);

        lettersGrid = new JPanel(new GridLayout(2, 13));
        lettersGrid.setBounds(-5, this.getHeight()*3/5,this.getWidth(), this.getHeight()*13/40);


        for(JButton letter: alphabet) {
            letter.setFocusable(false);
            letter.setFont(new Font("Serif", Font.BOLD, 50));
            letter.addActionListener(this);
            lettersGrid.add(letter);
        }



        this.add(phrasePanel);
        this.add(hangman);
        this.add(buttonsGrid);
        this.add(lettersGrid);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        response = (JButton) e.getSource();
        check(response);
    }

    public void check(JButton r) {
        if(!word.contains(r.getText()) && r.getText().length() == 1) {  //if no letters in the word (guessed wrong) and the button clicked was a letter button
            strikes += 1;
            hangman.addParts(strikes);
            if (strikes >= 6) {     //if you lost
                phrase.setFont(new Font("Serif", Font.BOLD, 35));
                blankWord = "<html><body>Game Over!<br>Word: " + word.toUpperCase() + "<br>Score: " + score + "</body></html>";
                playAgain.setEnabled(true);
                for (JButton b:alphabet) {
                    b.setEnabled(false);
                }
            }
        } else if(word.contains(r.getText()) && r.getText().length() == 1) {    //if letter is in the word and the button clicked was a letter button
            //System.out.println(blankWord);
            foundLetters.add(r.getText());

            blankWord = "";

            //rewriting the label to match correctly

            for (int i = 0; i < word.length(); i++) {
                if (foundLetters.contains("" + word.charAt(i))) {
                    blankWord += word.charAt(i) + " ";
                }  else {
                    blankWord += "_ ";
                }
            }

            //If the text has no underscores, that means that you have no letters to find, therefore you have won

            if(!blankWord.contains("_")){
                blankWord = "Good Job!";
                cont.setEnabled(true);
                for (JButton b:alphabet) {
                    b.setEnabled(false);
                }
            }

        } else {    //either the play again button or the continue button
            word = pickRandomWord(allWords);
            foundLetters.clear();
            setBoard();
            strikes = 0;
            hangman.addParts(-1);
            phrase.setFont(new Font("Serif", Font.BOLD, 50));

            for (JButton b: alphabet) {
                b.setEnabled(true);
            }
            if (r.getText().equals("Next Word")) {
                score += 1;
            } if (r.getText().equals("Play Again")) {
                score = 0;
            }
        }


        r.setEnabled(false);
        phrase.setText(blankWord);

    }

    public ArrayList<String> loadWords() throws IOException {
        ArrayList <String> words = new ArrayList<String>();
        String wrd = reader.readLine();
        while(wrd != null) {
            words.add(wrd);
            wrd = reader.readLine();
            //System.out.println(wrd);
        }
        return words;
    }


    public String pickRandomWord(ArrayList <String> wds) {
        String wrd = wds.get((int)(Math.random()*wds.size()));
        return wrd;
    }

    public void setBoard() {
        blankWord = "";
        for(int i = 0;i < word.length();i++) {
            blankWord += "_ ";
        }
    }
}
