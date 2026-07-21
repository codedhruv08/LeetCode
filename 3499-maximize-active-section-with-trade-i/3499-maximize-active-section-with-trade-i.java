class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0, best = 0;
        for (char c : s.toCharArray())
            if (c == '1') ones++;

        String t = "1" + s + "1";

        int i = 0;
        while (i < t.length()) {
            int start = i;
            char c = t.charAt(i);

            while (i < t.length() && t.charAt(i) == c)
                i++;

            int len = i - start;

            if (c == '1' && start > 0 && i < t.length()
                    && t.charAt(start - 1) == '0'
                    && t.charAt(i) == '0') {

                int left = 0, j = start - 1;
                while (j >= 0 && t.charAt(j) == '0') {
                    left++;
                    j--;
                }

                int right = 0;
                j = i;
                while (j < t.length() && t.charAt(j) == '0') {
                    right++;
                    j++;
                }

                best = Math.max(best, left + right);
            }
        }

        return ones + best;
    }
}