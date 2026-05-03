package org.leviatanplatform.fractal.engine.calculators;

import java.math.BigDecimal;

/**
 * General Formulae:
 * <p>
 * z(n+1) = z(n)^3 + c
 * <p>
 *  Square calculus:
 * <p>
 *  z = a + bi
 *  z^2 = (a^2 - b^2) + (2ab)i
 *  z^3 = (a^3 - ab^2 - 2ab^2) + (2a^2b + ba^2 - b^3)i
 *  z^3 = (a^3 - 3ab^2) + (3ba^2 - b^3)i
 *
 */
public class AlexCalculator implements Calculator {

    @Override
    public int calculate(double c_real, double c_imaginary) {

        int iterations = 100;
        double zre = 0;
        double zim = 0;

        for (int i = 0; i < iterations; i++) {

            double z3re = zre * zre * zre - 3 * zre * zim * zim;
            double z3im = 3 * zim * zre * zre - zim * zim * zim;

            zre = z3re + c_real;
            zim = z3im + c_imaginary;

            double modulus = zre * zre + zim * zim;

            if (modulus > 10) {
                // If exits return iteration
                return i;
            }
        }

        // Never exited in those iterations
        return -1;
    }

    @Override
    public int calculate(BigDecimal real, BigDecimal imaginary) {
        return calculate(real.doubleValue(), imaginary.doubleValue());
    }

    @Override
    public void addToParameter1(double delta) {

    }

    @Override
    public void addToParameter2(double delta) {

    }
}
