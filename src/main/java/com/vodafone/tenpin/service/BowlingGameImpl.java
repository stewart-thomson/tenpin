package com.vodafone.tenpin.service;

import com.vodafone.tenpin.model.Frame;
import com.vodafone.tenpin.util.Frames;
import com.vodafone.tenpin.util.Pins;

/**
 * Provides implementation to handle a bowling game.  Allows for the adding of felled pins in a players throw.
 * <p>
 * Maintains which frame is currently being played and if the first throw was a strike then moves the current frame
 * to be the next frame.  If the first throw is not a strike then the current frame will remain active for the
 * players next throw.
 * <p>
 * Created by stewart thomson
 */
public class BowlingGameImpl implements BowlingGame {

    private int currentFrame = 0;
    private boolean firstThrowInFrame = true;

    private Scoring scoring;


    private Frame[] frames;

    /**
     * Default constructor, sets up the basic frames for a standard game i.e 10 frames with the last being
     * set up to use the bonus ball should it be needed.
     */
    public BowlingGameImpl() {
        init();
    }

    public BowlingGameImpl(Scoring scoring, Frame[] frames) {
        this.scoring = scoring;
        this.frames = frames;
    }

    /**
     * Initialise the standard game frames and scoring mechanism.
     */
    private void init() {
        frames = new Frame[Frames.MAX_FRAMES.frames()];
        for (int i = 0; i < Frames.MAX_FRAMES.frames(); i++) {
            Frame frame;
            if (i < Frames.MAX_FRAMES.frames() - 1) {
                frame = new Frame(false);
            } else {
                frame = new Frame(true);
            }
            frames[i] = frame;
        }
        scoring = new ScoringImpl();
    }

    public void roll(int noOfPins) {
        addThrowPinsToFrame(currentFrame, noOfPins);
        adjustCurrentFrame(noOfPins);
    }

    public int score() {
        return scoring.calculateScore(frames);
    }

    public int currentFrame() {
        return currentFrame;
    }

    private void adjustCurrentFrame(int pins) {
        if (firstThrowInFrame) {
            if (!adjustFrameForStrike(pins)) {
                firstThrowInFrame = false;
            }
        } else {
            firstThrowInFrame = true;
            advanceFrame();
        }
    }

    private boolean adjustFrameForStrike(int pins) {
        if (pins == Pins.MAX_PINS.pins()) {
            advanceFrame();
            return true;
        }
        return false;
    }

    private void advanceFrame() {
        currentFrame = Math.min(Frames.MAX_FRAMES.frames() - 1, currentFrame + 1);
    }

    private void addThrowPinsToFrame(int currentThrow, int pins) {
        Frame frame = frames[currentThrow];
        frame.setTurnScore(pins);
    }
}
