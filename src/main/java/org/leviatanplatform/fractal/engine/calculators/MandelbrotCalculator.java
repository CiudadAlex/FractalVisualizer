package org.leviatanplatform.fractal.engine.calculators;

import org.leviatanplatform.fractal.engine.calculators.utils.JuliaUtils;

import java.math.BigDecimal;

public class MandelbrotCalculator implements Calculator {

    public MandelbrotCalculator() {
    }

    @Override
    public String getName() {
        return "Mandelbrot";
    }

    @Override
    public int calculate(double real, double imaginary) {
        return JuliaUtils.calculate(0, 0, real, imaginary);
    }

    @Override
    public int calculate(BigDecimal real, BigDecimal imaginary) {
        return JuliaUtils.calculate(BigDecimal.ZERO, BigDecimal.ZERO, real, imaginary);
    }

    @Override
    public void addToParameter1(double delta) {
    }

    @Override
    public void addToParameter2(double delta) {
    }
}
