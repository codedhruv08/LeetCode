import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Step 1: Duplicate and sort the array
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        // Step 2: Map unique numbers to their sequential ranks
        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int num : sortedArr) {
            rankMap.putIfAbsent(num, rankMap.size() + 1);
        }
        
        // Step 3: Replace original values with computed ranks
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rankMap.get(arr[i]);
        }
        
        return arr;
    }
}
