import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     * Tests default constructor: boundary case, only possible case.
     */
    @Test
    public final void defaultConstructorTest() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> test = this.constructorTest();
        Stack<String> ref = this.constructorRef();
        /*
         * Assert variable values meet expected values
         */
        assertEquals(ref, test);
    }

    /**
     * Tests push: boundary case, adding to empty Stack.
     */
    @Test
    public final void testPushEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.constructorTest();
        Stack<String> ref = this.constructorRef();
        /*
         * Call method under test
         */
        test.push("test1");
        ref.push("test1");
        /*
         * Assert variable values meet expected values
         */
        assertEquals(ref, test);
    }

    /**
     * Tests push: routine case, adding to nonempty Stack.
     */
    @Test
    public final void testPushNonEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("test2", "test4");
        Stack<String> ref = this.createFromArgsRef("test2", "test4");
        /*
         * Call method under test
         */
        test.push("test1");
        ref.push("test1");
        /*
         * Assert variable values meet expected values
         */
        assertEquals(ref, test);
    }

    /**
     * Tests pop: boundary case, popping from Stack of length one.
     */
    @Test
    public final void testPopToEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("test1");
        Stack<String> ref = this.createFromArgsRef("test1");
        /*
         * Call method under test
         */
        String testPopped = test.pop();
        String refPopped = ref.pop();
        /*
         * Assert variable values meet expected values
         */
        assertEquals(ref, test);
        assertEquals(refPopped, testPopped);
    }

    /**
     * Tests pop: routine case, popping from Stack of length more than one.
     */
    @Test
    public final void testPopToNonEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("test1", "test2", "test3");
        Stack<String> ref = this.createFromArgsRef("test1", "test2", "test3");
        /*
         * Call method under test
         */
        String testPopped = test.pop();
        String refPopped = ref.pop();
        /*
         * Assert variable values meet expected values
         */
        assertEquals(ref, test);
        assertEquals(refPopped, testPopped);
    }

    /**
     * Tests length: boundary case, Stack of length 0
     */
    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest();
        Stack<String> ref = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int testLength = test.length();
        int refLength = ref.length();
        /*
         * Assert variable values meet expected values
         */
        assertEquals(ref, test);
        assertEquals(refLength, testLength);
    }

    /**
     * Tests length: routine case, Stack of length >0
     */
    @Test
    public final void testLengthNonEmpty() {
        /*
         * Set up variables
         */
        Stack<String> test = this.createFromArgsTest("test1", "test2", "test3");
        Stack<String> ref = this.createFromArgsRef("test1", "test2", "test3");
        /*
         * Call method under test
         */
        int testLength = test.length();
        int refLength = ref.length();
        /*
         * Assert variable values meet expected values
         */
        assertEquals(ref, test);
        assertEquals(refLength, testLength);
    }

    // TODO - add test cases for constructor, push, pop, and length

}
