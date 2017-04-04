package com.vodafone.tenpin.service;

import com.vodafone.tenpin.model.Frame;
import com.vodafone.tenpin.scoring.FrameScoring;
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
public class ScoringImplTest {


    private ScoringImpl underTest;
    private Frame[] frames;

    @Mock
    private FrameScoring frameScoring;

    @Before
    public void  setup() {
        MockitoAnnotations.initMocks(this);
        underTest = new ScoringImpl(frameScoring);
        frames = new Frame[10];

    }

    @Test
    public void shouldAddScoresForAllFrames() {
        when(frameScoring.execute(any(Frame[].class), anyInt())).thenReturn(5);

        int score = underTest.calculateScore(frames);

        assertThat("Expected total score", score, is(50));
        verify(frameScoring, times(10)).execute(any(Frame[].class), anyInt());
    }

    @Test
    public void shouldReturnZeroWhenFramesAreNull() {

        int score = underTest.calculateScore(null);

        assertThat("Zero returned", score, is(0));
        verify(frameScoring, never()).execute(any(Frame[].class), anyInt());
    }

}
