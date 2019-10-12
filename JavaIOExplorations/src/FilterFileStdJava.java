import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import components.set.Set;
import components.set.Set1L;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class FilterFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private FilterFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {

        int i = 0;
        while (args.length > i) {
            BufferedReader in;
            try {
                in = new BufferedReader(new FileReader(args[i]));
            } catch (IOException e) {
                System.err.println("Error opening file");
                return;
            }

            i++;
            PrintWriter out;
            try {
                out = new PrintWriter(
                        new BufferedWriter(new FileWriter(args[i])));
            } catch (IOException e) {
                System.err.print("Error opening file");
                return;
            }

            i++;

            BufferedReader comp;
            try {
                comp = new BufferedReader(new FileReader(args[i]));
            } catch (IOException e) {
                System.err.println("Error opening file");
                return;
            }

            try {
                Set<String> strs = new Set1L<>();
                while (comp.ready()) {
                    strs.add(comp.readLine());
                }
                while (in.ready()) {
                    String line = in.readLine();

                    for (String s : strs) {
                        if (line.contains(s)) {
                            out.println(line);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading file");
            }

            try {
                in.close();
                out.close();
                comp.close();
            } catch (IOException e) {
                System.err.println("Error closing file");
            }
            out.close();

            i++;
        }

    }

}
