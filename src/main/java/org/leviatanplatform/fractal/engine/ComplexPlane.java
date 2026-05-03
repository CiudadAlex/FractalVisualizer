package org.leviatanplatform.fractal.engine;

import com.google.common.collect.Lists;
import org.leviatanplatform.fractal.engine.calculators.Calculator;
import org.leviatanplatform.fractal.engine.calculators.utils.BigDecimalCalculator;
import org.leviatanplatform.fractal.engine.calculators.utils.TicToc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ComplexPlane {

    private final double real_min;
    private final double imag_min;
    private final double step;

    private final BigDecimal bd_real_min;
    private final BigDecimal bd_imag_min;
    private final BigDecimal bd_step;

    private final int mumber_steps_real;
    private final int mumber_steps_imag;
    private final int numThreads;
    private final int[][] plane;

    public ComplexPlane(double real_min, double imag_min, double step, int mumber_steps_real, int mumber_steps_imag, int numThreads) {
        this.real_min = real_min;
        this.imag_min = imag_min;
        this.step = step;
        this.mumber_steps_real = mumber_steps_real;
        this.mumber_steps_imag = mumber_steps_imag;

        this.bd_real_min = BigDecimal.valueOf(real_min);
        this.bd_imag_min = BigDecimal.valueOf(imag_min);
        this.bd_step = BigDecimal.valueOf(step);
        this.numThreads = numThreads;

        plane = new int[mumber_steps_real][mumber_steps_imag];
    }

    public ComplexPlane(BigDecimal bd_real_min, BigDecimal bd_imag_min, BigDecimal bd_step, int mumber_steps_real, int mumber_steps_imag, int numThreads) {
        this.bd_real_min = bd_real_min;
        this.bd_imag_min = bd_imag_min;
        this.bd_step = bd_step;
        this.mumber_steps_real = mumber_steps_real;
        this.mumber_steps_imag = mumber_steps_imag;

        this.real_min = bd_real_min.doubleValue();
        this.imag_min = bd_imag_min.doubleValue();
        this.step = bd_step.doubleValue();
        this.numThreads = numThreads;

        plane = new int[mumber_steps_real][mumber_steps_imag];
    }

    public void calculate(Calculator calculator) {

        System.out.println("Calculation fast");

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

        BigDecimalCalculator bigDecimalCalculator = BigDecimalCalculator.get();

        System.out.println("Calculation precise: " + bigDecimalCalculator.getPrecision());

        TicToc ticToc = new TicToc();

        List<List<Integer>> partitionR = getPartitionReals();

        List<Thread> listThread = new ArrayList<>();

        for (List<Integer> subListR : partitionR) {
            Thread thread = buildCalculationPrecisionThread(calculator, bigDecimalCalculator, subListR);
            thread.start();
            listThread.add(thread);
        }

        join(listThread);

        ticToc.toc("Precision calculus");
    }

    private Thread buildCalculationPrecisionThread(Calculator calculator, BigDecimalCalculator bigDecimalCalculator, List<Integer> subListR) {
        return new Thread(() -> {
            for (Integer r: subListR) {
                calculatePrecisionRealColumn(calculator, bigDecimalCalculator, r);
            }
        });
    }

    private void join(List<Thread> listThread) {

        for (Thread thread : listThread) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<List<Integer>> getPartitionReals() {

        List<Integer> listR = new ArrayList<>();

        for (int r = 0; r < mumber_steps_real; r++) {
            listR.add(r);
        }

        int sizeSubList = (listR.size() / numThreads) + 1;

        return Lists.partition(listR, sizeSubList);
    }

    private void calculatePrecisionRealColumn(Calculator calculator, BigDecimalCalculator bigDecimalCalculator, int r) {

        for (int i = 0; i < mumber_steps_imag; i++) {

            BigDecimal real = bd_real_min.add(bigDecimalCalculator.multiply(r, bd_step));
            BigDecimal imaginary = bd_imag_min.add(bigDecimalCalculator.multiply(i, bd_step));

            plane[r][i] = calculator.calculate(real, imaginary);
        }
    }

    public int getValue(int r, int i) {
        return plane[r][i];
    }

}
