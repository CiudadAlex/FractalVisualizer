package org.leviatanplatform.fractal.ui;

import org.leviatanplatform.fractal.engine.ComplexPlane;
import org.leviatanplatform.fractal.engine.calculators.Calculator;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FractalVisualizer {

    private static final List<Color> LIST_COLORS = generateListColor();

    private PixelCanvas pixelCanvas;
    private ComplexPlane complexPlane;
    private BigDecimal real_center;
    private BigDecimal imag_center;
    private BigDecimal step;
    private int w;
    private int h;
    private Calculator calculator;
    private JFrame frame;
    private int iterations;
    private boolean usePrecision;

    public FractalVisualizer(double real_center, double imag_center, double step, Calculator calculator, int w, int h, int iterations, boolean usePrecision) {
        this.pixelCanvas = new PixelCanvas(w, h);
        this.complexPlane = new ComplexPlane(real_center - w * step/2, imag_center - h * step/2, step, w, h);
        this.real_center = BigDecimal.valueOf(real_center);
        this.imag_center = BigDecimal.valueOf(imag_center);
        this.step = BigDecimal.valueOf(step);
        this.w = w;
        this.h = h;
        this.calculator = calculator;
        this.iterations = iterations;
        this.usePrecision = usePrecision;
    }

    public void show() {

        if (frame == null) {

            frame = new JFrame("Fractal");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(w + 30, h + 50);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.addKeyListener(new CommandListener(this));

            frame.add(pixelCanvas);
        }

        paintCanvas();
    }

    public void translate(int stepsReal, int stepsImag) {

        // this.real_center = this.real_center + stepsReal * step;
        this.real_center = this.real_center.add(step.multiply(BigDecimal.valueOf(stepsReal)));

        // this.imag_center = this.imag_center + stepsImag * step;
        this.imag_center = this.imag_center.add(step.multiply(BigDecimal.valueOf(stepsImag)));

        rebuildComplexPlane();
        paintCanvas();
    }

    public void iterations(double times) {

        this.iterations = (int) Math.round(times * this.iterations);

        if (this.iterations < 10) {
            this.iterations = 10;
        }

        paintCanvas();
    }

    public void zoom(double times) {

        // this.step = times * step;
        this.step = step.multiply(BigDecimal.valueOf(times));

        rebuildComplexPlane();
        paintCanvas();
    }

    private void rebuildComplexPlane() {

        // double real_min = real_center - w * step/2;
        BigDecimal real_min = real_center.subtract(step.multiply(BigDecimal.valueOf(w/2.0)));

        // double imag_min = imag_center - h * step/2;
        BigDecimal imag_min = imag_center.subtract(step.multiply(BigDecimal.valueOf(h/2.0)));

        this.complexPlane = new ComplexPlane(real_min, imag_min, step, w, h);
    }

    public void paintCanvas() {
        SwingUtilities.invokeLater(() -> {
            innerPaintCanvas();
            pixelCanvas.invalidate();
            pixelCanvas.validate();
            pixelCanvas.repaint();
        });
    }

    public void innerPaintCanvas() {

        if (usePrecision) {
            complexPlane.calculatePrecision(calculator);
        } else {
            complexPlane.calculate(calculator);
        }

        for (int r = 0; r < w; r++) {
            for (int i = 0; i < h; i++) {
                int escapedIteration = complexPlane.getValue(r, i);
                Color color = getColor(escapedIteration);
                pixelCanvas.setPixel(r, h - 1 - i, color);
            }
        }

        pixelCanvas.repaint();
    }

    private Color getColor(int escapedIteration) {

        if (escapedIteration == -1) {
            return Color.black;
        }

        return rainbowEffect(escapedIteration);
    }

    private Color rainbowEffect(int escapedIteration) {

        // escapeVelocity  --> iterations
        // x               --> numColors

        int escapeVelocity = iterations - escapedIteration;
        int numColors = LIST_COLORS.size();
        int indexColor = escapeVelocity * numColors / iterations;

        if (indexColor > numColors - 1) {
            indexColor = numColors - 1;
        }

        if (indexColor < 0) {
            indexColor = 0;
        }

        return LIST_COLORS.get(indexColor);
    }

    private static List<Color> generateListColor() {

        List<Color> listColor = new ArrayList<>();

        for (int i = 0; i < 255; i++) {
            listColor.add(new Color(i, 0, 0));
        }

        for (int i = 0; i < 255; i++) {
            listColor.add(new Color(255, i, 0));
        }

        for (int i = 0; i < 255; i++) {
            listColor.add(new Color(255 - i, 255, 0));
        }

        for (int i = 0; i < 255; i++) {
            listColor.add(new Color(0, 255, i));
        }

        for (int i = 0; i < 255; i++) {
            listColor.add(new Color(0, 255 - i, 255));
        }

        return listColor;
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
