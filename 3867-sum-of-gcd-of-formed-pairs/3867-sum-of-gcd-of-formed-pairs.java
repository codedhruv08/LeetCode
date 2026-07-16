import java.util.Arrays;

public class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int maxEl = -1;
        
        // Step 1 & 2: Construct the prefixGcd array using prefix max
        for (int i = 0; i < n; i++) {
            maxEl = Math.max(maxEl, nums[i]);
            prefixGcd[i] = gcd(nums[i], maxEl);
        }
        
        // Step 3: Sort the resulting array
        Arrays.sort(prefixGcd);
        
        long totalSum = 0;
        int left = 0;
        int right = n - 1;
        
        // Step 4 & 5: Form pairs from outside-in and sum their GCDs
        while (left < right) {
            totalSum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return totalSum;
    }
    
    // Helper method to calculate Greatest Common Divisor (GCD)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
