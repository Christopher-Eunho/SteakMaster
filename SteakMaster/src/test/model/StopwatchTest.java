package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StopwatchTest {
    private Clock testStopWatch;

    @BeforeEach
    void runBefore() {
        testStopWatch = new Clock();
    }


    @Test
    void testAddTime() {
        testStopWatch.addTime(1);
        assertEquals(1, testStopWatch.getTime());

        testStopWatch.addTime(100);
        assertEquals(100 + 1, testStopWatch.getTime());

        testStopWatch.addTime(12378);
        assertEquals(100 + 1 + 12378, testStopWatch.getTime());


    }


    @Test
    void testGetTimeLeft() {
        testStopWatch.addTime(10);
        assertEquals(10 ,testStopWatch.getTimeLeft());

        testStopWatch.addTime(100);
        assertEquals(10 + 100 ,testStopWatch.getTimeLeft());
    }

    @Test
    void testTickSecond() {
        testStopWatch.addTime(100);

        testStopWatch.tickSecond();
        assertEquals(100 - 1 ,testStopWatch.getTimeLeft());

        for(int i = 0; i < 13; i++) {
            testStopWatch.tickSecond();
        }
        assertEquals(100 - 1 - 13  ,testStopWatch.getTimeLeft());

        for(int i = 0; i < 100 - 1 - 13; i++) {
            testStopWatch.tickSecond();
        }
        assertEquals(0  ,testStopWatch.getTimeLeft());

    }



    @Test
    void testGetTimeInMinSec() {
        testStopWatch.addTime(1);
        assertEquals("1 s", testStopWatch.getTimeInMinSec());

        testStopWatch.addTime(30);
        assertEquals("31 s", testStopWatch.getTimeInMinSec());

        testStopWatch.addTime(28);
        assertEquals("59 s", testStopWatch.getTimeInMinSec());

        testStopWatch.addTime(1);
        assertEquals("1 m 0 s", testStopWatch.getTimeInMinSec());

        testStopWatch.addTime(13);
        assertEquals("1 m 13 s", testStopWatch.getTimeInMinSec());

        testStopWatch.addTime(107);
        assertEquals("3 m 0 s", testStopWatch.getTimeInMinSec());

        testStopWatch.addTime(10000);
        assertEquals("169 m 40 s", testStopWatch.getTimeInMinSec());
    }


    @Test
    void testResetTimeLeft() {
        testStopWatch.addTime(100);

        testStopWatch.tickSecond();
        assertEquals(99, testStopWatch.getTimeLeft());

        testStopWatch.resetTimeLeft();
        assertEquals(100, testStopWatch.getTimeLeft());

        for(int i = 0; i < 100; i++) {
            testStopWatch.tickSecond();
        }

        testStopWatch.resetTimeLeft();
        assertEquals(100, testStopWatch.getTimeLeft());

    }

}
