import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Ask the user what constant should be approximated and then ask in turn for
 * each of the four personal numbers w, x, y and z. The program should then
 * calculate and report the values of the exponents a, b, c, and d that bring
 * the de Jager formula as close as possible to the constant, as well as the
 * value of the formula and the relative error of the approximation.
 *
 * @author Aisha Iftikhar
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * input method checks whether the user input is a positive number not equal
     * to 1.
     *
     * @param input
     *            the parameter to check
     *
     * @param in
     *            SimpleReader input variable
     * @param out
     *            SimpleWriter output variable
     *
     */
    public static void check(double input, SimpleReader in, SimpleWriter out) {
        while (input == 1 || input <= 0) {
            out.println("Please enter a positive number not equal to 1.");
            input = in.nextDouble();
        }
    }

    /**
     *
     * @param mu
     *            user input constant
     * @param estimate
     *            estimated value for constant using de Jager formula
     * @param out
     *            SimpleWriter output variable
     * @param a
     *            first exponent
     * @param b
     *            second exponent
     * @param c
     *            third exponent
     * @param d
     *            fourth exponent
     */
    public static void calculateError(double mu, double estimate,
            SimpleWriter out, double a, double b, double c, double d) {
        double error = 0;
        // calculate the error
        // if within specified error range, print out the values for a, b, c, and d and the approximation as well as the relative error
        if (mu > estimate) {
            error = (((mu - estimate) / mu) * 100);
            if (error < 0.1) {
                out.println("The values of a, b, c, and d are: ");
                out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d);
                out.println("The approximated value of the constant within "
                        + error + "% error is " + estimate + ".");
            }
        } else {
            error = (((estimate - mu) / mu) * 100);
            if (error < 0.1) {
                out.println("The values of a, b, c, and d are: ");
                out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d);
                out.println("The approximated value of the constant within "
                        + error + "% error is " + estimate + ".");
            }
        }
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

        // Create array to store de Jager values
        double[] deJager = { -5, -4, -3, -2, -1, (double) -1 / 2,
                (double) -1 / 3, (double) -1 / 4, 0, (double) 1 / 4,
                (double) 1 / 3, (double) 1 / 2, 1, 2, 3, 4, 5 };

        // User input constant to be approximated
        out.println("Enter a constant number to be approximated: ");
        double mu = in.nextDouble();
        check(mu, in, out);

        // User input constant and check if it's within the required range of values
        out.println(
                "Enter any four positive numbers not equal to 1 that have personal meaning to you: ");

        double w = in.nextDouble();
        check(w, in, out);

        double x = in.nextDouble();
        check(x, in, out);

        double y = in.nextDouble();
        check(y, in, out);

        double z = in.nextDouble();
        check(z, in, out);

        double newEstimate = 0;
        double a = 0.0, b = 0.0, c = 0.0, d = 0.0;

        // Use a for loop to determine values for a, b, c, and d that allow for the smallest margin of error
        for (int i = 0; i < deJager.length; i++) {
            for (int j = 0; j < deJager.length; j++) {
                for (int k = 0; k < deJager.length; k++) {
                    for (int n = 0; n < deJager.length; n++) {
                        a = deJager[i];
                        b = deJager[j];
                        c = deJager[k];
                        d = deJager[n];

                        newEstimate = Math.pow(w, a) * Math.pow(x, b)
                                * Math.pow(y, c) * Math.pow(z, d);

                        // Use the method to calculate and determine if error is less than 1%
                        calculateError(mu, newEstimate, out, a, b, c, d);
                    }
                }
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
