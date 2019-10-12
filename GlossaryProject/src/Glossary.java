import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 * A program that takes a text file from the user and makes an html file of the
 * text file in the format of a glossary
 *
 * @author Aisha Iftikhar
 *
 */
public class Glossary {
    /**
     * Alphabetizes a queue
     *
     * @param s1
     *            first string
     * @param s2
     *            second string
     *
     */
    private static class alphabetize implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    /**
     * Reads the input file and puts the terms and definitions into the map and
     * returns a queue containing the terms
     *
     * @param wordDefMap
     *            map of strings where <term,term's definition>
     * @param in
     *            the text file from the user to be read
     * @return a queue containing all the terms
     * @requires Map is empty in is formatted as term then definition on
     *           following lines and a blank space in between the definition of
     *           a word and the next term
     * @ensures Map is modified to have a term as the key and its corresponding
     *          definition as its value
     */
    private static Queue<String> extractTermsAndDefinitions(
            Map<String, String> wordDefMap, SimpleReader in) {
        Queue<String> termList = new Queue1L<>();
        //reads input file and uses blank space to detect in line
        //term or definition
        while (!in.atEOS()) {
            String word = in.nextLine();
            String def = in.nextLine();
            String temp = " ";
            //accounting for definition being more than one line
            //and being at the end of the document
            while (!in.atEOS() && temp.length() > 0) {
                temp = in.nextLine();
                if (temp.length() > 0) {
                    def += temp;
                }
            }
            //adds terms and definition to map and queue
            wordDefMap.add(word, def);
            termList.enqueue(word);
        }
        return termList;
    }

    /**
     * Reads the map and produces links for each term. The links contain the
     * term on the top followed by its definition Link are in the same folder as
     * index
     *
     * @param wordDefMap
     *            map of strings where <term,term's definition>
     * @param termList
     *            a queue that contains all the terms
     * @requires all of termList are the keys of wordDefMap Neither wordDefMap
     *           or termList are empty
     * @ensures neither wordDefMap or termList will be modified
     */
    private static void createLinks(Map<String, String> wordDefMap,
            Queue<String> termList, String out) {
        Queue<String> temp = new Queue1L<>();
        //goes through every term's definitions
        while (termList.length() > 0) {
            String word = termList.dequeue();
            String def = wordDefMap.value(word);
            temp.enqueue(word);
            //creates link
            SimpleWriter link = new SimpleWriter1L(out + "/" + word + ".html");
            link.println("<html>");
            link.println("<head>");
            link.println("<title>" + word + "</title>");
            link.println("</head>");
            link.println("<body>");
            link.println("<h2><b><i><font color=\"red\">" + word
                    + "</font></i></b></h2>");
            link.println("<blockquote>" + def + "</blockquote>");
            link.println("<hr />");
            link.println("<p>Return to <a href=\"" + out
                    + "/index.html\">index</a>.</p>");
            link.println("</body>");
            link.println("</html>");
            link.close();
        }
        //in order to not modify termList

        termList.transferFrom(temp);
    }

