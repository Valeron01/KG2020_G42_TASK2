package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {
    public DrawPanel(){
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                mX = mouseEvent.getX();
                mY = mouseEvent.getY();
                paint(getGraphics());
            }
        });
    }

    int mX = 10;
    int mY = 10;

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        PixelDrawer drawer = new BufferedImagePixelDrawer(bi);

        Graphics gr = bi.createGraphics();
        gr.setColor(Color.white);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.dispose();
        LineDrawer ld = new WuLineDrawer(drawer);
        drawSnowflake(getWidth() / 2, getHeight() / 2, 64, 250, ld);

        ld.drawLine(getWidth() / 2, getHeight() / 2, mX, mY);


        g.drawImage(bi, 0, 0, null);
    }

    void drawSnowflake(int x, int y, int n, int r, LineDrawer ld) {

        for (int i = 0; i < n; i++) {
            int x1 = (int) (x + (r * Math.cos((Math.PI * 2f / n) * i)));
            int y1 = (int) (y + (r * Math.sin((Math.PI * 2f / n) * i)));

            ld.drawLine(x, y, x1, y1);
        }
    }
}
