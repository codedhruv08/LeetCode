class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        int currentIndex = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr.next != null) {
            // Check for local maxima or local minima
            boolean isLocalMaxima = (curr.val > prev.val && curr.val > curr.next.val);
            boolean isLocalMinima = (curr.val < prev.val && curr.val < curr.next.val);

            if (isLocalMaxima || isLocalMinima) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                prevCriticalIndex = currentIndex;
            }

            prev = curr;
            curr = curr.next;
            currentIndex++;
        }

        // If fewer than two critical points exist
        if (minDistance == Integer.MAX_VALUE) {
            return new int[] {-1, -1};
        }

        int maxDistance = prevCriticalIndex - firstCriticalIndex;
        return new int[] {minDistance, maxDistance};
    }
}
