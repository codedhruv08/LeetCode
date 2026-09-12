import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, index;

        Interval(int l, int r, int w, int index) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    Interval[] arr;
    State[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);

            arr[i] = new Interval(
                x.get(0),
                x.get(1),
                x.get(2),
                i
            );
        }

        // Sort by starting point
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);

            return Integer.compare(a.r, b.r);
        });

        dp = new State[n][5];

        State ans = solve(0, 4);

        int[] result = new int[ans.indices.size()];

        for (int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        return result;
    }

    State solve(int i, int k) {

        if (i == arr.length || k == 0) {
            return new State(0, new ArrayList<>());
        }

        if (dp[i][k] != null) {
            return dp[i][k];
        }

        // Don't take current interval
        State skip = solve(i + 1, k);

        // Take current interval
        int next = findNext(i + 1, arr[i].r);

        State nextState = solve(next, k - 1);

        List<Integer> selected = new ArrayList<>(
            nextState.indices
        );

        selected.add(arr[i].index);

        // Important: answer must be lexicographically smallest
        Collections.sort(selected);

        State take = new State(
            arr[i].w + nextState.score,
            selected
        );

        dp[i][k] = better(take, skip);

        return dp[i][k];
    }

    // Find first interval whose start > current end
    int findNext(int start, int end) {

        int low = start;
        int high = arr.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid].l > end) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    State better(State a, State b) {

        if (a.score > b.score)
            return a;

        if (a.score < b.score)
            return b;

        // Same score -> lexicographically smaller
        if (compare(a.indices, b.indices) < 0)
            return a;

        return b;
    }

    int compare(List<Integer> a, List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(
                    a.get(i),
                    b.get(i)
                );
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}