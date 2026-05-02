package org.leviatanplatform.fractal.engine.calculators;

import org.leviatanplatform.fractal.engine.calculators.utils.JuliaUtils;

public class JuliaCalculator implements Calculator {

    private final double c_real;
    private final double c_imaginary;
    private final int iterations;

    public JuliaCalculator(double c_real, double c_imaginary, int iterations) {
        this.c_real = c_real;
        this.c_imaginary = c_imaginary;
        this.iterations = iterations;
    }

    @Override
    public int calculate(double real, double imaginary) {
        return JuliaUtils.calculate(real, imaginary, c_real, c_imaginary, iterations);
    }
}
