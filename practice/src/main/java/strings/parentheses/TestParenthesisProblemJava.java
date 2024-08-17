package strings.parentheses;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestParenthesisProblemJava {

    // ()() - true
    // (()()) - true
    // )( - false

    private final char OPEN = '(';

    private boolean hasValidParenthesis(String s) {
        if (s.charAt(0) == OPEN)
            return hasValidParenthesis(s, 0);
        else
            return false;
    }

    private boolean hasValidParenthesis(String remained, int level) {
        if (remained.length() == 0)
            return level == 0;
        else {
            if (remained.charAt(0) == OPEN)
                return hasValidParenthesis(remained.substring(1), level + 1);
            else
                return hasValidParenthesis(remained.substring(1), level - 1);
        }
    }

    @Test
    public void test1() {
        Assert.assertTrue(hasValidParenthesis("()()"));
    }
    @Test
    public void test2() {
        Assert.assertTrue(hasValidParenthesis("(()())"));
    }
    @Test
    public void test3() {
        Assert.assertFalse(hasValidParenthesis("(())))"));
    }
    @Test
    public void test4() {
        Assert.assertFalse(hasValidParenthesis(")("));
    }
}
