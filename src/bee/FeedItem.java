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
	protected final String datePattern = "yyyy.MM.dd. HH:mm";

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
		if (this.pubDate == null) {
			return another.pubDate == null ? 0 : -1;
		} else if (another.pubDate == null) {
			return 1;
		} else if (this.pubDate.equals(another.pubDate)) {
			return 0;
		} else if (this.pubDate.compareTo(another.pubDate) == -1) {
			return -1;
		} else {
			return 1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((pubDate == null) ? 0 : pubDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FeedItem))
			return false;
		FeedItem other = (FeedItem) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (pubDate == null) {
			if (other.pubDate != null)
				return false;
		} else if (!pubDate.equals(other.pubDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * @return
	 * 
	 *         Returns the PubDate field in human readable form.
	 */
	public String getDateInFormattedString() {
		return new SimpleDateFormat(datePattern, Locale.ENGLISH)
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
