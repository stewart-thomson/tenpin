package com.vodafone.tenpin.service;

import com.vodafone.tenpin.model.Frame;
import com.vodafone.tenpin.scoring.FrameScoring;
import com.vodafone.tenpin.scoring.NormalScoring;
import com.vodafone.tenpin.scoring.SpareScoring;
import com.vodafone.tenpin.scoring.StrikeScoring;
import com.vodafone.tenpin.util.Frames;

/**
 * Implementation for game total score
 * Loops around all the frames and sends the frame to work out the fame score.
 * <p>
 * Created by stewart thomson
 */
public class ScoringImpl implements Scoring {

    private final FrameScoring scoring;

    /**
     * Default constructor.  Sets up the default calculation process.
     * The default process is strike, spare, normal in that order.
     */
    ScoringImpl() {
        scoring = buildScoring();
    }

    ScoringImpl(FrameScoring scoring) {
        this.scoring = scoring;
    }

    public int calculateScore(Frame[] frames) {
        int score = 0;
        if (frames == null) {
            return score;
        }

        for (int i = 0; i < Frames.MAX_FRAMES.frames(); i++) {
            score += scoring.execute(frames, i);
        }
        return score;
    }

    private FrameScoring buildScoring() {
        FrameScoring normal = new NormalScoring();
        FrameScoring spare = new SpareScoring(normal);
        return new StrikeScoring(spare);
    }
}
