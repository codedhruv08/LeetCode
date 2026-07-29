class Solution {

    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        // Count characters
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Find middle character (if length is odd)
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                middle = (char) ('a' + i);
                freq[i]--;
                break;
            }
        }

        // We only need half of the palindrome
        int[] half = new int[26];
        int n = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            n += half[i];
        }

        // Check total number of possible permutations
        long total = countPermutations(half, n);

        if (total < k) {
            return "";
        }

        // Build k-th smallest half
        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < n; pos++) {

            for (int ch = 0; ch < 26; ch++) {

                if (half[ch] == 0) {
                    continue;
                }

                // Try putting this character
                half[ch]--;

                long ways = countPermutations(half, n - pos - 1);

                if (k > ways) {
                    // Skip these permutations
                    k -= ways;
                    half[ch]++;
                } else {
                    // This character belongs here
                    left.append((char) ('a' + ch));
                    break;
                }
            }
        }

        // Create complete palindrome
        String right = new StringBuilder(left).reverse().toString();

        return left.toString()
                + (middle == 0 ? "" : String.valueOf(middle))
                + right;
    }

    private long countPermutations(int[] freq, int total) {

        long result = 1;

        for (int i = 0; i < 26; i++) {

            for (int j = 1; j <= freq[i]; j++) {

                result = result * (total - freq[i] + j) / j;

                // We only care if result reaches k
                if (result >= LIMIT) {
                    return LIMIT;
                }
            }

            total -= freq[i];
        }

        return result;
    }
}