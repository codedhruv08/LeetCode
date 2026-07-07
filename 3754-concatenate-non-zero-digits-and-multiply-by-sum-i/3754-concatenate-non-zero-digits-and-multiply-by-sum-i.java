class Solution {
    public long sumAndMultiply(int n) {
        long sum = 0;
        long x = 0;
        
        // Handle the case where n is initially 0
        if (n == 0) {
            return 0;
        }

        // Use a string or a reversed reconstruction strategy to maintain the original digit order.
        // Converting to a String makes processing digits from left to right simple.
        String s = Integer.toString(n);
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != '0') {
                int digit = ch - '0';
                sum += digit;
                x = x * 10 + digit;
            }
        }
        
        return x * sum;
    }
}
