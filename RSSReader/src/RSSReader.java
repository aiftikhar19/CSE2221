import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Aisha Iftikhar
 *
 */
public final class RSSReader {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSReader() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * output title by finding index location
         */
        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        int index = getChildElement(channel, "title");

        // Check to ensure the title isn't empty
        String title = "Empty Title";
        if (channel.child(index).numberOfChildren() == 0) {
            out.println(title);
        } else {
            title = channel.child(index).child(0).label();
            out.println(title);
        }
        out.println("</title>");
        out.println("</head>");

        /*
         * output link to title
         */
        out.println("<body>");
        index = getChildElement(channel, "link");
        String link = channel.child(index).child(0).label();
        out.print("<h1><a href=\"");
        out.print(link);
        out.print("\">");
        out.print(title);
        out.println("</a></h1>");

        /*
         * output description
         */
        out.println("<p>");
        index = getChildElement(channel, "description");

        // Check to ensure description isn't empty
        String description = "No description";
        if (channel.child(index).numberOfChildren() == 0) {
            out.println(description);
        } else {
            description = channel.child(index).child(0).label();
            out.println(description);
        }
        out.println("</p>");

        /*
         * create table and output header
         */
        out.println("<table border = \"1\">");
        out.println("<tr>");
        out.println("<th>Date</th>");
        out.println("<th>Source</th>");
        out.println("<th>News</th>");
        out.println("</tr>");

    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        // Output final file tags
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        // use loop to determine whether a child element is present
        int j = -1;
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            if (xml.child(i).isTag()) {
                if (xml.child(i).label().equals(tag)) {
                    j = i;
                }
            }
        }

        return j;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<tr>");
        // determine the index locations of various tags, if present
        int date = getChildElement(item, "pubDate");
        int source = getChildElement(item, "source");
        int title = getChildElement(item, "title");
        int link = getChildElement(item, "link");
        int description = getChildElement(item, "description");

        // if there is a "pubDate" tag, output, else no date available
        if (date != -1) {
            out.println("<td>" + item.child(date).child(0).label() + "</td>");
        } else {
            out.println("<td>No date available</td>");
        }

        // if source exists, output including link, else no source available
        if (source != -1) {
            out.println("<td>");
            out.print("<a href=\"");
            out.print(item.child(source).attributeValue("url"));
            out.println("\">" + item.child(source).child(0).label() + "</a>");
            out.println("</td>");
        } else {
            out.println("<td>No source available</td>");
        }

        // if link exists, output link
        out.println("<td>");
        if (link != -1) {
            out.print("<a href=\"");
            out.print(item.child(link).child(0).label());
            out.print("\">");
        }
        /*
         * if title tag exists and has a child, output title, if not check for
         * description tag. if description exists, check for child, else print
         * no description
         */
        if (title != -1) {
            if (item.child(title).child(0).label().equals("")) {
                out.print("No title available");
            } else {
                out.print(item.child(title).child(0).label());
            }
        } else if (description != -1) {
            if (item.child(description).child(0).label().equals("")) {
                out.print("No description available");
            } else {
                out.print(item.child(description).child(0).label());
            }
        }
        // close link tag
        if (link != -1) {
            out.println("</a>");
        }

        // close row tag
        out.println("</td>");
        out.println("</tr>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Input the source URL.
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();

        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);

        /*
         * Check to make sure that the input file is an RSS 2.0 file
         */
        if (xml.label().equals("rss") && xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0")) {
            /*
             * Ask the user to input a file name for the html file. Create an
             * output stream to the file
             */
            out.print(
                    "Enter the name of the output file with a .html extension: ");
            String filename = in.nextLine();
            SimpleWriter fileOut = new SimpleWriter1L(filename);

            /*
             * Call methods and output to file
             */
            XMLTree channel = xml.child(0);

            outputHeader(channel, fileOut);
            /*
             * use the processItem method to print out remaining rows of table
             */
            for (int i = 0; i < channel.numberOfChildren(); i++) {
                if (channel.child(i).isTag()) {
                    if (channel.child(i).label().equals("item")) {
                        XMLTree item = channel.child(i);
                        processItem(item, fileOut);
                    }
                }
            }
            outputFooter(fileOut);
            fileOut.close();
        } else {
            out.println("The provided URL is not an RSS 2.0 file.");
        }

        /*
         * Close input and output streams
         */

        in.close();
        out.close();

    }

}