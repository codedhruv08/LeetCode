import java.util.*;

class Solution {
    int k;
    int[][] cnt;
    int[] prod;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;

        cnt = new int[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val % k);

            int[] res = query(1, 0, n - 1, start, n - 1);

            ans[i] = res[x];
        }

        return ans;
    }

    void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            prod[node] = nums[l] % k;
            cnt[node][prod[node]] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        merge(node);
    }

    void merge(int node) {
        int left = node * 2;
        int right = node * 2 + 1;

        prod[node] = (prod[left] * prod[right]) % k;

        for (int i = 0; i < k; i++)
            cnt[node][i] = cnt[left][i];

        for (int i = 0; i < k; i++) {
            int rem = (prod[left] * i) % k;
            cnt[node][rem] += cnt[right][i];
        }
    }

    void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            prod[node] = val;

            Arrays.fill(cnt[node], 0);
            cnt[node][val] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid)
            update(node * 2, l, mid, idx, val);
        else
            update(node * 2 + 1, mid + 1, r, idx, val);

        merge(node);
    }

    int[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return cnt[node].clone();

        int mid = (l + r) / 2;

        if (qr <= mid)
            return query(node * 2, l, mid, ql, qr);

        if (ql > mid)
            return query(node * 2 + 1, mid + 1, r, ql, qr);

        int[] a = query(node * 2, l, mid, ql, qr);
        int[] b = query(node * 2 + 1, mid + 1, r, ql, qr);

        int[] res = new int[k];

        for (int i = 0; i < k; i++)
            res[i] = a[i];

        // Need product of left part
        int leftProd = getProd(node * 2, l, mid, ql, qr);

        for (int i = 0; i < k; i++) {
            int rem = (leftProd * i) % k;
            res[rem] += b[i];
        }

        return res;
    }

    int getProd(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return prod[node];

        int mid = (l + r) / 2;

        if (qr <= mid)
            return getProd(node * 2, l, mid, ql, qr);

        if (ql > mid)
            return getProd(node * 2 + 1, mid + 1, r, ql, qr);

        return (getProd(node * 2, l, mid, ql, qr)
              * getProd(node * 2 + 1, mid + 1, r, ql, qr)) % k;
    }
}