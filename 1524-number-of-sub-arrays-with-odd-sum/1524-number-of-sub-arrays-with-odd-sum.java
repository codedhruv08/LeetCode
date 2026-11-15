class Solution {
    public int numOfSubarrays(int[] arr) {
        int odd = 0, even = 1, sum = 0, ans = 0;
        int mod = 1000000007;

        for (int x : arr) {
            sum += x;

            if (sum % 2 == 0) {
                ans += odd;
                even++;
            } else {
                ans += even;
                odd++;
            }

            ans %= mod;
        }

        return ans;
    }
}