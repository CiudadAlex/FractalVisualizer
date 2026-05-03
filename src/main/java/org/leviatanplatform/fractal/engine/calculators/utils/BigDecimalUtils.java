package org.leviatanplatform.fractal.engine.calculators.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static BigDecimal getModulus(BigDecimal real, BigDecimal imag) {

        BigDecimal real2 = real.multiply(real);
        BigDecimal imag2 = imag.multiply(imag);

        return real2.add(imag2);
    }

    public static BigDecimal multiply(BigDecimal d1, BigDecimal d2) {

        //BigDecimal truncado = valor.setScale(2, RoundingMode.DOWN);
        return d1.multiply(d2);
    }

    public static BigDecimal multiply(BigDecimal d1, BigDecimal d2, BigDecimal d3) {

        //BigDecimal truncado = valor.setScale(2, RoundingMode.DOWN);
        return d1.multiply(d2).multiply(d3);
    }
}
