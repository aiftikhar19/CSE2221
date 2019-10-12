import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Aisha Iftikhar
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("0", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber m = new NaturalNumber2(21);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("3", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_10_20() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber m = new NaturalNumber2(20);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("10", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_52_13() {
        NaturalNumber n = new NaturalNumber2(52);
        NaturalNumber m = new NaturalNumber2(13);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("13", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_100_100() {
        NaturalNumber n = new NaturalNumber2(100);
        NaturalNumber m = new NaturalNumber2(100);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("100", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_555_5() {
        NaturalNumber n = new NaturalNumber2(555);
        NaturalNumber m = new NaturalNumber2(5);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("5", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_128_1024() {
        NaturalNumber n = new NaturalNumber2(128);
        NaturalNumber m = new NaturalNumber2(1024);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("128", n.toString());
        assertEquals("0", m.toString());
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("0", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("1", n.toString());
        assertTrue(!result);
    }

    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("2", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_17() {
        NaturalNumber n = new NaturalNumber2(17);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("17", n.toString());
        assertTrue(!result);
    }

    @Test
    public void testIsEven_26224524() {
        NaturalNumber n = new NaturalNumber2(26224524);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("26224524", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_big() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE - 1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("2147483646", n.toString());
        assertTrue(result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("0", p.toString());
        assertEquals("2", m.toString());
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("18", p.toString());
        assertEquals("19", m.toString());
    }

    @Test
    public void testPowerMod_7_7_7() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber p = new NaturalNumber2(7);
        NaturalNumber m = new NaturalNumber2(7);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("0", n.toString());
        assertEquals("7", p.toString());
        assertEquals("7", m.toString());
    }

    @Test
    public void testPowerMod_big() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(Integer.MAX_VALUE);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("0", n.toString());
        assertEquals("1", p.toString());
        assertEquals("2147483647", m.toString());
    }

    @Test
    public void testPowerMod_115_0_8() {
        NaturalNumber n = new NaturalNumber2(115);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(8);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("0", p.toString());
        assertEquals("8", m.toString());
    }

    /*
     * Tests of isWitnessToCompositeness
     */
    @Test
    public void compositeWitness_4_2() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber w = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    @Test
    public void compositeWitness_7_2() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber w = new NaturalNumber2(2);
        assertTrue(!CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    @Test
    public void Compositeness_100_30() {
        NaturalNumber n = new NaturalNumber2(100);
        NaturalNumber w = new NaturalNumber2(30);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    @Test
    public void Compositeness_243_31() {
        NaturalNumber n = new NaturalNumber2(243);
        NaturalNumber w = new NaturalNumber2(31);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    @Test
    public void Compositeness_134535_341() {
        NaturalNumber n = new NaturalNumber2(134535);
        NaturalNumber w = new NaturalNumber2(341);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    /*
     * Tests of isPrime1
     */

    @Test
    public void isPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void isPrime1_3() {
        NaturalNumber n = new NaturalNumber2(3);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void isPrime1_15() {
        NaturalNumber n = new NaturalNumber2(15);
        assertTrue(!CryptoUtilities.isPrime2(n));
    }

    @Test
    public void isPrime1_99() {
        NaturalNumber n = new NaturalNumber2(99);
        assertTrue(!CryptoUtilities.isPrime2(n));
    }

    @Test
    public void isPrime1_big() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    /*
     * Tests of isPrime2
     */
    @Test
    public void isPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void isPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    @Test
    public void isPrime2_15() {
        NaturalNumber n = new NaturalNumber2(15);
        assertTrue(!CryptoUtilities.isPrime2(n));
    }

    @Test
    public void isPrime2_99() {
        NaturalNumber n = new NaturalNumber2(99);
        assertTrue(!CryptoUtilities.isPrime2(n));
    }

    @Test
    public void isPrime2_big() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    /*
     * Tests of generateNextLikelyToPrime
     */

    @Test
    public void testGenerateNextLikelyToPrime_4() {
        NaturalNumber n = new NaturalNumber2(4);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("5", n.toString());

    }

    @Test
    public void testGenerateNextLikelyToPrime_8() {
        NaturalNumber n = new NaturalNumber2(8);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("11", n.toString());

    }

    @Test
    public void testGenerateNextLikelyToPrime_55() {
        NaturalNumber n = new NaturalNumber2(55);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("59", n.toString());
    }

    @Test
    public void testGenerateNextLikelyToPrime_32425345() {
        NaturalNumber n = new NaturalNumber2(32425345);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("32425373", n.toString());
    }

    @Test
    public void testGenerateNextLikelyToPrime_big() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE - 1);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("2147483647", n.toString());
    }

}
