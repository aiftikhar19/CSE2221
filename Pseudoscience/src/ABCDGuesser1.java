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
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
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

        // User input constant and check if it's within the required range of values
        out.println(
                "Enter any four positive numbers not equal to 1 that have personal meaning to you: ");

        double w = in.nextDouble();
        while (w == 1 || w <= 0) {
            out.println("Please enter a positive number not equal to 1.");
            w = in.nextDouble();
        }
        double x = in.nextDouble();
        while (x == 1 || x <= 0) {
            out.println("Please enter a positive number not equal to 1.");
            x = in.nextDouble();
        }
        double y = in.nextDouble();
        while (y == 1 || y <= 0) {
            out.println("Please enter a positive number not equal to 1.");
            y = in.nextDouble();
        }
        double z = in.nextDouble();
        while (z == 1 || z <= 0) {
            out.println("Please enter a positive number not equal to 1.");
            z = in.nextDouble();
        }

        // initialize variables
        double newEstimate = 0;
        double a = 0.0, b = 0.0, c = 0.0, d = 0.0;
        int i = 0, j = 0, k = 0, n = 0;
        double error = 0;

        // use nested while loops to determine values for a, b, c, and d
        while (i < deJager.length) {
            j = 0;
            while (j < deJager.length) {
                k = 0;
                while (k < deJager.length) {
                    n = 0;
                    while (n < deJager.length) {
                        a = deJager[i];
                        b = deJager[j];
                        c = deJager[k];
                        d = deJager[n];

                        newEstimate = Math.pow(w, a) * Math.pow(x, b)
                                * Math.pow(y, c) * Math.pow(z, d);

                        // Print out values for a, b, c, and d and the approximation and relative error
                        if (mu > newEstimate) {
                            error = (((mu - newEstimate) / mu) * 100);
                            if (error < 0.1) {
                                out.println(
                                        "The values of a, b, c, and d are: ");
                                out.println("a: " + a + " b: " + b + " c: " + c
                                        + " d: " + d);
                                out.println(
                                        "The approximated value of the constant within "
                                                + error + "% error is "
                                                + newEstimate + ".");
                            }
                        } else {
                            error = (((newEstimate - mu) / mu) * 100);
                            if (error < 0.1) {
                                out.println(
                                        "The values of a, b, c, and d are: ");
                                out.println("a: " + a + " b: " + b + " c: " + c
                                        + " d: " + d);
                                out.println(
                                        "The approximated value of the constant within "
                                                + error + "% error is "
                                                + newEstimate + ".");
                            }
                        }
                        n++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
