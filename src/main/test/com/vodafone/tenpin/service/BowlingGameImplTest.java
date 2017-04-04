package com.vodafone.tenpin.service;

import com.vodafone.tenpin.model.Frame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by stewart thomson
 */
public class BowlingGameImplTest {

    private BowlingGameImpl underTest;

    @Mock
    private ScoringImpl scoring;

    private Frame[] frames;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        frames = new Frame[10];
        buildFrames();

    }

    @Test
    public void shouldAdvanceToNextFrameWhenAStrikeOccurs() {
        underTest = new BowlingGameImpl(scoring, frames);
        underTest.roll(10);

        int currentFrame = underTest.currentFrame();
        assertThat("Current frame is next", currentFrame, is(1));
        verify(frames[0], times(1)).setTurnScore(eq(10));
    }

    @Test
    public void shouldStillBeSameFrameWhenNotStrike() {
        underTest = new BowlingGameImpl(scoring, frames);
        underTest.roll(1);

        int currentFrame = underTest.currentFrame();
        assertThat("Current frame is same", currentFrame, is(0));
        verify(frames[0], times(1)).setTurnScore(eq(1));
    }

    @Test
    public void shouldAdvanceToNextFrameAfterSecondBall() {
        underTest = new BowlingGameImpl(scoring, frames);
        underTest.roll(1);
        underTest.roll(1);

        int currentFrame = underTest.currentFrame();
        assertThat("Current frame is same", currentFrame, is(1));
        verify(frames[0], times(2)).setTurnScore(eq(1));
    }

    @Test
    public void shouldReturnScore() {
        when(scoring.calculateScore(frames)).thenReturn(120);
        underTest = new BowlingGameImpl(scoring, frames);

        int score = underTest.score();
        assertThat("Expected score returned", score, is(120));
        verify(scoring, times(1)).calculateScore(frames);
    }


    private void buildFrames() {
        for (int i = 0; i < 10; i++) {
            Frame frame = mock(Frame.class);
            frames[i] = frame;
        }
    }
}
