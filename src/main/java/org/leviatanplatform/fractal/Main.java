package org.leviatanplatform.fractal;

import org.leviatanplatform.fractal.engine.calculators.AlexCalculator;
import org.leviatanplatform.fractal.engine.calculators.Calculator;
import org.leviatanplatform.fractal.engine.calculators.JuliaCalculator;
import org.leviatanplatform.fractal.engine.calculators.MandelbrotCalculator;
import org.leviatanplatform.fractal.ui.FractalVisualizer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        int w = 1000;
        int h = 800;
        double real_center = 0;
        double imag_center = 0;
        double step = 0.005;
        int iterations = 100;
        boolean usePrecision = false;
        int precision = 30;
        int numThreads = 16;

        Calculator mandelbrotCalculator = new MandelbrotCalculator();
        Calculator juliaCalculator = new JuliaCalculator(0.5, -0.3);
        Calculator alexCalculator = new AlexCalculator();

        Calculator calculator = mandelbrotCalculator;

        SwingUtilities.invokeLater(() -> {
            FractalVisualizer fractalVisualizer = new FractalVisualizer(real_center, imag_center, step, calculator, w, h, iterations, usePrecision, precision, numThreads);
            fractalVisualizer.show();
        });
    }
}
