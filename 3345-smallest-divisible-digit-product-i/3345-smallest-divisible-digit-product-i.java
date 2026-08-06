class Solution {
    public int smallestNumber(int n, int t) {
        // Increment sequentially starting from n
        while (true) {
            if (getDigitProduct(n) % t == 0) {
                return n;
            }
            n++;
        }
    }

    // Helper method to calculate the product of digits
    private int getDigitProduct(int num) {
        int product = 1;
        while (num > 0) {
            int digit = num % 10;
            if (digit == 0) {
                return 0; // Early exit optimization: anything multiplied by 0 is 0
            }
            product *= digit;
            num /= 10;
        }
        return product;
    }
}

