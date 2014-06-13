package ru.nilebox.lastfm.fetcher.config;

/**
 *
 * @author nile
 */
public class ConfigDefaults {
	public static final String DEFAULT_SEARCH_URL = "http://ws.audioscrobbler.com/2.0/?method=artist.search&limit=1";
	public static final String DEFAULT_DESCRIPTION_URL = "http://ws.audioscrobbler.com/2.0/?method=artist.info&lang=ru";
	public static final String DEFAULT_PARAM_KEY_NAME = "api_key";
	public static final String DEFAULT_PARAM_ID_NAME = "mbid";
	public static final String DEFAULT_PARAM_QUERY_NAME = "artist";
	public static final String DEFAULT_XPATH_ID = "/lfm/results/artistmatches/artist[1]/mbid";
	public static final String DEFAULT_XPATH_NAME = "/lfm/results/artistmatches/artist[1]/name";
	public static final String DEFAULT_XPATH_DESCRIPTION = "/lfm/artist/bio/summary";
	public static final String DEFAULT_XPATH_IMG_SMALL = "/lfm/results/artistmatches/artist[1]/image[@size=\"large\"]";
	public static final String DEFAULT_XPATH_IMG_MEDIUM = "/lfm/results/artistmatches/artist[1]/image[@size=\"extralarge\"]";
	public static final String DEFAULT_XPATH_IMG_LARGE = "/lfm/results/artistmatches/artist[1]/image[@size=\"mega\"]";
	
}
