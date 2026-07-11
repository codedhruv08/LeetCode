import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private List<Integer>[] graph;
    private boolean[] visited;

    public int countCompleteComponents(int n, int[][] edges) {
        // Initialize the adjacency list
        graph = new List[n];
        visited = new boolean[n];
        Arrays.setAll(graph, k -> new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int completeComponentsCount = 0;

        // Traverse all components using DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] counts = dfs(i);
                int vertexCount = counts[0];
                int totalDegrees = counts[1];

                // A component is complete if the total degree matches V * (V - 1)
                if (vertexCount * (vertexCount - 1) == totalDegrees) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }

    private int[] dfs(int node) {
        visited[node] = true;
        int vertexCount = 1;
        int totalDegrees = graph[node].size();

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                int[] neighborCounts = dfs(neighbor);
                vertexCount += neighborCounts[0];
                totalDegrees += neighborCounts[1];
            }
        }

        return new int[]{vertexCount, totalDegrees};
    }
}
