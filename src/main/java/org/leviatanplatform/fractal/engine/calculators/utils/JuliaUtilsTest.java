package org.leviatanplatform.fractal.engine.calculators.utils;

import org.junit.jupiter.api.Test;

class JuliaUtilsTest {

    @Test
    void test() {

        double c_real = -1.6;
        double c_imaginary = -1.6;
        int iterations = 20;
        double value = JuliaUtils.calculate(0, 0, c_real, c_imaginary, iterations);
        System.out.println("value = " + value);
    }
}