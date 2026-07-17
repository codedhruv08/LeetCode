import java.util.Arrays;

public class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Step 1: Find the maximum value in nums
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        // Frequency table for elements in nums
        long[] freq = new long[maxNum + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // countGcdPair[g] will store the exact number of pairs with GCD equal to g
        long[] countGcdPair = new long[maxNum + 1];

        // Step 2: Iterate backwards to compute exact GCD pairs using inclusion-exclusion
        for (int gcd = maxNum; gcd >= 1; --gcd) {
            long totalMultiples = 0;
            
            // Count how many numbers in nums are multiples of 'gcd'
            for (int multiple = gcd; multiple <= maxNum; multiple += gcd) {
                totalMultiples += freq[multiple];
            }

            // Total pairs that share a common divisor 'gcd'
            long totalPairs = totalMultiples * (totalMultiples - 1) / 2;

            // Subtract pairs whose actual GCD is a strictly larger multiple of 'gcd'
            for (int largerGcd = 2 * gcd; largerGcd <= maxNum; largerGcd += gcd) {
                totalPairs -= countGcdPair[largerGcd];
            }

            countGcdPair[gcd] = totalPairs;
        }

        // Step 3: Create a prefix sum array of the GCD pair counts
        long[] prefixCountGcdPair = new long[maxNum + 1];
        for (int gcd = 1; gcd <= maxNum; ++gcd) {
            prefixCountGcdPair[gcd] = prefixCountGcdPair[gcd - 1] + countGcdPair[gcd];
        }

        // Step 4: Answer each query using binary search
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            ans[i] = binarySearch(queries[i], prefixCountGcdPair, maxNum);
        }

        return ans;
    }

    // Finds the smallest GCD index where the cumulative pairs count covers the query index
    private int binarySearch(long query, long[] prefixCounts, int maxNum) {
        int low = 1;
        int high = maxNum;
        int result = maxNum;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            // Since query is 0-indexed, we check if prefixCounts[mid] is strictly greater than the query
            if (prefixCounts[mid] > query) {
                result = mid;
                high = mid - 1; // Try to find a smaller valid GCD
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}
