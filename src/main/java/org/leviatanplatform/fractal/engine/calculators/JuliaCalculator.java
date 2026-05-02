package org.leviatanplatform.fractal.engine.calculators;

import org.leviatanplatform.fractal.engine.calculators.utils.JuliaUtils;

public class JuliaCalculator implements Calculator {

    private double c_real;
    private double c_imaginary;

    public JuliaCalculator(double c_real, double c_imaginary) {
        this.c_real = c_real;
        this.c_imaginary = c_imaginary;
    }

    @Override
    public double calculate(double real, double imaginary) {
        return JuliaUtils.calculate(real, imaginary, c_real, c_imaginary, 20);
    }
}
