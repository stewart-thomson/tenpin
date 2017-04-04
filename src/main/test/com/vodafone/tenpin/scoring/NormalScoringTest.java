package com.vodafone.tenpin.scoring;

import com.vodafone.tenpin.model.Frame;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by stewart thomson
 */
public class NormalScoringTest {

    private NormalScoring underTest;
    private Frame[] frames;

    @Before
    public void setup() {

        underTest = new NormalScoring();
        frames = new Frame[10];
    }

    @Test
    public void shouldReturnFrameScore() {
        Frame aFrame = new Frame(false);
        aFrame.setTurnScore(1);
        aFrame.setTurnScore(3);
        frames[0] = aFrame;

        int value = underTest.execute(frames, 0);

        assertThat("Expected value", value, is(4));
    }

    @Test
    public  void shouldReturnZeroWhenFramesAreNull() {

        int value = underTest.execute(null, 1);

        assertThat("Zero is returned", value, is(0));
    }

    @Test
    public void shouldReturnZeroWhenCurrentFrameIsNull() {
        Frame aFrame = new Frame(false);
        aFrame.setTurnScore(1);
        aFrame.setTurnScore(3);
        frames[0] = aFrame;

        int value = underTest.execute(frames, 1);

        assertThat("Zero is returned", value, is(0));
    }
}
