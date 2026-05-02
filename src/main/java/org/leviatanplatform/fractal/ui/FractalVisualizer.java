package org.leviatanplatform.fractal.ui;

import org.leviatanplatform.fractal.engine.ComplexPlane;
import org.leviatanplatform.fractal.engine.calculators.Calculator;

public class FractalVisualizer {

    private PixelCanvas pixelCanvas;
    private ComplexPlane complexPlane;

    public FractalVisualizer(double real_center, double imag_center, double step, Calculator calculator, int w, int h) {
        this.pixelCanvas = new PixelCanvas(w, h);
        this.complexPlane = new ComplexPlane(real_center - w * step/2, imag_center - h * step/2, step, w, h);
    }

    // FIXME paint
}
