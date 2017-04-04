package com.vodafone.tenpin.scoring;

import com.vodafone.tenpin.model.Frame;

/**
 * If the frame is not either a Strike or a Spare then use
 * this default calculation
 * <p>
 * Created by stewart thomson
 */
public class NormalScoring implements FrameScoring {


    public int execute(Frame[] frames, int currentPosition) {
        if (frames == null) {
            return 0;
        }
        Frame current = frames[currentPosition];
        if (current == null) {
            return 0;
        }
        return current.getScore();
    }
}
