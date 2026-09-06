class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length();
        int n = s.length();
        
        // dp[i][j] means the count of distinct subsequences of t[0..i-1] in s[0..j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base case: An empty string t can always be formed 1 time by any prefix of s
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Carry over the value without the current character of s
                dp[i + 1][j + 1] = dp[i + 1][j];
                
                // If characters match, add the ways from the previous states
                if (t.charAt(i) == s.charAt(j)) {
                    dp[i + 1][j + 1] += dp[i][j];
                }
            }
        }
        
        return dp[m][n];
    }
}
