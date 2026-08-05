import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the adjacency list for the directed graph
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            graph[inv[0]].add(inv[1]);
        }

        // Step 2: Use BFS to find all suspicious methods starting from k
        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.offer(k);
        isSuspicious[k] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph[current]) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        // Step 3: Check if any non-suspicious method invokes a suspicious method
        for (int[] inv : invocations) {
            int u = inv[0]; // Caller
            int v = inv[1]; // Callee
            // If caller is NOT suspicious, but callee IS suspicious
            if (!isSuspicious[u] && isSuspicious[v]) {
                // Cannot isolate/remove the suspicious group; return all methods
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        // Step 4: If isolated, return only the remaining non-suspicious methods
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                remaining.add(i);
            }
        }
        return remaining;
    }
}