    /**
     * Takes a string and looks for the first word starting at the position The
     * word is determined by if there is a separator (periods, spaces, commas,
     * etc). Once the method finds a separator, it determines the word is the
     * starting position until right before the separator The word is returned
     * If the position is at a separator, the separator alone is returned
     *
     * @param text
     *            String that is to have words examined
     * @param position
     *            Staring position to check for word
     * @param separators
     *            A set of designated separators
     * @return Word or separator if the position starts at one
     * @requires text is not empty separators is not empty position is less than
     *           the length of text
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";
        //-2 was picked because it had to be initialized and
        //there's no way a position could be -2
        int endingPosition = -2;
        String word = " ";
        //runs through string looking for first occurence of separator
        for (int n = position; n < text.length(); n++) {
            if (separators.contains(text.charAt(n)) && endingPosition == -2) {
                endingPosition = n;
            }
        }
        //if there is no separator, the word is the rest of the string
        if (endingPosition == -2) {
            word = text.substring(position);
            //if this happens, that means the position chosen was a separator
        } else if (endingPosition == position) {
            word = text.substring(position, endingPosition + 1);
            //this is the word
        } else {
            word = text.substring(position, endingPosition);
        }
        return word;
    }

    /**
     * Modifies the definition in the string (the value) so that if there are
     * any terms that are stored as the keys and also in termList within the
     * definition, they will be linked to corresponding definition page
     *
     * @param wordDefMap
     *            map of strings where <term,term's definition>
     * @param termList
     *            a queue that contains all the terms
     * @param out
     *            file where index and other files are being stored from user
     *            input
     * @requires wordDefMap and termList are not empty termList is the same as
     *           the keys in wordDefMap
     * @ensures wordDefMap and termList are not modified will link to files
     *          created in createLinks
     */
    private static void modifyDefinitions(Map<String, String> wordDefMap,
            Queue<String> termList, String out) {
        Queue<String> temp = new Queue1L<>();
        //creates set filled with separators
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('.');
        separators.add(',');
        separators.add('/');
        separators.add(':');
        separators.add(';');
        separators.add('\'');
        separators.add('?');
        separators.add('!');
        //runs through all terms
        int position = 0;
        while (termList.length() > 0) {
            String term = termList.dequeue();
            temp.enqueue(term);
            String definition = wordDefMap.value(term);
            String finalDefinition = " ";
            //the position can not be larger than the string
            while (position < definition.length()) {
                String word = nextWordOrSeparator(definition, position,
                        separators);
                //if the word is term, the word is linked to that page
                if (wordDefMap.hasKey(word)) {
                    finalDefinition += "<a href=\"" + out + "/" + word
                            + ".html\">" + word + "</a>";
                } else {
                    finalDefinition += word;
                }
                //moves position to after the word
                position = position + word.length();
            }
            //updates definition
            wordDefMap.replaceValue(term, finalDefinition.substring(1));
            //resets position
            position = 0;
        }
        //restores termList
        termList.transferFrom(temp);
    }

    /**
     * Given a file name from the user, output files are created in user given
     * folder. One of them is an index with each term from the input file. This
     * index links to their definitions, also given by the user.
     *
     * @requires The input file must be formatted this way: term definition(can
     *           be multiple lines)
     *
     *           term definition
     *
     *           and so on
     *
     * @param args
     *            Command-line arguements: not used
     */
    public static void main(String[] args) {
        //asks user for input and folder
        SimpleReader userIn = new SimpleReader1L();
        SimpleWriter userOut = new SimpleWriter1L();
        userOut.print("Enter input file: ");
        String input = userIn.nextLine();
        userOut.print("Enter folder where output files will be saved: ");
        String output = userIn.nextLine();
        //creates index, blank word definition map, and blank termList Queue
        SimpleReader in = new SimpleReader1L(input);
        SimpleWriter out = new SimpleWriter1L(output + "/index.html");
        Map<String, String> wordDefMap = new Map1L<>();
        Queue<String> termList = new Queue1L<>();
        //creates term list and adds terms and definitions to map
        termList.append(extractTermsAndDefinitions(wordDefMap, in));
        //alphabetizes terms
        Comparator<String> a = new alphabetize();
        termList.sort(a);
        //adds links to defintions
        modifyDefinitions(wordDefMap, termList, output);
        //creates all the terms' links
        createLinks(wordDefMap, termList, output);
        //creates index
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Glossary</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Glossary</h2>");
        out.println("<hr />");
        out.println("<h3>Index</h3>");
        out.println("<ul>");
        //creates list of terms
        while (termList.length() > 0) {
            String term = termList.dequeue();
            out.println("<li><a href=\"" + output + "/" + term + ".html\">"
                    + term + "</a></li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
        userIn.close();
        userOut.close();
        in.close();
        out.close();
    }
}
