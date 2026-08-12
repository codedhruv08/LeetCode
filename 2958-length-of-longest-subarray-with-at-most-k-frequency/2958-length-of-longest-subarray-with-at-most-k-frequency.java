import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int maxLength = 0;
        int left = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        for (int right = 0; right < nums.length; right++) {
            // Add the current element to the frequency map
            frequencyMap.put(nums[right], frequencyMap.getOrDefault(nums[right], 0) + 1);
            
            // If the element's frequency exceeds k, shrink the window from the left
            while (frequencyMap.get(nums[right]) > k) {
                frequencyMap.put(nums[left], frequencyMap.get(nums[left]) - 1);
                left++;
            }
            
            // Calculate and update the maximum length of a valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
