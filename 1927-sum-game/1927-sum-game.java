class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;
        
        // Loop through the first half of the string
        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                leftQ++;
            } else {
                leftSum += c - '0';
            }
        }
        
        // Loop through the second half of the string
        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                rightQ++;
            } else {
                rightSum += c - '0';
            }
        }
        
        // Bob can only win if the missing sums can perfectly balance out.
        // For every pair of '?' on the same side, Bob can mirror Alice's move to cancel it out.
        // For every 2 '?' on opposite sides, they can neutralize a sum value of 9.
        return (leftSum - rightSum) * 2 != (rightQ - leftQ) * 9;
    }
}
