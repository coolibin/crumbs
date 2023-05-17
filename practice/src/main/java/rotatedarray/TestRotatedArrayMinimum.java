package rotatedarray;

import $.JavaTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import scala.Tuple2;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class TestRotatedArrayMinimum extends JavaTest {

    private Tuple2<int[], int[]> split(int[] a) {
        int mid = a.length / 2;
        var left = Arrays.copyOfRange(a, 0, mid);
        var right = Arrays.copyOfRange(a, mid, a.length);
        return new Tuple2<>(left, right);
    }

    private int findMinimum(int[] array) {
        if (array.length == 0) {
            throw new InvalidParameterException("array cannot be empty!");
        } else if (array.length == 1) {
            return array[0];
        } else if (array.length == 2) {
            return Math.min(array[0], array[1]);
        } else {
            var leftRight = split(array);
            var left = leftRight._1;
            var right = leftRight._2;
            if (left[0] <= left[left.length - 1]) {
                return findMinimum(right);
            } else {
                return findMinimum(left);
            }
        }
    }

    @Test
    void test1() {
        Assert.assertEquals(findMinimum(new int[]{3, 4, 5, 6, 7, 1, 2}), 1);
    }

    @Test
    void test2() {
        Assert.assertEquals(findMinimum(new int[]{7, 8, 1, 2, 3, 4, 5, 6}), 1);
    }

    @Test
    void test3() {
        Assert.assertEquals(findMinimum(new int[]{0}), 0);
    }
}
