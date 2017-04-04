package com.vodafone.tenpin.util;

/**
 * Created by stewart thomson
 */
public enum Frames {

    MAX_FRAMES(10);

    private final int frames;

    Frames(int frames) {
        this.frames = frames;
    }

    public int frames() {
        return this.frames;
    }
}
