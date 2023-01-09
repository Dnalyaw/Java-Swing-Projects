package com.beginner.projects.Paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

public class DragPanel extends JPanel {
    Graphics2D g2D;
    Point cursorPt = new Point(0,0);

    DrawingGraphics graphics = new DrawingGraphics();
    DragPanel() {
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }

    public void paintComponent(Graphics g) {//repaints the panel to
        super.paintComponent(g);    //the parent of class is JPanel
        if (g2D == null) {
            g2D = (Graphics2D) g;
            g2D.setStroke(new BasicStroke(5));
            //g2D.setColor(new Color(238, 238, 238));
            g2D.setColor(Color.black);
            g2D.fillOval(cursorPt.x-(graphics.getFontSz()/2), cursorPt.y-(graphics.getFontSz()/2), graphics.getFontSz(), graphics.getFontSz());

        }

    }

    public void changeSize(int size) {
        graphics.setFontSz(size);
    }
    private class ClickListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            cursorPt = e.getPoint();
            System.out.println(cursorPt);
            repaint();
        }
    }

    private class DragListener extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent e) {
            cursorPt = e.getPoint();
            System.out.println(cursorPt);
            g2D.fillOval(cursorPt.x-(graphics.getFontSz()/2), cursorPt.y-(graphics.getFontSz()/2), graphics.getFontSz(), graphics.getFontSz());
            repaint();
        }
    }
}
