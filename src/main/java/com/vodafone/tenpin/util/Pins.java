package com.vodafone.tenpin.util;

/**
 * Holds pin related information.
 *
 * Created by stewart thomson
 */
public enum Pins {

    MAX_PINS(10);

    private final int pins;

    Pins(int pins) {
        this.pins = pins;
    }

    public int pins() {
        return pins;
    }
}
