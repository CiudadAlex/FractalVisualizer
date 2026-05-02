package org.leviatanplatform.fractal.engine;

import org.leviatanplatform.fractal.engine.calculators.Calculator;
import org.leviatanplatform.fractal.engine.calculators.utils.TicToc;

import java.math.BigDecimal;

public class ComplexPlane {

    private final double real_min;
    private final double imag_min;
    private final double step;

    private final BigDecimal bd_real_min;
    private final BigDecimal bd_imag_min;
    private final BigDecimal bd_step;

    private final int mumber_steps_real;
    private final int mumber_steps_imag;
    private final int[][] plane;

    public ComplexPlane(double real_min, double imag_min, double step, int mumber_steps_real, int mumber_steps_imag) {
        this.real_min = real_min;
        this.imag_min = imag_min;
        this.step = step;
        this.mumber_steps_real = mumber_steps_real;
        this.mumber_steps_imag = mumber_steps_imag;

        this.bd_real_min = BigDecimal.valueOf(real_min);
        this.bd_imag_min = BigDecimal.valueOf(imag_min);
        this.bd_step = BigDecimal.valueOf(step);

        plane = new int[mumber_steps_real][mumber_steps_imag];
    }

    public ComplexPlane(BigDecimal bd_real_min, BigDecimal bd_imag_min, BigDecimal bd_step, int mumber_steps_real, int mumber_steps_imag) {
        this.bd_real_min = bd_real_min;
        this.bd_imag_min = bd_imag_min;
        this.bd_step = bd_step;
        this.mumber_steps_real = mumber_steps_real;
        this.mumber_steps_imag = mumber_steps_imag;

        this.real_min = bd_real_min.doubleValue();
        this.imag_min = bd_imag_min.doubleValue();
        this.step = bd_step.doubleValue();

        plane = new int[mumber_steps_real][mumber_steps_imag];
    }

    public void calculate(Calculator calculator) {

        TicToc ticToc = new TicToc();

        for (int r = 0; r < mumber_steps_real; r++) {
            for (int i = 0; i < mumber_steps_imag; i++) {

                double real = real_min + r * step;
                double imaginary = imag_min + i * step;

                plane[r][i] = calculator.calculate(real, imaginary);
            }
        }

        ticToc.toc("Fast calculus");
    }

    public void calculatePrecision(Calculator calculator) {

        TicToc ticToc = new TicToc();

        for (int r = 0; r < mumber_steps_real; r++) {
            for (int i = 0; i < mumber_steps_imag; i++) {

                BigDecimal real = bd_real_min.add(bd_step.multiply(BigDecimal.valueOf(r)));
                BigDecimal imaginary = bd_imag_min.add(bd_step.multiply(BigDecimal.valueOf(i)));

                plane[r][i] = calculator.calculate(real, imaginary);
            }
        }

        ticToc.toc("Precision calculus");
    }

    public int getValue(int r, int i) {
        return plane[r][i];
    }

}
