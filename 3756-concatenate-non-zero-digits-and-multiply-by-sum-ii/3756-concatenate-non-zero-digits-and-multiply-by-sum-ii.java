class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long MOD = 1000000007L;

        // Precompute powers of 10 modulo 10^9 + 7
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 0; i < n; i++) {
            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }

        // prefixNonZeroCount[i] = count of non-zero digits in first i chars
        int[] prefixNonZeroCount = new int[n + 1];
        // prefixConcatValue[i] = integer formed by non-zero digits in first i chars (modulo MOD)
        long[] prefixConcatValue = new long[n + 1];
        // prefixDigitSum[i] = arithmetic sum of all digits in first i chars
        long[] prefixDigitSum = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';

            // 1. Maintain count of non-zero digits
            prefixNonZeroCount[i + 1] = prefixNonZeroCount[i] + (d != 0 ? 1 : 0);

            // 2. Compute the ongoing concatenated integer value
            if (d != 0) {
                prefixConcatValue[i + 1] = (prefixConcatValue[i] * 10 + d) % MOD;
            } else {
                prefixConcatValue[i + 1] = prefixConcatValue[i];
            }

            // 3. Maintain prefix sum of digits
            prefixDigitSum[i + 1] = prefixDigitSum[i] + d;
        }

        int numQueries = queries.length;
        int[] answer = new int[numQueries];

        for (int q = 0; q < numQueries; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            // Extract the range characteristics from our prefix arrays
            int nonZeroDigitsInRange = prefixNonZeroCount[r + 1] - prefixNonZeroCount[l];
            long digitSumInRange = prefixDigitSum[r + 1] - prefixDigitSum[l];

            // Use the prefix string subtraction formula:
            // x = prefixConcatValue[r+1] - prefixConcatValue[l] * 10^(nonZeroDigitsInRange)
            long shiftedLeft = (prefixConcatValue[l] * pow10[nonZeroDigitsInRange]) % MOD;
            long x = (prefixConcatValue[r + 1] - shiftedLeft + MOD) % MOD;

            // Calculate the answer for the current query
            long finalProduct = (x * digitSumInRange) % MOD;
            answer[q] = (int) finalProduct;
        }

        return answer;
    }
}
