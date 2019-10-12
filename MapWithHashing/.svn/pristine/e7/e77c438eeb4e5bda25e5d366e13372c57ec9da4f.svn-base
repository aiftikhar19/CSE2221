import components.map.Map;
import components.map.Map1L;

/**
 * Customized JUnit test fixture for {@code Map4} using non-default constructor
 * and hash table size 30. Routine case; size multiple of 3,2, and 5.
 */
public class Map4Test30 extends MapTest {

    /**
     * Size of hash table to be used in tests.
     */
    private static final int TEST_HASH_TABLE_SIZE = 30;

    @Override
    protected final Map<String, String> constructorTest() {
        return new Map4<String, String>(TEST_HASH_TABLE_SIZE);
    }

    @Override
    protected final Map<String, String> constructorRef() {
        return new Map1L<String, String>();
    }

}
