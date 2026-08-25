import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Step 1: Add all numbers from the array to a HashSet for O(1) lookups
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            seen.add(num);
        }
        
        // Step 2: Test positive multiples of k (k, 2k, 3k...) sequentially
        int currentMultiple = k;
        while (seen.contains(currentMultiple)) {
            currentMultiple += k;
        }
        
        // Step 3: Return the first multiple not found in the set
        return currentMultiple;
    }
}
