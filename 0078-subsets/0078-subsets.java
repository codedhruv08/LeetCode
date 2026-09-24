class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    void solve(int[] nums, int index, List<Integer> temp,
               List<List<Integer>> ans) {

        ans.add(new ArrayList<>(temp));

        for (int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            solve(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}