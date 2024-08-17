package version;

import org.testng.Assert;
import org.testng.annotations.Test;

// Time Complexity: O(n)
// Auxiliary space: O(1)
public class TestCompareVersionsGeeksSolution {
    // Method to compare two versions.
    // Returns 1 if v2 is
    // smaller, -1 if v1 is smaller, 0 if equal
    static int compareVersions(String v1, String v2)
    {
        // vnum stores each numeric part of version
        int vnum1 = 0, vnum2 = 0;

        // loop until both String are processed
        for (int i = 0, j = 0; (i < v1.length() || j < v2.length());) {
            // Storing numeric part of
            // version 1 in vnum1
            while (i < v1.length() && v1.charAt(i) != '.') {
                vnum1 = vnum1 * 10 + (v1.charAt(i) - '0');
                i++;
            }

            // storing numeric part
            // of version 2 in vnum2
            while (j < v2.length() && v2.charAt(j) != '.') {
                vnum2 = vnum2 * 10 + (v2.charAt(j) - '0');
                j++;
            }

            if (vnum1 > vnum2)
                return 1;
            if (vnum2 > vnum1)
                return -1;

            // if equal, reset variables and
            // go for next numeric part
            vnum1 = vnum2 = 0;
            i++;
            j++;
        }
        return 0;
    }

    @Test
    public void test1() {
        Assert.assertEquals(compareVersions("0.9", "1.0.3.4"), -1);
    }

    @Test
    public void test2() {
        Assert.assertEquals(compareVersions("1.1.1", "1.1"), 1);
    }

    @Test
    public void test3() {
        Assert.assertEquals(compareVersions("2.3.4", "2.3.4"), 0);
    }

    @Test
    public void test4() {
        Assert.assertEquals(compareVersions("0.9", "2.1"), -1);
    }

    @Test
    public void test5() {
        Assert.assertEquals(compareVersions("2.1", "2.01"), 0);
    }

    @Test
    public void test6() {
        Assert.assertEquals(compareVersions("2.1", "2.01.0"), 0);
    }


    static int compareVersions2(String v1, String v2) {
        String[] version1 = v1.split("\\.");
        String[] version2 = v2.split("\\.");

        int length = Math.max(version1.length, version2.length);

        for (int i = 0; i < length; i++) {
            int num1 = (i < version1.length) ? Integer.parseInt(version1[i]) : 0;
            int num2 = (i < version2.length) ? Integer.parseInt(version2[i]) : 0;

            if (num1 > num2)
                return 1;
            if (num2 > num1)
                return -1;
        }

        return 0;
    }

    @Test
    public void test21() {
        Assert.assertEquals(compareVersions2("0.9", "1.0.3.4"), -1);
    }

    @Test
    public void test22() {
        Assert.assertEquals(compareVersions2("1.1.1", "1.1"), 1);
    }

    @Test
    public void test23() {
        Assert.assertEquals(compareVersions2("2.3.4", "2.3.4"), 0);
    }

    @Test
    public void test24() {
        Assert.assertEquals(compareVersions2("0.9", "2.1"), -1);
    }

    @Test
    public void test25() {
        Assert.assertEquals(compareVersions2("2.1", "2.01"), 0);
    }

    @Test
    public void test26() {
        Assert.assertEquals(compareVersions2("2.1", "2.01.0"), 0);
    }

}
