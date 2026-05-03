package org.leviatanplatform.fractal.engine.calculators.utils;

import java.math.BigDecimal;

/**
 * General Formulae:
 * <p>
 * z(n+1) = z(n)^2 + c
 * <p>
 *  Square calculus:
 * <p>
 *  z = a + bi
 *  z^2 = a^2 - b^2 + 2abi
 *
 */
public class JuliaUtils {

    private static final BigDecimal VALUE_2 = BigDecimal.valueOf(2);
    private static final BigDecimal VALUE_10 = BigDecimal.valueOf(10);

    public static int calculate(double z_real, double z_imaginary, double c_real, double c_imaginary, int iterations) {

        double zre = z_real;
        double zim = z_imaginary;

        for (int i = 0; i < iterations; i++) {

            double z2re = zre * zre - zim * zim;
            double z2im = 2 * zre * zim;

            zre = z2re + c_real;
            zim = z2im + c_imaginary;

            double modulus = zre * zre + zim * zim;

            if (modulus > 10) {
                // If exits return iteration
                return i;
            }
        }

        // Never exited in those iterations
        return -1;
    }

    public static int calculate(BigDecimal z_real, BigDecimal z_imaginary, BigDecimal c_real, BigDecimal c_imaginary, int iterations) {

        BigDecimal zre = z_real;
        BigDecimal zim = z_imaginary;

        for (int i = 0; i < iterations; i++) {

            BigDecimal zre2 = BigDecimalCalculator.multiply(zre, zre);
            BigDecimal zim2 = BigDecimalCalculator.multiply(zim, zim);

            BigDecimal z2re = zre2.subtract(zim2);
            BigDecimal z2im = BigDecimalCalculator.multiply(VALUE_2, zre, zim);

            zre = z2re.add(c_real);
            zim = z2im.add(c_imaginary);

            BigDecimal modulus = BigDecimalCalculator.getModulus(zre, zim);

            if (modulus.compareTo(VALUE_10) > 0) {
                // If exits return iteration
                return i;
            }
        }

        // Never exited in those iterations
        return -1;
    }




}
