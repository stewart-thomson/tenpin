package com.vodafone.tenpin.scoring;

import com.vodafone.tenpin.model.Frame;

/**
 * Interface of the different scoring methods
 * <p>
 * Created by stewart thomson
 */
public interface FrameScoring {

    /**
     * Execute the calculation based on the frames provided and the current frame position.
     *
     * @param frames          All game frames
     * @param currentPosition Current frame position
     * @return the frame score
     */
    int execute(Frame[] frames, int currentPosition);
}
