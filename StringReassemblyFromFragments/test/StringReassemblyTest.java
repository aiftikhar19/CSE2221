import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * tests of overlap
     */
    @Test
    public void testOverlapRacecar() {
        String str1 = "racec";
        String str2 = "cecar";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(3, overlap);
    }

    @Test
    public void testOverlapWashington() {
        String str1 = "Washingt";
        String str2 = "hington";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(5, overlap);
    }

    @Test
    public void testOverlapHey() {
        String str1 = "Hey";
        String str2 = "hi";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(0, overlap);
    }

    /*
     * tests of combination
     */
    @Test
    public void testCombinationRacecar() {
        String str1 = "racec";
        String str2 = "cecar";
        int overlap = 3;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("racecar", combine);
    }

    @Test
    public void testCombinationWashington() {
        String str1 = "Washin";
        String str2 = "ington";
        int overlap = 2;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Washington", combine);
    }

    /*
     * tests of addToSetAvoidingSubstrings
     */
    @Test
    public void testAddToSetAvoidingSubstrings1() {
        Set<String> strSet = new Set1L<>();
        strSet.add("hey");
        strSet.add("hello");
        strSet.add("come");
        String str = "welcome";
        Set<String> expect = new Set1L<>();
        expect.add("hey");
        expect.add("hello");
        expect.add("welcome");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> strSet = new Set1L<>();
        strSet.add("woman");
        strSet.add("candy");
        strSet.add("cars");
        String str = "car";
        Set<String> expect = new Set1L<>();
        expect.add("woman");
        expect.add("candy");
        expect.add("cars");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    /*
     * tests of printWithLineSeparators
     */
    @Test
    public void testPrintWithLineSeparators1() {
        SimpleWriter out = new SimpleWriter1L("testoutput.txt");
        SimpleReader in = new SimpleReader1L("testoutput.txt");
        String text = "Testing 1~2 3 4~firetruck";
        String expect = "Testing 1" + "\n" + "2 3 4" + "\n" + "firetruck";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3);
    }

    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L("testoutput.txt");
        SimpleReader in = new SimpleReader1L("testoutput.txt");
        String text = "Testing 1 2 3 4~firetruck";
        String expect = "Testing 1 2 3 4" + "\n" + "firetruck";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2);
    }

    @Test
    public void testPrintWithLineSeparators3() {
        SimpleWriter out = new SimpleWriter1L("testoutput.txt");
        SimpleReader in = new SimpleReader1L("testoutput.txt");
        String text = "Hello my~name is~Aisha";
        String expect = "Hello my" + "\n" + "name is" + "\n" + "Aisha";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3);
    }

    /*
     * tests of assemble
     */
    @Test
    public void testAssemble1() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Hey h");
        strSet.add("y how'");
        strSet.add("w's it g");
        strSet.add("it going?");
        Set<String> expect = new Set1L<>();
        expect.add("Hey how's it going?");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAssemble2() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Hey h");
        strSet.add("y how'");
        strSet.add("hello");
        strSet.add("w's it g");
        strSet.add("asdfds");
        strSet.add("it going?");
        Set<String> expect = new Set1L<>();
        expect.add("Hey how's it going?");
        expect.add("asdfds");
        expect.add("hello");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

}
