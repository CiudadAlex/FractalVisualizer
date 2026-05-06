package org.leviatanplatform.fractal.engine.calculators.utils;

import org.leviatanplatform.fractal.engine.calculators.params.GlobalCalculatorParams;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalCalculator {

    public static BigDecimal getModulus(BigDecimal real, BigDecimal imag) {

        BigDecimal real2 = multiply(real, real);
        BigDecimal imag2 = multiply(imag, imag);

        return real2.add(imag2);
    }

    public static BigDecimal multiply(int i, BigDecimal d) {
        return multiply(d, BigDecimal.valueOf(i));
    }

    public static BigDecimal multiply(double dd, BigDecimal d) {
        return multiply(d, BigDecimal.valueOf(dd));
    }

    public static BigDecimal multiply(BigDecimal d1, BigDecimal d2, BigDecimal d3) {
        return multiply(multiply(d1, d2), d3);
    }

    public static BigDecimal multiply(BigDecimal d1, BigDecimal d2) {

        BigDecimal result = d1.multiply(d2);

        int precision = GlobalCalculatorParams.get().getPrecision();
        return result.setScale(precision, RoundingMode.DOWN);
    }
}
