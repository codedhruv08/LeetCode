public class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // lastIndex[j] stores the largest index in word1 that can form 
        // a valid exact match for the suffix word2[j...m-1]
        int[] lastIndex = new int[m];
        java.util.Arrays.fill(lastIndex, -1);
        
        int i = n - 1;
        int j = m - 1;
        
        // Step 1: Precompute the rightmost valid matches for suffixes
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                lastIndex[j] = i;
                j--;
            }
            i--;
        }
        
        int[] result = new int[m];
        boolean canModify = true; // We can modify at most 1 character
        j = 0; // Pointer for word2
        
        // Step 2: Greedily construct the lexicographically smallest sequence
        for (i = 0; i < n; i++) {
            if (j == m) {
                break;
            }
            
            // Scenario A: Exact character match
            if (word1.charAt(i) == word2.charAt(j)) {
                result[j] = i;
                j++;
            } 
            // Scenario B: Characters mismatch, attempt a greedy modification
            else if (canModify && (j == m - 1 || lastIndex[j + 1] > i)) {
                result[j] = i; // Use our modification slot here
                canModify = false; // Modification consumed
                j++;
            }
        }
        
        // If we successfully matched all characters of word2, return the sequence
        return j == m ? result : new int[0];
    }
}
