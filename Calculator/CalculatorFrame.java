package com.beginner.projects.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame implements ActionListener {

    JTextField textField;
    String text = "";
    JPanel buttonGrid;

    double firstNum;
    double secondNum;
    String symbol;

    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");
    JButton b0 = new JButton("0");
    JButton bEquals = new JButton("=");
    JButton bPlus = new JButton("+");
    JButton bMinus = new JButton("-");
    JButton bTimes = new JButton("*");
    JButton bDivide = new JButton("/");
    JButton bClear = new JButton("AC");

    JButton[] buttons = {bDivide, b7, b8, b9, bTimes, b4, b5, b6, bMinus, b1, b2, b3, bPlus, b0, bEquals, bClear};


    CalculatorFrame() {
        this.setTitle("Calculator");  //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(480, 50));
        textField.setFont(new Font("Consolas", Font.PLAIN, 35));
        textField.setForeground(Color.BLACK);
        textField.setCaretColor(Color.BLACK);


        buttonGrid = new JPanel(new GridLayout(4, 4));
        buttonGrid.setPreferredSize(new Dimension(500, 405));
        buttonGrid.setBackground(Color.darkGray);

        for (JButton b:buttons) {
            b.setFocusable(false);
            b.setFont(new Font("Serif", Font.BOLD, 50));
            b.addActionListener(this);
            buttonGrid.add(b);
        }




        this.add(textField);
        this.add(buttonGrid);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1) {
            text += 1;
            textField.setText(text);
        } else if(e.getSource() == b2) {
            text += 2;
            textField.setText(text);
        } else if(e.getSource() == b3) {
            text += 3;
            textField.setText(text);
        } else if(e.getSource() == b4) {

            text += 4;
            textField.setText(text);
        } else if(e.getSource() == b5) {

            text += 5;
            textField.setText(text);
        } else if(e.getSource() == b6) {

            text += 6;
            textField.setText(text);
        } else if(e.getSource() == b7) {

            text += 7;
            textField.setText(text);
        } else if(e.getSource() == b8) {

            text += 8;
            textField.setText(text);
        } else if(e.getSource() == b9) {

            text += 9;
            textField.setText(text);
        } else if(e.getSource() == b0) {

            text += 0;
            textField.setText(text);
        } else if(e.getSource() == bPlus) {
            firstNum = Double.parseDouble(text);
            symbol = "+";
            text += "+";
            textField.setText(text);
        } else if(e.getSource() == bMinus) {
            firstNum = Double.parseDouble(text);
            symbol = "-";
            text += "-";
            textField.setText(text);
        } else if(e.getSource() == bDivide) {
            firstNum = Double.parseDouble(text);
            symbol = "/";
            text += "/";
            textField.setText(text);
        } else if(e.getSource() == bTimes) {
            firstNum = Double.parseDouble(text);
            symbol = "*";
            text += "*";
            textField.setText(text);
        } else if(e.getSource() == bEquals) {
            secondNum = Double.parseDouble(text.substring(text.indexOf(symbol)+1, text.length()));
            switch (symbol) {
                case "+":
                    text = "" + (firstNum + secondNum);
                    break;
                case "-":
                    text = "" + (firstNum - secondNum);
                    break;
                case "*":
                    text = "" + (firstNum * secondNum);
                    break;
                case "/":
                    text = "" + (firstNum / (double) secondNum);
                    break;
            }

            textField.setText(text);
        } else if(e.getSource() == bClear) {

            text = "";
            textField.setText(text);
        }
    }
}
