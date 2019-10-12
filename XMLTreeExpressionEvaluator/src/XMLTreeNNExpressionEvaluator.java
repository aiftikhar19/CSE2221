import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code NN}.
 *
 * @author Aisha Iftikhar
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        NaturalNumber number1, number2;
        String errorSubtract = "ERROR: Cannot subtract by larger number.";
        String errorDivide = "ERROR: Cannot divide by zero.";

        // Check for the first number and assign it to number1
        if (!exp.child(0).hasAttribute("value")) {
            number1 = evaluate(exp.child(0));
        } else {
            number1 = new NaturalNumber2(
                    Integer.parseInt(exp.child(0).attributeValue("value")));
        }

        // Find the second number and assign it to number2
        if (!exp.child(1).hasAttribute("value")) {
            number2 = evaluate(exp.child(1));
        } else {
            number2 = new NaturalNumber2(
                    Integer.parseInt(exp.child(1).attributeValue("value")));
        }

        // Find the operator label and perform corresponding operation
        String operation = exp.label();
        if (operation.equals("plus")) {
            number1.add(number2);
        } else if (operation.equals("minus")) {
            // Check if this >= n and report
            if (number2.compareTo(number1) > 0) {
                Reporter.fatalErrorToConsole(errorSubtract);
            }
            number1.subtract(number2);
        } else if (operation.equals("times")) {
            number1.multiply(number2);
        } else if (operation.equals("divide")) {
            // Check if divide by zero and report
            if (number2.isZero()) {
                Reporter.fatalErrorToConsole(errorDivide);
            }
            number1.divide(number2);
        }

        NaturalNumber solution = new NaturalNumber2();
        solution.copyFrom(number1);

        return solution;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
