package com.vodafone.tenpin.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by stewart thomson
 */
public class FrameTest {

    private Frame underTest;


    @Before
    public void setup() {

        underTest = new Frame(false);
    }


    @Test
    public void shouldAddSingleThrow() {
        underTest.setTurnScore(3);

        assertThat("Throw is added in the correct place", underTest.firstBallScore(), is(3));
        assertThat("frame score is", underTest.getScore(), is(3));
    }

    @Test
    public void shouldAddBothThrows() {
        underTest.setTurnScore(3);
        underTest.setTurnScore(5);

        assertThat("First throw is added in the correct place", underTest.firstBallScore(), is(3));
        assertThat("Second throw is added in the correct place", underTest.secondBallScore(), is(5));

        assertThat("frame score is expected", underTest.getScore(), is(8));
    }

    @Test
    public void shouldAddBonusThrowInFinalFrameForStrike() {
        underTest = new Frame(true);
        underTest.setTurnScore(10);
        underTest.setTurnScore(5);
        underTest.setTurnScore(7);

        assertThat("First throw is added in the correct place", underTest.firstBallScore(), is(10));
        assertThat("Second throw is added in the correct place", underTest.secondBallScore(), is(5));
        assertThat("Bonus score is correct", underTest.getBonusScore(), is(7));

        assertThat("frame score is expected", underTest.getScore(), is(10));
    }

    @Test
    public void shouldAddBonusThrowInFinalFrameForSpare() {
        underTest = new Frame(true);
        underTest.setTurnScore(5);
        underTest.setTurnScore(5);
        underTest.setTurnScore(7);

        assertThat("First throw is added in the correct place", underTest.firstBallScore(), is(5));
        assertThat("Second throw is added in the correct place", underTest.secondBallScore(), is(5));
        assertThat("Bonus score is correct", underTest.getBonusScore(), is(7));

        assertThat("frame score is expected", underTest.getScore(), is(10));
    }

    @Test
    public void shouldNotAddMoreThanTenPinsForNormalThrow() {
        underTest.setTurnScore(3);
        underTest.setTurnScore(9);

        assertThat("First throw is added in the correct place", underTest.firstBallScore(), is(3));
        assertThat("Second throw is added in the correct place", underTest.secondBallScore(), is(9));

        assertThat("frame score is set to pin max", underTest.getScore(), is(10));
    }

    @Test
    public void shouldReturnFrameIsAStrike() {
        underTest.setTurnScore(10);

        assertThat("Frame is a strike", underTest.isStrike(), is(true));
    }

    @Test
    public void shouldReturnFrameIsSpare() {
        underTest.setTurnScore(5);
        underTest.setTurnScore(5);

        assertThat("Frame is a spare", underTest.isSpare(), is(true));
    }

    @Test
    public void shouldReturnFrameIsFinal() {
        underTest = new Frame(true);

        assertThat("Frame is the final", underTest.isFinal(), is(true));
    }
}
