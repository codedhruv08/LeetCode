import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int[][] id = new int[m][n];
        int sr = 0, sc = 0;
        int litter = 0;

        // Find S and give every L an id
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sr = i;
                    sc = j;
                } 
                else if (c == 'L') {
                    id[i][j] = litter++;
                }
            }
        }

        if (litter == 0)
            return 0;

        int totalMask = (1 << litter) - 1;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litter];

        Queue<int[]> q = new LinkedList<>();

        // row, col, energy, mask
        q.offer(new int[]{sr, sc, energy, 0});
        visited[sr][sc][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int[] cur = q.poll();

                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];

                if (mask == totalMask)
                    return moves;

                // No energy -> cannot move
                if (e == 0)
                    continue;

                for (int k = 0; k < 4; k++) {

                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n ||
                        classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int ne = e - 1;
                    int nm = mask;

                    char cell = classroom[nr].charAt(nc);

                    // Reset energy
                    if (cell == 'R') {
                        ne = energy;
                    }

                    // Collect litter
                    if (cell == 'L') {
                        nm |= (1 << id[nr][nc]);
                    }

                    if (!visited[nr][nc][ne][nm]) {

                        visited[nr][nc][ne][nm] = true;

                        q.offer(new int[]{
                            nr, nc, ne, nm
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}