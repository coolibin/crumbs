package findmaxlenofdistseq;

import org.testng.annotations.Test;

import java.util.HashSet;

import static org.testng.Assert.assertEquals;

/**
 * Given an array of integers
 * finds the length of the longest sequence of non-repeated digits
 * [5,2,2,6,8,1,3,10,2] -> [2,6,8,1,3,10] -> 6
 */
public class JavaSolutionWithSet {

    private static int findLenOfSeq(int[] array) {
        if (array == null) {
            return 0;
        } else {
            int result = 0;
            for (int i = 0; i < array.length; i++) {
                // move the starting position in a loop
                // and for each item calculate the length of the maximum subset before it repeats
                HashSet<Integer> found = new HashSet<Integer>();
                for (int j = i; j < array.length; j++) {
                    if (found.contains(array[j])) {
                        break;
                    } else {
                        found.add(array[j]);
                    }
                }
                result = Math.max(result, found.size());
            }
            return result;
        }
    }

    @Test
    void test1() {
        assertEquals(findLenOfSeq(new int[]{5, 2, 2, 6, 8, 1, 3, 10, 2}), 6);
    }

    @Test
    void test2() {
        assertEquals(findLenOfSeq(new int[]{5, 2, 2, 6, 8, 2, 3, 10, 5, 2}), 6);
    }

    @Test
    void test3() {
        assertEquals(findLenOfSeq(new int[]{1, 2}), 2);
    }

    @Test
    void test4() {
        assertEquals(findLenOfSeq(new int[]{}), 0);
    }

    @Test
    void test5() {
        assertEquals(findLenOfSeq(null), 0);
    }
}