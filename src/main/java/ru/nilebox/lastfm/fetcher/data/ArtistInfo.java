package ru.nilebox.lastfm.fetcher.data;

/**
 *
 * @author
 * nile
 */
public class ArtistInfo {
	private String lastFmId;
	private String name;
	private String smallImageUrl;
	private String mediumImageUrl;
	private String largeImageUrl;
	private String description;

	public String getLastFmId() {
		return lastFmId;
	}

	public void setLastFmId(String lastFmId) {
		this.lastFmId = lastFmId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	public String getMediumImageUrl() {
		return mediumImageUrl;
	}

	public void setMediumImageUrl(String mediumImageUrl) {
		this.mediumImageUrl = mediumImageUrl;
	}

	public String getLargeImageUrl() {
		return largeImageUrl;
	}

	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
