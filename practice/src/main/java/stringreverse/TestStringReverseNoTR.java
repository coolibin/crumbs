package stringreverse;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TestStringReverseNoTR {

    // no tail recursion
    private static String reversed(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(str.length() - 1)
                    .concat(reversed(str.substring(0, str.length() - 1)));
        }
    }

    public static void main(String[] args) {
        System.out.println(reversed("abcd"));
        assertEquals(reversed("abcd"), "dcba", "abcd");
        assertEquals(reversed(""), "");
        assertNull(reversed(null));
    }
}
