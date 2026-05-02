package org.leviatanplatform.fractal.engine.calculators.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class JuliaUtilsTest {

    @Test
    void test() {

        double c_real = -1.6;
        double c_imaginary = -1.6;
        int iterations = 20;
        int value = JuliaUtils.calculate(0, 0, c_real, c_imaginary, iterations);
        System.out.println("value = " + value);
    }

    @Test
    void testBigDecimal() {

        BigDecimal c_real = BigDecimal.valueOf(-0.16);
        BigDecimal c_imaginary = BigDecimal.valueOf(-0.16);
        int iterations = 20;
        int value = JuliaUtils.calculate(BigDecimal.ZERO, BigDecimal.ZERO, c_real, c_imaginary, iterations);
        System.out.println("value = " + value);
    }
}