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
            case KeyEvent.VK_2 -> zoomIn();
            case KeyEvent.VK_1 -> zoomOut();
            case KeyEvent.VK_U -> decreaseIterations();
            case KeyEvent.VK_I -> increaseIterations();
            case KeyEvent.VK_9 -> decreasePrecision();
            case KeyEvent.VK_0 -> increasePrecision();
            case KeyEvent.VK_P -> useHighPrecision();
            case KeyEvent.VK_F -> useFastPrecision();
            case KeyEvent.VK_H -> help();
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

    private void decreasePrecision() {
        fractalVisualizer.precision(-2);
    }

    private void increasePrecision() {
        fractalVisualizer.precision(2);
    }

    private void useHighPrecision() {
        fractalVisualizer.activatePrecision(true);
    }

    private void useFastPrecision() {
        fractalVisualizer.activatePrecision(false);
    }

    private void help() {
        System.out.println("====================================================");
        System.out.println();
        System.out.println("Useful keys:");
        System.out.println(" - Arrows to navigate the fractal");
        System.out.println(" - H: help");
        System.out.println(" - 2: zoom in");
        System.out.println(" - 1: zoom out");
        System.out.println(" - U: decrease iterations");
        System.out.println(" - I: increase iterations");
        System.out.println(" - 9: decrease precision");
        System.out.println(" - 0: increase precision");
        System.out.println(" - P: high precision");
        System.out.println(" - F: fast precision");
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
        System.out.println("Parameters");
        System.out.println(" - center real: " + fractalVisualizer.getReal_center());
        System.out.println(" - center imag: " + fractalVisualizer.getImag_center());
        System.out.println(" - step: " + fractalVisualizer.getStep());
        System.out.println(" - iterations: " + fractalVisualizer.getIterations());
        System.out.println(" - use precision: " + fractalVisualizer.isUsePrecision());
        System.out.println(" - precision: " + fractalVisualizer.getPrecision());
        System.out.println();
        System.out.println("====================================================");
    }
}
