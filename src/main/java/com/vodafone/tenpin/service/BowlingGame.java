package com.vodafone.tenpin.service;

/**
 * Interface to a bowling game
 * <p>
 * Created by stewart thomson
 */
public interface BowlingGame {

    /**
     * Adds the number of pins scored in a throw.
     *
     * @param noOfPins number of pins
     */
    void roll(int noOfPins);

    /**
     * Returns the score for the whole game.
     *
     * @return the total game score
     */
    int score();

    /**
     * Returns the current frame number that is being played.
     *
     * @return the position
     */
    int currentFrame();
}
