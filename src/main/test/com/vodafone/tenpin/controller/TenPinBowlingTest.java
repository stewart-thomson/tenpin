package com.vodafone.tenpin.controller;

import com.vodafone.tenpin.service.BowlingGame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

/**
 * Created by stewart thomson
 */
public class TenPinBowlingTest {

    private TenPinBowling underTest;

    @Mock
    private PrintStream printStream;
    @Mock
    private BowlingGame bowlingGame;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        underTest = new TenPinBowling(bowlingGame);

    }

    @Test
    public void shouldBuildGameWithSuppliedValues() {
        ByteArrayInputStream in = new ByteArrayInputStream("1 1 1 1".getBytes());
        when(bowlingGame.score()).thenReturn(4);

        underTest.start(in, printStream);

        verify(bowlingGame, times(4)).roll(1);
        verify(bowlingGame, times(1)).score();
    }

    @Test
    public void shouldBuildGameWithNegativeValuesAsZero() {
        ByteArrayInputStream in = new ByteArrayInputStream("-1 1 1 -1".getBytes());
        when(bowlingGame.score()).thenReturn(0);

        underTest.start(in, printStream);

        verify(bowlingGame, times(2)).roll(0);
        verify(bowlingGame, times(2)).roll(1);
        verify(bowlingGame, times(1)).score();
    }

    @Test
    public void shouldBuildGameWithNonNumberValuesAsZero() {
        ByteArrayInputStream in = new ByteArrayInputStream("1 f 1 s".getBytes());
        when(bowlingGame.score()).thenReturn(0);

        underTest.start(in, printStream);

        verify(bowlingGame, times(2)).roll(0);
        verify(bowlingGame, times(2)).roll(1);
        verify(bowlingGame, times(1)).score();
    }

    @Test
    public void shouldNotAddRollsIsNoValuesAdded(){
        ByteArrayInputStream in = new ByteArrayInputStream("\n".getBytes());

        underTest.start(in, printStream);

        verify(bowlingGame, never()).roll(anyInt());
    }
}
