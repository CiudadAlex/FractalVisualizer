package org.leviatanplatform.fractal.engine.calculators.utils;

import java.util.Date;

public class TicToc {

    private static final String DEFAULT_MESSAGE = "Time since last TIC";

    private Date date;

    /** Constructor for TicToc. */
    public TicToc() {
        tic();
    }

    /** Performs a tic. */
    public void tic() {
        this.date = new Date();
    }

    /** Performs a toc. */
    public void toc() {
        toc(DEFAULT_MESSAGE);
    }

    /**
     * Performs a toc.
     *
     * @param txt
     *            txt
     */
    public void toc(final String txt) {

        final Date now = new Date();
        final String timeSinceLastTic = toStringTimeLapse(this.date, now);
        System.out.println(txt + ": " + timeSinceLastTic);
    }

    /** Performs a toc. */
    public void toctic() {
        toctic(DEFAULT_MESSAGE);
    }

    /**
     * Performs a toc.
     *
     * @param txt
     *            txt
     */
    public void toctic(final String txt) {
        toc(txt);
        tic();
    }

    public static String toStringTimeLapse(final Date begin, final Date end) {

        final long diff = end.getTime() - begin.getTime();

        final long millisUnit = diff % 1000;
        final long seconds = diff / 1000;

        final long secondsUnit = seconds % 60;
        final long minutes = seconds / 60;

        final long minutesUnit = minutes % 60;
        final long hours = minutes / 60;

        return hours + "h " + minutesUnit + "m " + secondsUnit + "s " + millisUnit + "ms";
    }
}
