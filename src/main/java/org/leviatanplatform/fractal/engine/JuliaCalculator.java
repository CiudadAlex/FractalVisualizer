package org.leviatanplatform.fractal.engine;

import org.leviatanplatform.fractal.engine.utils.JuliaUtils;

public class JuliaCalculator implements Calculator {

    private double c_real;
    private double c_imaginary;

    public JuliaCalculator(double c_real, double c_imaginary) {
        this.c_real = c_real;
        this.c_imaginary = c_imaginary;
    }

    @Override
    public double calculate(double re, double im) {
        return JuliaUtils.calculate(0, 0, c_real, c_imaginary, 7);
    }
}
