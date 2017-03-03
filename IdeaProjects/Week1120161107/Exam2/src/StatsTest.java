import static org.junit.Assert.*;
import org.junit.Test;


public class StatsTest {

    @Test(timeout = 100)
    public void testOneElementZero() {
        int[] list = {0};

        String message = "Check if the list has just one element";
        int expected = 0;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testOneElementNegative() {
        int[] list = {-3};

        String message = "Check if the list has just one element";
        int expected = -3;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testOneElementPositive() {
        int[] list = {7};

        String message = "Check if the list has just one element";
        int expected = 7;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testTwoElementsZero() {
        int[] list = {0, 0};

        String message = "Check if the list has two elements";
        int expected = 0;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testTwoElementsSameNegative() {
        int[] list = {-11, -11};

        String message = "Check if the list has two elements";
        int expected = -11;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testTwoElementsSamePositive() {
        int[] list = {27, 27};

        String message = "Check if the list has two elements";
        int expected = 27;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testThreeElementsNegativeMode1() {
        int[] list = {-5, -5, 8};

        String message = "Check if the list has three elements with two of the same value";
        int expected = -5;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testThreeElementsNegativeMode2() {
        int[] list = {-13, -13, 0};

        String message = "Check if the list has three elements with two of the same value";
        int expected = -13;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testThreeElementsZeroMode1() {
        int[] list = {-2, 0, 0};

        String message = "Check if the list has three elements with two of the same value";
        int expected = 0;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testThreeElementsZeroMode2() {
        int[] list = {0, 0, 37};

        String message = "Check if the list has three elements with two of the same value";
        int expected = 0;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testThreeElementsPositiveMode1() {
        int[] list = {-6, 6, 6};

        String message = "Check if the list has three elements with two of the same value";
        int expected = 6;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testThreeElementsPositiveMode2() {
        int[] list = {0, 17, 17};

        String message = "Check if the list has three elements with two of the same value";
        int expected = 17;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testThreeElementsSame() {
        int[] list = {6, 6, 6};

        String message = "Check if the list has three elements that are all the same value";
        int expected = 6;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsSameZero() {
        int[] list = {0, 0, 0, 0};

        String message = "Check if the list has four elements that are all the same value";
        int expected = 0;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsSameNegative() {
        int[] list = {-15, -15, -15, -15};

        String message = "Check if the list has four elements that are all the same value";
        int expected = -15;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsSamePositive() {
        int[] list = {9, 9, 9, 9};

        String message = "Check if the list has four elements that are all the same value";
        int expected = 9;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsOneNegativeThreeSamePositive() {
        int[] list = {-87, 97, 97, 97};

        String message = "Check if the list has four elements with three of the same value";
        int expected = 97;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsThreeSameNegativeOnePositive() {
        int[] list = {-61, -61, -61, 4};

        String message = "Check if the list has four elements with three of the same value";
        int expected = -61;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsThreeSameNegativeOneZero() {
        int[] list = {-24, -24, -24, 0};

        String message = "Check if the list has four elements with three of the same value";
        int expected = -24;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsThreeSamePositiveOneZero() {
        int[] list = {0, 16, 16, 16};

        String message = "Check if the list has four elements with three of the same value";
        int expected = 16;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsOneNegativeTwoZeroOnePositive() {
        int[] list = {-5, 0, 0, 7};

        String message = "Check if the list has four elements with two of the same value";
        int expected = 0;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsTwoNegativeOneZeroOnePositive() {
        int[] list = {-2, -2, 0, 42};

        String message = "Check if the list has four elements with two of the same value";
        int expected = -2;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsOneNegativeOneZeroTwoPositive() {
        int[] list = {-5, 0, 8, 8};

        String message = "Check if the list has four elements with two of the same value";
        int expected = 8;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsTwoSameTwoDifferent1() {
        int[] list = {-4, -4, 6, 7};

        String message = "Check if the list has four elements with two of the same value";
        int expected = -4;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsTwoSameTwoDifferent2() {
        int[] list = {-4, -2, -2, 6};

        String message = "Check if the list has four elements with two of the same value";
        int expected = -2;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsTwoSameTwoDifferent3() {
        int[] list = {-9, -7, -4, -4};

        String message = "Check if the list has four elements with two of the same value";
        int expected = -4;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsTwoSameTwoDifferent4() {
        int[] list = {-1, 4, 7, 7};

        String message = "Check if the list has four elements with two of the same value";
        int expected = 7;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsTwoSameTwoDifferent5() {
        int[] list = {-6, 2, 2, 8};

        String message = "Check if the list has four elements with two of the same value";
        int expected = 2;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFourElementsTwoSameTwoDifferent6() {
        int[] list = {-13, 5, 8, 8};

        String message = "Check if the list has four elements with two of the same value";
        int expected = 8;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testLongList1() {
        int[] list = {-3, -3, -3, 5, 5, 6, 6, 10};

        String message = "Check when the mode is the first element in the list";
        int expected = -3;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testLongList2() {
        int[] list = {-7, -4, -4, -3, -3, -1, 0, 0, 6, 6, 9, 9, 13, 13, 13};

        String message = "Check when the mode is the last element in the list";
        int expected = 13;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testLongList3() {
        int[] list = {-11, -7, -8, -8, 0, 0, 0, 1, 5, 6, 7, 7};

        String message = "Check when the mode is in the middle of the list";
        int expected = 0;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testLongList4() {
        int[] list = {-52, -42, -17, -17, -9, -5, -5, -5, 0, 0, 2, 5, 5, 5, 5, 6, 9, 13, 13};

        String message = "Check when the mode changes multiple times scanning from left to right";
        int expected = 5;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testLongList5() {
        int[] list = {0, 0, 0, 0, 0, 0, 0, 0, 0};

        String message = "Check when the list has all the same elements";
        int expected = 0;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testLongList6() {
        int[] list = {-6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6};

        String message = "Check when the list has all the same elements";
        int expected = -6;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testLongList7() {
        int[] list = {13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13};

        String message = "Check when the list has all the same elements";
        int expected = 13;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testLongList8() {
        int[] list = {-5, -5, -5, -5, -5, -5, 0, 0, 0, 0, 0, 0, 0};

        String message = "Check when the list is long with two unique elements";
        int expected = 0;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testLongList9() {
        int[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 10, 11, 12, 13, 14, 14, 15};

        String message = "Check when the list is long and mode is in the middle";
        int expected = 9;
        int actual = Stats.mode(list);

        assertEquals(message, expected, actual);
    }
}
