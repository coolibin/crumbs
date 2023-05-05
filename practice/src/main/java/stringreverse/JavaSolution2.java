package stringreverse;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class JavaSolution2 {

    // tail-recursion
    private static String recursively(String toSort, String sorted) {
        if (toSort == null) {
            return null;
        } else if (toSort.length() < 2) {
            return sorted + toSort;
        } else {
            String lastChar = toSort.substring(toSort.length() - 1);
            String init = toSort.substring(0, toSort.length() - 1);
            String newSorted = sorted + lastChar;
            return recursively(init, newSorted);
        }
    }

    private static String reversed(String str) {
        return recursively(str, "");
    }

    public static void main(String[] args) {
        System.out.println(reversed("abcd"));
        assertEquals(reversed("abcd"), "dcba", "abcd");
        assertEquals(reversed(""), "");
        assertNull(reversed(null), "null");
    }
}
