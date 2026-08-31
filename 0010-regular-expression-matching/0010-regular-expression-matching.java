class Solution {
    private Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p);
    }

    private boolean dp(int i, int j, String s, String p) {
        // If the result is already computed, return it from the cache
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        // Base Case: If pattern is exhausted, string must be exhausted too
        if (j == p.length()) {
            return i == s.length();
        }

        // Check if current characters match
        boolean firstMatch = (i < s.length() && 
                             (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));

        boolean ans;
        // If the next character in pattern is '*'
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // Case 1: Match 0 times (skip the character and '*')
            // Case 2: Match 1 or more times (if firstMatch is true, move string index)
            ans = dp(i, j + 2, s, p) || (firstMatch && dp(i + 1, j, s, p));
        } else {
            // No '*', proceed to the next characters sequentially
            ans = firstMatch && dp(i + 1, j + 1, s, p);
        }

        // Cache the answer before returning
        memo[i][j] = ans;
        return ans;
    }
}
