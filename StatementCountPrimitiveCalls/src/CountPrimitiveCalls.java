import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                int i = 0;
                while (i < s.lengthOfBlock()) {
                    Statement removed = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(removed);
                    s.addToBlock(i, removed);
                    i++;
                }
                break;
            }
            case IF: {
                Statement block = s.newInstance();
                Condition condition = s.disassembleIf(block);
                count = countOfPrimitiveCalls(block);
                s.assembleIf(condition, block);
                break;
            }
            case IF_ELSE: {
                Statement blockOne = s.newInstance();
                Statement blockTwo = s.newInstance();
                Condition condition = s.disassembleIfElse(blockOne, blockTwo);
                count = countOfPrimitiveCalls(blockOne)
                        + countOfPrimitiveCalls(blockTwo);
                s.assembleIfElse(condition, blockOne, blockTwo);
                break;
            }
            case WHILE: {
                Statement block = s.newInstance();
                Condition condition = s.disassembleWhile(block);
                count = countOfPrimitiveCalls(block);
                s.assembleWhile(condition, block);
                break;
            }
            case CALL: {
                String call = s.disassembleCall();
                String primitive = "moveturnleftturnrightinfectskip";
                if (primitive.contains(call)) {
                    count = 1;
                }
                s.assembleCall(call);
                break;
            }
            default: {
                // this will never happen...
                break;
            }
        }
        return count;
    }

}
