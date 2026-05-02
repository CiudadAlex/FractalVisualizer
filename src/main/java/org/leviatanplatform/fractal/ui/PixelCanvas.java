package org.leviatanplatform.fractal.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelCanvas extends JPanel {

    private BufferedImage canvas;

    public PixelCanvas(int w, int h) {
        canvas = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    }

    public void setPixel(int x, int y, Color color) {
        canvas.setRGB(x, y, color.getRGB());
        repaint(); // FIXME quitar solicita redibujado
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }
}
