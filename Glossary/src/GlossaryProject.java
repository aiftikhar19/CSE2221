import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue2;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack2;

/**
 * A program that takes a text file from the user and makes an html file of the
 * text file in the format of a glossary.
 *
 * @author Aisha Iftikhar
 *
 */
public final class GlossaryProject {

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private GlossaryProject() {
    }

    /**
     * Outputs the opening tags in the generated HTML file.
     *
     * @param out
     *            the output stream
     *
     * @updates {@code out.content}
     * @requires <pre>
     * {@code out.is_open and [inputFileName is not null]}
     * </pre>
     * @ensures <pre>
     * {@code out.content = #out.content * [the HTML opening tags]}
     * </pre>
     */
    public static void outputHTMLHeader(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * Output the index header HTML text.
         */
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Glossary</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Glossary</h2>");
        out.println("<hr>");
        out.println("<h3>Index</h3>");
        out.println("<ul>");
    }

    /**
     * Outputs the hyperlinked list of terms and closing tags in index.html.
     *
     * @param out
     *            the output stream
     * @param words
     *            map containing terms and definitions
     * @updates {@code out.content}
     * @requires <pre>
     * {@code out.is_open and [terms is NOT EMPTY!]}
     * </pre>
     * @ensures <pre>
     * {@code out.content = #out.content
     * [list of hyperlinked terms]
     * [the HTML closing tags]
     * </pre>
     */
    private static void outputHTMLList(SimpleWriter out,
            Map<String, String> words) {

        /*
         * Create a new sequence containing the words (not definitions) and
         * input them using for-each loop
         */
        Sequence<String> index = new Sequence1L<String>();
        for (Map.Pair<String, String> pairTerms : words) {
            index.add(index.length(), pairTerms.key());
        }

        // use while loop to create hyperlinked words in list on main index page
        while (index.length() > 0) {
            String term = index.remove(0);
            out.print("<li><a href=\"" + term + ".html\">");
            out.println(term + "</a></li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Creates .html files for each term in Map and links all related terms in
     * the definitions.
     *
     * @param fileOut
     *            String containing the path where the sites are created.
     * @param words
     *            map containing terms and their definitions
     * @requires <pre>
     * {@code [fileOut is not an empty string] and [terms is NOT EMPTY]}
     * </pre>
     * @ensures <pre>
     * {@code [Provided terms have their own sites in the given location, definitions
     * that contain terms inside are linked to term's .html file]}
     * </pre>
     */
    private static void outputHTMLWords(String fileOut,
            Map<String, String> words) {

        // Create a new sequence containing the words
        Sequence<String> termsSequence = new Sequence1L<String>();
        boolean flag = false;
        for (Map.Pair<String, String> pairTerms : words) {
            termsSequence.add(termsSequence.length(), pairTerms.key());
        }

        // while the sequence contains a word, create a new webpge for each term
        while (termsSequence.length() > 0) {
            String termName = termsSequence.remove(0);
            // make sure to create a new output stream each time
            SimpleWriter out = new SimpleWriter1L(
                    fileOut + "/" + termName + ".html");
            // output header
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + termName + "</title");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2><b><i><font color=\"red\">" + termName
                    + "</font></i></b></h2>");
            out.print("<blockquote>");

            // this loop checks if a given term is contained in a definition
            for (Map.Pair<String, String> element : words) {
                if (words.value(termName).contains(element.key()) && !flag) {
                    flag = true;
                    String definitionContainsTerm = words.value(termName);
                    int position = 0;
                    Set<Character> separators = new Set1L<Character>();
                    separators.add(' ');
                    separators.add('.');
                    separators.add(',');
                    separators.add('/');
                    separators.add(':');
                    separators.add(';');
                    separators.add('\'');
                    separators.add('?');
                    separators.add('!');
                    separators.add('\t');
                    /*
                     * this loop will print out a link for the term contained in
                     * the definition
                     */
                    while (position < definitionContainsTerm.length()) {
                        String word = nextWordOrSeparator(
                                definitionContainsTerm, position, separators);
                        if (!words.hasKey(word)) {
                            out.print(word);
                        } else {
                            out.print("<a href=\"" + word + ".html\">" + word
                                    + "</a>");
                        }
                        position += word.length();
                    }
                }
            }
            /*
             * after the terms contained in the definition have been
             * hyperlinked, the definition is printed out onto the webpage
             */
            if (!flag) {
                String definition = words.value(termName);
                out.print(definition);
            }
            flag = false;

            /*
             * here the html tag is closed after creating a link back to
             * original index.html webpage
             */
            out.println("</blockquote>");
            out.println("<hr />");
            out.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        char first = text.charAt(position);
        String nextWordOrSeparator = "";
        int i = position + 1;
        nextWordOrSeparator = nextWordOrSeparator + first;
        if (!separators.contains(first)) {
            while (i < text.length() && !separators.contains(first)) {
                first = text.charAt(i);
                if (!separators.contains(first)) {
                    String concat = "" + first;
                    nextWordOrSeparator = nextWordOrSeparator.concat(concat);

                }
                i++;
            }
        } else {
            while (i < text.length() && separators.contains(first)) {
                first = text.charAt(i);
                if (separators.contains(first)) {
                    String concat = "" + first;
                    nextWordOrSeparator = nextWordOrSeparator.concat(concat);
                }
                i++;
            }
        }
        return nextWordOrSeparator;

    }

    /**
     * Processes each term and it's definition into a complete map.
     *
     * @param in
     *            the file to be read
     * @return map containing terms and their definitions
     *
     * @requires <pre>
     * {@code in.is_open and [Input file is formatted correctly]}
     * </pre>
     * @ensures <pre>
     * {@code [Returned map contains terms and their definitions provided
     * by input file]}
     * </pre>
     */
    private static Map<String, String> createMap(SimpleReader in) {
        Map<String, String> termsAndDefinitions = new Map1L<String, String>();

        // create stacks for words and for definitions
        Stack<String> terms = new Stack2<String>();
        Stack<String> definitions = new Stack2<String>();
        Stack<String> multipleLineDefinition = new Stack2<String>();

        // create new strings and check next line
        String longDefinition;
        String inputLines = in.nextLine();

        // while the end of the input stream hasn't been reached
        while (!in.atEOS()) {
            // if you reach an empty line, input next line
            if (inputLines.isEmpty()) {
                if (!in.atEOS()) {
                    inputLines = in.nextLine();
                }
            }
            /*
             * if the line contains whitespace, it is the definition, otherwise
             * its the word
             */
            while (!inputLines.isEmpty()) {
                if (!inputLines.contains(" ")) {
                    terms.push(inputLines);
                    if (!in.atEOS()) {
                        inputLines = in.nextLine();
                    }
                } else if (inputLines.contains(" ")) {
                    while (!inputLines.isEmpty()) {
                        multipleLineDefinition.push(inputLines);
                        if (!in.atEOS()) {
                            inputLines = in.nextLine();
                        } else {
                            inputLines = "";
                        }
                    }
                    /*
                     * if the definition is on multiple lines, then remove
                     * latest line from definition and add to String
                     */
                    multipleLineDefinition.flip();
                    longDefinition = multipleLineDefinition.pop();
                    while (multipleLineDefinition.length() > 0) {
                        longDefinition = longDefinition.concat(" ");
                        longDefinition = longDefinition
                                .concat(multipleLineDefinition.pop());
                    }
                    /*
                     * clear to prevent aliasing and add the long definition to
                     * the stack containing all definitions
                     */
                    multipleLineDefinition.clear();
                    definitions.push(longDefinition);
                }
            }
        }
        // add all terms and definitions in order from stack to map
        while (terms.length() > 0) {
            termsAndDefinitions.add(terms.pop(), definitions.pop());
        }

        return termsAndDefinitions;

    }

    /**
     * Sorts the given map alphabetically.
     *
     * @param termsAndDefinitions
     *            map containing terms and their definitions
     *
     * @updates {@code termsAndDefinitions}
     * @requires <pre>
     * {@code [Map is NOT EMPTY!]}
     * </pre>
     * @ensures <pre>
     * {@code termsAndDefinitions.content = [#termsAndDefinitions.content sorted
     * alphabetically]}
     * </pre>
     */
    private static void sortMap(Map<String, String> termsAndDefinitions) {

        Comparator<String> order = new StringLT();
        Map<String, String> tempMap = termsAndDefinitions.newInstance();
        tempMap.transferFrom(termsAndDefinitions);

        Queue<String> q = new Queue2<String>();
        for (Map.Pair<String, String> pairTerm : tempMap) {
            q.enqueue(pairTerm.key());
        }

        while (q.length() > 0) {
            q.sort(order);
            String term = q.dequeue();
            String definition = tempMap.value(term);
            termsAndDefinitions.add(term, definition);
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

        // Get the location of input and open stream to it.
        out.print("Enter name of input file: ");
        SimpleReader index = new SimpleReader1L(in.nextLine());

        // Get the desired location for output of all .html files.
        out.print("Enter location of glossary: ");
        String output = in.nextLine();

        // Create the index.html and Open stream to location
        SimpleWriter fileOut = new SimpleWriter1L(output + "/index.html");

        // Create map containing terms and definitions read in from input file.
        Map<String, String> termsAndDefinitions = createMap(index);

        // Sort map alphabetically
        sortMap(termsAndDefinitions);

        // Create complete index.html and each term's .html file
        outputHTMLHeader(fileOut);
        outputHTMLList(fileOut, termsAndDefinitions);
        outputHTMLWords(output, termsAndDefinitions);

        in.close();
        out.close();
        fileOut.close();
    }
}
