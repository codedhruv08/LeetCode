class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];

        // dp[end] stores the maximum number of valid palindromes up to index `end`
        for (int end = 1; end <= n; end++) {
            dp[end] = dp[end - 1];

            // Check palindrome of length k
            int startK = end - k;
            if (startK >= 0 && isPalindrome(s, startK, end - 1)) {
                dp[end] = Math.max(dp[end], dp[startK] + 1);
            }

            // Check palindrome of length k + 1
            int startK1 = end - (k + 1);
            if (startK1 >= 0 && isPalindrome(s, startK1, end - 1)) {
                dp[end] = Math.max(dp[end], dp[startK1] + 1);
            }
        }

        return dp[n];
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
