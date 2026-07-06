import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // 1. Sort by start point ascending. If starts are equal, sort by end point descending.
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(b[1], a[1]); // Descending end point
            }
        });

        int nonCoveredCount = 0;
        int maxEnd = 0;

        // 2. Iterate through the sorted intervals
        for (int[] interval : intervals) {
            int currentEnd = interval[1];
            
            // If the current interval's end point extends beyond the maxEnd seen so far,
            // it cannot be fully covered by any previously processed interval.
            if (currentEnd > maxEnd) {
                nonCoveredCount++;
                maxEnd = currentEnd; // Update the boundary marker
            }
        }

        return nonCoveredCount;
    }
}
