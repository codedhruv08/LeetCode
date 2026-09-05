class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] rightMin = new int[n];
        
        // Step 1: Precompute the minimum values from the right side (suffix minimum)
        rightMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], nums[i]);
        }
        
        int leftMax = 0;
        // Step 2: Iterate from left to right tracking max prefix and checking condition
        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, nums[i]);
            if (leftMax - rightMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
