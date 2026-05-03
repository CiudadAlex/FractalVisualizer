package org.leviatanplatform.fractal.engine.calculators.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalCalculator {

    private static BigDecimalCalculator instance;

    public static BigDecimalCalculator get() {

        if (instance == null) {
            instance = new BigDecimalCalculator(100);
        }

        return instance;
    }

    public static void set(int precision) {
        instance = new BigDecimalCalculator(precision);
    }

    private final int precision;

    private BigDecimalCalculator(int precision) {
        this.precision = precision;
    }

    public BigDecimal getModulus(BigDecimal real, BigDecimal imag) {

        BigDecimal real2 = multiply(real, real);
        BigDecimal imag2 = multiply(imag, imag);

        return real2.add(imag2);
    }

    public BigDecimal multiply(int i, BigDecimal d) {
        return multiply(d, BigDecimal.valueOf(i));
    }

    public BigDecimal multiply(double dd, BigDecimal d) {
        return multiply(d, BigDecimal.valueOf(dd));
    }

    public BigDecimal multiply(BigDecimal d1, BigDecimal d2, BigDecimal d3) {
        return multiply(multiply(d1, d2), d3);
    }

    public BigDecimal multiply(BigDecimal d1, BigDecimal d2) {

        BigDecimal result = d1.multiply(d2);
        return result.setScale(precision, RoundingMode.DOWN);
    }
}
