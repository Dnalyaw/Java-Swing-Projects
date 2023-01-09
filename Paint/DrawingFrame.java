package com.beginner.projects.Paint;

import com.beginner.tutorial.extrastuff.Sliders;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawingFrame extends JFrame implements ActionListener, ChangeListener {
    DragPanel panel = new DragPanel();
    JPanel sliderPanel = new JPanel();
    JSlider slider = new JSlider(0, 100);
    JPanel buttonGrid = new JPanel(new GridLayout());
    JButton clear = new JButton("Clear");
    JButton erase = new JButton("Erase");
    JButton fill = new JButton("Fill");
    JButton draw = new JButton("Draw");
    JButton[] buttons = {draw, erase, fill, clear};
    JLabel label = new JLabel("Size: 50");


    DrawingFrame() {
        this.setTitle("Paint");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(null);

        panel.setBounds(0, 0, this.getWidth(), this.getHeight()*4/5);

        label.setFont(new Font("Serif", Font.BOLD, 35));
        slider.setPreferredSize(new Dimension(275, 25));
        slider.addChangeListener(this);
        sliderPanel.setBounds(0, this.getHeight()*4/5, (this.getWidth()-25)/2, (this.getHeight()-175)/5);
        sliderPanel.add(label);
        sliderPanel.add(slider);

        for (JButton b:buttons) {
            b.setFont(new Font("Serif", Font.BOLD, 15));
            b.setFocusable(false);
            buttonGrid.add(b);
        }
        buttonGrid.setBounds((this.getWidth()-25)/2, this.getHeight()*4/5, (this.getWidth())/2, (this.getHeight()-175)/5);

        this.add(panel);
        this.add(sliderPanel);
        this.add(buttonGrid);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText("Size: " + slider.getValue());
        panel.changeSize(slider.getValue());
    }
}
