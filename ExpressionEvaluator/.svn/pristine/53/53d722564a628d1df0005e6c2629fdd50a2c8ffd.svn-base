import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code ExpressionEvaluator}'s {@code valueOfExpr}
 * static method.
 *
 * @author Put your name here
 *
 */
public final class ExpressionEvaluatorTest {

    @Test
    public void testExample() {
        StringBuilder exp = new StringBuilder(
                "281/7/2-1-5*(15-(14-1))+((1))+20=30!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(30, value);
        assertEquals("=30!", exp.toString());
    }

    /*
     * Boundary case: single digit expression
     */
    @Test
    public void testSingleDigit() {
        StringBuilder exp = new StringBuilder("2=2!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(2, value);
        assertEquals("=2!", exp.toString());
    }

    /*
     * Routine case: single digit in parenthesis
     */
    @Test
    public void testParenthesisSingleDigit() {
        StringBuilder exp = new StringBuilder("(2)=2!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(2, value);
        assertEquals("=2!", exp.toString());
    }

    /*
     * Routine case: parenthesis with multop
     */
    @Test
    public void testParenthesisMultop() {
        StringBuilder exp = new StringBuilder("(2*9)/3=6!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(6, value);
        assertEquals("=6!", exp.toString());
    }

    @Test
    public void testParenthesisMultop1() {
        StringBuilder exp = new StringBuilder("15*5-13+72*(7-2)=422!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(422, value);
        assertEquals("=422!", exp.toString());
    }

}
