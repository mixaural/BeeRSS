package bee;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author Kincses Zoltan
 * 
 */
public class FeedItem implements Comparable<FeedItem> {
	protected String title;
	protected String link;
	protected String description;
	protected Timestamp pubDate;
	protected String creator;
	protected List<String> category;

	// TODO Implement new version, with public constructor.
	public FeedItem(String title, String link, String description,
			Timestamp pubDate, String creator, List<String> category) {
		this.title = title;
		this.description = description;
		this.link = link;
		this.pubDate = pubDate;
		this.creator = creator;
		this.category = category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TITLE:" + this.title + "\r\nLINK: " + this.link + "\r\nPUB: "
				+ this.pubDate + "\r\nDESCRIPTION: " + this.description
				+ "\r\nCREATOR: " + this.creator + "\r\nCATEGORY: "
				+ this.category.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FeedItem another) {
		if (this.pubDate.equals(another.pubDate)) {
			return 0;
		} else if (this.pubDate.compareTo(another.pubDate) == -1) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * @return
	 * 
	 *         Returns the PubDate field in human readable form.
	 */
	public String getDateInFormattedString() {
		return new SimpleDateFormat("yyyy.MM.dd. HH:mm", Locale.ENGLISH)
				.format(this.pubDate);
	}

	/**
	 * @return
	 * 
	 *         Returns the PubDate field in human readable form.
	 */
	public String getDateInFormattedString(String format) {
		return new SimpleDateFormat(format, Locale.ENGLISH)
				.format(this.pubDate);
	}

	/**
	 * @return the title
	 */
	protected String getTitle() {
		return title;
	}

	/**
	 * @return the link
	 */
	protected String getLink() {
		return link;
	}

	/**
	 * @return the description
	 */
	protected String getDescription() {
		return description;
	}

	/**
	 * @return the pubDate
	 */
	protected Timestamp getPubDate() {
		return pubDate;
	}

	/**
	 * @return the creator
	 */
	protected String getCreator() {
		return creator;
	}

	/**
	 * @return the category
	 */
	protected List<String> getCategory() {
		return category;
	}

}
