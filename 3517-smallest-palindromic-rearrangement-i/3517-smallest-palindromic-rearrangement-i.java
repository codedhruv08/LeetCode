import java.util.Arrays;

public class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        
        // Step 1: Extract the first half of the string
        String half = s.substring(0, n / 2);
        
        // Step 2: Sort the first half to make it lexicographically smallest
        char[] chars = half.toCharArray();
        Arrays.sort(chars);
        String sortedHalf = new String(chars);
        
        // Step 3: Handle the middle character for odd-length strings
        String middle = (n % 2 == 1) ? String.valueOf(s.charAt(n / 2)) : "";
        
        // Step 4: Reverse the sorted first half to build the right half
        String reversedHalf = new StringBuilder(sortedHalf).reverse().toString();
        
        // Step 5: Combine all pieces
        return sortedHalf + middle + reversedHalf;
    }
}
