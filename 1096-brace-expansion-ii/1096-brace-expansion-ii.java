import java.util.*;

public class Solution {
    private int idx = 0;

    public List<String> braceExpansionII(String expression) {
        this.idx = 0;
        Set<String> resultSet = parseUnion(expression);
        List<String> sortedList = new ArrayList<>(resultSet);
        Collections.sort(sortedList);
        return sortedList;
    }

    // Handles the top-level comma separations (Union operations)
    private Set<String> parseUnion(String expr) {
        Set<String> unionSet = new HashSet<>();
        
        while (idx < expr.length() && expr.charAt(idx) != '}') {
            Set<String> concatSet = parseConcat(expr);
            unionSet.addAll(concatSet);
            
            if (idx < expr.length() && expr.charAt(idx) == ',') {
                idx++; // Consume ',' and continue union
            }
        }
        return unionSet;
    }

    // Handles adjacent terms that need to be multiplied (Cartesian product)
    private Set<String> parseConcat(String expr) {
        Set<String> concatSet = new HashSet<>();
        concatSet.add(""); // Base item for Cartesian product chain

        while (idx < expr.length() && expr.charAt(idx) != ',' && expr.charAt(idx) != '}') {
            Set<String> nextSet;
            if (expr.charAt(idx) == '{') {
                idx++; // Consume '{'
                nextSet = parseUnion(expr);
                idx++; // Consume '}'
            } else {
                // It's a single lowercase letter
                nextSet = new HashSet<>();
                nextSet.add(String.valueOf(expr.charAt(idx)));
                idx++;
            }
            
            // Perform Cartesian Product: concatSet x nextSet
            Set<String> temp = new HashSet<>();
            for (String s1 : concatSet) {
                for (String s2 : nextSet) {
                    temp.add(s1 + s2);
                }
            }
            concatSet = temp;
        }
        return concatSet;
    }
}
