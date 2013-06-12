package bee;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Zoltan Kincses
 * 
 */
public class Feed {

	private final URL url;
	private List<FeedItem> feed = new ArrayList<FeedItem>();

	static final String PUB_DATE = "pubDate";
	static final String DESCRIPTION = "description";
	static final String LINK = "link";
	static final String TITLE = "title";
	static final String ITEM = "item";
	static final String CREATOR = "dc:creator";
	static final String CATEGORY = "category";

	public Feed(URL url) {
		this.url = url;
	}

	/**
	 * @param in
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * 
	 *             The function takes an InputStream as parameter, and returns
	 *             the converted rss feed as a <code>List<FeedItem></code> as
	 *             result.
	 */
	public void parse() throws ParserConfigurationException, SAXException,
			IOException {
		InputStream in = openStream(this.url);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();
		Document dom = builder.parse(in);
		Element root = dom.getDocumentElement();
		NodeList items = root.getElementsByTagName(ITEM);

		for (int i = 0; i < items.getLength(); i++) {
			feed.add(createItemFromNode(items.item(i)));
		}
	}

	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private InputStream openStream(URL url) throws IOException {
		URLConnection uc = url.openConnection();
		InputStream is = uc.getInputStream();
		return is;
	}

	/**
	 * @param s
	 * @return
	 * 
	 *         Parses the given parameter to date.
	 */
	private Date convertPubDate(String s) {
		DateFormat formatter = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		Date date = new Date();
		try {
			date = formatter.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @param node
	 * @return
	 * 
	 *         Creates a FeedItem from the given Node type parameter.
	 */
	private FeedItem createItemFromNode(Node node) {
		String description = "";
		String title = "";
		String link = "";
		String creator = "";
		List<String> category = new ArrayList<String>();
		Timestamp date = new Timestamp(0);

		NodeList itemChildTags = node.getChildNodes();

		for (int i = 0; i < itemChildTags.getLength(); i++) {
			String nodeName = itemChildTags.item(i).getNodeName();
			String textContent = itemChildTags.item(i).getTextContent();
			if (nodeName.equals(TITLE)) {
				title = textContent;
			} else if (nodeName.equals(DESCRIPTION)) {
				description = textContent;
			} else if (nodeName.equals(LINK)) {
				link = textContent;
			} else if (nodeName.equals(PUB_DATE)) {
				date = new Timestamp(convertPubDate(textContent).getTime());
			} else if (nodeName.equals(CREATOR)) {
				creator = textContent;
			} else if (nodeName.equals(CATEGORY)) {
				category.add(textContent);
			}
		}
		return new FeedItem(title, link, description, date, creator, category);
	}

	/**
	 * @return the feed
	 */
	protected List<FeedItem> getFeed() {
		return feed;
	}

	/**
	 * @return the url
	 */
	protected URL getUrl() {
		return url;
	}
}
