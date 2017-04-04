package com.vodafone.tenpin.controller;

import com.vodafone.tenpin.service.BowlingGame;
import com.vodafone.tenpin.service.BowlingGameImpl;
import com.vodafone.tenpin.util.Pins;
import com.vodafone.tenpin.util.StreamHelper;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Controls the ten pin bowling game.  Accepts the user input and output the final score
 * <p>
 * The input is expected to be a entered as a line in the command prompt separated with a space i.e.
 * 1 3 4 0 3 5
 * <p>
 * Created by stewart thomson
 */
public class TenPinBowling {

    private final BowlingGame bowlingGame;

    public TenPinBowling() {
        this.bowlingGame = new BowlingGameImpl();
    }

    public TenPinBowling(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public void start(InputStream is, PrintStream out) {
        StreamHelper helper = new StreamHelper(is, out);
        generateGame(helper.parse());
        helper.outputResult(bowlingGame.score());
    }

    private void generateGame(String[] values) {
        if (values == null) {
            return;
        }
        for (String value : values) {
            int entry = validateEntry(value);
            bowlingGame.roll(entry);
        }
    }

    private int validateEntry(String value) {
        int entry = NumberUtils.toInt(value, 0);
        if (entry < 0) {
            entry = 0;
        }
        return Math.min(Pins.MAX_PINS.pins(), entry);
    }

}
