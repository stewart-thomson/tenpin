package com.vodafone.tenpin.service;

import com.vodafone.tenpin.model.Frame;

/**
 * Interface for calculating a game's total score once play has finished.
 * <p>
 * Created by stewart thomson
 */
public interface Scoring {


    /**
     * Calculate the games score for the frames played
     *
     * @param frames All frames in the current game
     * @return the game score
     */
    int calculateScore(Frame[] frames);
}
