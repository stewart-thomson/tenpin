package com.vodafone.tenpin.model;

import com.vodafone.tenpin.util.Pins;

import java.util.Arrays;

/**
 * Represents a single frame in the game.  Stores the first and second throws if they are needed and also the
 * bonus ball if this is the final frame in the game.
 * <p>
 * Created by stewart thomson
 */
public class Frame {

    private final static int MAX_FRAME_THROWS = 3;
    private int currentTurn;
    private int score = 0;
    private final boolean isFinal;
    private final int[] turnPins = new int[MAX_FRAME_THROWS];

    private final static int BONUS_POSITION = 2;


    /**
     * Constructor that take in whether this frame is the final frame or not.
     *
     * @param isFinal true if this is the final frame otherwise false
     */
    public Frame(boolean isFinal) {
        this.isFinal = isFinal;
        currentTurn = 0;
        Arrays.fill(turnPins, 0);
    }

    public int getScore() {
        return score;
    }


    /**
     * Checks to see if this frame is a strike
     *
     * @return true if this frame is a strike otherwise false
     */
    public boolean isStrike() {
        return this.turnPins[0] == Pins.MAX_PINS.pins();
    }

    /**
     * Checks to see if this frame is a spare
     *
     * @return true if this frame is a spare otherwise false
     */
    public boolean isSpare() {
        return (!this.isStrike() && this.score == Pins.MAX_PINS.pins());
    }


    /**
     * Set the number of pins knocked down for a turn
     *
     * @param pins Number of pins scored
     */
    public void setTurnScore(int pins) {
        if (currentTurn + 1 <= MAX_FRAME_THROWS) {
            if (currentTurn == BONUS_POSITION) {
                if (isStrike() || isSpare()) {
                    this.turnPins[currentTurn++] = pins;
                }
            } else {
                this.turnPins[currentTurn++] = pins;
                this.score = Math.min(Pins.MAX_PINS.pins(), this.score += pins);
            }
        }
    }

    public int firstBallScore() {
        return turnPins[0];
    }

    public int secondBallScore() {
        return turnPins[1];
    }

    public int getBonusScore() {
        return this.turnPins[BONUS_POSITION];
    }

    /**
     * Checks to see if this frame is the final frame in the game.
     *
     * @return true if this is the final frame otherwise false
     */
    public boolean isFinal() {
        return isFinal;
    }


}
