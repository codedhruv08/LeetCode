import java.util.Stack;

class Solution {
    public String smallestSubsequence(String s) {
        // Array to store the last occurrence of each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // To keep track of characters already in our stack
        boolean[] inStack = new boolean[26];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Skip if character is already in the stack
            if (inStack[c - 'a']) continue;

            // Pop characters that are greater, and reappear later
            while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
                inStack[stack.pop() - 'a'] = false;
            }

            // Add the current character
            stack.push(c);
            inStack[c - 'a'] = true;
        }

        // Build the result string from the stack
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }
}
