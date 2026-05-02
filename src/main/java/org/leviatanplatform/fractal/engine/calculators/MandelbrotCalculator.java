package org.leviatanplatform.fractal.engine.calculators;

import org.leviatanplatform.fractal.engine.calculators.utils.JuliaUtils;

public class MandelbrotCalculator implements Calculator {

    private int iterations;

    public MandelbrotCalculator(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public double calculate(double real, double imaginary) {
        return JuliaUtils.calculate(0, 0, real, imaginary, iterations);
    }
}
