package com.vodafone.tenpin.scoring;

import com.vodafone.tenpin.model.Frame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

/**
 * Created by stewart thomson
 */
public class SpareScoringTest {

    private Frame[] scores;

    private Frame current;

    @Mock
    private FrameScoring next;
    private SpareScoring underTest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        scores = new Frame[10];
        underTest = new SpareScoring(next);
    }

    @Test
    public void shouldIncludeFirstBallScoreOfNextFrame() {
        scores[0] = buildFrame(false, 5, 5, 0);
        scores[1] = buildFrame(false, 5, 5, 0);
        current = scores[0];

        int score = underTest.execute(scores, 0);

        assertThat("Expected score", score, is(15));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldIncludeBonusBallWhenFrameIsFinal() {
        scores[0] = buildFrame(true, 5, 5, 7);
        current = scores[0];

        int score = underTest.execute(scores, 0);

        assertThat("Expected score", score, is(17));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldDelegateToNextScoringClass() {
        when(next.execute(any(Frame[].class), anyInt())).thenReturn(6);
        scores[0] = buildFrame(false, 5, 0, 0);

        int score = underTest.execute(scores, 0);

        assertThat("Delegate score returned", score, is(6));
        verify(next, times(1)).execute(eq(scores), eq(0));
    }

    @Test
    public void shouldReturnZeroWhenCurrentFrameIsNull() {
        scores[0] = buildFrame(false, 5, 0, 0);

        int score = underTest.execute(scores, 1);

        assertThat("Zero is returned", score, is(0));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldReturnZeroWhenFrameIsNull() {
        int score = underTest.execute(null, 1);

        assertThat("Zero is returned", score, is(0));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }



    private Frame buildFrame(boolean isFinal, int firstPin, int secondPin, int bonus) {
        Frame aFrame = new Frame(isFinal);
        aFrame.setTurnScore(firstPin);
        aFrame.setTurnScore(secondPin);
        aFrame.setTurnScore(bonus);
        return aFrame;
    }
}
