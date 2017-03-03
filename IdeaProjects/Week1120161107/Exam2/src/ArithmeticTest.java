import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test cases for Arithmetic (Exam 2 - Version 1).
 */
public class ArithmeticTest {
    private ArithmeticDummy a;

    @Before
    public void setUp() {
        a = new ArithmeticDummy();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test(timeout = 100)
    public void testDenominatorZeroExceptionDefaultConstructor() {
        boolean hasDefaultConstructor = false;
        try {
            Constructor<?> c = DenominatorZeroException.class
                    .getDeclaredConstructor();
            if (c != null) {
                hasDefaultConstructor = true;
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        String message = "DenominatorZeroException(): you must include the "
                + "default constructor in your custom exception.";

        assertTrue(message, hasDefaultConstructor);
    }

    @Test(timeout = 100)
    public void testDenominatorZeroExceptionConstructorStringArgument() {
        boolean hasConstructorWithStringArgument = false;
        try {
            Constructor<?> c = DenominatorZeroException.class
                    .getDeclaredConstructor(String.class);
            if (c != null) {
                hasConstructorWithStringArgument = true;
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        String message = "DenominatorZeroException(): you must include a "
                + "constructor that takes a String argument which is the "
                + "message for your custom exception.";

        assertTrue(message, hasConstructorWithStringArgument);
    }

    @Test(timeout = 100)
    public void testDenominatorZeroExceptionIsSubclassOfException() {
        Class<?> superclass = (DenominatorZeroException.class)
                .getSuperclass();

        String message = "DenominatorZeroException: your custom exception "
                + "class must directly inherit from the appropriate class for "
                + "custom exceptions.";

        assertTrue(message, Exception.class == superclass);
    }

    @Test(timeout = 100)
    public void testTruncatedDivisionThrowsDenominatorZeroException1()
            throws DenominatorZeroException {
        exception.expect(DenominatorZeroException.class);
        a.truncatedDivision(42, 0);
    }

    @Test(timeout = 100)
    public void testTruncatedDivisionThrowsDenominatorZeroException2()
            throws DenominatorZeroException {
        exception.expect(DenominatorZeroException.class);
        a.truncatedDivision(0, 0);
    }

    @Test(timeout = 100)
    public void testTruncatedDivisionNoExceptionThrown()
            throws DenominatorZeroException {
        String message = "truncatedDivision(): this method should return the "
                + "truncated, integer quotient of 'a' and 'b'.";
        int expectedResult = 6;
        int actualResult = a.truncatedDivision(43, 7);

        assertEquals(message, expectedResult, actualResult);
    }

    @Test(timeout = 100)
    public void testModulusNoRemainder() {
        String message = "modulus(): this method should compute the remainder "
                + "after division of arguments 'a' and 'b'.";
        int expectedResult = 0;
        int actualResult = a.modulus(125, 25);

        assertEquals(message, expectedResult, actualResult);
    }

    @Test(timeout = 100)
    public void testModulusHasRemainder() {
        String message = "modulus(): this method should compute the remainder "
                + "after division of arguments 'a' and 'b'.";
        int expectedResult = 6245;
        int actualResult = a.modulus(5781723, 9182);

        assertEquals(message, expectedResult, actualResult);
    }

    @Test(timeout = 100)
    public void testModulusCallsTruncatedDivision1() {
        String message = "modulus(): you must call truncatedDivision() when "
                + "computing the modulus. Do not use the modulus operator (%)."
                + " Also, make sure that the DenominatorZeroException is "
                + "thrown from truncatedDivision, not from modulus().";

        a.modulus(1, 1);

        assertTrue(message, a.truncatedDivisionCalled);
    }

    @Test(timeout = 100)
    public void testModulusCallsTruncatedDivision2() {
        String message = "modulus(): you must call truncatedDivision() when "
                + "computing the modulus. Do not use the modulus operator (%)."
                + " Also, make sure that the DenominatorZeroException is "
                + "thrown from truncatedDivision, not from modulus().";
        PrintStream ps = new PrintStream(new ByteArrayOutputStream());
        PrintStream old = System.err;
        System.setErr(ps);
        a.modulus(1, 0);
        System.err.flush();
        System.setErr(old);

        assertTrue(message, a.truncatedDivisionCalled);
    }

    class ArithmeticDummy extends Arithmetic {
        public boolean truncatedDivisionCalled = false;

        @Override
        public int truncatedDivision(int a, int b) throws DenominatorZeroException {
            truncatedDivisionCalled = true;
            return super.truncatedDivision(a, b);
        }
    }
}
