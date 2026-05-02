package org.leviatanplatform.fractal;

import org.leviatanplatform.fractal.engine.calculators.Calculator;
import org.leviatanplatform.fractal.engine.calculators.MandelbrotCalculator;
import org.leviatanplatform.fractal.ui.FractalVisualizer;

import javax.swing.*;

public class Main {

    // FIXME execute in multiple threads

    public static void main(String[] args) {

        int w = 640;
        int h = 640;
        double real_center = 0;
        double imag_center = 0;
        double step = 0.005;
        int iterations = 20;
        Calculator calculator = new MandelbrotCalculator(iterations);

        SwingUtilities.invokeLater(() -> {
            FractalVisualizer fractalVisualizer = new FractalVisualizer(real_center, imag_center, step, calculator, w, h);
            fractalVisualizer.show();
        });
    }
}
