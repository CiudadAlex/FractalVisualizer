package org.leviatanplatform.fractal.engine.calculators.utils;

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

    public static double calculate(double z_real, double z_imaginary, double c_real, double c_imaginary, int iterations) {

        double zre = z_real;
        double zim = z_imaginary;

        for (int i = 0; i < iterations; i++) {

            double z2re = zre * zre - zim * zim;
            double z2im = 2 * zre * zim;

            zre = z2re + c_real;
            zim = z2im + c_imaginary;
        }

        // Return modulus
        return zre * zre + zim * zim;
    }


}
