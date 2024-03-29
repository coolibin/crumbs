package brokenpalindrome;

import $.JavaTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestBrokenPalindrome1
        extends JavaTest {

    private boolean isBrokenPalindrome(String str, int maxErrors) {
        int halfOfLen = str.length() / 2;
        char[] left = str.substring(halfOfLen).toCharArray();
        char[] right = str.substring(halfOfLen).toCharArray();
        int errors = 0;
        for (int i = 0; i < left.length; i++) {
            if (left[i] != right[i]) {
                errors += 1;
            }
        }
        return errors <= maxErrors;
    }

    @Test
    void test1() {
        assertTrue(isBrokenPalindrome("abcdcba", 3), "\"abcdcba\", 3");
    }

    @Test
    void test2() {
        assertTrue(isBrokenPalindrome("abcdxza", 2), "\"abcdxza\", 2");
    }

    @Test
    void test3() {
        assertTrue(isBrokenPalindrome("abcdxyz", 2), "\"abcdxyz\", 2");
    }
}
