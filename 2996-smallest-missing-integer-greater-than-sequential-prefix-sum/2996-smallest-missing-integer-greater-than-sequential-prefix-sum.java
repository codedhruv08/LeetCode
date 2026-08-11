import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Calculate the longest sequential prefix sum
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break; // Sequential prefix breaks here
            }
        }
        
        // Step 2: Store all array elements in a HashSet for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        // Step 3: Find the smallest missing integer >= sum
        while (numSet.contains(sum)) {
            sum++;
        }
        
        return sum;
    }
}
