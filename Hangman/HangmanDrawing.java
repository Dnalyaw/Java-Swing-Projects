package com.beginner.projects.Hangman;

import javax.swing.*;
import java.awt.*;

public class HangmanDrawing extends JPanel { //this class is basically just like a JPanel
    ColorChanger colorChanger = new ColorChanger();
    Graphics2D g2D;
    public void paintComponent(Graphics g) { //adding graphics
        super.paintComponent(g);
        g2D = (Graphics2D) g;        //turning this into 2D gives more options to draw

        g2D.setPaint(Color.BLACK);
        g2D.setStroke(new BasicStroke(5));  //border thickness
        g2D.drawLine(250, 250, 400, 250);
        g2D.drawLine(325, 50, 325, 250);
        g2D.drawLine(225, 50, 325, 50);
        g2D.drawLine(225, 50, 225, 75);



        g2D.setPaint(colorChanger.getHangmanColor3());
        g2D.drawLine(200, 150, 225, 150);
        g2D.setPaint(colorChanger.getHangmanColor4());
        g2D.drawLine(250, 150, 225, 150);
        g2D.setPaint(colorChanger.getHangmanColor6());
        g2D.drawLine(225, 200, 250, 225);
        g2D.setPaint(colorChanger.getHangmanColor5());
        g2D.drawLine(225, 200, 200, 225);
        g2D.setPaint(colorChanger.getHangmanColor2());
        g2D.drawLine(225, 125, 225, 200);
        g2D.setPaint(colorChanger.getHangmanColor1());
        g2D.drawOval(200,75,50,50);

    }

    public void addParts(int limbs) {
        switch (limbs) {
            case -1:
                colorChanger.reset();
                break;
            case 1:
                colorChanger.changeColor1();
                break;
            case 2:
                colorChanger.changeColor2();
                break;
            case 3:
                colorChanger.changeColor3();
                break;
            case 4:
                colorChanger.changeColor4();
                break;
            case 5:
                colorChanger.changeColor5();
                break;
            case 6:
                colorChanger.changeColor6();
                break;
        }

        repaint();
    }


}
