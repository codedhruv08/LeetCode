class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Calculate the prefix sums in-place to get values of merged stones
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Step 2: Initialize DP tracking the maximum score difference from the end
        // If a player chooses the last valid option (taking all remaining stones),
        // they get the total sum of all stones, which is stored in stones[n - 1].
        int maxScoreDifference = stones[n - 1];
        
        // Step 3: Iterate backwards from the second-to-last stone down to index 1
        for (int i = n - 2; i >= 1; i--) {
            maxScoreDifference = Math.max(maxScoreDifference, stones[i] - maxScoreDifference);
        }
        
        return maxScoreDifference;
    }
}
