import java.util.HashSet;
import java.util.Set;

class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        int n = digits.length;

        // Iterate through all triplets of distinct indices (i, j, k)
        // i -> hundreds place, j -> tens place, k -> ones place
        for (int i = 0; i < n; i++) {
            // Hundreds place cannot be zero
            if (digits[i] == 0) continue; 
            
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // Must be a different index
                
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue; // Must be a different index
                    
                    // Ones place must be even
                    if (digits[k] % 2 != 0) continue; 
                    
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    uniqueNumbers.add(num);
                }
            }
        }

        return uniqueNumbers.size();
    }
}
