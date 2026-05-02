package org.leviatanplatform.fractal.engine;

import org.leviatanplatform.fractal.engine.calculators.Calculator;

public class ComplexPlane {

    private final double real_min;
    private final double imag_min;
    private final double step;
    private final int mumber_steps_real;
    private final int mumber_steps_imag;
    private final double[][] plane;

    public ComplexPlane(double real_min, double imag_min, double step, int mumber_steps_real, int mumber_steps_imag) {
        this.real_min = real_min;
        this.imag_min = imag_min;
        this.step = step;
        this.mumber_steps_real = mumber_steps_real;
        this.mumber_steps_imag = mumber_steps_imag;

        plane = new double[mumber_steps_real][mumber_steps_imag];
    }

    public void calculate(Calculator calculator) {

        for (int r = 0; r < mumber_steps_real; r++) {
            for (int i = 0; i < mumber_steps_imag; i++) {

                double real = real_min + r * step;
                double imaginary = imag_min + i * step;

                plane[r][i] = calculator.calculate(real, imaginary);
            }
        }
    }

    public double getValue(int r, int i) {
        return plane[r][i];
    }

}
