import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        
        if(digits == null || digits.length() == 0)
            return res;
        
        String[] map = {
            "", "", "abc", "def", "ghi", 
            "jkl", "mno", "pqrs", "tuv", "wxyz"
        };
        
        backtrack(digits, 0, new StringBuilder(), res, map);
        return res;
    }
    
    private void backtrack(String digits, int index, StringBuilder path, 
                           List<String> res, String[] map) {
        
        // base case
        if(index == digits.length()) {
            res.add(path.toString());
            return;
        }
        
        String letters = map[digits.charAt(index) - '0'];
        
        for(char ch : letters.toCharArray()) {
            path.append(ch);              // choose
            backtrack(digits, index + 1, path, res, map); // explore
            path.deleteCharAt(path.length() - 1); // undo (backtrack)
        }
    }
}