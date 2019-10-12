import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program inputs number from user and reports the square root of that number to
 * within a relative error of no more than 0.01% using Newton iteration
 *
 * @author Aisha Iftikhar
 *
 */
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01 %.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
        double r = x;
        /*
         * calculate square root estimate. check if input is zero
         */
        if (r == 0) {
            return 0;
        } else {
            double root = 0.5 * (x / r + r);
            /*
             * construct loop to check if estimate is within 0.01 % error
             */
            while ((((root * root) - r) / r) > (epsilon * epsilon)) {
                root = 0.5 * (r / root + root);
            }
            return root;
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

        double input = 0.0;
        double root = 0.0;
        String response;
        double epsilon = 0.0;

        /*
         * Ask the user whether they wish to calculate a square root and input
         * answer
         */

        out.println(
                "Do you want to calculate a square root? Enter 'y' for yes, otherwise select any key");
        response = in.nextLine();

        /*
         * Construct a loop to print out square root for each successive input
         */
        if (response.equals("y") || response.equals("Y")) {
            /*
             * Ask the user to input an error range only once
             */
            out.print("Enter an epsilon (error range) value: ");
            epsilon = in.nextDouble();
            do {
                /*
                 * Input value to square root function
                 */
                out.print("Enter a positive number: ");
                input = in.nextDouble();
                root = sqrt(input, epsilon);
                /*
                 * Output square root value
                 */
                out.println(
                        "The square root of " + input + " is " + root + ".");
                /*
                 * Ask user if they wish to continue
                 */
                out.println(
                        "Do you want to continue? Enter 'y' for yes, otherwise select any key");
                response = in.nextLine();
            } while (response.equals("y") || response.equals("Y"));
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
