package findpair;

import $.JavaTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Given a sorted array of integers and the value
 * find the first found two elements that gives the value
 * ([3,5,10, 20], 15) -> [5,10]
 */
public class TestFindPairWithTwoPointers extends JavaTest {

    // Solution with two pointers
    private static int[] findPair(int[] sortedArray, int total) {
        int left = 0;
        int right = sortedArray.length - 1;

        while (left < right) {
            if (sortedArray[right] >= total) {
                right = right - 1;
            } else if (sortedArray[left] + sortedArray[right] == total) {
                return new int[]{sortedArray[left], sortedArray[right]};
            } else if (sortedArray[left] + sortedArray[right] > total) {
                right = right - 1;
            } else {
                left = left + 1;
            }
        }
        return new int[]{};
    }

    @Test
    void test1() {
        Assert.assertEquals(findPair(new int[]{3, 5, 10, 20}, 15), new int[]{5, 10});
    }

    @Test
    void test2() {
        Assert.assertEquals(findPair(new int[]{3, 5, 10, 20}, 30), new int[]{10, 20});
    }

    @Test
    void test3() {
        Assert.assertEquals(findPair(new int[]{3, 5, 10, 20}, 50), new int[]{});
    }
}
