package com.vodafone.tenpin.scoring;

import com.vodafone.tenpin.model.Frame;

/**
 * If the frame has a spare then use this calculation to work out the frame score.
 * <p>
 * Created by stewart thomson
 */
public class SpareScoring implements FrameScoring {

    private final FrameScoring next;

    public SpareScoring(FrameScoring next) {
        this.next = next;
    }

    public int execute(Frame[] frames, int currentPosition) {
        if (frames == null) {
            return 0;
        }
        Frame current = frames[currentPosition];
        if (current == null) {
            return 0;
        }
        if (current.isSpare()) {
            int score = current.getScore();
            if (current.isFinal()) {
                score += current.getBonusScore();
            } else {
                score += frames[currentPosition + 1].firstBallScore();
            }
            return score;
        }
        return next.execute(frames, currentPosition);
    }
}
