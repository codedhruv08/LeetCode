import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count frequencies of each letter
        int[] frequency = new int[26];
        for (char c : word.toCharArray()) {
            frequency[c - 'a']++;
        }
        
        // Step 2: Sort frequencies in ascending order
        Arrays.sort(frequency);
        
        int totalPushes = 0;
        int distinctLettersProcessed = 0;
        
        // Step 3: Map the most frequent letters to the earliest positions (Greedy)
        // Process from highest frequency (index 25) down to lowest (index 0)
        for (int i = 25; i >= 0; i--) {
            if (frequency[i] == 0) {
                break; // No more letters left to process
            }
            
            // Calculate the number of key presses required for the current letter position
            int pressesNeeded = (distinctLettersProcessed / 8) + 1;
            totalPushes += frequency[i] * pressesNeeded;
            
            distinctLettersProcessed++;
        }
        
        return totalPushes;
    }
}
