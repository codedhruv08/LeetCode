import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        
        // Collect coordinates of all '1's in both images
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    ones1.add(new int[]{i, j});
                }
                if (img2[i][j] == 1) {
                    ones2.add(new int[]{i, j});
                }
            }
        }
        
        Map<Integer, Integer> offsetCount = new HashMap<>();
        int maxOverlap = 0;
        int magic = 100; // Large enough multiplier to avoid coordinate collision
        
        // Calculate the relative shift for each pair of ones
        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int rOffset = p1[0] - p2[0];
                int cOffset = p1[1] - p2[1];
                int key = rOffset * magic + cOffset;
                
                offsetCount.put(key, offsetCount.getOrDefault(key, 0) + 1);
            }
        }
        
        // Find the maximum frequency of any single shift vector
        for (int count : offsetCount.values()) {
            maxOverlap = Math.max(maxOverlap, count);
        }
        
        return maxOverlap;
    }
}
