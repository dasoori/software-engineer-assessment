package com.assessment.problemsolving;

public class CountAnalogousArrays {

    /**
     * Returns the number of integer arrays a[] that are "analogous"
     * given the consecutive differences and bounds.
     * Approach:
     *  - Let a[0] = x (unknown). From (a[i-1] - a[i]) = diff[i-1], we get:
     *      a[i] = x + offset[i]
     *      where offset[0] = 0 and offset[i] = -sum_{k=0..i-1} diff[k]
     *  - For all i: lower <= x + offset[i] <= upper
     *      ==> x in [lower - offset[i], upper - offset[i]]
     *  - Intersect all intervals to get [lo, hi]
     *  - Answer = max(0, hi - lo + 1)
     * Complexity: O(n) time, O(1) space.
     */
    public static long countAnalogousArrays(int[] diff, long lower, long upper) {
        long lo = Long.MIN_VALUE;
        long hi = Long.MAX_VALUE;

        long offset = 0L;

        // Check for i = 0
        lo = Math.max(lo, lower - offset);
        hi = Math.min(hi, upper - offset);

        for (int d : diff) {
            offset -= (long) d;
            lo = Math.max(lo, lower - offset);
            hi = Math.min(hi, upper - offset);

            if (lo > hi) return 0L; // early exit
        }
        return Math.max(0L, hi - lo + 1);
    }
}
