class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIndex = 0;
        int maxIndex = 0;
        
        // Find indices of minimum and maximum elements
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        // Ensure leftIndex is smaller than rightIndex
        int leftIndex = Math.min(minIndex, maxIndex);
        int rightIndex = Math.max(minIndex, maxIndex);
        
        // Option 1: Remove both from the front (left side)
        int deleteFromFront = rightIndex + 1;
        
        // Option 2: Remove both from the back (right side)
        int deleteFromBack = n - leftIndex;
        
        // Option 3: Remove one from the front and one from the back
        int deleteFromBoth = (leftIndex + 1) + (n - rightIndex);
        
        // Return the minimum of the three options
        return Math.min(deleteFromFront, Math.min(deleteFromBack, deleteFromBoth));
    }
}
