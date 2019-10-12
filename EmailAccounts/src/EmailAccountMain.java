import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
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

        Set<EmailAccount> names = new Set1L<>();
        out.print("Enter full name: ");
        String name = in.nextLine();
        while (!name.equals("")) {
            String firstName = "", lastName = "";
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) == ' ') {
                    firstName = name.substring(0, i);
                    lastName = name.substring(i + 1, name.length());
                }
            }
            EmailAccount myAccount = new EmailAccount1(firstName, lastName);
            names.add(myAccount);
            for (EmailAccount account : names) {
                out.println(account.name());
                out.println(account.emailAddress());
                out.println(account);
            }
            out.println("Enter full name: ");
            name = in.nextLine();
        }

        in.close();
        out.close();
    }

}
