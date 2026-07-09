class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Array to store the group/component ID for each node
        int[] g = new int[n];
        int cnt = 0;
        
        // Single pass to group nodes into connected components
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                cnt++; // A gap larger than maxDiff breaks the chain, start a new group
            }
            g[i] = cnt;
        }
        
        // Process each query in O(1) time
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            answer[i] = (g[u] == g[v]);
        }
        
        return answer;
    }
}
