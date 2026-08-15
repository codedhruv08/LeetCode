class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;
        
        // Step 1: Calculate total XOR sum and check for any non-zero element
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // Step 2: Handle greedy case evaluation
        if (totalXor != 0) {
            return nums.length;
        }
        
        if (hasNonZero) {
            return nums.length - 1;
        }
        
        return 0;
    }
}
