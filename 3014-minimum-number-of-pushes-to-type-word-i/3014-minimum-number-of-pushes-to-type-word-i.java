class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);

        int ans = 0;

        for (int i = 25; i >= 0; i--) {
            int position = 25 - i;
            int pushes = position / 8 + 1;
            ans += freq[i] * pushes;
        }

        return ans;
    }
}