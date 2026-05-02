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
            case KeyEvent.VK_UP -> trans2(0.01);
            case KeyEvent.VK_DOWN ->trans2(0.01);

            case KeyEvent.VK_1, KeyEvent.VK_LEFT -> rot01(0.01);
            case KeyEvent.VK_Q, KeyEvent.VK_RIGHT -> rot01(-0.01);

            case KeyEvent.VK_2 -> rot02(0.01);
            case KeyEvent.VK_W -> rot02(-0.01);

            case KeyEvent.VK_3 -> rot03(0.01);
            case KeyEvent.VK_E -> rot03(-0.01);

            case KeyEvent.VK_4 -> rot12(0.01);
            case KeyEvent.VK_R -> rot12(-0.01);

            case KeyEvent.VK_5 -> rot13(0.01);
            case KeyEvent.VK_T -> rot13(-0.01);

            case KeyEvent.VK_6 -> rot23(0.01);
            case KeyEvent.VK_Y -> rot23(-0.01);

            case KeyEvent.VK_A -> trans0(0.01);
            case KeyEvent.VK_Z -> trans0(-0.01);

            case KeyEvent.VK_S -> trans1(0.01);
            case KeyEvent.VK_X -> trans1(-0.01);

            case KeyEvent.VK_D -> trans2(0.01);
            case KeyEvent.VK_C -> trans2(-0.01);

            case KeyEvent.VK_F -> trans3(0.01);
            case KeyEvent.VK_V -> trans3(-0.01);

            case KeyEvent.VK_N -> trans2(0.01);
            case KeyEvent.VK_M -> trans2(0.01);

            case KeyEvent.VK_H -> trans2(0.01);
            case KeyEvent.VK_J -> trans2(0.01);
        }

        SwingUtilities.invokeLater(() -> {
            pixelCanvas.invalidate();
            pixelCanvas.validate();
            pixelCanvas.repaint();
        });
    }

    private void trans0(double amount) {

    }

    private void trans1(double amount) {

    }

    private void trans2(double amount) {

    }

    private void trans3(double amount) {

    }

    private void rot01(double angle) {

    }

    private void rot02(double angle) {

    }

    private void rot03(double angle) {
    }

    private void rot12(double angle) {

    }

    private void rot13(double angle) {

    }

    private void rot23(double angle) {

    }
}
