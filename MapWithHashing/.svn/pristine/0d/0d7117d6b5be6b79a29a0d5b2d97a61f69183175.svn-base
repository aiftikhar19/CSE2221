import components.map.Map;
import components.map.Map1L;

/**
 * Customized JUnit test fixture for {@code Map4} using non-default constructor
 * and hash table size 2401(routine case, multiple of 7).
 */
public class Map4Test2401 extends MapTest {

    /**
     * Size of hash table to be used in tests.
     */
    private static final int TEST_HASH_TABLE_SIZE = 2401;

    @Override
    protected final Map<String, String> constructorTest() {
        return new Map4<String, String>(TEST_HASH_TABLE_SIZE);
    }

    @Override
    protected final Map<String, String> constructorRef() {
        return new Map1L<String, String>();
    }

}
