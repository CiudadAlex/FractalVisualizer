package org.leviatanplatform.fractal.engine;

import org.leviatanplatform.fractal.engine.utils.JuliaUtils;

public class MandelbrotCalculator implements Calculator {

    @Override
    public double calculate(double re, double im) {
        return JuliaUtils.calculate(0, 0, re, im, 7);
    }
}
