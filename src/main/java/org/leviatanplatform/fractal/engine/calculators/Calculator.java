package org.leviatanplatform.fractal.engine.calculators;

import java.math.BigDecimal;

public interface Calculator {

    String getName();

    int calculate(double real, double imaginary);

    int calculate(BigDecimal real, BigDecimal imaginary);

    void addToParameter1(double delta);

    void addToParameter2(double delta);
}
