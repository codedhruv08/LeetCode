class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        // dp[x][y] stores the number of disjoint subsequence pairs 
        // with GCDs equal to x and y respectively.
        int[][] dp = new int[maxNum + 1][maxNum + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            int[][] nextDp = new int[maxNum + 1][maxNum + 1];
            
            for (int x = 0; x <= maxNum; x++) {
                for (int y = 0; y <= maxNum; y++) {
                    if (dp[x][y] == 0) continue;

                    long currentWays = dp[x][y];

                    // Option 1: Do not include 'num' in either subsequence
                    nextDp[x][y] = (int) ((nextDp[x][y] + currentWays) % MOD);

                    // Option 2: Include 'num' in the first subsequence
                    int newX = gcd(x, num);
                    nextDp[newX][y] = (int) ((nextDp[newX][y] + currentWays) % MOD);

                    // Option 3: Include 'num' in the second subsequence
                    int newY = gcd(y, num);
                    nextDp[x][newY] = (int) ((nextDp[x][newY] + currentWays) % MOD);
                }
            }
            dp = nextDp;
        }

        long totalPairs = 0;
        // Sum up pairs where both subsequences are non-empty (g > 0) and have equal GCD
        for (int g = 1; g <= maxNum; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        if (a == 0) return b;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
