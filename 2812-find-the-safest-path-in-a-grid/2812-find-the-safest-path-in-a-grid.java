import java.util.*;

class Solution {
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // If the start or end cell has a thief, the safeness factor is immediately 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        int[][] distToThief = computeDistToThief(grid, n);
        
        // Step 2: Modified Dijkstra's Algorithm using a Max-Heap
        // The heap stores arrays of size 3: {safeness_factor, row, col}
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] visited = new boolean[n][n];

        // Initialize with the starting cell
        maxHeap.offer(new int[]{distToThief[0][0], 0, 0});
        visited[0][0] = true;

        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int maxSafe = curr[0];
            int r = curr[1];
            int c = curr[2];

            // If we reached the bottom-right corner, return the accumulated safeness
            if (r == n - 1 && c == n - 1) {
                return maxSafe;
            }

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // The safeness of the path is limited by the minimum safeness encountered
                    int nextSafe = Math.min(maxSafe, distToThief[nr][nc]);
                    maxHeap.offer(new int[]{nextSafe, nr, nc});
                }
            }
        }

        return 0;
    }

    // Step 1: Multi-source BFS to calculate minimum distance to any thief for each cell
    private int[][] computeDistToThief(List<List<Integer>> grid, int n) {
        int[][] distToThief = new int[n][n];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.offer(new int[]{i, j});
                    distToThief[i][j] = 0; // Thief cell has a distance of 0
                } else {
                    distToThief[i][j] = -1; // Unvisited flag
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && distToThief[nr][nc] == -1) {
                    distToThief[nr][nc] = distToThief[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return distToThief;
    }
}
