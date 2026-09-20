class Solution {
    public int reverseDegree(String s) {
        int totalReverseDegree = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // 1-based position in the string
            int position = i + 1;
            // Reverse alphabetical value ('a' -> 26, 'z' -> 1)
            int reverseVal = 26 - (ch - 'a');
            
            totalReverseDegree += position * reverseVal;
        }
        
        return totalReverseDegree;
    }
}