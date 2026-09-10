/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int matchCount = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchCount;
    }

    // Returns an int array where index 0 is the sum, and index 1 is the count
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] { 0, 0 };
        }

        // Post-order traversal: process left and right subtrees
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int currentSum = left[0] + right[0] + root.val;
        int currentCount = left[1] + right[1] + 1;

        // Check if node value equals the average (integer division handles floor average)
        if (currentSum / currentCount == root.val) {
            matchCount++;
        }

        return new int[] { currentSum, currentCount };
    }
}
