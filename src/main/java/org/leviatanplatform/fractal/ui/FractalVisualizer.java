package org.leviatanplatform.fractal.ui;

import org.leviatanplatform.fractal.engine.ComplexPlane;
import org.leviatanplatform.fractal.engine.calculators.Calculator;
import org.leviatanplatform.fractal.engine.calculators.params.GlobalCalculatorParams;
import org.leviatanplatform.fractal.engine.calculators.utils.BigDecimalCalculator;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FractalVisualizer {

    private static final List<Color> LIST_COLORS = generateListColor();

    private final PixelCanvas pixelCanvas;
    private ComplexPlane complexPlane;
    private BigDecimal real_center;
    private BigDecimal imag_center;
    private BigDecimal step;
    private final int w;
    private final int h;
    private final Calculator calculator;
    private JFrame frame;
    private boolean usePrecision;
    private final int numThreads;

    public FractalVisualizer(double real_center, double imag_center, double step, Calculator calculator, int w, int h, int iterations, boolean usePrecision, int precision, int numThreads) {

        GlobalCalculatorParams.get().setPrecision(precision);
        GlobalCalculatorParams.get().setIterations(iterations);

        this.pixelCanvas = new PixelCanvas(w, h);
        this.complexPlane = new ComplexPlane(real_center - w * step/2, imag_center - h * step/2, step, w, h, numThreads);
        this.real_center = BigDecimal.valueOf(real_center);
        this.imag_center = BigDecimal.valueOf(imag_center);
        this.step = BigDecimal.valueOf(step);
        this.w = w;
        this.h = h;
        this.calculator = calculator;
        this.usePrecision = usePrecision;
        this.numThreads = numThreads;
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
        this.real_center = this.real_center.add(BigDecimalCalculator.multiply(stepsReal, step));

        // this.imag_center = this.imag_center + stepsImag * step;
        this.imag_center = this.imag_center.add(BigDecimalCalculator.multiply(stepsImag, step));

        refreshAll();
    }

    public void iterations(double times) {

        int iterations = GlobalCalculatorParams.get().getIterations();

        iterations = (int) Math.round(times * iterations);

        if (iterations < 10) {
            iterations = 10;
        }

        GlobalCalculatorParams.get().setIterations(iterations);

        refreshAll();
    }

    public void zoom(double times) {

        // this.step = times * step;
        this.step = BigDecimalCalculator.multiply(times, step);

        refreshAll();
    }

    public void activatePrecision(boolean activate) {
        usePrecision = activate;

        refreshAll();
    }

    public void precision(int sum) {

        int precision = GlobalCalculatorParams.get().getPrecision();

        precision = precision + sum;
        GlobalCalculatorParams.get().setPrecision(precision);

        refreshAll();
    }

    public void addToCalculatorParameter1(double delta) {
        this.calculator.addToParameter1(delta);
        refreshAll();
    }

    public void addToCalculatorParameter2(double delta) {
        this.calculator.addToParameter2(delta);
        refreshAll();
    }

    private void refreshAll() {
        rebuildComplexPlane();
        paintCanvas();
    }

    private void rebuildComplexPlane() {

        // double real_min = real_center - w * step/2;
        BigDecimal real_min = real_center.subtract(BigDecimalCalculator.multiply(w/2.0, step));

        // double imag_min = imag_center - h * step/2;
        BigDecimal imag_min = imag_center.subtract(BigDecimalCalculator.multiply(h/2.0, step));

        this.complexPlane = new ComplexPlane(real_min, imag_min, step, w, h, numThreads);
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

        frame.setTitle(buildJFrameTitle());

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

    private String buildJFrameTitle() {

        // FIXME title JFrame
        String calculatorName = calculator.getName();
        return "Fractal " + calculatorName + " ";
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
        int iterations = GlobalCalculatorParams.get().getIterations();

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

    public BigDecimal getReal_center() {
        return real_center;
    }

    public BigDecimal getImag_center() {
        return imag_center;
    }

    public BigDecimal getStep() {
        return step;
    }

    public boolean isUsePrecision() {
        return usePrecision;
    }

}
