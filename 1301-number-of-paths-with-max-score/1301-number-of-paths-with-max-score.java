import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        // dp[i][j] stores max score from 'S' to (i, j)
        int[][] dp = new int[n][n];
        // count[i][j] stores number of paths achieving that max score
        int[][] count = new int[n][n];

        // Initialize grid with -1 (unreachable)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        // Base case: Start cell 'S' at the bottom-right corner
        dp[n - 1][n - 1] = 0;
        count[n - 1][n - 1] = 1;

        // Iterate backwards from bottom-right up to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Skip if it's an obstacle or the starting cell itself
                if (board.get(i).charAt(j) == 'X' || (i == n - 1 && j == n - 1)) {
                    continue;
                }

                int maxScore = -1;
                int paths = 0;

                // 3 valid directions to come from: down, right, diagonal-down-right
                int[][] directions = {{1, 0}, {0, 1}, {1, 1}};

                for (int[] dir : directions) {
                    int prevI = i + dir[0];
                    int prevJ = j + dir[1];

                    // Check bounds and if the previous cell is reachable
                    if (prevI < n && prevJ < n && dp[prevI][prevJ] != -1) {
                        if (dp[prevI][prevJ] > maxScore) {
                            maxScore = dp[prevI][prevJ];
                            paths = count[prevI][prevJ];
                        } else if (dp[prevI][prevJ] == maxScore) {
                            paths = (paths + count[prevI][prevJ]) % MOD;
                        }
                    }
                }

                // If at least one path reached this cell, update state
                if (maxScore != -1) {
                    char c = board.get(i).charAt(j);
                    int cellValue = (c == 'E') ? 0 : (c - '0');
                    dp[i][j] = maxScore + cellValue;
                    count[i][j] = paths;
                }
            }
        }

        // Return results at destination 'E' (0, 0)
        if (dp[0][0] == -1) {
            return new int[]{0, 0};
        }
        return new int[]{dp[0][0], count[0][0]};
    }
}
