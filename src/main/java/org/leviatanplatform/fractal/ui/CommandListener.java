package org.leviatanplatform.fractal.ui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CommandListener extends KeyAdapter {

    private PixelCanvas pixelCanvas;

    public CommandListener(PixelCanvas pixelCanvas) {
        this.pixelCanvas = pixelCanvas;
    }

    // FIXME finish

    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> up();
            case KeyEvent.VK_DOWN ->down();
            case KeyEvent.VK_LEFT -> left();
            case KeyEvent.VK_RIGHT -> right();
        }

        SwingUtilities.invokeLater(() -> {
            pixelCanvas.invalidate();
            pixelCanvas.validate();
            pixelCanvas.repaint();
        });
    }

    private void up() {
    }

    private void down() {
    }

    private void left() {
    }

    private void right() {
    }
}
