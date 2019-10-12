import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Creates tag cloud HTML page in output file for the word counts from the input
 * file.
 *
 * @author Aisha Iftikhar
 * @author Cerys Hughes
 *
 */
public final class TagCloudGenerator {

    /**
     * Compare {@code Map.Pair<String, Integer>}s in alphabetical order
     * according to key value.
     */
    private static class SortAlphabet
            implements Serializable, Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {

            String s1 = o1.getKey();
            String s2 = o2.getKey();

            //Ensure case insensitivity
            int compare = s1.compareToIgnoreCase(s2);

            //Ensure that returns 0 if and only if s1.equals(s2)
            if (compare == 0) {
                compare = s1.compareTo(s2);
            }

            return compare;
        }
    }

    /**
     * Compare {@code Map.Pair<String, Integer>}s by decreasing order by value.
     */
    private static class SortNumerical
            implements Serializable, Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {

            Integer i1 = o1.getValue();
            Integer i2 = o2.getValue();
            int compare = i2.compareTo(i1);

            //Ensure consistency with equals
            if (compare == 0) {
                compare = o1.getKey().compareTo(o2.getKey());
            }

            return compare;
        }
    }

    /**
     * Creates a List of the entries in countWords sorted by their word counts.
     *
     * @clears countWords
     * @param countWords
     *            the Map of words and their counts to sort
     * @return a list of the Map.Entrys from countWords sorted in descending
     *         order by their word counts
     * @ensures countSort contains all of the Map.Entrys from #countWords and
     *          for each element in countSort, countsort(i)>=countsort(i+1)
     *
     */
    private static List<Map.Entry<String, Integer>> countSort(
            Map<String, Integer> countWords) {
        List<Map.Entry<String, Integer>> countSort;
        countSort = new ArrayList<Map.Entry<String, Integer>>();
        Set<Map.Entry<String, Integer>> entries = countWords.entrySet();
        Iterator<Map.Entry<String, Integer>> transfer = entries.iterator();

        //Adding/removing manually instead of with addAll to avoid aliasing
        while (transfer.hasNext()) {
            Map.Entry<String, Integer> current = transfer.next();
            transfer.remove();
            countSort.add(current);
        }
        Comparator<Map.Entry<String, Integer>> order = new SortNumerical();
        countSort.sort(order);
        return countSort;

    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
    }

    /**
     * Global String containing list of separators.
     */
    private static final String SEPARATORS = "'., ()-_?\"/!@#$%^&*\t1234567890:"
            + ";[]{}+=~`><";

    /**
     * Outputs the opening tags in the generated HTML file.
     *
     * @param out
     *            the output stream
     * @param inFile
     *            the name of the file to read from
     * @param numWords
     *            the number of words with the highest word counts to be
     *            displayed
     * @updates {@code out.content}
     * @requires <pre>
     * {@code out.is_open and [inFile is not null]}
     * </pre>
     * @ensures <pre>
     * {@code out.content = #out.content * [the HTML opening tags]}
     * </pre>
     */
    private static void outputHTMLHeader(PrintWriter out, String inFile,
            int numWords) {
        assert out != null : "Violation of: out is not null";
        assert inFile != null : "Violation of: inFile is not null";
        /*
         * Output the index header HTML text.
         */
        out.println("<html>");
        out.println("<head>");
        out.println(
                "<title>Top " + numWords + " words in " + inFile + "</title>");
        out.println(
                "<link href=\"doc/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Top " + numWords + " words in " + inFile + "</h2>");
        out.println("<hr>");
        out.println("<div class = \"cdiv\">");
        out.println("<p class = \"cbox\">");
    }

    /**
     * Reads file read by SimpleReader text, inputting each unique,
     * case-insensitive word as a key of wordCounts and the number of times it
     * appears as its value.
     *
     *
     *
     * @param wordCounts
     *            the Map to hold each word and its count
     * @param text
     *            the SimpleReader connected to the file whose words and counts
     *            are to be recorded
     * @param separators
     *            the Set of characters defined as separating words
     * @replaces wordCounts
     * @requires text.is_open
     * @ensures <pre>each key in wordCounts is a unique word as defined by separators
     *               from text.content and
     *               each unique word from text.content is a key in wordCounts and
     *               each key's value is the # of times it appears in in.content and
     *               text.is_open
     *          </pre>
     * @throws IOException
     *             if an I/O exception occurs
     */
    private static void readFile(Map<String, Integer> wordCounts,
            Set<Character> separators, BufferedReader text) throws IOException {
        wordCounts.clear();
        //Go through each line of text in file
        String currentLine = text.readLine();
        while (currentLine != null) {
            currentLine = currentLine.toLowerCase();
            int linePosition = 0;
            //Get each word/separator from currentLine
            while (linePosition < currentLine.length()) {
                String word = nextWordOrSeparator(currentLine, linePosition,
                        separators);
                //If the substring is a word(and not a separator), update wordsToCounts
                if (!separators.contains(word.charAt(0))) {
                    //If word hasn't appeared before, add it to wordsToCounts
                    if (!wordCounts.containsKey(word)) {
                        wordCounts.put(word, 1);
                    } else {
                        //Otherwise, increment its count
                        int newCount = wordCounts.get(word) + 1;
                        wordCounts.remove(word);
                        wordCounts.put(word, newCount);
                    }
                }
                //Update position in currentLine
                linePosition += word.length();

            }
            currentLine = text.readLine();
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
     * @requires <pre>
     * {@code 0 <= position < |text|}
     * </pre>
     * @ensures <pre>
     * {@code nextWordOrSeparator =
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
     *      is not subset of separators)}
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        //Check if char at position is a separator or not
        boolean isSeparator = separators.contains(text.charAt(position));

        //Grow string until hit a non-word/non-separator
        int nextPosition = position + 1;
        while (nextPosition < text.length() && (separators
                .contains(text.charAt(nextPosition)) == isSeparator)) {

            nextPosition++;

        }
        return text.substring(position, nextPosition);
    }

    /**
     * Gets the appropriate font size for a word belonging in a tag cloud whose
     * range of counts is [scaleMax, scaleMin].
     *
     * @param scaleMax
     *            the maximum count in the tag cloud words
     * @param scaleMin
     *            the minimum count in the tag cloud words
     * @param count
     *            the count of the word whose font size is to be calculated
     * @requires scaleMax =< count =< scaleMin and scaleMax>0 and scaleMin>0
     * @ensures getFontSize is a valid CSS/HTML font size that varies according
     *          to count relative to the scale [scaleMin, scaleMAx]
     * @return the appropriate font size for the word that appears count number
     *         of times for a tag cloud of the given range
     */

    private static String getFontSize(int scaleMax, int scaleMin, int count) {
        final int maxFontSize = 48;
        final int minFontSize = 11;

        //Get in correct interval according to indexing formula
        int fontNumber = maxFontSize - minFontSize;
        if (scaleMax > scaleMin) {
            fontNumber *= (count - scaleMin);
            fontNumber /= (scaleMax - scaleMin);
            fontNumber += minFontSize;
        } else {
            fontNumber = maxFontSize;
        }

        return "f" + fontNumber;

    }

    /**
     * Outputs the HTML for a total of numWords words from wordCounts with the
     * highest counts in alphabetical order for a CSS tag cloud.
     *
     * @param output
     *            the file to print the top words to
     * @param numWords
     *            the number of top words to print
     * @param wordCounts
     *            the map of words and their counts
     * @clears wordCounts
     * @ensures output.content = #output.content * numWords many top words from
     *          wordCounts and output.is_open
     */
    private static void outputTopWords(PrintWriter output, int numWords,
            Map<String, Integer> wordCounts) {

        /*
         * Sort words by counts and only keep top wordcounts
         */
        List<Map.Entry<String, Integer>> orderedCounts = countSort(wordCounts);
        List<Map.Entry<String, Integer>> topWords;
        topWords = new ArrayList<Map.Entry<String, Integer>>();
        while (orderedCounts.size() > 0 && topWords.size() < numWords) {
            Map.Entry<String, Integer> topWord = orderedCounts.get(0);
            orderedCounts.remove(0);
            topWords.add(topWord);

        }

        /*
         * Get min and max topWord counts
         */
        int minCount = 1;
        int maxCount = 1;
        if (topWords.size() > 0) {
            minCount = topWords.get(topWords.size() - 1).getValue();
            maxCount = topWords.get(0).getValue();
        }

        /*
         * Sort top words alphabetically
         */
        Comparator<Map.Entry<String, Integer>> alphOrder = new SortAlphabet();
        topWords.sort(alphOrder);

        /*
         * Output HTML for the top words
         */
        for (Map.Entry<String, Integer> wordCount : topWords) {
            String fontSize = getFontSize(maxCount, minCount,
                    wordCount.getValue());
            String tag = "<span style=\"cursor:default\" class=\"" + fontSize
                    + "\" title=\"count: " + wordCount.getValue() + "\">"
                    + wordCount.getKey() + "</span>";
            output.println(tag);
        }

    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @return set of characters equal to entries(str)
     * @ensures generateElements = entries(str)
     **/
    private static Set<Character> generateElements(String str) {
        Set<Character> elements = new HashSet<Character>();
        for (int i = 0; i < str.length(); i++) {
            if (!elements.contains(str.charAt(i))) {
                elements.add(str.charAt(i));
            }
        }
        return elements;

    }

    /**
     * Outputs HTML tags for top numWords words from the file represented by
     * input to output, followed by closing HTML tags.
     *
     * @param output
     *            the file to print to
     * @param input
     *            the file to read the highest count words from
     * @param numWords
     *            the number of highest count words to process
     * @requires input is open output.is_open and numWords>0
     * @ensures input.content = #output.content*HTML tags for top words in
     *          input*closing HTML tags input.is_open output.is_open
     *
     */
    private static void createTagCloud(PrintWriter output, BufferedReader input,
            int numWords) {

        //Get word counts and output
        Map<String, Integer> wordCounts = new HashMap<String, Integer>();
        try {
            readFile(wordCounts, generateElements(SEPARATORS), input);
        } catch (IOException e) {
            System.out.println("Problem reading file and counting words");
        }
        outputTopWords(output, numWords, wordCounts);

        // output footer
        output.println("</p>");
        output.println("</div>");
        output.println("</body>");
        output.println("</html>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));

        // Get the input file's name and check for errors
        System.out.println("Enter the name of the input text file: ");
        String file = "";

        BufferedReader input = null;
        while (input == null) {
            try {
                file = in.readLine();
                input = new BufferedReader(new FileReader(file));
            } catch (IOException e) {
                System.out.println("Try again. File invalid because of " + e);
            }
        }
        String inFile = file;

        // Get the output file's name and check for errors
        System.out.print("Enter the name of the output file: ");
        String outFile = "";
        PrintWriter out = null;
        while (out == null) {
            try {
                outFile = in.readLine();
                out = new PrintWriter(
                        new BufferedWriter(new FileWriter(outFile)));
            } catch (IOException e) {
                System.out.println("Try again. File invalid because of " + e);

            }
        }

        // Get the number of words to output and check for errors
        System.out.print("Enter number of words to be included in tag cloud: ");
        int numWords = 0;
        try {
            numWords = Integer.parseInt(in.readLine());
        } catch (NumberFormatException e) {
            System.err.println("Error: number is in incorrect format");
        } catch (IOException e) {
            System.err.println("Error: cannot read input");
        }
        if (numWords < 0) {
            numWords = 0;
        }

        //Output the header tags
        outputHTMLHeader(out, inFile, numWords);

        //Output tags for top words and closing tags
        createTagCloud(out, input, numWords);

        //Close streams
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
        }

    }

}
