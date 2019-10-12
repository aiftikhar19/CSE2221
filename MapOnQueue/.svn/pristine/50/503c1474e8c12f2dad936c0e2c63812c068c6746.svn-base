import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size
    /*
     * Tests constructor; boundary case, only possible case
     */
    @Test
    public final void construtorTest() {
        Map<String, String> test = this.constructorTest();
        Map<String, String> ref = this.constructorRef();
        assertEquals(ref, test);
    }

    /*
     * Tests add; boundary case, adding to empty Map
     */
    @Test
    public final void addTestEmpty() {
        Map<String, String> test = this.constructorTest();
        Map<String, String> ref = this.constructorRef();
        test.add("test1", "test2");
        ref.add("test1", "test2");
        assertEquals(ref, test);
    }

    /*
     * Tests add; routine case, adding to nonempty Map
     */
    @Test
    public final void addTestNonEmpty() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2",
                "test3", "test4");
        Map<String, String> ref = this.createFromArgsRef("test1", "test2",
                "test3", "test4");
        test.add("test5", "test2");
        ref.add("test5", "test2");
        assertEquals(ref, test);
    }

    /*
     * Tests remove; boundary case, Map of length one
     */
    @Test
    public final void removeTestEmpty() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2");
        Map<String, String> ref = this.constructorRef();
        Map.Pair<String, String> removed = test.remove("test1");
        assertEquals(removed.key(), "test1");
        assertEquals(removed.value(), "test2");
        assertEquals(ref, test);
    }

    /*
     * Tests remove; routine case, Map of length more than one
     */
    @Test
    public final void removeTestNonEmpty() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2",
                "test3", "test4");
        Map<String, String> ref = this.createFromArgsRef("test3", "test4");
        Map.Pair<String, String> removed = test.remove("test1");
        assertEquals(removed.key(), "test1");
        assertEquals(removed.value(), "test2");
        assertEquals(ref, test);
    }

    /*
     * Tests removeAny; boundary case, Map of length one
     */
    public final void removeAnyTestEmpty() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2");
        Map<String, String> ref = this.constructorRef();
        Map.Pair<String, String> removed = test.removeAny();
        assertEquals(removed.key(), "test1");
        assertEquals(removed.value(), "test2");
        assertEquals(ref, test);
    }

    /*
     * Tests removeAny; routine case, Map of length more than one
     */
    @Test
    public final void removeAnyTestNonEmpty() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2",
                "test3", "test4", "test5", "test6");
        Map<String, String> ref = this.createFromArgsRef("test1", "test2",
                "test3", "test4", "test5", "test6");
        Map.Pair<String, String> removedTest = test.removeAny();
        Map.Pair<String, String> removedRef = ref.remove(removedTest.key());
        assertEquals(removedTest.key(), removedRef.key());
        assertEquals(removedTest.value(), removedRef.value());
        assertEquals(ref, test);
    }

    /*
     * Tests value; boundary case, Map of length one
     */
    @Test
    public final void valueOne() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2");
        Map<String, String> ref = this.createFromArgsRef("test1", "test2");
        String testValue = test.value("test1");
        assertEquals(testValue, "test2");
        assertEquals(ref, test);
    }

    /*
     * Tests value; routine case, Map of length more than one
     */
    @Test
    public final void valueMoreThanOne() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2",
                "test3", "test4", "test5", "tes6");
        Map<String, String> ref = this.createFromArgsRef("test1", "test2",
                "test3", "test4", "test5", "tes6");
        String testValue = test.value("test3");
        assertEquals(testValue, "test4");
        assertEquals(ref, test);
    }

    /*
     * Tests value; challenge case, two keys share same value
     */
    @Test
    public final void valueSharedBy2Keys() {
        Map<String, String> test = this.createFromArgsTest("test1",
                "testDuplicate", "test3", "testDuplicate", "test5", "tes6");
        Map<String, String> ref = this.createFromArgsRef("test1",
                "testDuplicate", "test3", "testDuplicate", "test5", "tes6");
        String testValue = test.value("test3");
        assertEquals(testValue, "testDuplicate");
        assertEquals(ref, test);
    }

    /*
     * Tests hasKey; boundary case,Map of length 0
     */
    @Test
    public final void hasKeyEmpty() {
        Map<String, String> test = this.constructorTest();
        Map<String, String> ref = this.constructorRef();
        boolean hasKeyTest = test.hasKey("test1");
        assertEquals(hasKeyTest, false);
        assertEquals(ref, test);
    }

    /*
     * Tests hasKey; routine case,Map of length 1 (true)
     */
    @Test
    public final void hasKeyLengthOneTrue() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2");
        Map<String, String> ref = this.createFromArgsRef("test1", "test2");
        boolean hasKeyTest = test.hasKey("test1");
        assertEquals(hasKeyTest, true);
        assertEquals(ref, test);
    }

    /*
     * Tests hasKey; routine case,Map of length 1 (false)
     */
    @Test
    public final void hasKeyLengthOneFalse() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2");
        Map<String, String> ref = this.createFromArgsRef("test1", "test2");
        boolean hasKeyTest = test.hasKey("test3");
        assertEquals(hasKeyTest, false);
        assertEquals(ref, test);
    }

    /*
     * Tests hasKey; routine case,Map of length >1 (false)
     */
    @Test
    public final void hasKeyLengthMultiFalse() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2",
                "test4", "test5");
        Map<String, String> ref = this.createFromArgsRef("test1", "test2",
                "test4", "test5");
        boolean hasKeyTest = test.hasKey("test3");
        assertEquals(hasKeyTest, false);
        assertEquals(ref, test);
    }

    /*
     * Tests hasKey; routine case,Map of length >1 (true)
     */
    @Test
    public final void hasKeyLengthMultiTrue() {
        Map<String, String> test = this.createFromArgsTest("test1", "test2",
                "test4", "test5");
        Map<String, String> ref = this.createFromArgsRef("test1", "test2",
                "test4", "test5");
        boolean hasKeyTest = test.hasKey("test1");
        assertEquals(hasKeyTest, true);
        assertEquals(ref, test);
    }

    /*
     * Tests size; boundary case,Map of length 0
     */
    @Test
    public final void sizeZero() {
        Map<String, String> test = this.constructorTest();
        Map<String, String> ref = this.constructorRef();
        int sizeTest = test.size();
        assertEquals(sizeTest, 0);
        assertEquals(ref, test);
    }

    /*
     * Tests size; routine case,Map of length even number
     */
    @Test
    public final void sizeEven() {
        Map<String, String> test = this.createFromArgsTest("1", "2", "3", "4");
        Map<String, String> ref = this.createFromArgsRef("1", "2", "3", "4");
        int sizeTest = test.size();
        assertEquals(sizeTest, 2);
        assertEquals(ref, test);
    }

    /*
     * Tests size; routine case,Map of length odd number
     */
    @Test
    public final void sizeOdd() {
        Map<String, String> test = this.createFromArgsTest("1", "2", "3", "4",
                "5", "6");
        Map<String, String> ref = this.createFromArgsRef("1", "2", "3", "4",
                "5", "6");
        int sizeTest = test.size();
        assertEquals(sizeTest, 3);
        assertEquals(ref, test);
    }

    /*
     * Tests size; routine case,Map of length one
     */
    @Test
    public final void sizeOne() {
        Map<String, String> test = this.createFromArgsTest("1", "2");
        Map<String, String> ref = this.createFromArgsRef("1", "2");
        int sizeTest = test.size();
        assertEquals(sizeTest, 1);
        assertEquals(ref, test);
    }

    @Test
    public final void testadd() {
        Map<String, String> s = this.createFromArgsTest("A", "B", "C", "D");
        Map<String, String> sExpected = this.createFromArgsRef("A", "B", "C",
                "D", "E", "F");
        s.add("E", "F");
        boolean result = false;
        for (Pair<String, String> x : sExpected) {
            if (s.hasKey(x.key()) && s.hasValue(x.value())
                    && s.key(x.value()).equals(x.key())) {
                result = true;
            }
        }
        assertTrue(result);
    }

    @Test
    public final void testremove() {
        Map<String, String> s = this.createFromArgsTest("A", "B", "C", "D");
        Map<String, String> sExpected = this.createFromArgsRef("A", "B", "C");
        s.remove("D");
        assertTrue(!s.hasKey("D") && s.equals(sExpected));
    }

    @Test
    public final void testremoveAny() {
        Map<String, String> s = this.createFromArgsTest("A", "B", "C", "D");
        int length = s.size();
        s.removeAny();
        int lengthnew = s.size();
        assertTrue(lengthnew == length - 1);
    }

    @Test
    public final void testvalue() {
        Map<String, String> s = this.createFromArgsTest("A", "B", "C", "D");
        String value1 = s.value("A");
        String value2 = s.value("C");
        assertTrue(value1.equals("B") && value2.equals("D"));
    }

    @Test
    public final void testhasKey() {
        Map<String, String> s = this.createFromArgsTest("A", "B", "C", "D");
        assertTrue(s.hasKey("A") && s.hasKey("C") && !s.hasKey("D"));
    }

    @Test
    public final void testsize() {
        Map<String, String> s = this.createFromArgsTest("A", "B", "C", "D");
        int length = s.size();
        int number = 2;
        assertEquals(length, number);
    }

}
