package findpair;

import static org.testng.Assert.assertEquals;

/**
 * Given a sorted array of integers and the value
 * find the first found two elements that gives the value
 * ([3,5,10, 20], 15) -> [5,10]
 */
public class JavaSolution1 {

    // Solution with two loops
    private static int[] findPair(int[] sortedArray, int total) {
        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = i + 1; j < sortedArray.length; j++) {
                if (sortedArray[i] + sortedArray[j] == total) {
                    return new int[]{sortedArray[i], sortedArray[j]};
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {

        assertEquals(findPair(new int[]{3, 5, 10, 20}, 15), new int[]{5, 10});
        assertEquals(findPair(new int[]{3, 5, 10, 20}, 30), new int[]{10, 20});
        assertEquals(findPair(new int[]{3, 5, 10, 20}, 50), new int[]{});

    }
}
