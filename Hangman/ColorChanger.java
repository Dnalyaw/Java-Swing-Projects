package com.beginner.projects.Hangman;

import java.awt.*;

public class ColorChanger {
    private Color hangmanColor1 = new Color(238,238,238);
    private Color hangmanColor2 = new Color(238,238,238);
    private Color hangmanColor3 = new Color(238,238,238);
    private Color hangmanColor4 = new Color(238,238,238);
    private Color hangmanColor5 = new Color(238,238,238);
    private Color hangmanColor6 = new Color(238,238,238);

    public void changeColor1() {

        hangmanColor1 = Color.BLACK;
    }
    public void changeColor2() {
        hangmanColor2 = Color.BLACK;
    }
    public void changeColor3() {

        hangmanColor3 = Color.BLACK;
    }
    public void changeColor4() {

        hangmanColor4 = Color.BLACK;
    }
    public void changeColor5() {

        hangmanColor5 = Color.BLACK;
    }
    public void changeColor6() {

        hangmanColor6 = Color.BLACK;
    }

    public void reset() {
         hangmanColor1 = new Color(238,238,238);
         hangmanColor2 = new Color(238,238,238);
         hangmanColor3 = new Color(238,238,238);
         hangmanColor4 = new Color(238,238,238);
         hangmanColor5 = new Color(238,238,238);
         hangmanColor6 = new Color(238,238,238);
    }

    public Color getHangmanColor1() {
        return hangmanColor1;
    }
    public Color getHangmanColor2() {
        return hangmanColor2;
    }
    public Color getHangmanColor3() {
        return hangmanColor3;
    }
    public Color getHangmanColor4() {
        return hangmanColor4;
    }
    public Color getHangmanColor5() {
        return hangmanColor5;
    }
    public Color getHangmanColor6() {
        return hangmanColor6;
    }
}
