import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        // 1. Count factor frequencies and find the overall cheapest item
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // 2. Group available 2-for-1 deals by price (sorted ascending via TreeMap)
        int[] multiplesCount = new int[n + 1];
        TreeMap<Integer, Long> dealsByPrice = new TreeMap<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // Only consider items cheaper than buying 2 copies of the cheapest item
            if ((long) price >= 2L * minPrice) {
                continue;
            }

            // Compute total items whose factor is a multiple of `factor` (memoized)
            if (multiplesCount[factor] == 0) {
                for (int m = factor; m <= n; m += factor) {
                    multiplesCount[factor] += freq[m];
                }
            }

            // Subtract 1 to exclude the item itself (i != j)
            int validFreeItems = multiplesCount[factor] - 1;
            if (validFreeItems > 0) {
                dealsByPrice.merge(price, (long) validFreeItems, Long::sum);
            }
        }

        // 3. Greedily buy the cheapest 2-for-1 deals first
        long totalItems = 0;
        long remainingBudget = budget;

        for (Map.Entry<Integer, Long> entry : dealsByPrice.entrySet()) {
            long price = entry.getKey();
            long availableDeals = entry.getValue();

            if (remainingBudget < price) {
                break;
            }

            long canBuy = Math.min(availableDeals, remainingBudget / price);
            remainingBudget -= canBuy * price;
            totalItems += canBuy * 2;
        }

        // 4. Spend any leftover budget on the cheapest single item
        totalItems += remainingBudget / minPrice;

        return (int) totalItems;
    }
}
