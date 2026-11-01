class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        int sum = 0;

        for (int n : nums) {
            min = Math.min(min, n);
            sum += n;
        }

        return sum - nums.length * min;
    }
}