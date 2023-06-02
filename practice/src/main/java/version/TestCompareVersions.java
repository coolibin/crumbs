package version;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestCompareVersions {

    /**
     * -1: version1 < version2
     * 0: version1 == version2
     * 1: version1 > version2
     * <p>
     * Example:
     * 0.9 < 1.0.3.4 < 1.1.0 < 2.0 < 2.1
     */

    private int recursively(List<Integer> ver1, List<Integer> ver2) {
        if (ver1.isEmpty() && ver2.isEmpty()) {
            return 0;
        } else if (ver1.isEmpty()) {
            if (ver2.stream().allMatch(e -> e > 0)) {
                return -1;
            } else {
                return 0;
            }
        } else if (ver2.isEmpty()) {
            if (ver1.stream().allMatch(e -> e > 0)) {
                return 1;
            } else {
                return 0;
            }
        } else if (ver1.stream().findFirst().get() > ver2.stream().findFirst().get()) {
            return 1;
        } else if (ver1.stream().findFirst().get() < ver2.stream().findFirst().get()) {
            return -1;
        } else {
            return recursively(
                    ver1.stream().skip(1).collect(Collectors.toList()),
                    ver2.stream().skip(1).collect(Collectors.toList())
            );
        }

    }

    int compareVersions(String version1, String version2) {
        return recursively(
                Arrays.stream(version1.split("\\.")).map(Integer::valueOf).collect(Collectors.toList()),
                Arrays.stream(version2.split("\\.")).map(Integer::valueOf).collect(Collectors.toList())
        );
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

}
