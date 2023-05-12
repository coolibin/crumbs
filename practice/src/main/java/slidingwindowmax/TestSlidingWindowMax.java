package slidingwindowmax;

import $.JavaTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * You are given an array of integers {{nums}}, there is a sliding window of size {{k}}
 * which is moving from the fery lefot of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 * Return the max sliding window.
 * Input: nums = [1,3,-1-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * [1,3,-1],-3,5,3,6,7      3
 * 1,[3,-1,-3],5,3,6,7      3
 * 1,3,[-1,-3,5],3,6,7      5
 * 1,3,-1,[-3,5,3],6,7      5
 * 1,3,-1,-3,[5,3,6],7      6
 * [1,3,-1],-3,5,[3,6,7]    7
 */


public class TestSlidingWindowMax extends JavaTest {

    // Complexity O(N*K)

    private int[] maxOfFrames(int[] array, int k) {
        if (array == null) return null;
        int[] result = new int[array.length - k + 1];
        for (int start = 0; start < array.length - k + 1; start++) {
            int currentMax = 0;
            for (int framePos = start; framePos < start + k; framePos++) {
                currentMax = Math.max(currentMax, array[framePos]);
            }
            result[start] = currentMax;
        }
        return result;
    }

    @Test
    void test1() {
        Assert.assertEquals(maxOfFrames(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3), new int[]{3, 3, 5, 5, 6, 7});
    }

    @Test
    void test2() {
        Assert.assertEquals(maxOfFrames(new int[]{1}, 1), new int[]{1});
    }
}
