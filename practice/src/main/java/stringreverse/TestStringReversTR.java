package stringreverse;

import $.JavaTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStringReversTR extends JavaTest {

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


    @Test
    void test1() {
        Assert.assertEquals(reversed("abcd"), "dcba", "abcd");
    }

    @Test
    void test2() {
        Assert.assertEquals(reversed(""), "");
    }

    @Test
    void test3() {
        Assert.assertNull(reversed(null), "null");
    }
}
