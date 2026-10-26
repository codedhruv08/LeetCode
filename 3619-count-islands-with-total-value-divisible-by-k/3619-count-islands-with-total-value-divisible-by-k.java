class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !vis[i][j]) {
                    if (dfs(grid, vis, i, j, k) == 0)
                        ans++;
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] g, boolean[][] vis, int i, int j, int k) {
        int m = g.length, n = g[0].length;
        vis[i][j] = true;
        int sum = g[i][j] % k;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int d = 0; d < 4; d++) {
            int x = i + dx[d], y = j + dy[d];

            if (x >= 0 && x < m && y >= 0 && y < n
                && g[x][y] > 0 && !vis[x][y]) {
                sum = (sum + dfs(g, vis, x, y, k)) % k;
            }
        }
        return sum;
    }
}