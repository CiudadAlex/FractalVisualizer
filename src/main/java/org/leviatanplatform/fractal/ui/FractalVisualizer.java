package org.leviatanplatform.fractal.ui;

import org.leviatanplatform.fractal.engine.ComplexPlane;
import org.leviatanplatform.fractal.engine.calculators.Calculator;

import java.awt.*;

public class FractalVisualizer {

    private PixelCanvas pixelCanvas;
    private ComplexPlane complexPlane;
    private int w;
    private int h;

    private Calculator calculator;

    public FractalVisualizer(double real_center, double imag_center, double step, Calculator calculator, int w, int h) {
        this.pixelCanvas = new PixelCanvas(w, h);
        this.complexPlane = new ComplexPlane(real_center - w * step/2, imag_center - h * step/2, step, w, h);
        this.w = w;
        this.h = h;
        this.calculator = calculator;
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

        return Color.black;
    }

    // FIXME finish
}
