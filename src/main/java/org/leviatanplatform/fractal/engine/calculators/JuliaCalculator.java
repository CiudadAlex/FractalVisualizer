package org.leviatanplatform.fractal.engine.calculators;

import org.leviatanplatform.fractal.engine.calculators.utils.JuliaUtils;

import java.math.BigDecimal;

public class JuliaCalculator implements Calculator {

    private double c_real;
    private double c_imaginary;
    private BigDecimal bd_c_real;
    private BigDecimal bd_c_imaginary;
    private final int iterations;

    public JuliaCalculator(double c_real, double c_imaginary, int iterations) {
        this.c_real = c_real;
        this.c_imaginary = c_imaginary;
        this.iterations = iterations;

        this.bd_c_real = BigDecimal.valueOf(c_real);
        this.bd_c_imaginary = BigDecimal.valueOf(c_imaginary);
    }

    public JuliaCalculator(BigDecimal bd_c_real, BigDecimal bd_c_imaginary, int iterations) {
        this.bd_c_real = bd_c_real;
        this.bd_c_imaginary = bd_c_imaginary;
        this.iterations = iterations;

        this.c_real = bd_c_real.doubleValue();
        this.c_imaginary = bd_c_imaginary.doubleValue();
    }

    @Override
    public int calculate(double real, double imaginary) {
        return JuliaUtils.calculate(real, imaginary, c_real, c_imaginary, iterations);
    }

    @Override
    public int calculate(BigDecimal real, BigDecimal imaginary) {
        return JuliaUtils.calculate(real, imaginary, bd_c_real, bd_c_imaginary, iterations);
    }

    @Override
    public void addToParameter1(double delta) {

        c_real = c_real + delta;
        bd_c_real = bd_c_real.add(BigDecimal.valueOf(delta));
    }

    @Override
    public void addToParameter2(double delta) {

        c_imaginary = c_imaginary + delta;
        bd_c_imaginary = bd_c_imaginary.add(BigDecimal.valueOf(delta));
    }
}
