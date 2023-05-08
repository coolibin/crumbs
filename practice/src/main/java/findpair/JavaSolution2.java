package findpair;

import static org.testng.Assert.assertEquals;

/**
 * Given a sorted array of integers and the value
 * find the first found two elements that gives the value
 * ([3,5,10, 20], 15) -> [5,10]
 */
public class JavaSolution2 {


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
        return new int[] {};
    }

    public static void main(String[] args) {
        assertEquals(findPair(new int[]{3, 5, 10, 20}, 15), new int[]{5, 10});
        assertEquals(findPair(new int[]{3, 5, 10, 20}, 30), new int[]{10, 20});
        assertEquals(findPair(new int[]{3, 5, 10, 20}, 50), new int[]{});
    }
}
