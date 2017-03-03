import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for Matrix.
 */
public class MatrixTest {
    private Matrix q;

    @Before
    public void setUp() {
        q = new Matrix();
    }

    @Test(timeout = 100)
    public void testNullMatrix() {
        int[][] matrix = null;

        String message = "Should return \"NULL\" if the matrix is null";
        String expected = "NULL";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testEmptyMatrix() {
        int[][] matrix = {};

        String message = "Should return \"NULL\" if the matrix is empty";
        String expected = "NULL";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testRectangularMatrix1by2() {
        int[][] matrix = {{1, 2}};

        String message = "Should return \"RECTANGULAR\" if the matrix is not square";
        String expected = "RECTANGULAR";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testRectangularMatrix2by1() {
        int[][] matrix = {{1}, {2}};

        String message = "Should return \"RECTANGULAR\" if the matrix is not square";
        String expected = "RECTANGULAR";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testRectangularMatrix2by3() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};

        String message = "Should return \"RECTANGULAR\" if the matrix is not square";
        String expected = "RECTANGULAR";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testRectangularMatrix3by2() {
        int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};

        String message = "Should return \"RECTANGULAR\" if the matrix is not square";
        String expected = "RECTANGULAR";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testDiagonalNotZeroSkewSymmetric() {
        int[][] matrix = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        String message = "Entries along the diagonal should be 0";
        String expected = "FALSE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testDiagonalZeroNotSkewSymmetric() {
        int[][] matrix = {{0, 1, 1}, {2, 0, 1}, {2, 2, 0}};

        String message = "Opposing entries must sum to 0";
        String expected = "FALSE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testAllZero1by1() {
        int[][] matrix = {{0}};

        String message = "Should return \"TRUE\" for an all zero matrix";
        String expected = "TRUE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testAllZero3by3() {
        int[][] matrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        String message = "Should return \"TRUE\" for an all zero matrix";
        String expected = "TRUE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void test1by1NotSS() {
        int[][] matrix = {{2}};

        String message = "Check diagonal entries to be 0 and opposing entries sum to 0";
        String expected = "FALSE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void test2by2SS() {
        int[][] matrix = {{0, 5}, {-5, 0}};

        String message = "Check diagonal entries to be 0 and opposing entries sum to 0";
        String expected = "TRUE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void test2by2NotSS() {
        int[][] matrix = {{0, 5}, {5, 0}};

        String message = "Check diagonal entries to be 0 and opposing entries sum to 0";
        String expected = "FALSE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void test3by3SS() {
        int[][] matrix = {{0, 4, 7}, {-4, 0, 2}, {-7, -2, 0}};

        String message = "Check diagonal entries to be 0 and opposing entries sum to 0";
        String expected = "TRUE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void test3by3NotSS() {
        int[][] matrix = {{0, 1, 4}, {-1, 0, 6}, {-4, -6, 1}};

        String message = "Check diagonal entries to be 0 and opposing entries sum to 0";
        String expected = "FALSE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void test5by5SS() {
        int[][] matrix = {{0, 5, -6, 3, -1}, {-5, 0, -4, -1, -2}, {6, 4, 0, 9, -12},
                {-3, 1, -9, 0, 7}, {1, 2, 12, -7, 0}};

        String message = "Check diagonal entries to be 0 and opposing entries sum to 0";
        String expected = "TRUE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void test5by5NotSS() {
        int[][] matrix = {{0, 5, -6, 3, -1}, {-5, 0, -4, -1, -2}, {6, 4, 0, 9, -12},
                {-3, 1, -9, 0, 7}, {1, 2, 12, 7, 0}};

        String message = "Check diagonal entries to be 0 and opposing entries sum to 0";
        String expected = "FALSE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void test2by2SSRandom() {
        int[][] matrix = new int[2][2];

        matrix[0][0] = matrix[1][1] = 0;

        Random r = new Random();
        matrix[0][1] = r.nextInt();
        matrix[1][0] = -matrix[0][1];

        String message = "Check diagonal entries to be 0 and opposing entries sum to 0";
        String expected = "TRUE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void test2by2NotSSRandom() {
        int[][] matrix = new int[2][2];

        matrix[0][0] = matrix[1][1] = 0;

        Random r = new Random();
        matrix[0][1] = r.nextInt();
        matrix[1][0] = matrix[0][1];

        String message = "Check diagonal entries to be 0 and opposing entries sum to 0";
        String expected = "FALSE";
        String actual = q.isSkewSymmetric(matrix);

        assertEquals(message, expected, actual);
    }
}
