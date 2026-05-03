package org.leviatanplatform.fractal;

import org.leviatanplatform.fractal.engine.calculators.Calculator;
import org.leviatanplatform.fractal.engine.calculators.JuliaCalculator;
import org.leviatanplatform.fractal.engine.calculators.MandelbrotCalculator;
import org.leviatanplatform.fractal.ui.FractalVisualizer;

import javax.swing.*;

public class Main {

    // FIXME execute in multiple threads
    // FIXME truncar mantisa BigDecimal
    // FIXME Cambiar con teclas parametros de julia
    // FIXME Nuevos fractales diferentes

    public static void main(String[] args) {

        int w = 1000;
        int h = 800;
        double real_center = 0;
        double imag_center = 0;
        double step = 0.005;
        int iterations = 100;
        boolean isMandelbrot = true;
        boolean usePrecision = false;

        Calculator mandelbrotCalculator = new MandelbrotCalculator(iterations);
        Calculator juliaCalculator = new JuliaCalculator(0.5, -0.3, iterations);

        Calculator calculator = isMandelbrot ? mandelbrotCalculator : juliaCalculator;

        SwingUtilities.invokeLater(() -> {
            FractalVisualizer fractalVisualizer = new FractalVisualizer(real_center, imag_center, step, calculator, w, h, iterations, usePrecision);
            fractalVisualizer.show();
        });
    }
}
