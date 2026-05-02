package org.leviatanplatform.fractal.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CommandListener extends KeyAdapter {

    private FractalVisualizer fractalVisualizer;

    public CommandListener(FractalVisualizer fractalVisualizer) {
        this.fractalVisualizer = fractalVisualizer;
    }

    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> up();
            case KeyEvent.VK_DOWN -> down();
            case KeyEvent.VK_LEFT -> left();
            case KeyEvent.VK_RIGHT -> right();
            case KeyEvent.VK_Q -> zoomIn();
            case KeyEvent.VK_A -> zoomOut();
            case KeyEvent.VK_O -> decreaseIterations();
            case KeyEvent.VK_P -> increaseIterations();
        }
    }

    private void up() {
        fractalVisualizer.translate(0, 30);
    }

    private void down() {
        fractalVisualizer.translate(0, -30);
    }

    private void left() {
        fractalVisualizer.translate(-30, 0);
    }

    private void right() {
        fractalVisualizer.translate(30, 0);
    }

    private void zoomIn() {
        fractalVisualizer.zoom(0.5);
    }

    private void zoomOut() {
        fractalVisualizer.zoom(2);
    }

    private void decreaseIterations() {
        fractalVisualizer.iterations(0.5);
    }

    private void increaseIterations() {
        fractalVisualizer.iterations(2);
    }
}
