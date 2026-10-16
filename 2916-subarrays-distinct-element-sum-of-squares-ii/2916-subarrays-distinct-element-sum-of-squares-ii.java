class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long[] sum = new long[4 * n];
        long[] sq = new long[4 * n];
        long[] lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            last[nums[i]] = i + 1;

            update(1, 0, n - 1, prev, i, sum, sq, lazy);

            ans = (ans + query(1, 0, n - 1, 0, i, sum, sq, lazy)) % MOD;
        }

        return (int) ans;
    }

    void push(int node, int l, int r, long[] sum,
              long[] sq, long[] lazy) {
        if (lazy[node] == 0) return;

        long v = lazy[node];
        long len = r - l + 1L;

        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                    + len % MOD * v % MOD * v % MOD) % MOD;
        sum[node] = (sum[node] + len * v) % MOD;

        if (l != r) {
            lazy[node * 2] = (lazy[node * 2] + v) % MOD;
            lazy[node * 2 + 1] = (lazy[node * 2 + 1] + v) % MOD;
        }

        lazy[node] = 0;
    }

    void update(int node, int l, int r, int ql, int qr,
                long[] sum, long[] sq, long[] lazy) {
        push(node, l, r, sum, sq, lazy);

        if (r < ql || l > qr) return;

        if (ql <= l && r <= qr) {
            lazy[node] = (lazy[node] + 1) % MOD;
            push(node, l, r, sum, sq, lazy);
            return;
        }

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr, sum, sq, lazy);
        update(node * 2 + 1, mid + 1, r, ql, qr, sum, sq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    long query(int node, int l, int r, int ql, int qr,
               long[] sum, long[] sq, long[] lazy) {
        push(node, l, r, sum, sq, lazy);

        if (r < ql || l > qr) return 0;

        if (ql <= l && r <= qr) return sq[node];

        int mid = (l + r) / 2;

        return (query(node * 2, l, mid, ql, qr, sum, sq, lazy)
                + query(node * 2 + 1, mid + 1, r, ql, qr,
                        sum, sq, lazy)) % MOD;
    }
}