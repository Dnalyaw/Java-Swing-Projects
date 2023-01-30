package com.beginner.projects.PokemonGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


//game that times your memory on how fast you can match colors

public class PokemonGameFrame extends JFrame implements ActionListener {
    JPanel buttonGrid = new JPanel(new GridLayout(4, 4));
    JPanel extraButtonsGrid = new JPanel(new GridLayout(4, 2));
    JPanel panel = new JPanel(new GridLayout(1, 2));
    JButton b1 = new JButton("");JButton b2 = new JButton("");JButton b3 = new JButton("");JButton b4 = new JButton("");JButton b5 = new JButton("");JButton b6 = new JButton("");JButton b7 = new JButton("");JButton b8 = new JButton("");JButton b9 = new JButton("");JButton b10 = new JButton("");JButton b11 = new JButton("");JButton b12 = new JButton("");JButton b13 = new JButton("");JButton b14 = new JButton("");JButton b15 = new JButton("");JButton b16 = new JButton("");
    JButton[][] buttons = {{b1,b2,b3,b4},{b5,b6,b7,b8},{b9,b10,b11,b12},{b13,b14,b15,b16}};
    JButton[] availableButtons = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16};

    JButton b17 = new JButton("");JButton b18 = new JButton("");JButton b19 = new JButton("");JButton b20 = new JButton("");
    JButton[] choiceButtons = {b17, b18, b19, b20};
    ArrayList<String> choices = new ArrayList<String>();

    int score = 5;
    JLabel scoreLabel = new JLabel("Score: " + score);

    JButton reset = new JButton("Next");

    File directory = new File("C:\\Users\\richa\\IdeaProjects\\Swing_Basics\\src\\com\\beginner\\projects\\PokemonGame\\pokemon");
    File[] imageFiles = directory.listFiles();

    BufferedReader nameFile = new BufferedReader(new FileReader("C:\\Users\\richa\\IdeaProjects\\Swing_Basics\\src\\com\\beginner\\projects\\PokemonGame\\pokemon.txt"));
    ArrayList <String> names = new ArrayList<String>();

    Icon[][] imageParts = new Icon[4][4];

    File currentPokemon;


    PokemonGameFrame() throws IOException {
        loadWords();
        this.setTitle("Pokemon Game");  //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(513, 700);
        this.setLayout(null);

        buttonGrid.setBounds(0, 0, 500, 500);
        extraButtonsGrid.setBounds(0, 500, 500, 225);

        for (JButton[] bList:buttons) {
            for (JButton b:bList) {
                b.setFocusable(false);
                b.addActionListener(this);
                buttonGrid.add(b);
            }

        }

        for (JButton b:choiceButtons) {
            b.setFont(new Font("Serif", Font.BOLD, 25));
            b.setFocusable(false);
            b.addActionListener(this);
            extraButtonsGrid.add(b);
        }

        scoreLabel.setFont(new Font("Serif", Font.BOLD, 25));
        scoreLabel.setVerticalAlignment(JLabel.CENTER);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        extraButtonsGrid.add(scoreLabel);

        reset.setFont(new Font("Serif", Font.BOLD, 25));
        reset.setEnabled(false);
        reset.setFocusable(false);
        reset.addActionListener(this);
        extraButtonsGrid.add(reset);

        panel.setBounds(0, 500, 500, 50);


        this.add(buttonGrid);
        this.add(extraButtonsGrid);
        this.add(panel);
        this.setVisible(true);

        setUpBoard();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        boolean pickedWrong = false;
        JButton response = (JButton) e.getSource();

        if(response.getIcon() == null && response.getText().equals("")) {
            for(int i = 0; i<imageParts.length;i++) {
                for(int j = 0; j<imageParts[i].length;j++) {
                    if (response.equals(buttons[i][j])) {
                        score -= 1;
                        buttons[i][j].setIcon(imageParts[i][j]);
                    }
                }
            }
        } else if (response.getText().equals(getPokemonName(currentPokemon))) {
            score += 3;
            showAll();
            response.setBackground(Color.green);
            for (JButton b:choiceButtons) {
                b.setEnabled(false);
            }
            reset.setEnabled(true);
        } else if (choices.contains(response.getText())) {
            pickedWrong = true;
            showAll();

            for (JButton b:choiceButtons) {
                b.setEnabled(false);
                if(b.getText().equals(getPokemonName(currentPokemon))) {
                    b.setBackground(Color.green);
                }
            }
            reset.setText("Reset");
            reset.setEnabled(true);
        } else if (response.getText().equals("Next")) {
            try {
                setUpBoard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (response.getText().equals("Reset")) {
            score = 5;
            try {
                setUpBoard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (score == 0) {
            showAll();
            scoreLabel.setText("You Lost");
            for (JButton b:choiceButtons) {
                b.setEnabled(false);
                if(b.getText().equals(getPokemonName(currentPokemon))) {
                    b.setBackground(Color.green);
                }
            }
            reset.setText("Reset");
            reset.setEnabled(true);
        } else if (pickedWrong){
            scoreLabel.setText("Final Score: " + score);
        }else{
            scoreLabel.setText("Score: " + score);
        }



    }

    public String getPokemonName(File image) {
        int pokedexNumber;
        //System.out.println(image.getName());
        if (image.getName().contains("-")) {
            pokedexNumber = Integer.parseInt(image.getName().substring(0, image.getName().indexOf("-")));
        } else {
            pokedexNumber = Integer.parseInt(image.getName().substring(0, image.getName().indexOf(".")));
        }
        //System.out.println(names.get(pokedexNumber));
        pokedexNumber--;
        return names.get(pokedexNumber);
    }

    public void loadWords() throws IOException {
        String wrd = nameFile.readLine();
        while(wrd != null) {
            names.add(wrd.toUpperCase());
            wrd = nameFile.readLine();
            //System.out.println(wrd);
        }
    }

    public ImageIcon imageOf(String fileName) {
        System.out.println(fileName);
        return new ImageIcon(fileName);
    }

    public void createImageParts(File image) throws IOException {
        int x = 0;
        int y = 0;
        for(int i = 0; i<imageParts.length;i++) {
            for(int j = 0; j<imageParts[i].length;j++) {
                BufferedImage imageTest = ImageIO.read(image).getSubimage(x, y, 64, 64);
                Image randomPokemonImage = imageTest.getScaledInstance(125, 125, BufferedImage.SCALE_SMOOTH);
                Icon newImage = new ImageIcon(randomPokemonImage);
                imageParts[i][j] = newImage;
                x += 64;
            }
            x = 0;
            y += 64;
        }
    }

    public void showAll(){
        for(int i = 0; i<imageParts.length;i++) {
            for(int j = 0; j<imageParts[i].length;j++) {
                buttons[i][j].setIcon(imageParts[i][j]);
            }

        }
    }

    public void setUpBoard() throws IOException {
        for (JButton[] row: buttons) {
            for (JButton b: row) {
                b.setIcon(null);
            }
        }


        currentPokemon = imageFiles[(int) (Math.random()* imageFiles.length)];
        //System.out.println(getPokemonName(currentPokemon) + " - " + currentPokemon.getName());
        createImageParts(currentPokemon);

        choices.clear();
        choices.add(getPokemonName(currentPokemon));
        choices.add(getPokemonName(imageFiles[(int) (Math.random()* imageFiles.length)]));
        choices.add(getPokemonName(imageFiles[(int) (Math.random()* imageFiles.length)]));
        choices.add(getPokemonName(imageFiles[(int) (Math.random()* imageFiles.length)]));

        Collections.shuffle(choices);

        for (int i = 0; i < choiceButtons.length; i++) {
            choiceButtons[i].setBackground(null);
            choiceButtons[i].setText(choices.get(i));
            choiceButtons[i].setEnabled(true);
        }

        reset.setText("Next");
        reset.setEnabled(false);


    }
}
