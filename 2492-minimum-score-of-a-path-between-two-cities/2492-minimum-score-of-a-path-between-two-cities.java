import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Step 1: Build the adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int distance = road[2];
            graph.get(u).add(new int[]{v, distance});
            graph.get(v).add(new int[]{u, distance}); // Bidirectional
        }
        
        // Step 2: Initialize BFS tools
        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        // Start BFS from city 1
        queue.offer(1);
        visited[1] = true;
        
        // Step 3: Traverse the connected component
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int[] neighbor : graph.get(current)) {
                int nextCity = neighbor[0];
                int distance = neighbor[1];
                
                // Track the absolute minimum edge weight seen in this component
                minScore = Math.min(minScore, distance);
                
                if (!visited[nextCity]) {
                    visited[nextCity] = true;
                    queue.offer(nextCity);
                }
            }
        }
        
        return minScore;
    }
}
