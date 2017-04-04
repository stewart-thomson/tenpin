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
public class StrikeScoringTest {

    private StrikeScoring underTest;
    private Frame[] scores;

    private Frame current;

    @Mock
    private FrameScoring next;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        scores = new Frame[10];
        underTest = new StrikeScoring(next);
    }


    @Test
    public void shouldReturnBonusForFinalFrame() {
        scores[0] = buildFrame(true, 10, 10, 10);
        int score = underTest.execute(scores, 0);

        assertThat("Expected max score", score, is(30));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldReturnMaxStrikeScore() {
        scores[0] = buildFrame(false, 10, 0, 0);
        scores[1] = buildFrame(false, 10, 0, 0);
        scores[2] = buildFrame(false, 10, 0, 0);
        current = scores[0];

        int score = underTest.execute(scores, 0);

        assertThat("Expected max score", score, is(30));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldUseBothValuesWhenNonStrikeFrame() {
        scores[0] = buildFrame(false, 10, 0, 0);
        scores[1] = buildFrame(false, 10, 0, 0);
        scores[2] = buildFrame(false, 2, 5, 0);
        current = scores[0];

        int score = underTest.execute(scores, 0);

        assertThat("Expected max score", score, is(22));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldReturnScoreWhenFinalFrameFollowsAStrike() {
        scores[0] = buildFrame(false, 10, 0, 0);
        scores[1] = buildFrame(true, 5, 5, 0);
        current = scores[0];

        int score = underTest.execute(scores, 0);

        assertThat("Expected max score", score, is(20));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldReturnStrikeScoreWhenFinalFrameFollowsAStrike() {
        scores[0] = buildFrame(false, 10, 0, 0);
        scores[1] = buildFrame(true, 10, 5, 2);
        current = scores[0];

        int score = underTest.execute(scores, 0);

        assertThat("Expected max score", score, is(25));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldDelegateToNextScoringClass() {
        when(next.execute(any(Frame[].class), anyInt())).thenReturn(6);
        scores[0] = buildFrame(false, 5, 2, 0);

        int score = underTest.execute(scores, 0);

        assertThat("Delegate score returned", score, is(6));
        verify(next, times(1)).execute(eq(scores), eq(0));
    }

    @Test
    public void shouldReturnZeroWhenFramesAreNull() {
        int score = underTest.execute(null, 1);

        assertThat("Zero is returned", score, is(0));
        verify(next, never()).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldReturnZeroWhenCurrentFrameIsNull() {
        scores[0] = buildFrame(false, 5, 2, 0);

        int score = underTest.execute(scores, 1);
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
