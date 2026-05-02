package org.leviatanplatform.fractal.engine.calculators;

import java.math.BigDecimal;

public interface Calculator {

    int calculate(double real, double imaginary);

    int calculate(BigDecimal real, BigDecimal imaginary);
}
