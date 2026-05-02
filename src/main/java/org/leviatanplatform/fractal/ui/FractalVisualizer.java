package org.leviatanplatform.fractal.ui;

import org.leviatanplatform.fractal.engine.ComplexPlane;
import org.leviatanplatform.fractal.engine.calculators.Calculator;

import javax.swing.*;
import java.awt.*;

public class FractalVisualizer {

    private static final double RAINBOW_EFFECT_TRANSITION_STEP = 0.01;

    private PixelCanvas pixelCanvas;
    private ComplexPlane complexPlane;
    private int w;
    private int h;
    private Calculator calculator;
    private JFrame frame;

    public FractalVisualizer(double real_center, double imag_center, double step, Calculator calculator, int w, int h) {
        this.pixelCanvas = new PixelCanvas(w, h);
        this.complexPlane = new ComplexPlane(real_center - w * step/2, imag_center - h * step/2, step, w, h);
        this.w = w;
        this.h = h;
        this.calculator = calculator;
    }

    public void show() {

        if (frame == null) {

            frame = new JFrame("Fractal");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(w + 30, h + 50);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.addKeyListener(new CommandListener(pixelCanvas));

            frame.add(pixelCanvas);
        }

        SwingUtilities.invokeLater(() -> {
            paintCanvas();
            pixelCanvas.invalidate();
            pixelCanvas.validate();
            pixelCanvas.repaint();
        });
    }

    public void paintCanvas() {

        complexPlane.calculate(calculator);

        for (int r = 0; r < w; r++) {
            for (int i = 0; i < h; i++) {
                double value = complexPlane.getValue(r, i);
                Color color = getColor(value);
                pixelCanvas.setPixel(r, h - 1 - i, color);
            }
        }

        pixelCanvas.repaint();
    }

    private Color getColor(double value) {

        if (value <= 1) {
            return Color.black;
        }

        return rainbowEffect(value);
    }

    private Color rainbowEffect(double value) {

        double valueGrounded = value - 1;

        double transitionStep = RAINBOW_EFFECT_TRANSITION_STEP;
        double colorStep = transitionStep / 255;

        if (valueGrounded < transitionStep) {
            int g = (int) Math.floor(valueGrounded / colorStep);
            return getColor(255, g, 0);
        }

        valueGrounded = valueGrounded - transitionStep;

        if (valueGrounded < transitionStep) {
            int r = 255 - (int) Math.floor(valueGrounded / colorStep);
            return getColor(r, 255, 0);
        }

        valueGrounded = valueGrounded - transitionStep;

        if (valueGrounded < transitionStep) {
            int b = (int) Math.floor(valueGrounded / colorStep);
            return getColor(0, 255, b);
        }

        valueGrounded = valueGrounded - transitionStep;

        if (valueGrounded < transitionStep) {
            int g = 255 - (int) Math.floor(valueGrounded / colorStep);
            return getColor(0, g, 255);
        }

        return getColor(0, 0, 255);
    }

    private Color getColor(int r, int g, int b) {

        int r_ok = r;
        int g_ok = g;
        int b_ok = b;

        if (r_ok < 0) {
            r_ok = 0;
        }

        if (r_ok > 255) {
            r_ok = 255;
        }

        if (g_ok < 0) {
            g_ok = 0;
        }

        if (g_ok > 255) {
            g_ok = 255;
        }

        if (b_ok < 0) {
            b_ok = 0;
        }

        if (b_ok > 255) {
            b_ok = 255;
        }

        return new Color(r_ok, g_ok, b_ok);
    }

}
