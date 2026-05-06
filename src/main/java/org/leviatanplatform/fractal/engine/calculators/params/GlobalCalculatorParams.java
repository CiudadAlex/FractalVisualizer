package org.leviatanplatform.fractal.engine.calculators.params;

public class GlobalCalculatorParams {

    private static GlobalCalculatorParams instance;

    public static GlobalCalculatorParams get() {

        if (instance == null) {
            instance = new GlobalCalculatorParams(30, 100);
        }

        return instance;
    }

    private int precision;
    private int iterations;

    public GlobalCalculatorParams(int precision, int iterations) {
        this.precision = precision;
        this.iterations = iterations;
    }

    public int getPrecision() {
        return precision;
    }

    public int getIterations() {
        return iterations;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
