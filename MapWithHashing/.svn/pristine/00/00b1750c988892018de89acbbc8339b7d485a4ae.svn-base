import components.map.Map;
import components.map.Map1L;

/**
 * Customized JUnit test fixture for {@code Map4} using non-default constructor
 * and hash table size 1.(Trivial because no advantage to having hash table of
 * size 1, but testing because boundary case: @requires hashTableSize > 0 from
 * constructor contact).
 */
public class Map4Test1 extends MapTest {

    /**
     * Size of hash table to be used in tests.
     */
    private static final int TEST_HASH_TABLE_SIZE = 1;

    @Override
    protected final Map<String, String> constructorTest() {
        return new Map4<String, String>(TEST_HASH_TABLE_SIZE);
    }

    @Override
    protected final Map<String, String> constructorRef() {
        return new Map1L<String, String>();
    }

}
