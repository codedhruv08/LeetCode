import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        // Modulo k by total elements to avoid redundant full rotations
        k = k % totalElements;
        
        // Initialize the outer result list
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>());
        }
        
        // Build the result matrix using mathematical mapping
        for (int i = 0; i < totalElements; i++) {
            // Find the original 1D index by shifting backward by k positions
            int original1DIndex = (i - k + totalElements) % totalElements;
            
            // Map the 1D index back into its 2D coordinates in the original grid
            int originalRow = original1DIndex / n;
            int originalCol = original1DIndex % n;
            
            // Map the current 1D index into its target 2D row index
            int targetRow = i / n;
            
            // Add the element directly to the pre-initialized row list
            result.get(targetRow).add(grid[originalRow][originalCol]);
        }
        
        return result;
    }
}
