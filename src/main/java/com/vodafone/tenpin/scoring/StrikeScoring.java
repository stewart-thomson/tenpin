package com.vodafone.tenpin.scoring;


import com.vodafone.tenpin.model.Frame;

/**
 * If the frame is a strike then use this calculation to work out the frame score.
 * <p>
 * Created by stewart thomson
 */
public class StrikeScoring implements FrameScoring {

    private final FrameScoring next;

    public StrikeScoring(FrameScoring next) {
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
        if (current.isStrike()) {
            if (current.isFinal()) {
                return current.firstBallScore() + current.secondBallScore() + current.getBonusScore();
            } else {
                Frame nextFrame = frames[currentPosition + 1];
                if (nextFrame.isStrike()) {
                    if (nextFrame.isFinal()) {
                        return current.getScore() + nextFrame.firstBallScore() + nextFrame.secondBallScore();
                    } else {
                        Frame twoFrames = frames[currentPosition + 2];
                        return current.getScore() + nextFrame.getScore() + twoFrames.firstBallScore();
                    }
                } else {
                    return current.getScore() + nextFrame.getScore();
                }
            }
        } else {
            return next.execute(frames, currentPosition);
        }
    }
}
