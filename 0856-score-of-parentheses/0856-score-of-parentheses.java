class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int x = stack.pop();
                int prev = stack.pop();

                if (x == 0) {
                    stack.push(prev + 1);
                } else {
                    stack.push(prev + 2 * x);
                }
            }
        }

        return stack.pop();
    }
}