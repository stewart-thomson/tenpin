package com.vodafone.tenpin.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by stewart thomson
 */
public class TenPinBowlingIntegrationTest {

    private TenPinBowling underTest;

    private PrintStream printStream;
    private ByteArrayOutputStream baos;

    @Before
    public void setup() {
        underTest = new TenPinBowling();
        baos = new ByteArrayOutputStream();
        printStream = new PrintStream(baos);
    }

    @Test
    public void shouldHandlePerfectScore() {
        testValue("10 10 10 10 10 10 10 10 10 10 10 10", "300");
    }

    @Test
    public void shouldHandleGame1() {
        testValue("3 5 4 6 10 10 9 0 4 5 4 2 0 6 5 2 2 4", "119");
    }

    @Test
    public void shouldHandleGame2() {
        testValue("9 1 9 1 9 1 9 1 9 1 9 1 9 1 9 1 9 1 9 1 6", "187");
    }

    @Test
    public void shouldHandleGame3() {
        testValue("1 2 3 4", "10");
    }

    @Test
    public void shouldHandleGame4() {
        testValue("9 1 9 1", "29");
    }

    @Test
    public void shouldHandleGame5() {
        testValue("1 1 1 1 10 1 1", "18");
    }

    @Test
    public void shouldHandleGame6() {
        testValue("f 3 4 6 3 5 10 10 6 4 3 5 2 5 2 5 10 10 10", "135");
    }

    @Test
    public void shouldHandleGame7() {
        testValue("2 3 5 5 4 5 6 4 3 7 3 5 2 7 2 5 4 6 4 6 5", "107");
    }

    @Test
    public void shouldHandleGame8() {
        testValue("2 10 5 5 4 5 6 4 3 7 3 5 2 7 2 5 4 6 4 6 5", "117");
    }

    @Test
    public void shouldHandleGame9() {
        testValue("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1", "20");
    }

    private void testValue(String entry, String total) {
        ByteArrayInputStream in = new ByteArrayInputStream(entry.getBytes());

        underTest.start(in, printStream);

        String output = new String(baos.toByteArray());

        assertThat(output, startsWith(total));
    }
}
