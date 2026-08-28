class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length(), h = n / 2;
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int mid = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                if (mid != -1) return "";
                mid = i;
            }
            cnt[i] /= 2;
        }

        // First try exact target left half
        int[] c = cnt.clone();
        StringBuilder left = new StringBuilder();

        for (int i = 0; i < h; i++) {
            int x = target.charAt(i) - 'a';
            if (c[x] == 0) break;
            c[x]--;
            left.append((char)('a' + x));
        }

        if (left.length() == h) {
            String ans = make(left, mid);
            if (ans.compareTo(target) > 0) return ans;
        }

        // Make left half just bigger
        for (int p = h - 1; p >= 0; p--) {
            c = cnt.clone();

            boolean ok = true;
            for (int i = 0; i < p; i++) {
                int x = target.charAt(i) - 'a';
                if (c[x] == 0) {
                    ok = false;
                    break;
                }
                c[x]--;
            }

            if (!ok) continue;

            int x = target.charAt(p) - 'a';

            for (int ch = x + 1; ch < 26; ch++) {
                if (c[ch] == 0) continue;

                StringBuilder l = new StringBuilder();
                for (int i = 0; i < p; i++)
                    l.append(target.charAt(i));

                l.append((char)('a' + ch));
                c[ch]--;

                for (int j = 0; j < 26; j++)
                    while (c[j]-- > 0)
                        l.append((char)('a' + j));

                return make(l, mid);
            }
        }

        return "";
    }

    private String make(StringBuilder l, int mid) {
        String r = new StringBuilder(l).reverse().toString();
        return l.toString() +
               (mid == -1 ? "" : (char)('a' + mid)) + r;
    }
}