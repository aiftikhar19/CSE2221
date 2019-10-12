import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Aisha Iftikhar, Cerys Hughes
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Tests default constructor: boundary case, only possible case
     */
    @Test
    public final void testDefaultConstructor() {
        /*
         * Set up variables and call method under test
         *
         */
        NaturalNumber test = this.constructorTest();
        NaturalNumber ref = this.constructorRef();
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Test int constructor, boundary case: smallest possible int
     */
    @Test
    public final void testIntConstructorZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber ref = this.constructorRef(0);
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests int constructor, routine case: nonzero number
     */
    @Test
    public final void testIntConstructorNonZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest(15);
        NaturalNumber qExpected = this.constructorRef(15);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Tests int constructor, boundary case: largest possible int
     */
    @Test
    public final void testIntConstructorIntMaxValue() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber ref = this.constructorRef(Integer.MAX_VALUE);
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests int constructor, routine case: even number
     */
    @Test
    public final void testIntConstructorEven() {
        /*
         * Set up variables
         */
        NaturalNumber test = this.constructorTest(22345678);
        NaturalNumber ref = this.constructorRef(22345678);
        /*
         * Assert values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests int constructor, routine case: odd number
     */
    @Test
    public final void testIntConstructorOdd() {
        /*
         * Set up variables
         */
        NaturalNumber test = this.constructorTest(22345677);
        NaturalNumber ref = this.constructorRef(22345677);
        /*
         * Assert values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests int constructor, challenge case: 0 as a digit in a non-0 number
     */
    @Test
    public final void testIntConstructorZeroDigits() {
        /*
         * Set up variables
         */
        NaturalNumber test = this.constructorTest(4020100);
        NaturalNumber ref = this.constructorRef(4020100);
        /*
         * Assert values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests int constructor, routine case: single-digit even number
     */
    @Test
    public final void testIntConstructorSingleDigitEven() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(4);
        NaturalNumber ref = this.constructorRef(4);
        /*
         * Assert values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests int constructor, routine case: single-digit odd number
     */
    @Test
    public final void testIntConstructorSingleDigitOdd() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(5);
        NaturalNumber ref = this.constructorRef(5);
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests String constructor, boundary case: smallest possible value
     */
    @Test
    public final void testStringConstructorZero() {
        /*
         * Set up variables and call method under test
         *
         */
        NaturalNumber test = this.constructorTest("0");
        NaturalNumber ref = this.constructorRef("0");
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests String constructor, routine case: nonzero number
     */
    @Test
    public final void testStringConstructorNonZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest("15");
        NaturalNumber qExpected = this.constructorRef("15");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Tests String constructor, challenge case: greater than Integer.MAX_VALUE
     */
    @Test
    public final void testStringConstructorIntMaxValue() {
        /*
         * Set up variables and call method under test
         *
         */
        NaturalNumber test = this
                .constructorTest("" + Integer.MAX_VALUE + "28");
        NaturalNumber ref = this.constructorRef("" + Integer.MAX_VALUE + "28");
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests String constructor, challenge case: greater than Long.MAX_VALUE
     */
    @Test
    public final void testStringConstructorLongMaxValue() {
        /*
         * Set up variables and call method under test
         *
         */
        NaturalNumber test = this.constructorTest("" + Long.MAX_VALUE + "28");
        NaturalNumber ref = this.constructorRef("" + Long.MAX_VALUE + "28");
        /*
         * Assert variable values meet expectations
         *
         */
        assertEquals(ref, test);
    }

    /*
     * Tests String constructor, routine case: even number
     */
    @Test
    public final void testStringConstructorEven() {
        /*
         * Set up variable values and call method under test
         *
         */
        NaturalNumber test = this.constructorTest("25678652");
        NaturalNumber ref = this.constructorRef("25678652");
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests String constructor, routine case: odd number
     */
    @Test
    public final void testStringConstructorOdd() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber test = this.constructorTest("25678653");
        NaturalNumber ref = this.constructorRef("25678653");
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests String constructor, routine case: single-digit number
     */
    @Test
    public final void testStringConstructorSingleDigit() {
        /*
         * Set up variables and call method under test
         *
         */
        NaturalNumber test = this.constructorTest("3");
        NaturalNumber ref = this.constructorRef("3");
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests String constructor, challenge case: non-zero number containing 0s
     * as digits
     */
    @Test
    public final void testStringConstructorZeroDigit() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber test = this.constructorTest("304055600980");
        NaturalNumber ref = this.constructorRef("304055600980");
        /*
         * Assert variable values meet expectations
         */
        assertEquals(ref, test);
    }

    /*
     * Tests NaturalNumber constructor, boundary case: 0 with ref as parameter
     */
    @Test
    public final void testNaturalNumberConstructorZero() {
        /*
         * Set up variables and call method under test
         *
         */
        NaturalNumber sourceRef = this.constructorRef(0);
        NaturalNumber sourcePreserve = this.constructorRef(0);
        NaturalNumber test = this.constructorTest(sourceRef);
        /*
         * Check postconditions
         */
        assertEquals(sourceRef, test);
        assertEquals(sourceRef, sourcePreserve);
    }

    /*
     * Tests NN constructor, routine case: nonzero number
     */
    @Test
    public final void testNaturalNumberConstructorNonZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber five = this.constructorRef(5);
        NaturalNumber q = this.constructorTest(five);
        NaturalNumber qExpected = this.constructorRef(5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Tests NaturalNumber constructor, challenge case: greater than
     * Integer.MAX_VALUE
     */
    @Test
    public final void testNaturalNumberConstructorIntMaxValue() {
        /*
         * Set up variables and call method under test
         *
         */
        NaturalNumber sourceRef = this
                .constructorRef(Integer.MAX_VALUE + "" + 5);
        NaturalNumber sourcePreserve = this
                .constructorRef(Integer.MAX_VALUE + "" + 5);
        NaturalNumber test = this.constructorTest(sourceRef);
        /*
         * Check postconditions
         */
        assertEquals(sourceRef, test);
        assertEquals(sourceRef, sourcePreserve);
    }

    /*
     * Tests NaturalNumber constructor, challenge case: greater than
     * Long.MAX_VALUE
     */
    @Test
    public final void testNaturalNumberConstructorLongMaxValue() {
        /*
         * Set up variables and call method under test
         *
         */
        NaturalNumber sourceRef = this.constructorRef(Long.MAX_VALUE + "" + 5);
        NaturalNumber sourcePreserve = this
                .constructorRef(Long.MAX_VALUE + "" + 5);
        NaturalNumber test = this.constructorTest(sourceRef);
        /*
         * Check postconditions
         */
        assertEquals(sourceRef, test);
        assertEquals(sourceRef, sourcePreserve);
    }

    /*
     * Tests NaturalNumber constructor, challenge case: non-zero number with
     * zeros as digits
     */
    @Test
    public final void testNaturalNumberConstructorZeroDigits() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber sourceRef = this.constructorRef(40050900);
        NaturalNumber sourcePreserve = this.constructorRef(40050900);
        NaturalNumber test = this.constructorTest(sourceRef);
        /*
         * Check postconditions
         *
         */
        assertEquals(sourceRef, test);
        assertEquals(sourceRef, sourcePreserve);
    }

    /*
     * Tests NaturalNumber constructor, routine case: single digit even number
     */
    @Test
    public final void testNaturalNumberConstructorEven() {
        /*
         * Set up variable values and call method under test
         */
        NaturalNumber sourceRef = this.constructorRef(4);
        NaturalNumber sourcePreserve = this.constructorRef(4);
        NaturalNumber test = this.constructorTest(sourceRef);
        /*
         * Check postconditions
         */
        assertEquals(sourceRef, test);
        assertEquals(sourceRef, sourcePreserve);
    }

    /*
     * Tests NaturalNumber constructor, routine case: single digit odd number
     */
    @Test
    public final void testNaturalNumberConstructorOdd() {
        /*
         * Set up variables and call method under test
         *
         */
        NaturalNumber sourceRef = this.constructorRef(5);
        NaturalNumber sourcePreserve = this.constructorRef(5);
        NaturalNumber test = this.constructorTest(sourceRef);
        /*
         * Check postconditions
         */
        assertEquals(sourceRef, test);
        assertEquals(sourceRef, sourcePreserve);
    }

    /*
     * Tests multiplyBy10, boundary case: this=0
     */
    @Test
    public final void testMultiplyBy10ThisZero() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber ref = this.constructorRef(0);
        /*
         * Call method under test
         *
         */
        test.multiplyBy10(5);
        ref.multiplyBy10(5);
        /*
         * Check postconditions
         */
        assertEquals(ref, test);
    }

    /*
     * Tests multiplyBy10, boundary case: largest possible value of k
     */
    @Test
    public final void testMultiplyBy109() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(2);
        NaturalNumber ref = this.constructorRef(2);
        /*
         * Call method under test
         */
        test.multiplyBy10(9);
        ref.multiplyBy10(9);
        /*
         * Check postconditions
         *
         */
        assertEquals(ref, test);
    }

    /*
     * Tests multiplyBy10, boundary case: smallest possible value of k
     */
    @Test
    public final void testMultiplyBy10KZero() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(2);
        NaturalNumber ref = this.constructorRef(2);
        /*
         * Call method under test
         *
         */
        test.multiplyBy10(0);
        ref.multiplyBy10(0);
        /*
         * Check postconditions
         */
        assertEquals(ref, test);
    }

    /*
     * Tests multiplyBy10, boundary case: smallest value of k and this
     */
    @Test
    public final void testMultiplyBy10KThisZero() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber ref = this.constructorRef(0);
        /*
         * Call method under test
         *
         */
        test.multiplyBy10(0);
        ref.multiplyBy10(0);
        /*
         * Check postconditions
         *
         */
        assertEquals(ref, test);
    }

    /*
     * Tests multiplyBy10, routine case: single digit number
     */
    @Test
    public final void testMultiplyBy10SingleDigit() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(5);
        NaturalNumber ref = this.constructorRef(5);
        /*
         * Call method under test
         *
         */
        test.multiplyBy10(3);
        ref.multiplyBy10(3);
        /*
         * Check postconditions
         *
         */
        assertEquals(ref, test);
    }

    /*
     * Tests multiplyBy10, routine case: multi digit number
     */
    @Test
    public final void testMultiplyBy10MultiDigit() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(589076);
        NaturalNumber ref = this.constructorRef(589076);
        /*
         * Call method under test
         *
         */
        test.multiplyBy10(3);
        ref.multiplyBy10(3);
        /*
         * Check postconditions
         *
         */
        assertEquals(ref, test);
    }

    /*
     * Tests multiplyBy10, challenge case: this> Integer.MAX_VALUE
     */
    @Test
    public final void testMultiplyBy10IntMaxValue() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE + "" + 5);
        NaturalNumber ref = this.constructorRef(Integer.MAX_VALUE + "" + 5);
        /*
         * Call method under test
         */
        test.multiplyBy10(3);
        ref.multiplyBy10(3);
        /*
         * Assert postconditions
         *
         */
        assertEquals(ref, test);
    }

    /*
     * Tests multiplyBy10, challenge case: this> Long.MAX_VALUE
     */
    @Test
    public final void testMultiplyBy10LongMaxValue() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(Long.MAX_VALUE + "" + 5);
        NaturalNumber ref = this.constructorRef(Long.MAX_VALUE + "" + 5);
        /*
         * Call method under test
         *
         */
        test.multiplyBy10(3);
        ref.multiplyBy10(3);
        /*
         * Assert postconditions
         */
        assertEquals(ref, test);
    }

    /*
     * Tests divideBy10, boundary case: this=0
     */
    @Test
    public final void testDivideBy10Zero() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber ref = this.constructorRef(0);
        /*
         * Call method under test
         *
         */
        int remainderTest = test.divideBy10();
        int remainderRef = ref.divideBy10();
        /*
         * Assert postconditions
         *
         */
        assertEquals(ref, test);
        assertEquals(remainderTest, remainderRef);
    }

    /*
     * Tests divideBy10, challenge case: this>Integer.MAX_VALUE
     */
    @Test
    public final void testDivideBy10IntMaxValue() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE + "" + 5);
        NaturalNumber ref = this.constructorRef(Integer.MAX_VALUE + "" + 5);
        /*
         * Call method under test
         *
         */
        int remainderTest = test.divideBy10();
        int remainderRef = ref.divideBy10();
        /*
         * Assert postconditions
         */
        assertEquals(ref, test);
        assertEquals(remainderTest, remainderRef);
    }

    /*
     * Tests divideBy10, challenge case: this>Long.MAX_VALUE
     */
    @Test
    public final void testDivideBy10LongMaxValue() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(Long.MAX_VALUE + "" + 5);
        NaturalNumber ref = this.constructorRef(Long.MAX_VALUE + "" + 5);
        /*
         * Call method under test
         *
         */
        int remainderTest = test.divideBy10();
        int remainderRef = ref.divideBy10();
        /*
         * Assert postconditions
         *
         */
        assertEquals(ref, test);
        assertEquals(remainderTest, remainderRef);
    }

    /*
     * Tests divideBy10, challenge case: non-zero number with zeros as digits
     */
    @Test
    public final void testDivideBy10DigitZeros() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(1005056710);
        NaturalNumber ref = this.constructorRef(1005056710);
        /*
         * Call method under test
         *
         */
        int remainderTest = test.divideBy10();
        int remainderRef = ref.divideBy10();
        /*
         * Assert postconditions
         *
         */
        assertEquals(ref, test);
        assertEquals(remainderTest, remainderRef);
    }

    /*
     * Tests divideBy10, routine case: single-digit number
     */
    @Test
    public final void testDivideBy10SingleDigit() {
        /*
         * Set up variables
         *
         */
        NaturalNumber test = this.constructorTest(7);
        NaturalNumber ref = this.constructorRef(7);
        /*
         * Call method under test
         */
        int remainderTest = test.divideBy10();
        int remainderRef = ref.divideBy10();
        /*
         * Check postconditions
         */
        assertEquals(ref, test);
        assertEquals(remainderTest, remainderRef);
    }

    /*
     * Tests isZero, boundary case: this=0
     */
    @Test
    public final void testIsZeroWithZero() {
        /*
         * Set up variables
         */
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber ref = this.constructorRef(0);
        assertEquals(ref, test);
        /*
         * Call method under test
         */
        boolean testIsZero = test.isZero();
        /*
         * Check postconditions
         */
        assertEquals(test, ref);
        assertEquals(testIsZero, true);
    }

    /*
     * Tests isZero, challenge case: non-zero number containing 0 as a digit
     */
    @Test
    public final void testIsZeroDigitZero() {
        /*
         * Set up variables
         */
        NaturalNumber test = this.constructorTest(10908990);
        NaturalNumber ref = this.constructorRef(10908990);
        assertEquals(ref, test);
        /*
         * Call method under test
         */
        boolean testIsZero = test.isZero();
        /*
         * Check postconditions
         */
        assertEquals(test, ref);
        assertEquals(testIsZero, false);
    }

    /*
     * Tests isZero, challenge case: this>Integer.MAX_VALUE
     */
    @Test
    public final void testIsZeroIntMaxValue() {
        /*
         * Set up variables
         */
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE + "" + 5);
        NaturalNumber ref = this.constructorRef(Integer.MAX_VALUE + "" + 5);
        assertEquals(ref, test);
        /*
         * Call method under test
         */
        boolean testIsZero = test.isZero();
        /*
         * Check postconditions
         */
        assertEquals(test, ref);
        assertEquals(testIsZero, false);
    }

    /*
     * Tests isZero, challenge case: this>Long.MAX_VALUE
     */
    @Test
    public final void testIsZeroLongMaxValue() {
        /*
         * Set up variables
         */
        NaturalNumber test = this.constructorTest(Long.MAX_VALUE + "" + 5);
        NaturalNumber ref = this.constructorRef(Long.MAX_VALUE + "" + 5);
        assertEquals(ref, test);
        /*
         * Call method under test
         */
        boolean testIsZero = test.isZero();
        /*
         * Check postconditions
         */
        assertEquals(test, ref);
        assertEquals(testIsZero, false);
    }

    /*
     * Tests isZero, routine case: this has single digit
     */
    @Test
    public final void testIsZeroSingleDigit() {
        /*
         * Set up variables
         */
        NaturalNumber test = this.constructorTest(3);
        NaturalNumber ref = this.constructorRef(3);
        assertEquals(ref, test);
        /*
         * Call method under test
         */
        boolean testIsZero = test.isZero();
        /*
         * Check postconditions
         */
        assertEquals(test, ref);
        assertEquals(testIsZero, false);
    }
}
