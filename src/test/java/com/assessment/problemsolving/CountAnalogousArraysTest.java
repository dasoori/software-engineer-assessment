package com.assessment.problemsolving;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountAnalogousArraysTest {

    @Test
    void sampleFromPrompt() {
        int[] diff = {-2, -1, -2, 5};
        long lower = 3, upper = 10;
        // Expected = 3 (arrays starting at 3,4,5)
        long ans = CountAnalogousArrays.countAnalogousArrays(diff, lower, upper);
        assertEquals(3L, ans);
    }

    @Test
    void singleElementNoDiffs() {
        int[] diff = {}; // only one element
        long lower = -5, upper = -1;
        // Any a0 in [-5,-1] works -> count = 5
        long ans = CountAnalogousArrays.countAnalogousArrays(diff, lower, upper);
        assertEquals(5L, ans);
    }

    @Test
    void emptyIntersection() {
        int[] diff = {1000000000};
        long lower = -1, upper = 1;
        // Impossible: a1 goes out of bounds
        long ans = CountAnalogousArrays.countAnalogousArrays(diff, lower, upper);
        assertEquals(0L, ans);
    }

    @Test
    void largeValuesButSafeWithLong() {
        int[] diff = {2000000000, -2000000000};
        long lower = -1000000000L, upper = 1000000000L;
        long ans = CountAnalogousArrays.countAnalogousArrays(diff, lower, upper);
        assertEquals(1L, ans); // integers from -1e9 to 1e9 inclusive
    }
}
