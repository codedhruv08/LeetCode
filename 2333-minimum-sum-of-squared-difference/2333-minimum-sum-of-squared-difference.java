class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int k = k1 + k2;

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.abs(nums1[i] - nums2[i]));
        }

        long[] freq = new long[max + 1];

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
        }

        // Reduce the largest differences first
        for (int d = max; d > 0 && k > 0; d--) {
            if (freq[d] == 0) continue;

            long use = Math.min(freq[d], (long) k);

            freq[d] -= use;
            freq[d - 1] += use;

            k -= use;
        }

        long ans = 0;

        for (int d = 1; d <= max; d++) {
            ans += freq[d] * d * d;
        }

        return ans;
    }
}